package com.revolut.moneytransfers.util;

import javax.enterprise.context.ApplicationScoped;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * A utility {@code class} based on <a href="https://httpd.apache.org"> HttpClient API</a>
 * <p>
 *
 * @author <a href="mailto:a.nejeoui@gmail.com">Abderrazzak Nejeoui</a>
 * @since 1.0
 */
@ApplicationScoped
public class HttpUtil {

	/**
	 * Self4j Logger
	 */
	private transient  Logger logger=LoggerFactory.getLogger(HttpUtil.class);
	
	public  String get(String url) throws Exception {
		String result = null;
		logger.info(url);
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			HttpGet httpget = new HttpGet(url);
			CloseableHttpResponse response = httpclient.execute(httpget);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity());

			}
			httpclient.close();
		}
		return result;
	}
}