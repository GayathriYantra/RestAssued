package com.crudOperationWithoutBDD;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class UpdateEntireProjectTest 
{
	@Test
	public void updateEntireProject()
	{
		//json body created
		JSONObject jobj= new JSONObject();
		jobj.put("createdBy", "GayathriSarath");
		jobj.put("projectName", "IOB_Onnupuram");
		jobj.put("status", "completedAlready");
		jobj.put("teamSize", 20);

		//Request Body and ContentType
		RequestSpecification reqSpec=RestAssured.given();
		reqSpec.body(jobj);
		reqSpec.contentType(ContentType.JSON);
		
		//update the project 
		Response response=reqSpec.put("http://localhost:8084/projects/TY_PROJ_1602");

		//Validation
		ValidatableResponse validate = response.then();
		validate.assertThat().contentType(ContentType.JSON);
		validate.assertThat().statusCode(200);
		validate.log().all();
	}
}
