package base.request;

public class ConnectionRequest extends ClientRequest {

    @Override
    public Byte getRequestCode() {
        return RequestType.CONNECTION_REQUEST;
    }

    @Override
    public Class<?> getResponseType() {
        return ConnectionRequest.class;
    }
}
