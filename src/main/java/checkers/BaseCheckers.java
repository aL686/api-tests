package checkers;

import io.qameta.allure.Step;
import org.hamcrest.Matchers;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class BaseCheckers {

    @Step("Проверка, что статус код в ответе {1}")
    public void checkStatusCode(int actualStatusCode, int expectedStatusCode) {
        assertThat("HTTP response status code", actualStatusCode, Matchers.is(expectedStatusCode));
    }

    @Step("Проверка, что список {1} не пустой")
    public void checkNotEmptyList(List<?> list) {
        assertThat("Список пустой", list.isEmpty(), Matchers.is(Matchers.not(true)));
    }
}
