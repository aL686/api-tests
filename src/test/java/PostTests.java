import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.restassured.response.Response;
import model.PostDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.apache.http.HttpStatus.SC_OK;

@Tag("")
@Epic("service-name")
@Feature("/posts")
public class PostTests extends BaseTest {

    private final Integer POST_ID = 1;

    @AllureId("")
    @Severity(NORMAL)
    @Test
    @DisplayName("Успешное получение всех сообщений")
    public void getPostsSuccessTest() {

        Response response = postsExecutor.getPosts();
        postsCheckers.checkStatusCode(response.getStatusCode(), SC_OK);

        List<PostDto> userList = Arrays.asList(response.as(PostDto[].class));
        postsCheckers.checkNotEmptyList(userList);
        postsCheckers.checkPostWithId(userList, POST_ID);
    }

    @AllureId("")
    @Severity(NORMAL)
    @Test
    @DisplayName("Успешное создание нового сообщения")
    public void createPostSuccessTest() {

        PostDto request = provider.buildPost();

        Response response = postsExecutor.createPosts(request);
        postsCheckers.checkStatusCode(response.getStatusCode(), SC_OK);

    }
}
