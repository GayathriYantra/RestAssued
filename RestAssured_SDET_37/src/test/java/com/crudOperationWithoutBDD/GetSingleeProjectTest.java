package com.crudOperationWithoutBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class GetSingleeProjectTest 
{
@Test
public void getSingleProjects()
{
	//sending request
	Response response = RestAssured.get("http://localhost:8084/projects/TY_PROJ_808");
	
	//Validation
	ValidatableResponse validate = response.then();
	validate.assertThat().contentType(ContentType.JSON);
	validate.assertThat().statusCode(200);
	validate.log().all();
}
}
