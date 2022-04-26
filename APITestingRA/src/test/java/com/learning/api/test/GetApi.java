package com.learning.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import com.learning.api.Base;
import com.learning.api.utils.LogUtils;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.CoreMatchers.*;

public class GetApi extends Base {

	LogUtils lu = new LogUtils();
	String responseBody = "";
	RequestSpecification httpRequest;
	Response response;

	String logFilePath = this.lu.LogFilePath();

	void getApi(String baseuri, String resource) throws IOException {

		RestAssured.baseURI = baseuri;
		PrintStream log = new PrintStream(new FileOutputStream(this.logFilePath, true));

		log.print("\n****************************** Request & Response :*********************************\n");
		this.httpRequest = RestAssured.given()
									  .when()
									  	.filter(RequestLoggingFilter.logRequestTo(log))
									  	.filter(ResponseLoggingFilter.logResponseTo(log));
		this.response = this.httpRequest.request(Method.GET, resource);
		this.responseBody = this.response.getBody().asPrettyString();

	}

}
