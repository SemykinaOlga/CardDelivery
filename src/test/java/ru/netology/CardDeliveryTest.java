package ru.netology;


import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @Test
    void shouldSuccessfulRequest() {
        open("http://localhost:9999");
        String dateToInput = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $(".form");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateToInput);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79803370000");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=notification]").waitUntil(visible, 15000);
        $(".notification__content").shouldHave(exactText("Встреча успешно забронирована на " + (dateToInput)));
    }

    @Test
    void shouldSuccessfulRequestWithoutPhone() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(withText("Поле обязательно для заполнения"));

    }

}
