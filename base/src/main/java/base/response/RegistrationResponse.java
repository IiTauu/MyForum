package base.response;

public class RegistrationResponse extends ServerResponse {
    @Override
    public Byte getResponseCode() {
        return ResponseType.REGISTRATION_RESPONSE;
    }

    @Override
    public Class<?> getResponseType() {
        return RegistrationResponse.class;
    }
}
