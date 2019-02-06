package com.revolut.moneytransfers.test;

import javax.ws.rs.ext.RuntimeDelegate;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;

import com.revolut.moneytransfers.service.rest.AccountRest;
import com.revolut.moneytransfers.service.rest.HelloRest;

public class RestServer {

	public static void main(String args[]) {
		try {
			ResourceConfig resourceConfig = new ResourceConfig();
			resourceConfig.register(AccountRest.class);
			resourceConfig.register(HelloRest.class);
			HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(resourceConfig, HttpHandler.class);
			HttpServer server = HttpServer.createSimpleServer(null, 8080);
			server.getServerConfiguration().addHttpHandler(handler);
			new Thread(() -> {
				try {
					System.out.println("Starting...!");
					server.start();
					System.out.println("http Server started!");
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