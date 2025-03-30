package base.response;

public class ConnectionResponse extends ServerResponse {
    @Override
    public Byte getResponseCode() {
        return ResponseType.CONNECTION_RESPONSE;
    }

    @Override
    public Class<?> getResponseType() {
        return ConnectionResponse.class;
    }
}
