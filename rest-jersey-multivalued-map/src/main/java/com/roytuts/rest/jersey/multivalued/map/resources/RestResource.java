package com.roytuts.rest.jersey.multivalued.map.resources;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MultivaluedMap;

@Path("/resource")
public class RestResource {

	@POST
	@Path("/multivalued")
	public String postMultivalued(MultivaluedMap<String, String> multivaluedMap) {

		if (multivaluedMap.get("fname") != null && multivaluedMap.get("lname") != null) {
			String fname = multivaluedMap.get("fname").get(0);
			String lname = multivaluedMap.get("lname").get(0);
			return "Your name : " + fname + " " + lname;
		}

		return "I have not received anything from you";
	}

}
