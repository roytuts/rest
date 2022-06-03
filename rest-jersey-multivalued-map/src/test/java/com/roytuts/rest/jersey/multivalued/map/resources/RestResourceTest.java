package com.roytuts.rest.jersey.multivalued.map.resources;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;

public class RestResourceTest {

	private HttpServer httpServer;
	private WebTarget webTarget;
	private static final URI baseUri = URI.create("http://localhost:9090/rest/");

	@Before
	public void setup() throws Exception {
		// create ResourceConfig from Resource class
		ResourceConfig rc = new ResourceConfig(RestResource.class);
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
		// below lines

		// System.out.println(String
		// .format("Application started.%nHit enter to stop it..."));
		// System.in.read();
		// stop the server
		httpServer.shutdown();
	}

	@Test
	public void testPostMultivalued() {
		MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>();
		map.add("fname", "Soumitra");
		map.add("lname", "Roy");
		String response = webTarget.path("resource/multivalued").request().post(Entity.form(map), String.class);
		System.out.println("Response: " + response);
	}

}
