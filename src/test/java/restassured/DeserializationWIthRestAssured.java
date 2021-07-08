package restassured;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import utils.Constance;

import java.io.File;

import static utils.Constance.*;

public class DeserializationWIthRestAssured {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";
    }

    @Test
    public void test1() {

//        File petJson = new File("pet.json");
//        RestAssured.given().header(ACCEPT, APPLICATION_JSON)
//                .header(CONTENT_TYPE, APPLICATION_JSON)
//                .body(petJson)
//                .when().put()
//                .then().assertThat().statusCode(200)
//                .and().assertThat().body("name", Matchers.equalTo("susu"))
//                .and().assertThat().body("status", Matchers.is("waiting"))
//                .body("category.name", Matchers.is("string"));
//    }
//}
        File petJsonFile = new File("pet.json");
        RestAssured.given().header(ACCEPT, APPLICATION_JSON)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .body(petJsonFile)
                .when().put()
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .assertThat().body("name", Matchers.equalTo("susu"))
                .and().assertThat().body("status", Matchers.is("waiting"))
                .body("category.name", Matchers.is("string"));
    }
}
