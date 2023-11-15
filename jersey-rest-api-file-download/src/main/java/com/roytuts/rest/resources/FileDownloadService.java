package com.roytuts.rest.resources;

import java.io.File;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

@Path("/file")
public class FileDownloadService {

	private static final String FILE_PATH = "C:/Download/250MB";

	@GET
	@Path("/download")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getFile() {
		File file = new File(FILE_PATH);

		ResponseBuilder response = Response.ok((Object) file);

		response.header("Content-Disposition", "attachment; filename=" + file.getName());

		return response.build();
	}

}
