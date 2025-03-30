package server.repository.model;

import javax.xml.crypto.Data;

public class RegisterData extends ForumData {
    private String account;
    private String password;
    private int state;

    public RegisterData(int id, String account, String password, int state) {
        super(id);
        this.account = account;
        this.password = password;
        this.state = state;
    }

    //region getter and setter

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    //endregion
}
