package com.roytuts.rest.resources;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.roytuts.rest.dto.Book;
import com.roytuts.rest.repo.RestResourceRepo;

@Path("/resource")
public class RestResources {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getListAsJson")
	public Response getListAsJson() {
		Gson gson = new Gson();
		List<Book> books = RestResourceRepo.getBooks();
		String jsonString = gson.toJson(books);
		return Response.status(Response.Status.OK).entity(jsonString).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getMapAsJson")
	public Response getMapAsJson() {
		Gson gson = new Gson();
		Map<String, Book> books = RestResourceRepo.getBookMap();
		String jsonString = gson.toJson(books);
		return Response.status(Response.Status.OK).entity(jsonString).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getMapListAsJson")
	public Response getMapListAsJson() {
		Gson gson = new Gson();
		Map<String, List<Book>> books = RestResourceRepo.getBookMapList();
		String jsonString = gson.toJson(books);
		return Response.status(Response.Status.OK).entity(jsonString).build();
	}

}
