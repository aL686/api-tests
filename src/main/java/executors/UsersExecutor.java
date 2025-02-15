package executors;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.http.ContentType.JSON;

/**
 * Ресурс /users
 */

public class UsersExecutor extends BaseExecutor {

    @Step("Запрос - Получение пользователей")
    public Response getUsers() {
        return requestSpecification(JSON)
                .when()
                .baseUri(BASE_URL)
                .log().all()
                .get(USERS)
                .then()
                .log().all().extract().response();
    }

    @Step("Запрос - Получение пользователя с id = {0}")
    public Response getUserById(String userId) {
        return requestSpecification(JSON)
                .when()
                .baseUri(BASE_URL)
                .pathParam("userId", userId)
                .log().all()
                .get(USERS + "/{userId}")
                .then()
                .log().all().extract().response();
    }

    @Step("Запрос - Удаление пользователя с id = {0}")
    public Response deleteUserById(String userId) {
        return requestSpecification(JSON)
                .when()
                .baseUri(BASE_URL)
                .pathParam("userId", userId)
                .log().all()
                .delete(USERS + "/{userId}")
                .then()
                .log().all().extract().response();
    }


}
