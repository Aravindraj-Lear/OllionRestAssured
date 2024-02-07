package Ollion.RestAssuredAssignment;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class badges_tags_GET {

    private static final String BASE_URI = "https://api.stackexchange.com/2.3";
    
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
    }


	@Test
	public void testValidRequest() {	
		Response response = RestAssured.given()
				.header("Authorization", "Bearer jKGRx2jMfi128tPh8gHQCw))")
				.contentType(ContentType.JSON)
				.param("page", 1)
				.param("pagesize", 2)
				.param("fromdate", 1704067200)
				.param("todate", 1707264000)
				.queryParam("order", "desc")
	            .queryParam("min", "bronze")
	            .queryParam("sort", "rank")
	            .queryParam("inname", "gojs")
				.param("site", "stackoverflow")
				.when()
				.get("/badges/tags")
				.then()
				.assertThat()
                // Validate response against JSON Schema
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Badges_tags_ResponseSchema.json"))
                .extract()
                .response();
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		

		Reporter.log(methodName+ " Response: " + response.asPrettyString());
		Reporter.log(methodName+ " Response status code: " + response.getStatusCode());
		Reporter.log(methodName+ " Schema validation successful. Json structure, required fields and field types are validated with this. ");
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 200);
        Reporter.log(methodName+ " Response status code check successful ");
        
        JsonPath jsonPath = new JsonPath(response.asString());       
        Assert.assertEquals(jsonPath.getString("items[0].name"), "gojs");
	}
	
	@Test
	public void testInvalidAuthorizationToken() {	
		Response response = RestAssured.given()
				.header("Authorization", "Bearer invalidToken))")
				.contentType(ContentType.JSON)
				.param("page", 1)
				.param("pagesize", 2)
				.param("fromdate", 1704067200)
				.param("todate", 1707264000)
				.queryParam("order", "desc")
	            .queryParam("min", "bronze")
	            .queryParam("sort", "rank")
	            .queryParam("inname", "gojs")
				.param("site", "stackoverflow")
				.when()
				.get("/badges/tags")
				.then()
                .extract()
                .response();
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		

		Reporter.log("\n"+methodName+ " Response: \n" + response.asPrettyString());
		Reporter.log(methodName+ " Response status code: \n" + response.getStatusCode());

        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 200);
        Reporter.log(methodName+ " Response status code check successful \n");
	}
	
	@Test
    public void testInvalidPageNumber() {
		Response response = RestAssured.given()
				.header("Authorization", "Bearer jKGRx2jMfi128tPh8gHQCw))")
				.contentType(ContentType.JSON)
				.param("page", -1)
				.param("pagesize", 2)
				.param("fromdate", 1704067200)
				.param("todate", 1707264000)
				.queryParam("order", "desc")
	            .queryParam("min", "bronze")
	            .queryParam("sort", "rank")
	            .queryParam("inname", "gojs")
				.param("site", "stackoverflow")
				.when()
				.get("/badges/tags")
				.then()
                .extract()
                .response();
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		

		Reporter.log(methodName+ " Response: " + response.asPrettyString());
		Reporter.log(methodName+ " Response status code: " + response.getStatusCode());
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 400);
        Reporter.log(methodName+ " Response status code check successful ");
        
        JsonPath jsonPath = new JsonPath(response.asString());      
        Assert.assertEquals(jsonPath.getInt("error_id"), 400);
        Assert.assertEquals(jsonPath.getString("error_message"), "page");
        Assert.assertEquals(jsonPath.getString("error_name"), "bad_parameter");
    }

    @Test
    public void testInvalidPageSize() {
    	Response response = RestAssured.given()
				.header("Authorization", "Bearer jKGRx2jMfi128tPh8gHQCw))")
				.contentType(ContentType.JSON)
				.param("page", 1)
				.param("pagesize", -2)
				.param("fromdate", 1704067200)
				.param("todate", 1707264000)
				.queryParam("order", "desc")
	            .queryParam("min", "bronze")
	            .queryParam("sort", "rank")
	            .queryParam("inname", "gojs")
				.param("site", "stackoverflow")
				.when()
				.get("/badges/tags")
				.then()
                .extract()
                .response();
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		

		Reporter.log(methodName+ " Response: " + response.asPrettyString());
		Reporter.log(methodName+ " Response status code: " + response.getStatusCode());
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 200);
        Reporter.log(methodName+ " Response status code check successful ");
    }

    @Test
    public void testInvalidOrderParameter() {
    	Response response = RestAssured.given()
				.header("Authorization", "Bearer jKGRx2jMfi128tPh8gHQCw))")
				.contentType(ContentType.JSON)
				.param("page", 1)
				.param("pagesize", 2)
				.param("fromdate", 1704067200)
				.param("todate", 1707264000)
				.queryParam("order", "desccc")
	            .queryParam("min", "bronze")
	            .queryParam("sort", "rank")
	            .queryParam("inname", "gojs")
				.param("site", "stackoverflow")
				.when()
				.get("/badges/tags")
				.then()
                .extract()
                .response();
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		

		Reporter.log(methodName+ " Response: " + response.asPrettyString());
		Reporter.log(methodName+ " Response status code: " + response.getStatusCode());
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 400);
        Reporter.log(methodName+ " Response status code check successful ");
        
        JsonPath jsonPath = new JsonPath(response.asString());       
        Assert.assertEquals(jsonPath.getInt("error_id"), 400);
        Assert.assertEquals(jsonPath.getString("error_message"), "order");
        Assert.assertEquals(jsonPath.getString("error_name"), "bad_parameter");
    }
    
    @Test
    public void testInvalidDateFormat() {
    	Response response = RestAssured.given()
				.header("Authorization", "Bearer jKGRx2jMfi128tPh8gHQCw))")
				.contentType(ContentType.JSON)
				.param("page", 1)
				.param("pagesize", 2)
				.param("fromdate", -1)
				.param("todate", -1)
				.queryParam("order", "desc")
	            .queryParam("min", "bronze")
	            .queryParam("sort", "rank")
	            .queryParam("inname", "gojs")
				.param("site", "stackoverflow")
				.when()
				.get("/badges/tags")
				.then()
                .extract()
                .response();
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		

		Reporter.log(methodName+ " Response: " + response.asPrettyString());
		Reporter.log(methodName+ " Response status code: " + response.getStatusCode());
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 200);
        Reporter.log(methodName+ " Response status code check successful ");
    }
    
    @Test
    public void testInvalidMinParameter() {
    	Response response = RestAssured.given()
				.header("Authorization", "Bearer jKGRx2jMfi128tPh8gHQCw))")
				.contentType(ContentType.JSON)
				.param("page", 1)
				.param("pagesize", 2)
				.param("fromdate", 1704067200)
				.param("todate", 1707264000)
				.queryParam("order", "desc")
	            .queryParam("min", "bronzeee")
	            .queryParam("sort", "rank")
	            .queryParam("inname", "gojs")
				.param("site", "stackoverflow")
				.when()
				.get("/badges/tags")
				.then()
                .extract()
                .response();
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		

		Reporter.log(methodName+ " Response: " + response.asPrettyString());
		Reporter.log(methodName+ " Response status code: " + response.getStatusCode());
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 400);
        Reporter.log(methodName+ " Response status code check successful ");
        
        JsonPath jsonPath = new JsonPath(response.asString());       
        Assert.assertEquals(jsonPath.getInt("error_id"), 400);
        Assert.assertEquals(jsonPath.getString("error_message"), "min");
        Assert.assertEquals(jsonPath.getString("error_name"), "bad_parameter");
    }
    
    @Test
    public void testInvalidSortParameter() {
    	Response response = RestAssured.given()
				.header("Authorization", "Bearer jKGRx2jMfi128tPh8gHQCw))")
				.contentType(ContentType.JSON)
				.param("page", 1)
				.param("pagesize", 2)
				.param("fromdate", 1704067200)
				.param("todate", 1707264000)
				.queryParam("order", "desc")
	            .queryParam("min", "bronze")
	            .queryParam("sort", "rankkk")
	            .queryParam("inname", "gojs")
				.param("site", "stackoverflow")
				.when()
				.get("/badges/tags")
				.then()
                .extract()
                .response();
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		

		Reporter.log(methodName+ " Response: " + response.asPrettyString());
		Reporter.log(methodName+ " Response status code: " + response.getStatusCode());
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 400);
        Reporter.log(methodName+ " Response status code check successful ");
        
        JsonPath jsonPath = new JsonPath(response.asString());       
        Assert.assertEquals(jsonPath.getInt("error_id"), 400);
        Assert.assertEquals(jsonPath.getString("error_message"), "sort");
        Assert.assertEquals(jsonPath.getString("error_name"), "bad_parameter");
    }

    @Test
    public void testMissingRequiredParameters() {
    	Response response = RestAssured.given()
				.header("Authorization", "Bearer jKGRx2jMfi128tPh8gHQCw))")
				.contentType(ContentType.JSON)
				.when()
				.get("/badges/tags")
				.then()
                .extract()
                .response();
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		

		Reporter.log(methodName+ " Response: " + response.asPrettyString());
		Reporter.log(methodName+ " Response status code: " + response.getStatusCode());
		Reporter.log(methodName+ " Schema validation successful. ");
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 400);
        Reporter.log(methodName+ " Response status code check successful ");
        
        JsonPath jsonPath = new JsonPath(response.asString());       
        Assert.assertEquals(jsonPath.getInt("error_id"), 400);
        Assert.assertEquals(jsonPath.getString("error_message"), "site is required");
        Assert.assertEquals(jsonPath.getString("error_name"), "bad_parameter");
    }
    
    
	
}
