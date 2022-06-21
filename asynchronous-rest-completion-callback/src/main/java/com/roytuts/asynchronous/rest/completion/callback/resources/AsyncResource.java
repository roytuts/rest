package com.roytuts.asynchronous.rest.completion.callback.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.CompletionCallback;
import javax.ws.rs.container.Suspended;
@Path("/resource")
public class AsyncResource {
	private static int numberOfSuccessResponses = 0;
	private static int numberOfFailures = 0;
	private static Throwable lastException = null;
	@GET
	@Path("/asyncCompCallback")
	public void asyncGetCompletionCallback(@Suspended final AsyncResponse asyncResponse) {
		asyncResponse.register(new CompletionCallback() {
			@Override
			public void onComplete(Throwable throwable) {
				if (throwable == null) {
					// no throwable - the processing ended successfully
					// (response already written to the client)
					numberOfSuccessResponses++;
				} else {
					numberOfFailures++;
					lastException = throwable;
				}
			}
		});
		new Thread(new Runnable() {
			@Override
			public void run() {
				String result = veryExpensiveOperation();
				asyncResponse.resume(result);
			}
			private String veryExpensiveOperation() {
				return "Very Expensive Operation with Completion Callback";
			}
		}).start();
	}
}
