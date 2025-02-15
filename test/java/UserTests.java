import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.restassured.response.Response;
import model.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.apache.http.HttpStatus.SC_OK;

@Tag("")
@Epic("service-name")
@Feature("/users")
public class UserTests extends BaseTest {

    private final String USER_ID = "1";

    @Test
    @AllureId("")
    @Severity(NORMAL)
    @DisplayName("Успешное получение всех пользователей")
    public void getUsersSuccessTest() {
        Response response = usersExecutor.getUsers();
        usersCheckers.checkStatusCode(response.getStatusCode(), SC_OK);

        List<UserDto> users = Arrays.asList(response.as(UserDto[].class));
        usersCheckers.checkNotEmptyList(users);
    }

    @Test
    @AllureId("")
    @Severity(NORMAL)
    @DisplayName("Успешное получение пользователя по id")
    public void getUserByIdSuccessTest() {
        Response response = usersExecutor.getUserById(USER_ID);
        usersCheckers.checkStatusCode(response.getStatusCode(), SC_OK);

        UserDto users = response.as(UserDto.class);
        //TODO Проверка тела ответа
    }

    @Test
    @AllureId("")
    @Severity(NORMAL)
    @DisplayName("Успешное удаление пользователя по id")
    public void deleteSuccessTest() {
        Response response = usersExecutor.deleteUserById(USER_ID);
        usersCheckers.checkStatusCode(response.getStatusCode(), SC_OK);
    }
}
