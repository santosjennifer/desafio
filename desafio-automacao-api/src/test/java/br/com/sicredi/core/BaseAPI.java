package br.com.sicredi.core;

import org.junit.BeforeClass;

import io.restassured.RestAssured;

public abstract class BaseAPI {
	
	@BeforeClass
	public static void url() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;		
		RestAssured.basePath = "/api/v1";		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}	
	
}
