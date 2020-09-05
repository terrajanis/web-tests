package models;

public class UserInfo {

    private String email;
    private String username;
    private String password;

    public String getEmail() {
        return email;
    }

    public UserInfo withEmail(String email) {
        this.email = email;
        return  this;
    }

    public String getUsername() {
        return username;
    }

    public UserInfo withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserInfo withPassword(String password) {
        this.password = password;
        return this;
    }


}
