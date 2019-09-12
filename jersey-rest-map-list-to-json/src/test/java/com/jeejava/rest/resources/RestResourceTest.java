package com.roytuts.rest.resources;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RestResourceTest {

	private HttpServer httpServer;
	private WebTarget webTarget;
	private static final URI baseUri = URI.create("http://localhost:9090/rest-service/");

	@Before
	public void setUp() throws Exception {
		// create ResourceConfig from Resource class
		ResourceConfig rc = new ResourceConfig(RestResources.class);

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
		// System.out.println(String
		// .format("Application started.%nHit enter to stop it..."));
		// System.in.read();

		// stop the server
		httpServer.shutdown();
	}

	@Test
	public void testGetListAsJson() {
		Response response = webTarget.path("resource/getListAsJson").request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).buildGet().invoke();
		System.out.println("JSON from List:");
		System.out.println();
		System.out.println(response.readEntity(String.class));
		System.out.println();
	}

	@Test
	public void testGetMapAsJson() {
		Response response = webTarget.path("resource/getMapAsJson").request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).buildGet().invoke();
		System.out.println("JSON from Map:");
		System.out.println();
		System.out.println(response.readEntity(String.class));
		System.out.println();
	}

	@Test
	public void testGetMapListAsJson() {
		Response response = webTarget.path("resource/getMapListAsJson").request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).buildGet().invoke();
		System.out.println("JSON from Map which contains List:");
		System.out.println();
		System.out.println(response.readEntity(String.class));
		System.out.println();
	}

}
