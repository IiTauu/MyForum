package server.repository.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserData extends ForumData {
    private String username;
    private String account;
    private LocalDateTime lastLoginTime;
    private LocalDate registrationDate;

    public UserData(int id, String username, String account, LocalDateTime lastLoginTime, LocalDate registrationDate) {
        super(id);
        this.username = username;
        this.account = account;
        this.lastLoginTime = lastLoginTime;
        this.registrationDate = registrationDate;
    }

    //region getter and setter

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    //endregion
}
