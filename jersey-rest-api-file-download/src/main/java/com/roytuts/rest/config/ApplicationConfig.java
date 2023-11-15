package com.roytuts.rest.config;

import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;

//@ApplicationPath("/") //Uncomment this line if you do not use web.xml file
public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {
		// this call has the same effect as
		// jersey.config.server.provider.packages
		// in the web.xml: it scans that packages for resources and providers.
		packages("com.roytuts.rest.resources");
	}

}
