package executors;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.PostDto;

import static io.restassured.http.ContentType.JSON;


/**
 * Ресурс /posts
 */
public class PostsExecutor extends BaseExecutor {

    @Step("Запрос - Получение сообщений")
    public Response getPosts() {
        return requestSpecification(JSON)
                .when()
                .baseUri(BASE_URL)
                .log().all()
                .get(POST)
                .then()
                .log().all().extract().response();
    }

    @Step("Запрос - Создание сообщения")
    public Response createPosts(PostDto requestBody) {
        return requestSpecification(JSON)
                .when()
                .baseUri(BASE_URL)
                .body(requestBody)
                .log().all()
                .post(POST)
                .then()
                .log().all().extract().response();
    }
}
