package base.request;

public abstract class ClientRequest {
    public abstract Byte getRequestCode();
    public abstract Class<?> getResponseType();
}
