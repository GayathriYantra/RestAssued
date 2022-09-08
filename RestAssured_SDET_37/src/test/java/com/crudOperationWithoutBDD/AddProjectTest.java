package com.crudOperationWithoutBDD;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class AddProjectTest 
{
	@Test
	public void create()
	{
		//json body created
		JSONObject jobj= new JSONObject();
		jobj.put("createdBy", "Gayathri");
		jobj.put("projectName", "IOBA101");
		jobj.put("status", "completed");
		jobj.put("teamSize", 10);
		
		//Request Body and ContentType
		RequestSpecification reqSpec=RestAssured.given();
		reqSpec.body(jobj);
		reqSpec.contentType(ContentType.JSON);
		
		//Validation
		Response response= reqSpec.post("http://localhost:8084/addProject");
		ValidatableResponse validate = response.then();
		validate.assertThat().contentType(ContentType.JSON);
		validate.assertThat().statusCode(201);
		validate.log().all();
		

	}
}
