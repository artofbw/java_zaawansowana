package pl.jazapp.app;

import pl.jazapp.app.webapp.register.RegisterRequest;

public class User {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String birthday;
    private String password;

    public User(RegisterRequest registerRequest) {
        this.username = registerRequest.getUsername();
        this.email = registerRequest.getEmail();
        this.firstName = registerRequest.getFirstName();
        this.lastName = registerRequest.getLastName();
        this.birthday = registerRequest.getBirthday();
        this.password = registerRequest.getPassword();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return capitalize(firstName);
    }

    public void setFirstName(String firstName) {
        this.firstName = capitalize(firstName);
    }

    public String getLastName() {
        return capitalize(lastName);
    }

    public void setLastName(String lastName) {
        this.lastName = capitalize(lastName);
    }

    public static String capitalize(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static Boolean passwordCheck(String password, String passwordCheck) {
        return password.equals(passwordCheck);
    }

    public String getFullName() {
        return String.format("%s %s", this.getFirstName(), this.getLastName());
    }
}
