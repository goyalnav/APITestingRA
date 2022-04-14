package com.learning.api.test;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import com.learning.api.Base;

class BaseTest extends Base {

	GetApi getApiObj = new GetApi();
	CreateUserApi createUserApiObj = new CreateUserApi();
	public Logger logger;

	@BeforeClass
	public void setup() {
		this.logger = Logger.getLogger("APITestingRA");
		PropertyConfigurator.configure("Log4j.properties");
		this.logger.setLevel(Level.ALL);
	}
}
