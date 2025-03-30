package base.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
    private String account;
    private String username;
    private int userId;
    private LocalDateTime lastLoginTime;
    private LocalDate registrationDate;

    public User(String account, String username, int userId) {
        this.account = account;
        this.username = username;
        this.userId = userId;
    }

    //region getter and setter

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getRegisterDate() {
        return registrationDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registrationDate = registerDate;
    }

    //endregion
}
