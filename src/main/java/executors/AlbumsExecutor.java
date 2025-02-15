package executors;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Album;

import static io.restassured.http.ContentType.JSON;

/**
 * Ресурс /albums
 */
public class AlbumsExecutor extends BaseExecutor {

    @Step("Запрос - Получение альбомов")
    public Response getAlbums() {
        return requestSpecification(JSON)
                .when()
                .baseUri(BASE_URL)
                .log().all()
                .get(ALBUMS)
                .then()
                .log().all().extract().response();
    }

    @Step("Запрос - Изменение альбома")
    public Response changeAlbums(Album requestBody, Integer id) {
        return requestSpecification(JSON)
                .when()
                .baseUri(BASE_URL)
                .pathParam("id", id)
                .body(requestBody)
                .log().all()
                .put(ALBUMS + "/{id}")
                .then()
                .log().all().extract().response();
    }
}
