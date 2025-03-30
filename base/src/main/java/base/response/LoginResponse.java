package base.response;

import base.model.User;

import java.time.LocalDate;

public class LoginResponse extends ServerResponse {
    private final User user;

    public LoginResponse(User user) {
        this.user = user;
    }

    //region getter and setter

    public User getUser() {
        return user;
    }

    //endregion

    @Override
    public Byte getResponseCode() {
        return ResponseType.LOGIN_RESPONSE;
    }

    @Override
    public Class<?> getResponseType() {
        return LoginResponse.class;
    }
}
