package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class WeatherAPITest {
    @Test
    public void testWeatherAPI() {
        RestAssured.baseURI = "https://api.open-meteo.com/v1/forecast";

        Response response = given()
                .queryParam("latitude", "52.52")
                .queryParam("longitude", "13.41")
                .queryParam("current", "temperature_2m,wind_speed_10m")
                .queryParam("hourly", "temperature_2m,relative_humidity_2m,wind_speed_10m")
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("latitude", equalTo(52.52f))
                .body("longitude", equalTo(13.419998F))
                .extract()
                .response();

        Assert.assertNotNull(response.jsonPath().get("current.temperature_2m"));
        Assert.assertNotNull(response.jsonPath().get("current.wind_speed_10m"));
    }
}