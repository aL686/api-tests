package checkers;

import io.qameta.allure.Step;
import model.PostDto;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostsCheckers extends BaseCheckers {

    @Step("Проверка, что в списке есть сообщение с id = {1} и полной информацией")
    public void checkPostWithId(List<PostDto> posts, Integer id) {

        PostDto post = posts.stream().filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("В список нет сообщения с id = %s", id)));

        Assertions.assertAll(
                () -> assertNotNull(post.getUserId()),
                () -> assertNotNull(post.getTitle()),
                () -> assertNotNull(post.getBody()));
    }
}
