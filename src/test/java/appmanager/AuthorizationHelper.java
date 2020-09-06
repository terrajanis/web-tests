package appmanager;

import models.UserInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthorizationHelper extends HelperBase {

    public AuthorizationHelper(WebDriver wd) {
        super(wd);
    }

    public void openAuthorizationForm() {
        click(By.className("i-lock"));
    }

    public void fillRegistrationInfo(UserInfo userInfo) {
        type(By.id("usrlog2"), userInfo.getUsername());
        type(By.id("usrpass2"), userInfo.getPassword());
        click(By.name("save"));
    }

    public void fillRegistrationInfo(String username, String password) {
        type(By.id("usrlog2"),username);
        type(By.id("usrpass2"), password);
        click(By.name("save"));
    }

    public void submitLogIn() {
        click(By.cssSelector("form > button.btn-plain"));
    }

    public void login(UserInfo userInfo)  {
        openAuthorizationForm();
        fillRegistrationInfo(userInfo);
        submitLogIn();
    }
}
