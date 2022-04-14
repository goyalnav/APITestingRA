package com.learning.api;

public class GlobalVariables {
	public static String projectPath = System.getProperty("user.dir");
	public static String baseUri = "https://reqres.in";
	public static String resource = "/api/products/3";
	public static String createUser = "/api/users";
	public static String logFilePath = GlobalVariables.projectPath + "/logs/";
	public static String dataFilePath = GlobalVariables.projectPath + "/data/createuser.xlsx";
}
