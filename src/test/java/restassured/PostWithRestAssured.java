package restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.PetPojo;
import utils.Constance;
import utils.PayloadUtils;

import java.io.File;
import java.util.Locale;

import static utils.Constance.*;

public class PostWithRestAssured {

    @Before
    public void setup(){
        // https://petstore.swagger.io/v2/pet
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";
    }
    @Test
    public void createPetTest1(){

        // https://petstore.swagger.io/v2/pet

        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(PayloadUtils.getPayload(4172, "susu", "on hold"))
                .when().post("https://petstore.swagger.io/v2/pet")
                .then().assertThat().statusCode(200).and()
                .assertThat().contentType("application/json").log().body().extract().response();

        PetPojo petPojo = response.as(PetPojo.class);
        Assert.assertEquals("susu", petPojo.getName());
        Assert.assertEquals("on hold", petPojo.getStatus());
    }

    @Test
    public void createPetTest2(){

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

        // serialization
        PetPojo pet = new PetPojo();
        pet.setName("java pet");
        pet.setId(2000);
        pet.setStatus("done");

        RestAssured.given().header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(pet)
                .when().post()
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json");

    }

    @Test
    public void createPetTest3(){

        File petJsonFile = new File("pet.json");

        RestAssured.given().header(ACCEPT, APPLICATION_JSON)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .body(petJsonFile)
                .when().post()
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType(APPLICATION_JSON);
    }
}
