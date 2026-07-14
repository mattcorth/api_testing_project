package com.sparta.simplerestassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SinglePostCodeTest {

    private static Response response;

    @BeforeAll
    static void setup() {
        response = RestAssured
                .given()
                .when()
                .get("https://api.postcodes.io/postcodes/ec2y5as")
                .then()
                .log().all()
                .extract().response();
    }

    @Test
    @DisplayName("Status code 200 returned")
    void testStatusCode200() {
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    @DisplayName("The server name in the headers is cloudflare")
    void testServerNameInHeadersIsCloudflare() {
        Assertions.assertEquals("cloudflare", response.getHeader("Server"));
    }

    @Test
    @DisplayName("Correct postcode returned in response")
    void testCorrectPostcodeReturnedInResponse() {
        Assertions.assertEquals("EC2Y 5AS", response.jsonPath().getString("result.postcode"));
    }
}
