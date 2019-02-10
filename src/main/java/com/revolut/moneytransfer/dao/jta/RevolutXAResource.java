package com.revolut.moneytransfer.dao.jta;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.slf4j.Logger;

import com.arjuna.ats.arjuna.common.Uid;

/**
 * Custom implementation of the {@code XAResource} interface
 * @author <a href="mailto:a.nejeoui@gmail.com">Abderrazzak Nejeoui</a> This
 *         implementation was designed based on JBoss Quickstart Guide, credits
 *         to <a href="mailto:gytis@redhat.com">Gytis Trikleris</a> and
 *         <a href="mailto:zfeng@redhat.com">Amos Feng</a>
 *  @see XAResource
 */
@ApplicationScoped
public class RevolutXAResource implements XAResource {

	/**
	 * Self4j Logger
	 */
	@Inject
	private transient Logger logger;
	
	public static final String XA_Folder = "target/xaTransactionFolder/RevolutXAResource/";

	private final boolean initJVMFailure;

	private Xid xaTransactionID;

	private File xidFile;

	public RevolutXAResource() {
		this(false);
	}

	public RevolutXAResource(boolean initJVMFailure) {
		this.initJVMFailure = initJVMFailure;
	}

	/**
	 * Constructor used by recovery manager to recreate XAResource
	 *
	 * @param file File where Xid of the XAResource is stored
	 */
	public RevolutXAResource(File file) throws IOException {
		this.initJVMFailure = false;
		this.xidFile = file;
		this.xaTransactionID = getXidFromFile(file);
	}

	public int prepare(final Xid xid) throws XAException {
		logger.info("Preparing " + RevolutXAResource.class.getSimpleName());

		this.xidFile = writeXaTransactionIDToFile(xid, XA_Folder);

		return XA_OK;
	}

	public void commit(final Xid xid, final boolean arg1) throws XAException {
		logger.info("Committing " + RevolutXAResource.class.getSimpleName());

		if (initJVMFailure) {
			logger.info("Crashing the system");
			Runtime.getRuntime().halt(1);
		}

		deletexidFile(xidFile);
		this.xidFile = null;
		this.xaTransactionID = null;
	}

	public void rollback(final Xid xid) throws XAException {
		logger.info("Rolling back " + RevolutXAResource.class.getSimpleName());

		deletexidFile(xidFile);
		this.xidFile = null;
		this.xaTransactionID = null;
	}

	public boolean isSameRM(XAResource xaResource) throws XAException {
		if (!(xaResource instanceof RevolutXAResource)) {
			return false;
		}

		RevolutXAResource other = (RevolutXAResource) xaResource;

		return xaTransactionID != null && other.xaTransactionID != null
				&& xaTransactionID.getFormatId() == other.xaTransactionID.getFormatId()
				&& Arrays.equals(xaTransactionID.getGlobalTransactionId(),
						other.xaTransactionID.getGlobalTransactionId())
				&& Arrays.equals(xaTransactionID.getBranchQualifier(), other.xaTransactionID.getBranchQualifier());
	}

	public Xid[] recover(int flag) throws XAException {
		return new Xid[] { xaTransactionID };
	}

	public void start(Xid xid, int flags) throws XAException {

	}

	public void end(Xid xid, int flags) throws XAException {

	}

	public void forget(Xid xid) throws XAException {

	}

	public int getTransactionTimeout() throws XAException {
		return 0;
	}

	public boolean setTransactionTimeout(final int seconds) throws XAException {
		return true;
	}

	private Xid getXidFromFile(File file) throws IOException {
		try (DataInputStream inputStream = new DataInputStream(new FileInputStream(file))) {
			int formatIdentifier = inputStream.readInt();
			int globalTransactionIdLength = inputStream.readInt();
			byte[] globalTransactionId = new byte[globalTransactionIdLength];
			inputStream.read(globalTransactionId, 0, globalTransactionIdLength);
			int branchQualifierLength = inputStream.readInt();
			byte[] branchQualifier = new byte[branchQualifierLength];
			inputStream.read(branchQualifier, 0, branchQualifierLength);

			return new XaTransactionIdImpl(formatIdentifier, globalTransactionId, branchQualifier);
		}
	}

	private File writeXaTransactionIDToFile(Xid aTransactionID, String folder) throws XAException {
		File dir = new File(folder);
		dir.mkdirs();

		File file = new File(dir, new Uid().fileStringForm() + "_");

		try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file))) {
			outputStream.writeInt(aTransactionID.getFormatId());
			outputStream.writeInt(aTransactionID.getGlobalTransactionId().length);
			outputStream.write(aTransactionID.getGlobalTransactionId(), 0,
					aTransactionID.getGlobalTransactionId().length);
			outputStream.writeInt(aTransactionID.getBranchQualifier().length);
			outputStream.write(aTransactionID.getBranchQualifier(), 0, aTransactionID.getBranchQualifier().length);
			outputStream.flush();
		} catch (IOException e) {
			throw new XAException(XAException.XAER_RMERR);
		}

		return file;
	}

	private void deletexidFile(File xidFile) throws XAException {
		if (xidFile != null) {
			if (!xidFile.delete()) {
				throw new XAException(XAException.XA_RETRY);
			}
		}
	}

	private class XaTransactionIdImpl implements Xid {

		private final int formatIdentifier;

		private final byte[] globalTransactionId;

		private final byte[] branchQualifier;

		public XaTransactionIdImpl(int formatIdentifier, byte[] globalTransactionId, byte[] branchQualifier) {
			this.formatIdentifier = formatIdentifier;
			this.globalTransactionId = globalTransactionId;
			this.branchQualifier = branchQualifier;
		}

		@Override
		public int getFormatId() {
			return formatIdentifier;
		}

		@Override
		public byte[] getGlobalTransactionId() {
			return globalTransactionId;
		}

		@Override
		public byte[] getBranchQualifier() {
			return branchQualifier;
		}

	}

}
