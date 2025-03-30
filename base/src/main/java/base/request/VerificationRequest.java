package base.request;

import base.response.VerificationResponse;

import java.time.Instant;

public class VerificationRequest extends ClientRequest {
    private final String account;
    private final Instant requestInstant;

    public VerificationRequest(String account) {
        this.account = account;
        this.requestInstant = Instant.now();
    }

    //region getter and setter

    public String getAccount() {
        return account;
    }

    public Instant getRequestDate() {
        return requestInstant;
    }

    //endregion

    @Override
    public Byte getRequestCode(){
        return RequestType.VERIFICATION_REQUEST;
    }

    @Override
    public Class<?> getResponseType() {
        return VerificationRequest.class;
    }
}
