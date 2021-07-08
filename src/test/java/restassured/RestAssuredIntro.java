package restassured;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.PetPojo;

import java.util.List;
import java.util.Map;

public class RestAssuredIntro {

    @Test
    public void Test1() {
        // get https://swapi.dev/api/vehicles

        Response response = RestAssured.given().header("Accept", "application/json")
                .when().get("https://swapi.dev/api/vehicles")
                .then().statusCode(200).log().all().extract().response();

        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        Assert.assertEquals(39, parsedResponse.get("count"));

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) parsedResponse.get("results");
        Assert.assertEquals("Sand Crawler", resultList.get(0).get("name"));

    }

    @Test
    public void Test2() {

        // https://petstore.swagger.io/v2/pet/78931

        Response response = RestAssured.given().header("Accept", "application/json")
                .when().get("https://petstore.swagger.io/v2/pet/78931")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json").extract().response();

        PetPojo parsedResponse = response.as(PetPojo.class);
        Assert.assertEquals(78931, parsedResponse.getId());
        Assert.assertEquals("zeus", parsedResponse.getName());
        Assert.assertEquals("don't touch", parsedResponse.getStatus());

    }
}
