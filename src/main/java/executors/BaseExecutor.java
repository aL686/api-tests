package executors;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseExecutor {

    public static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
    public static final String USERS = "/users";
    public static final String POST = "/posts";
    public static final String ALBUMS = "/albums";
    public static final String PHOTO = "/photos";
    public static final String COMMENTS = "/comments";
    public static final String TODOS = "/todos";


    public static RequestSpecification requestSpecification(ContentType contentType) {
        return given((new RequestSpecBuilder().setRelaxedHTTPSValidation()
                .setAccept(contentType)
                .setContentType(contentType))
                .build())
                .config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames().relaxedHTTPSValidation()))
                .filter(new AllureRestAssured());
    }
}
