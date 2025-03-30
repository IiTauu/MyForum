package base.request;

import base.response.RegistrationResponse;

import java.time.LocalDate;

public class RegistrationRequest extends ClientRequest {
    private final String account;

    private final String password;

    private final String username;

    private final String verificationCode;

    private final LocalDate registrationDate;

    public RegistrationRequest(String account, String password, String verificationCode, String username) {
        this.account = account;
        this.password = password;
        this.verificationCode = verificationCode;
        this.username = username;
        this.registrationDate = LocalDate.now();
    }

    //region getter and setter

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String getUsername() {
        return username;
    }

    //endregion

    @Override
    public Byte getRequestCode() {
        return RequestType.REGISTRATION_REQUEST;
    }

    @Override
    public Class<?> getResponseType() {
        return RegistrationRequest.class;
    }
}
