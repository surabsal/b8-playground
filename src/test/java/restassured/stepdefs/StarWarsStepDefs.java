package restassured.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import utils.Constance;

import static utils.Constance.ACCEPT;
import static utils.Constance.APPLICATION_JSON;

public class StarWarsStepDefs {

    Response response;

    @Given("I have a valid Start Wars characters endpoint")
    public void i_have_a_valid_start_wars_characters_endpoint() {
        RestAssured.baseURI = "https://swapi.dev";
        RestAssured.basePath = "api/people";

    }

    @When("I send GET request")
    public void i_send_get_request() {
        response = RestAssured.given().header(ACCEPT, APPLICATION_JSON)
                .when().get();

    }

    @Then("I should get {int} status code")
    public void i_should_get_status_code(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);

    }

    @Then("I should respond in JSON format")
    public void i_should_respond_in_json_format() {
        response.then().assertThat().contentType(APPLICATION_JSON);

    }

    @Then("I must get Luke Skywalker in the response")
    public void i_must_get_luke_skywalker_in_the_response() {
        response.then().assertThat().body("results[0].name", Matchers.equalTo("Luke Skywalker"));

        response.then().body("results.name", Matchers.hasItemInArray("Luke Skywalker"));


    }
}
