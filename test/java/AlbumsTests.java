import io.qameta.allure.*;
import io.restassured.response.Response;
import model.Album;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.apache.http.HttpStatus.SC_OK;

@Tag("")
@Epic("service-name")
@Feature("/albums")
public class AlbumsTests extends BaseTest {

    private final Integer ALBUM_ID = 1;
    private Album album;

    @BeforeEach
    @Step("Предусловие")
    public void precondition(TestInfo info) {
        if (info.getTags().toString().equals("changeAlbum")) {
            album = getAlbum();
        }
    }

    @AllureId("")
    @Severity(NORMAL)
    @Test
    @DisplayName("Успешное получение всех альбомов")
    public void getAlbumsSuccessTest() {

        Response response = albumsExecutor.getAlbums();
        albumsCheckers.checkStatusCode(response.getStatusCode(), SC_OK);

        List<Album> userList = Arrays.asList(response.as(Album[].class));
        albumsCheckers.checkNotEmptyList(userList);
        albumsCheckers.checkAlbumWithId(userList, ALBUM_ID);
    }

    @Tag("changeAlbum")
    @AllureId("")
    @Severity(NORMAL)
    @Test
    @DisplayName("Успешное изменение альбома")
    public void createPostSuccessTest() {

        album.setTitle("Обновленный альбом");
        Response response = albumsExecutor.changeAlbums(album, album.getId());
        albumsCheckers.checkStatusCode(response.getStatusCode(), SC_OK);
    }

    private Album getAlbum() {
        Response response = albumsExecutor.getAlbums();
        albumsCheckers.checkStatusCode(response.getStatusCode(), SC_OK);
        List<Album> userList = Arrays.asList(response.as(Album[].class));
        return userList.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Альбомы не найдены"));
    }

}
