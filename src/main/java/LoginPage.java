import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginPage {

    public LoginPage open() {
        Selenide.open("/");
        return this;
    }

    public LoginPage enterUsername(String text) {
        if(getWebDriver().getPageSource().contains("Зарегистрироваться")==true){
            $(".paywall__auth__tabs__item:nth-child(2)").click();
            $(".paywall__auth__form__row:nth-child(1) .paywall__auth__form__input").val(text);}
        else {
            $(".paywall__auth__form__row:nth-child(1) .paywall__auth__form__input").val(text); }

        return this;
    }

    public LoginPage enterPassword(String text) {
        $(".js-login-validate > .paywall__auth__form__row:nth-child(2) .paywall__auth__form__input").val(text);
        return this;
    }

    public void submitEnter() {
        $(".paywall__auth__form__submit-wrap > .js-yandex-counter").click(usingJavaScript()); //использоватие функции js для нажатия - быстрее и работает не по координатам как selenium
    }

    public void profileIcon() {

        $(".topline__auth__link").click();
    }
}
