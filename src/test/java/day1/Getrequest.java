package day1;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class Getrequest {

	
	int id;
	@Test(priority=1)
	 void getmethod()
	{
		given()
		.when()
		.get("https://reqres.in/api/users?page=2")
		.then().statusCode(200)
		.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsongetschema.json"));
//		.body("page",equalTo(2))
//		.log().all();
	    
	    
	}
	@Test(priority=2)
	void postmethod()
	{
		HashMap hs=new HashMap();
		hs.put("name", "basa");
		hs.put("job", "tester");
		
		id=given()
		.header("x-api-key","reqres-free-v1")
		.contentType("application/json")
		.body(hs)
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
//		.then()
//		.statusCode(201)
//		.log().all();
	}
	
	@Test(priority=3,dependsOnMethods= {"postmethod"})
	void updateuser()
	{
		HashMap hs=new HashMap();
		hs.put("name", "basa");
		hs.put("job", "developer");
		
		given()
		.header("x-api-key","reqres-free-v1")
		.contentType("application/json")
		.body(hs)
		.when()
		.post("https://reqres.in/api/users"+id)
		.then()
		.statusCode(201)
		.body("job",equalTo("developer"));
	}
	
	@Test
	void deleteuser()
	{
		Response res=given()
		.header("x-api-key","reqres-free-v1")
		.contentType("application/json")
		.when()
		.delete("https://reqres.in/api/users\"+id");
//		.then()
//		.statusCode(204)
//		.log().all();
		
		Map<String ,String> allcookies=res.cookies();
		for(String k:allcookies.keySet())
		{
			String cookvalue=res.getCookie(k);
			System.out.println(k+"  "+cookvalue);
		}
		
	}

}
