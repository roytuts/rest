package com.roytuts.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.ConnectionCallback;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

@Path("/resource")
public class AsyncResource {

	@GET
	@Path("/asyncConnCallback")
	public void asyncGetConnectionCallback(@Suspended final AsyncResponse asyncResponse) {
		asyncResponse.register(new ConnectionCallback() {
			@Override
			public void onDisconnect(AsyncResponse asyncResponse) {
				asyncResponse.resume(
						Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Connection Callback").build());
			}
		});

		new Thread(new Runnable() {
			@Override
			public void run() {
				String result = veryExpensiveOperation();
				asyncResponse.resume(result);
			}

			private String veryExpensiveOperation() {
				return "Very Expensive Operation with Connection Callback";
			}
		}).start();
	}

}
