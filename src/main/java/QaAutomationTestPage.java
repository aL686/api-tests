import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class QaAutomationTestPage {
    @FindBy(using = "formName")
    SelenideElement formPage = $x("//form");


    @FindBy(using = "emailFieldName")
    SelenideElement emailField = $x("//input[@type='email']");
    ElementsCollection radioButtonList = $$x("//*[contains(@class,'RadioRadio')]");
    SelenideElement dateField = $x("//input[@type='date']");
    ElementsCollection dateFieldList = $$x("//div[contains(@class,'DateDateInputs')]//input[contains(@class,'TextinputPaperinputInput')]");
    SelenideElement questionField = $x("//*[contains(@data-params,'Check that this question is mandatory, than fill it with name of current month')]//input");

    SelenideElement sendButton = $x("//div[@role='button']//*[text()='Отправить']");
    SelenideElement clearFormButton = $x("//div[@role='button']//*[text()='Очистить форму']");
    SelenideElement clearFormButtonInAlert = $x("//*[contains(@class,'BottomButtons')]//span[text()='Очистить форму']");
    SelenideElement dropDownButton = $x("//div[@class='quantumWizMenuPaperselectOptionList']");
    ElementsCollection dropDownList = $$x("//span[@class='quantumWizMenuPaperselectContent exportContent']");


    @Step("Открываем страницу: '{page}'")
    public QaAutomationTestPage openPage(String page) {
        open(page);
        return this;
    }

    @Step("Заполняем поле '{fieldName}', значением: '{value}'")
    public QaAutomationTestPage fillField(String fieldName, String value) {
        switch (fieldName) {
            case "Электронная почта": {
                emailField.scrollTo().click();
                emailField.scrollTo().sendKeys(value);
            }
            break;
            case "Дата": {
                if (dateField.isDisplayed()) {
                    dateField.scrollTo().sendKeys(value);
                } else {
                    String day = value.substring(0, 2);
                    String month = value.substring(2, 4);
                    String year = value.substring(4, 8);
                    dateFieldList.get(0).sendKeys(day);
                    dateFieldList.get(1).sendKeys(month);
                    dateFieldList.get(2).sendKeys(year);
                }
            }
            break;
            case "Check that this question is mandatory, than fill it with name of current month": {
                questionField.scrollTo().sendKeys(value);
            }
            break;
        }
        return this;
    }

    @Step("Нажимаем на радио-кнопку '{radioButtonName}'")
    public QaAutomationTestPage clickRadioButton(String radioButtonName) {
        radioButtonList.find(text(radioButtonName)).scrollTo().click();
        return this;
    }

    @Step("В выпадающем списке поля '{fieldName}', выбираем '{value}'")
    public QaAutomationTestPage selectInDropDownList(String fieldName, String value) {
        switch (fieldName) {
            case "Choose the correct answer": {
                dropDownButton.scrollTo().click();
                dropDownList.find(text(value)).scrollTo().click();
            }
            break;
        }
        return this;
    }

    @Step("Нажимаем на кнопку '{buttonName}'")
    public QaAutomationTestPage clickButton(String buttonName) throws InterruptedException {
        getClass().getDeclaredFields();
        switch (buttonName) {
            case "Отправить": {
                sendButton.scrollTo().click();
            }
            break;
            case "Очистить форму": {
                clearFormButton.scrollTo().click();
                Thread.sleep(2000);
                clearFormButtonInAlert.scrollTo().click();
                Thread.sleep(2000);
            }
            break;
        }
        return this;
    }

    @Step("Проверяем, что поле '{fieldName}' содержит ошибки, с текстом: '{errorText}'")
    public QaAutomationTestPage assertErrorsRequiredField(String fieldName, String errorText) {
        while (!$x("//div[text()='" + fieldName + "']//ancestor::div[@role='listitem']//div[text()='" + errorText + "']")
                .isDisplayed()) {
            sendButton.scrollTo().click();
        }
        $x("//div[text()='" + fieldName + "']//ancestor::div[@role='listitem']//div[text()='" + errorText + "']")
                .scrollTo().should(visible);
        return this;
    }

    @Step("Проверяем, что форма не содержит ошибки с текстом 'Это обязательный вопрос.' ")
    public QaAutomationTestPage assertNotErrorsRequiredFieldInPage() throws InterruptedException {
        Thread.sleep(2000);
        Assertions.assertFalse(formPage.getText().contains("Это обязательный вопрос."));
        return this;
    }

    @Step("Проверяем, что поле '{fieldName}' содержит значение: '{expectedValue}' ")
    public QaAutomationTestPage assertThatFieldContainsText(String fieldName, String expectedValue) {
        switch (fieldName) {
            case "Электронная почта": {
                Assertions.assertEquals(expectedValue, emailField.getValue(), "Поле " + fieldName + " содержит: " + emailField.getValue() + ", а ожидали: " + expectedValue);
            }
            break;
            case "Дата": {
                if (dateField.isDisplayed()) {
                    Assertions.assertEquals(expectedValue, dateField.getValue(), "Поле " + fieldName + " содержит: " + emailField.getValue() + ", а ожидали: " + expectedValue);
                } else {
                    String expectedDay = expectedValue.substring(8, 10);
                    String expectedMonth = expectedValue.substring(5, 7);
                    String expectedYear = expectedValue.substring(0, 4);
                    Assertions.assertEquals(expectedDay, dateFieldList.get(0).getValue(), "Поле день " + fieldName + " содержит: " + emailField.getValue() + ", а ожидали: " + expectedDay);
                    Assertions.assertEquals(expectedMonth, dateFieldList.get(1).getValue(), "Поле месяц " + fieldName + " содержит: " + emailField.getValue() + ", а ожидали: " + expectedMonth);
                    Assertions.assertEquals(expectedYear, dateFieldList.get(2).getValue(), "Поле год " + fieldName + " содержит: " + emailField.getValue() + ", а ожидали: " + expectedYear);
                }
            }
            break;
            case "Check that this question is mandatory, than fill it with name of current month": {
                Assertions.assertEquals(expectedValue, questionField.getValue(), "Поле " + fieldName + " содержит: " + emailField.getValue() + ", а ожидали: " + expectedValue);
            }
            break;
            case "Choose the correct answer": {
                Assertions.assertEquals(expectedValue, dropDownButton.getText(), "Поле " + fieldName + " содержит: " + emailField.getText() + ", а ожидали: " + expectedValue);
            }
            break;
        }
        return this;
    }

    @Step("Проверяем, что радио-кнопка '{radioButtonName}' выбрана")
    public QaAutomationTestPage assertSelectedRadioButton(String radioButtonName) {
        Assertions.assertTrue(radioButtonList.find(text(radioButtonName)).getAttribute("class").contains("isChecked"));
        return this;
    }
}
