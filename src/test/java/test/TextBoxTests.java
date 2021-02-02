package test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests {
    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void dataAppearsTestForPracticeForm() {

        Data data = new Data(
                "Oleg", "Koll",
                "oleg@bk.ru", "2312312321",
                "Chemistry", "usa",
                "NCR", "Noida");

        open("https://demoqa.com/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));

        $("#firstName").setValue(data.getFirstName());
        $("#lastName").setValue(data.getLastName());
        $("#userEmail").setValue(data.getUserEmail());
        $("[class='custom-control custom-radio custom-control-inline'], [id ='gender-radio-1']").click();
        $("#userNumber").setValue(data.getUserNumber());

        //date
        $("#dateOfBirthInput").click();
        $("[class='react-datepicker__day react-datepicker__day--024 react-datepicker__day--selected'], " +
                "[aria-label='Choose Wednesday, February 24th, 2021']").click();

        $("#subjectsInput").setValue(data.getSubjectsInput()).pressEnter();
        //$("#subjectsInput").value("music").pressEnter();
        $("[class='custom-control custom-checkbox custom-control-inline'], [id ='hobbies-checkbox-1']").click();

        //pict
        $("#uploadPicture").uploadFile(new File("src/test/java/docs/1.jpg"));

        $("#currentAddress").setValue(data.getCurrentAddress());
        $("#react-select-3-input").setValue(data.getState()).pressEnter();
        $("#react-select-4-input").setValue(data.getCity()).pressEnter();

        $("#submit").click();

        //form testing
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $$(".table-responsive tr").filterBy(text("Student name")).shouldHave(texts(data.getFirstName() + " " + data.getLastName()));
        //добавлю код

    }
}
