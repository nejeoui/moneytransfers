package com.revolut.moneytransfers;

import javax.ws.rs.ext.RuntimeDelegate;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import org.jboss.weld.environment.se.Weld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.moneytransfers.service.rest.AccountRest;
import com.revolut.moneytransfers.service.rest.BeneficiaryRest;
import com.revolut.moneytransfers.service.rest.HelloRest;
import com.revolut.moneytransfers.service.rest.TransferRest;

public class RestServer {
	private static Logger logger = LoggerFactory.getLogger(RestServer.class);

	public static void main(String args[]) {
		try {
			//initialize CDI Context
			Weld weld = new Weld();
			weld.initialize();
			
			//start Jersey Server
			ResourceConfig resourceConfig = new ResourceConfig();
			resourceConfig.register(AccountRest.class);
			resourceConfig.register(HelloRest.class);
			resourceConfig.register(TransferRest.class);
			resourceConfig.register(BeneficiaryRest.class);
			HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(resourceConfig, HttpHandler.class);
			HttpServer server = HttpServer.createSimpleServer(null, 8080);
			server.getServerConfiguration().addHttpHandler(handler);
			new Thread(() -> {
				try {
					logger.info("Starting...!");
					server.start();
					logger.info("http Server started!");
					System.in.read();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}).start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}