package tests;

import org.testng.asserts.SoftAssert;
import providers.UserDataProvider;
import models.UserInfo;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class RegistrationTests extends TestBase {


    @Test(dataProvider = "generateInfo", dataProviderClass = UserDataProvider.class)
    public void register(UserInfo userInfo) throws InterruptedException {

        app.getRegistration().createNewAccount(userInfo);
        app.getRegistration().skipPhone();
        Assert.assertEquals(userInfo.getUsername(), app.getRegistration().getLoggedInUser());
        app.getRegistration().logout();

    }

    @Test (dependsOnMethods = {"register"})
    public void registerWithTheSameUser() throws InterruptedException, IOException {
        SoftAssert softAssert = new SoftAssert();
        UserInfo userInfo = app.getRegistration().readUserFromJson(new File("src/test/resources/user.json"));
        app.getRegistration().createNewAccount(userInfo);
        softAssert.assertTrue(app.isElementPresent(By.cssSelector("input[data-content-alert=\"Этот логин уже занят. Постарайтесь быть более оригинальным :)\"]")));
        softAssert.assertFalse(app.isElementPresent(By.cssSelector("li.username")));
        softAssert.assertAll();

    }

    @Test
    public void registerWithEmptyUsername() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        app.getRegistration().openRegistrationForm();
        app.getRegistration().fillRegistrationInfo("", "email@gmail.com","qwerty");
        softAssert.assertTrue(app.isElementPresent(By.cssSelector("#usrlog[data-content-alert=\"Поле обязательное для заполнения\"]")));
        softAssert.assertFalse(app.isElementPresent(By.cssSelector("li.username")));
        softAssert.assertAll();

    }

    @Test
    public void registerWithEmptyEmail() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        app.getRegistration().openRegistrationForm();
        app.getRegistration().fillRegistrationInfo("awesomeUsername3400", "","qwerty");
        softAssert.assertTrue(app.isElementPresent(By.cssSelector("input#usremail.form-control.alert")));
        softAssert.assertFalse(app.isElementPresent(By.cssSelector("li.username")));
        softAssert.assertAll();

    }

    @Test
    public void registerWithEmptyPassword() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        app.getRegistration().openRegistrationForm();
        app.getRegistration().fillRegistrationInfo("awesomeUsername3400", "email@gmail.com","");
        softAssert.assertTrue(app.isElementPresent(By.cssSelector("#usrpass[data-content-alert=\"Поле обязательное для заполнения\"]")));
        softAssert.assertFalse(app.isElementPresent(By.cssSelector("li.username")));
        softAssert.assertAll();

    }

    @Test
    public void registerWithIncorrectEmail() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        app.getRegistration().openRegistrationForm();
        app.getRegistration().fillRegistrationInfo("awesomeUsername3400", "email","qwerty");
        softAssert.assertTrue(app.isElementPresent(By.cssSelector("input#usremail.form-control.alert")));
        softAssert.assertFalse(app.isElementPresent(By.cssSelector("li.username")));
        softAssert.assertAll();

    }

    @Test
    public void registerWithShortUsername() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        app.getRegistration().openRegistrationForm();
        app.getRegistration().fillRegistrationInfo("us", "email@gmail.com","qwerty");
        softAssert.assertTrue(app.isElementPresent(By.cssSelector("#usrlog[data-content=\"Длина логина не может быть меньше 3 символов\"]")));
        softAssert.assertFalse(app.isElementPresent(By.cssSelector("li.username")));
        softAssert.assertAll();

    }

    @Test
    public void registerWithShortPassword() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        app.getRegistration().openRegistrationForm();
        app.getRegistration().fillRegistrationInfo("awesomeUsername3400", "email@gmail.com","qwer");
        softAssert.assertTrue(app.isElementPresent(By.cssSelector("#usrpass[data-content-alert=\"Длина пароля не может быть меньше 5 символов\"]")));
        softAssert.assertFalse(app.isElementPresent(By.cssSelector("li.username")));
        softAssert.assertAll();
    }


}
