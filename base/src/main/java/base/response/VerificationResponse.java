package base.response;

public class VerificationResponse extends ServerResponse {
    @Override
    public Byte getResponseCode() {
        return ResponseType.VERIFICATION_RESPONSE;
    }

    @Override
    public Class<?> getResponseType() {
        return VerificationResponse.class;
    }
}
