package com.roytuts.rest.authentication.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MediaType;

public class ToDoResourceTest {

	private HttpServer httpServer;
	private WebTarget webTarget;
	private List<Cookie> cookies;
	private static final URI baseUri = URI.create("http://localhost:9090/rest/");

	@Before
	public void setup() throws Exception {
		// create ResourceConfig from Resource class
		ResourceConfig rc = new ResourceConfig(ToDoResource.class);
		// create the Grizzly server instance
		httpServer = GrizzlyHttpServerFactory.createHttpServer(baseUri, rc);
		// start the server
		httpServer.start();
		// configure client with the base URI path
		Client client = ClientBuilder.newClient();
		webTarget = client.target(baseUri);
		if (cookies == null) {
			buildCookies();
		}
	}

	@After
	public void tearDown() throws Exception {
		// if you want to stop the server from the input through keyboard then
		// uncomment below two lines
		// System.out.println(String
		// .format("Application started.%nHit enter to stop it..."));
		// System.in.read();
		// stop the server
		httpServer.shutdown();
	}

	@Test
	public void testGetToDo() {
		webTarget = webTarget.path("todo/getToDo/1");
		Builder builder = webTarget.request();
		// add cookie to the headers
		for (Cookie cookie : cookies) {
			builder.cookie(cookie);
		}
		String response = builder.accept(MediaType.APPLICATION_JSON).get().readEntity(String.class);
		System.out.println("Response: " + response);
	}

	@Test
	public void testAddToDo() {
		webTarget = webTarget.path("todo/addToDo");
		Builder builder = webTarget.request();
		// add cookie to the headers
		for (Cookie cookie : cookies) {
			builder.cookie(cookie);
		}
		String response = builder.accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity("NEW TODO", MediaType.APPLICATION_JSON), String.class);
		System.out.println("Response: " + response);
	}

	private void buildCookies() {
		cookies = new ArrayList<Cookie>();
		Cookie cookieUser = new Cookie("username", "roytuts");
		Cookie cookiePass = new Cookie("password", "roytuts1");
		cookies.add(cookieUser);
		cookies.add(cookiePass);
	}

}
