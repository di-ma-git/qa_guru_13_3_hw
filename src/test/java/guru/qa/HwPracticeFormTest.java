package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class HwPracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void practiceFormTest() {

        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        // Name
        $("#firstName").setValue("Dima");
        $("#lastName").setValue("Maltsev");

        // Email
        $("#userEmail").setValue("dimamaltsev@gmail.com");

        // Gender
        $(byText("Male")).click();

        // Mobile
        $("#userNumber").setValue("9229612908");

        // Date of Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("1");
        $(".react-datepicker__year-select").selectOptionByValue("1999");
        //$(byText("5")).click();
        $(".react-datepicker__day--005").click();

        // Subjects
        $(".subjects-auto-complete__control").click();
        //$("#subjectsInput").setValue("Arts");
        $("#subjectsInput").sendKeys("Arts");
        $("#subjectsInput").pressEnter();

        $(".subjects-auto-complete__control").click();
        $("#subjectsInput").sendKeys("Computer science");
        $("#subjectsInput").pressEnter();

        // Hobbies
        $(byText("Sports")).click();
        $(byText("Reading")).click();

        // Picture
        $("#uploadPicture").uploadFile(new File("src/test/resources/Screenshot_7.png"));

        // Current Address
        $("#currentAddress").setValue("Centralnaya Street");

        // State and City
        $("#state").click();
        //$(byText("Uttar Pradesh")).click();
        $x("//div[text()='Uttar Pradesh']").click();

        $("#city").click();
        $(byText("Lucknow")).click();
        //$x("//div[text()='Lucknow']").click();

        // Submit
        $("#submit").click();

        // Проверка
        $(".modal-content .table-responsive").shouldHave(
                text("Dima"),
                text("Maltsev"),
                text("dimamaltsev@gmail.com"),
                text("9229612908"),
                text("Male"),
                text("05 Feb 1999"),
                text("Arts"),
                text("Computer science"),
                text("Sport"),
                text("Reading"),
                text("Screenshot_7.png"),
                text("Centralnaya Street"),
                text("Uttah Pradesh"),
                text("Lucknow")

        );

        $("#closeLargeModal").click();
    }

}
