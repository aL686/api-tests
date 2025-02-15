package checkers;

import io.qameta.allure.Step;
import model.Album;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AlbumsCheckers extends BaseCheckers {

    @Step("Проверка, что в списке есть альбом с id = {1} и полной информацией")
    public void checkAlbumWithId(List<Album> albums, Integer id) {

        Album album = albums.stream().filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("В список нет альбом с id = %s", id)));

        Assertions.assertAll(
                () -> assertNotNull(album.getTitle()),
                () -> assertNotNull(album.getUserId()));
    }
}
