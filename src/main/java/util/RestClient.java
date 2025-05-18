package util;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.User;

import static io.restassured.RestAssured.given;
import static util.Config.*;

public class RestClient {
    private final Gson gson = new Gson();

    public RestClient(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }

    @Step("Создание пользователя через API")
    public String createUser(User user) {
        Response r = given()
                .header("Content-Type", "application/json")
                .body(gson.toJson(user))
                .post(REGISTER_ENDPOINT)
                .then().statusCode(200)
                .extract().response();
        return r.path("accessToken");
    }

    @Step("Удаление пользователя через API")
    public void deleteUser(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .delete(USER_ENDPOINT)
                .then().statusCode(202);
    }

    @Step("Логин пользователя через API")
    public String login(User user) {
        Response r = given()
                .header("Content-Type", "application/json")
                .body(gson.toJson(user))
                .post(LOGIN_ENDPOINT)
                .then().statusCode(200)
                .extract().response();
        return r.path("accessToken");
    }
}
