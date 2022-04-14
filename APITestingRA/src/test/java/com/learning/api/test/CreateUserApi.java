package com.learning.api.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.json.simple.JSONObject;

import com.learning.api.utils.LogUtils;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateUserApi {
	LogUtils lu = new LogUtils();
	String responseBody = "";
	RequestSpecification httpRequest;
	Response response;

	String logFilePath = this.lu.LogFilePath();

	void createUser(String baseuri, String resource, String name, String job) throws IOException {

		RestAssured.baseURI = baseuri;
		PrintStream log = new PrintStream(new FileOutputStream(this.logFilePath, true));

		log.print("\n****************************** Request & Response :*********************************\n");
		this.httpRequest = RestAssured.given().when().filter(RequestLoggingFilter.logRequestTo(log))
				.filter(ResponseLoggingFilter.logResponseTo(log));

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", name);
		requestParams.put("job", job);
		this.httpRequest.header("Content-Type", "application/json");
		this.httpRequest.body(requestParams.toJSONString());
		this.response = this.httpRequest.request(Method.POST, resource);
		this.responseBody = this.response.getBody().asPrettyString();

	}

}
