import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UI")
@Epic("Эпик")
@DisplayName("Описание")
public class QaAutomationTestPageTests extends BrowserSettings {

    public final String page = "https://docs.google.com/forms/d/1dgFKz9AD32hq326wnGaeFw6YccoNuzxwdVPFVb4UeXE/viewform?edit_requested=true";
    public final String eMail = "test4545@mail.ru";

    @AllureId("0000001")
    @Test
    @Description("Проверка формы ")
    public void test() throws InterruptedException {
        new QaAutomationTestPage()
                .openPage(page)

                .fillField("Электронная почта",eMail);

        Thread.sleep(20000);

    }
}
