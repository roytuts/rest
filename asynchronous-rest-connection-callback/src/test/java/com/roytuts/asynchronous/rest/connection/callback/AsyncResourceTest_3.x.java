package com.roytuts.asynchronous.rest.connection.callback.resources;

import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jakarta.ws.rs.client.AsyncInvoker;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

public class AsyncResourceTest {

	private HttpServer httpServer;
	private WebTarget webTarget;
	private static final URI baseUri = URI.create("http://localhost:9090/rest/");

	@Before
	public void setup() throws Exception {
		// create ResourceConfig from Resource class
		ResourceConfig rc = new ResourceConfig(AsyncResource.class);
		
		// create the Grizzly server instance
		httpServer = GrizzlyHttpServerFactory.createHttpServer(baseUri, rc);
		
		// start the server
		httpServer.start();
		
		// configure client with the base URI path
		Client client = ClientBuilder.newClient();
		webTarget = client.target(baseUri);
	}

	@After
	public void tearDown() throws Exception {
		// if you want to stop the server from the input through keyboard then uncomment
		// below two lines
		// System.out.println(String.format("Application started.%nHit enter to stop
		// it..."));
		// System.in.read();
		// stop the server
		httpServer.shutdown();
	}

	@Test
	public void testAsyncGetConnectionCallback() throws InterruptedException, ExecutionException {
		final AsyncInvoker asyncInvoker = webTarget.path("resource/asyncConnCallback").request().async();
		final Future<Response> responseFuture = asyncInvoker.get();
		
		System.out.println("Request is being processed asynchronously.");
		
		final Response response = responseFuture.get();
		
		// get() waits for the response to be ready
		System.out.println("Response received : " + response);
		System.out.println("Response from GET method : " + response.readEntity(String.class));
	}

}
