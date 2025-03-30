package base.request;

import base.response.LoginResponse;

import java.time.LocalDateTime;

public class LoginRequest extends ClientRequest {
    private final String account;
    private final String password;
    private final LocalDateTime loginTime;

    public LoginRequest(String account, String password) {
        this.account = account;
        this.password = password;
        this.loginTime = LocalDateTime.now();
    }

    //region getter and setter

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    //endregion

    @Override
    public Byte getRequestCode() {
        return RequestType.LOGIN_REQUEST;
    }

    @Override
    public Class<?> getResponseType() {
        return LoginRequest.class;
    }
}
