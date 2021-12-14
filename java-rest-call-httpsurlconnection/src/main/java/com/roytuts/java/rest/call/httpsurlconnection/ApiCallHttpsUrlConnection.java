package com.roytuts.java.rest.call.httpsurlconnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ApiCallHttpsUrlConnection {

	public static void main(String[] args) throws IOException {
		// getApiRequest();
		// postApiRequest();
		postApiRequestWithProxy();
	}

	public static void getApiRequest() throws IOException {
		// Get a list of users
		URL usersUrl = new URL("https://gorest.co.in/public/v1/users");

		HttpsURLConnection conection = (HttpsURLConnection) usersUrl.openConnection();

		// Set request method
		conection.setRequestMethod("GET");

		// Getting response code
		int statusCode = conection.getResponseCode();

		// If responseCode is 200, data fetch successful
		if (statusCode == HttpsURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
			StringBuffer jsonResponseData = new StringBuffer();
			String readLine = null;

			// Append response line by line
			while ((readLine = in.readLine()) != null) {
				jsonResponseData.append(readLine);
			}

			in.close();
			// Print result in string format
			System.out.println("List of users: " + jsonResponseData.toString());
		} else {
			System.out.println(statusCode);
		}

		System.out.println("==============================================");
		System.out.println("Single user details");
		// Get 9th user details
		URL userUrl = new URL("https://gorest.co.in/public/v1/users/9");

		conection = (HttpsURLConnection) userUrl.openConnection();

		// Getting response code
		statusCode = conection.getResponseCode();

		// If responseCode is 200, data fetch successful
		if (statusCode == HttpsURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
			StringBuffer jsonResponseData = new StringBuffer();
			String readLine = null;

			// Append response line by line
			while ((readLine = in.readLine()) != null) {
				jsonResponseData.append(readLine);
			}

			in.close();
			// Print result in string format
			System.out.println("9th user details: " + jsonResponseData.toString());
		} else {
			System.out.println(statusCode);
		}
	}

	public static void postApiRequest() throws IOException {
		// Url for making POST request
		URL postUrl = new URL("https://gorest.co.in/public/v1/users");

		String token = "eyJraWQioiJRVWRuYlktQmpaODFCaDRZMENfOVlyUVJpa3IyYkZDLVFQNXJtS0taUk9nIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULkxGb3hscnJ1cUdIdHl0QjUzOFMzUGdwMng5QlI3djNMN3J5Qm5FLThGM00iLCJpc3MiOiJodHRwczovL2RpZGVudGl0eWRldi5va3RhcHJldmlldy5jb20iLCJhdWQiOiJodHRwczovL2RpZGVudGl0eWRldi5va3RhcHJldmlldy5jb20iLCJzdWIiOiJzc2Fya2E4QHByb2RkZnMucGYuZGlzY292ZXJmaW5hbmNpYWwuY29tIwiaWF0IjoxNjM4NDYyNTQ0LCJleHAiOjE2Mzg0NjYxNDQsImNpZCI6IjBvYTEzZnBudHp2b3VVQlNwMGg4IiwidWlkIjoiMDB1MTFkNGd4Njh6S0dzOVgwaDgiLCJzY3AiOlsib3BlbmlkIiwiZW1haWwiLCJwcm9maWxlIl19.X3WNN0iji7aq7eiba6p3EKYSbQoIMpG5p1e8Wmh_oZtqu2x2GfumyCIcOp9i-A-LidrELrnrz0uhbePFni4ONY7rUvB7ALDGOPINh0ksiOwSuMEOkhFSWTfOGW1tak3At8KgEqNmysoOJ5O8MESZMEyGf0qlrEwqqO8aYpcdR47j1DJQ7ijteVTISZr89uzlaLknIZabhisncRMx9kKCGiRD-eaLr0gvREQ93tch3Tq8dXH9fsx0Ea9n4NlnMTmOrKNHZVk2yODIM9Qw4CplfFAe9s_NCdwMj3aF-yTd_mC502HJMN0vI4VUT9BpF9si2DA26K1pWhxe-B2nKxFZ-w";

		HttpsURLConnection connection = (HttpsURLConnection) postUrl.openConnection();

		// Set POST as request method
		connection.setRequestMethod("POST");

		// Setting Header Parameters
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Authorization", "Bearer " + token);
		connection.setUseCaches(false);
		connection.setDoOutput(true);

		// Adding Body payload for POST request
		String params = "{\"name\":\"Soumitra Roy\", \"gender\":\"male\", \"email\":[mailto:soumitra@roytuts.com]soumitra@roytuts.com\", \"status\":\"active\"}";

		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
		wr.write(params);
		wr.close();

		connection.connect();

		// Getting Response
		int statusCode = connection.getResponseCode();

		// Checking http status code for 201 (Created)
		if (statusCode == HttpsURLConnection.HTTP_CREATED) {
			StringBuffer jsonResponseData = new StringBuffer();
			String readLine = null;
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((readLine = bufferedReader.readLine()) != null) {
				jsonResponseData.append(readLine + "\n");
			}

			bufferedReader.close();
			System.out.println(jsonResponseData.toString());

		} else {
			System.out.println(statusCode);
		}

	}

	public static void postApiRequestWithProxy() throws IOException {
		// Url for making POST request
		URL postUrl = new URL("https://dev.oktapreview.com/oauth2/v1/userinfo");

		Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("proxy-cloud.example.com", 8080));

		String token = "eyJraWQioiJRVWRuYlktQmpaODFCaDRZMENfOVlyUVJpa3IyYkZDLVFQNXJtS0taUk9nIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULkxGb3hscnJ1cUdIdHl0QjUzOFMzUGdwMng5QlI3djNMN3J5Qm5FLThGM00iLCJpc3MiOiJodHRwczovL2RpZGVudGl0eWRldi5va3RhcHJldmlldy5jb20iLCJhdWQiOiJodHRwczovL2RpZGVudGl0eWRldi5va3RhcHJldmlldy5jb20iLCJzdWIiOiJzc2Fya2E4QHByb2RkZnMucGYuZGlzY292ZXJmaW5hbmNpYWwuY29tIwiaWF0IjoxNjM4NDYyNTQ0LCJleHAiOjE2Mzg0NjYxNDQsImNpZCI6IjBvYTEzZnBudHp2b3VVQlNwMGg4IiwidWlkIjoiMDB1MTFkNGd4Njh6S0dzOVgwaDgiLCJzY3AiOlsib3BlbmlkIiwiZW1haWwiLCJwcm9maWxlIl19.X3WNN0iji7aq7eiba6p3EKYSbQoIMpG5p1e8Wmh_oZtqu2x2GfumyCIcOp9i-A-LidrELrnrz0uhbePFni4ONY7rUvB7ALDGOPINh0ksiOwSuMEOkhFSWTfOGW1tak3At8KgEqNmysoOJ5O8MESZMEyGf0qlrEwqqO8aYpcdR47j1DJQ7ijteVTISZr89uzlaLknIZabhisncRMx9kKCGiRD-eaLr0gvREQ93tch3Tq8dXH9fsx0Ea9n4NlnMTmOrKNHZVk2yODIM9Qw4CplfFAe9s_NCdwMj3aF-yTd_mC502HJMN0vI4VUT9BpF9si2DA26K1pWhxe-B2nKxFZ-w";

		HttpsURLConnection connection = (HttpsURLConnection) postUrl.openConnection(proxy);

		// Set POST as request method
		connection.setRequestMethod("POST");

		// Setting Header Parameters
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Authorization", "Bearer " + token);
		connection.setUseCaches(false);
		connection.setDoOutput(true);

		// Adding Body payload for POST request
		String params = "";

		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
		wr.write(params);
		wr.flush();
		wr.close();

		connection.connect();

		// Getting Response
		int statusCode = connection.getResponseCode();

		// Checking ckode for 200 (Ok)
		if (statusCode == HttpsURLConnection.HTTP_OK) {
			StringBuffer jsonResponseData = new StringBuffer();
			String readLine = null;
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((readLine = bufferedReader.readLine()) != null) {
				jsonResponseData.append(readLine + "\n");
			}

			bufferedReader.close();
			connection.disconnect();
			System.out.println(jsonResponseData.toString());
		} else {
			System.out.println(statusCode);
		}

	}
}
