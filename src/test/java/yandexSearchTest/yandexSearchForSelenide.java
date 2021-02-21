package yandexSearchTest;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class yandexSearchForSelenide {

    @Test
    void searchSelenideWithYandex() {
        open("https://yandex.ru/");
        $x("//input[@id='text']").setValue("Selenide").pressEnter();
        $("div #search-result").shouldHave(text("Selenide: лаконичные и стабильные UI тесты на Java"));
    }
}
