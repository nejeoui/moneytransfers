package com.revolut.moneytransfer.dao.jta;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.xa.XAResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arjuna.ats.jta.recovery.XAResourceRecoveryHelper;

/**
 * Custom implementation of the {@code XAResourceRecoveryHelper} interface
 * 
 * @author <a href="mailto:a.nejeoui@gmail.com">Abderrazzak Nejeoui</a> This
 *         implementation was designed based on JBoss Quickstart Guide, credits
 *         to <a href="mailto:gytis@redhat.com">Gytis Trikleris</a> and
 *         <a href="mailto:zfeng@redhat.com">Amos Feng</a>
 * @see XAResourceRecoveryHelper
 */
@ApplicationScoped
public class RevolutXAResourceRecoveryHelper implements XAResourceRecoveryHelper {

	/**
	 * Self4j Logger
	 */
	@Inject
	private transient Logger logger=LoggerFactory.getLogger(RevolutXAResourceRecoveryHelper.class);
	
	@Override
	public boolean initialise(String p) throws Exception {
		return false;
	}

	@Override
	public XAResource[] getXAResources() throws RuntimeException {
		List<RevolutXAResource> resources;
		try {
			resources = getXAResourcesFromDirectory(RevolutXAResource.XA_Folder);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		logger.info(
				RevolutXAResourceRecoveryHelper.class.getSimpleName() + " returning list of resources: " + resources);

		return resources.toArray(new XAResource[] {});
	}

	private List<RevolutXAResource> getXAResourcesFromDirectory(String path) throws IOException {
		List<RevolutXAResource> xaResources = new ArrayList<>();

		if (!Files.exists(FileSystems.getDefault().getPath(path))) {
			return xaResources;
		}

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(FileSystems.getDefault().getPath(path), "*_")) {
			stream.forEach(p -> {
				try {
					xaResources.add(new RevolutXAResource(p.toFile()));
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});

			return xaResources;
		}
	}
}
