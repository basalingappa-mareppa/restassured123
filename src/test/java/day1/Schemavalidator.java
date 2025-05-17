package day1;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class Schemavalidator {		
	

		int id;
		@Test(priority=1)
		 void getmethod()
		{
			given()
			.when()
			.get("https://reqres.in/api/users?page=2")
			.then().statusCode(200)
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsongetschema.json"));
		}
}
