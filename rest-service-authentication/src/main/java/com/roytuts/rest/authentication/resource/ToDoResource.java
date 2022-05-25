package com.roytuts.rest.authentication.resource;

import java.util.Map;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/todo")
public class ToDoResource {
	@Context
	HttpHeaders httpHeaders;

	@GET
	@Path("getToDo/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getToDo(@PathParam("id") @NotNull int id) {
		// read headers information to retrieve cookie
		Map<String, Cookie> cookieMap = httpHeaders.getCookies();
		String userName = cookieMap.get("username").getValue();
		String userPass = cookieMap.get("password").getValue();
		
		// authenticate
		if ("roytuts".equals(userName) && "roytuts".equals(userPass)) {
			return Response.status(Response.Status.OK).entity("Get ToDo for " + id).type(MediaType.APPLICATION_JSON)
					.build();
		} else {
			return Response.status(Response.Status.OK).entity("Authentication failed : invalid credentials")
					.type(MediaType.APPLICATION_JSON).build();
		}
	}

	@POST
	@Path("addToDo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addToDo(String toDo) {
		// read headers information to retrieve cookie
		Map<String, Cookie> cookieMap = httpHeaders.getCookies();
		String userName = cookieMap.get("username").getValue();
		String userPass = cookieMap.get("password").getValue();

		// authenticate
		if ("roytuts".equals(userName) && "roytuts".equals(userPass)) {
			return Response.status(Response.Status.OK).entity("New ToDo " + toDo + " added successfully")
					.type(MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Response.Status.OK).entity("Authentication failed : invalid credentials")
					.type(MediaType.APPLICATION_JSON).build();
		}
	}
}
