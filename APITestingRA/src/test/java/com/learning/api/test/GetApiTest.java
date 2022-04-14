package com.learning.api.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.learning.api.utils.LogUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetApiTest extends BaseTest {
	LogUtils lu = new LogUtils();

	@Test
	void getApiTest1() throws IOException {
		this.getApiObj.getApi(this.gv.baseUri, this.gv.resource);
		this.commonAssertion(this.getApiObj.response);
	}

	void commonAssertion(Response response) {

		JsonPath jsonpath = response.jsonPath();
		this.lu.logs("\n***********Assertions******************");
		Assert.assertEquals(response.statusCode(), 200);
		this.lu.logs("\n StatusCode: " + response.statusCode());
		Assert.assertEquals(response.header("Server"), "cloudflare");
		this.lu.logs("\n Header Server: " + response.header("Server"));
		// Assert.assertEquals(jsonpath.get("data.id"), "3");
		this.lu.logs("\nNode data id: " + jsonpath.get("data.id"));
		// Assert.assertEquals(jsonpath.get("data.year"), "2002");
		this.lu.logs("\nNode Job: " + jsonpath.get("data.year"));
	}

}
