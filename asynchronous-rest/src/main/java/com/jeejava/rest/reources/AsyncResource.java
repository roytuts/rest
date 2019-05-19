package com.jeejava.rest.reources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

@Path("/resource")
public class AsyncResource {

	@GET
	@Path("/simpleAsync")
	public void asyncGet(@Suspended final AsyncResponse asyncResponse) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String result = veryExpensiveOperation();
				asyncResponse.resume(result);
			}

			private String veryExpensiveOperation() {
				return "Very Expensive Operation";
			}
		}).start();
	}

}
