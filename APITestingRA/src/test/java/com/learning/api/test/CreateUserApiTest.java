package com.learning.api.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.learning.api.GlobalVariables;
import com.learning.api.utils.LogUtils;
import com.learning.api.utils.XLUtils;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateUserApiTest extends BaseTest {
	LogUtils lu = new LogUtils();

	@Test(dataProvider = "empDataProvider")
	void createUserTest1(String dpname, String dpjob) throws IOException {
		this.createUserApiObj.createUser(GlobalVariables.baseUri, GlobalVariables.createUser, dpname, dpjob);
		this.commonAssertion(this.createUserApiObj.response, dpname, dpjob);

	}

	void commonAssertion(Response response, String name, String job) {

		JsonPath jsonpath = response.jsonPath();

		this.lu.logs("\n***********Assertions****************** \n");
		
		//this.lu.logs("\n JsonSchemaValidaton  " + response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createUserApiSchema.json")));
		Assert.assertEquals(response.statusCode(), 201);
		this.lu.logs("\nStatusCode: " + response.statusCode());
		Assert.assertEquals(response.header("Server"), "cloudflare");
		this.lu.logs("\nHeader Server: " + response.header("Server"));
		Assert.assertEquals(jsonpath.get("name"), name);
		this.lu.logs("\nNode name: " + jsonpath.get("name"));
		
		Assert.assertEquals(jsonpath.get("job"), job);
		this.lu.logs("\nNode Job: " + jsonpath.get("job"));
		this.logger.warn("Testing Logger " + response.statusCode() + response.header("Server") + name + job);
		
		

	}

	@DataProvider(name = "empDataProvider")
	String[][] createUserData() {

		try {
			int rowCount = XLUtils.getRowCount(GlobalVariables.dataFilePath, "user");
			int columnCount = XLUtils.getCellCount(GlobalVariables.dataFilePath, "user", 1);

			String empData[][] = new String[rowCount][columnCount];
			for (int rcounter = 1; rcounter <= rowCount; rcounter++) {
				for (int ccounter = 0; ccounter < columnCount; ccounter++) {
					empData[rcounter - 1][ccounter] = XLUtils.getCellData(GlobalVariables.dataFilePath, "user",
							rcounter, ccounter);
				}

			}
			return (empData);

			// String empData[][] = { { "naveen2", "qa" }, { "naveen3", "qa1" } };

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (null);
	}
}
