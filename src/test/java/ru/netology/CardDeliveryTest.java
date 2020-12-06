package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    @Test
    void shouldSuccessfulRequest(){
    open("http://localhost:9999");
    SelenideElement form = $(".form");
    $("[data-test-id=city] input").setValue("Москва");
    $("[data-test-id=name] input").setValue("Иванов Иван");
    $("[data-test-id=phone] input").setValue("+79803370000");
    $("[data-test-id=agreement]").click();
    $(".button").click();
    $(withText("Успешно!")).waitUntil(exist, 15000);
    }

    @Test
    void shouldSuccessfulRequestWithoutPhone(){
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(withText("Поле обязательно для заполнения"));

    }

}
