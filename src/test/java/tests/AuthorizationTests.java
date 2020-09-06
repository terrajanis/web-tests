package tests;

import models.UserInfo;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;

public class AuthorizationTests extends TestBase{

    @Test
    public void loginValidUser () throws IOException {
        UserInfo userInfo = app.getRegistration().readUserFromJson(new File("src/test/resources/user.json"));
        app.getAuthorization().login(userInfo);
        Assert.assertEquals(userInfo.getUsername(), app.getRegistration().getLoggedInUser());
    }

    @Test
    public void loginInvalidUser () {
        SoftAssert softAssert = new SoftAssert();
        app.getAuthorization().openAuthorizationForm();
        app.getAuthorization().fillRegistrationInfo("InvalidUser", "password");
        app.getAuthorization().submitLogIn();
        softAssert.assertTrue(app.isElementPresent(By.className("i-alert")));
        softAssert.assertFalse(app.isElementPresent(By.cssSelector("li.username")));
        softAssert.assertAll();

    }

    @Test
    public void loginWithEmptyCredentials () {
        SoftAssert softAssert = new SoftAssert();
        app.getAuthorization().openAuthorizationForm();
        app.getAuthorization().fillRegistrationInfo("", "");
        app.getAuthorization().submitLogIn();
        softAssert.assertTrue(app.isElementPresent(By.className("i-alert")));
        softAssert.assertFalse(app.isElementPresent(By.cssSelector("li.username")));
        softAssert.assertAll();

    }

    @Test
    public void loginWithoutUsername () {
        SoftAssert softAssert = new SoftAssert();
        app.getAuthorization().openAuthorizationForm();
        app.getAuthorization().fillRegistrationInfo("", "password");
        app.getAuthorization().submitLogIn();
        softAssert.assertTrue(app.isElementPresent(By.className("i-alert")));
        softAssert.assertFalse(app.isElementPresent(By.cssSelector("li.username")));
        softAssert.assertAll();

    }

    @Test
    public void loginWithoutPassword () {
        SoftAssert softAssert = new SoftAssert();
        app.getAuthorization().openAuthorizationForm();
        app.getAuthorization().fillRegistrationInfo("username", "");
        app.getAuthorization().submitLogIn();
        softAssert.assertTrue(app.isElementPresent(By.className("i-alert")));
        softAssert.assertTrue(app.isElementPresent(By.id("user_login")));
        softAssert.assertFalse(app.isElementPresent(By.cssSelector("li.username")));
        softAssert.assertAll();

    }
}
