import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.codeborne.selenide.Configuration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

//разделить методы - джейсон и тест
//использовать массив данных в джейсоне, а не одно значение

public class json extends LoginPage {
    //LoginPage auth = new LoginPage();

    @Before
    public void setup() { //То, что выполняется до запуска тестов (@Test)
        Configuration.baseUrl = "https://auth.rbc.ru/login"; //сокращаем URL
        Configuration.browserSize = "1366x768";
        Configuration.timeout = 6000;
        Configuration.fastSetValue = true;
        //Configuration.clickViaJs;
    }

    @Test
    public void correctLogin() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(new FileReader("data.json"));
        String name = (String) obj.get("USERNAME");
        String password = (String) obj.get("PASSWORD");
        System.out.println("Name: " + name);
        System.out.println("Password: " + password);

        open().enterUsername(name); //auth.
        enterPassword(password);
        submitEnter();
        profileIcon();
        sleep(1000);
        $(".topline__auth__profile__data").shouldHave(ownText("Платный"));
    }

    /*@Test
    public static void main(String[] args) throws InterruptedException {
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(new FileReader("data.json"));
            String name = (String) obj.get("USERNAME");
            String password = (String) obj.get("PASSWORD");
            System.out.println("Name: " + name);
            System.out.println("Password: " + password);

            open("/");
            $(By.name("username")).setValue(name);
            $(By.name("password")).setValue(password);
            //Thread.sleep(5000);
            $(".btn").click();
            $$(".errors").shouldBe(empty);
            $("#materials").shouldHave(text("Материалы"));

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }*/
}
