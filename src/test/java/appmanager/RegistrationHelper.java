package appmanager;

import com.google.gson.Gson;
import models.UserInfo;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(WebDriver wd) {
        super(wd);
    }


    public void openRegistrationForm() {
        click(By.className("i-new-user"));
    }

    public void fillRegistrationInfo(UserInfo userInfo) throws InterruptedException {
        Thread.sleep(5000);
        wd.switchTo().frame("modal-register");
        type(By.id("usrlog"), userInfo.getUsername());
        type(By.id("usrpass"), userInfo.getPassword());
        type(By.id("usremail"), userInfo.getEmail());
        click(By.name("user_confirm"));
    }

    public void fillRegistrationInfo(String username, String email, String password) throws InterruptedException {
        Thread.sleep(5000);
        wd.switchTo().frame("modal-register");
        type(By.id("usrpass"), password);
        type(By.id("usrlog"), username);
        type(By.id("usremail"), email);
        click(By.name("user_confirm"));
    }

    public void submit() {
        click(By.cssSelector("button.btn.btn-primary.btn-lg"));
    }


    public void createNewAccount(UserInfo userInfo) throws InterruptedException {
        openRegistrationForm();
        fillRegistrationInfo(userInfo);
        submit();
    }

    public void skipPhone() throws InterruptedException {
        click(By.cssSelector("a.skip"));
        Thread.sleep(5000);
    }

    public String getLoggedInUser() {
        return wd.findElement(By.className("username")).getText();
    }

    public void logout() {
        click(By.id("drop"));
        click(By.linkText("Выход"));
    }

    public UserInfo readUserFromJson(File file) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String userFromJson = "";
        String line = reader.readLine();
        while (line != null) {
            userFromJson += line;
            line = reader.readLine();
        }
        reader.close();

        Gson gson2 = new Gson();
        UserInfo user2 = gson2.fromJson(userFromJson, UserInfo.class);

        return user2;
    }


}




