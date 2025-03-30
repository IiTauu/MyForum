package base.response;

public abstract class ServerResponse {
    private boolean success = false;

    //region getter and setter

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    //endregion

    public abstract Byte getResponseCode();
    public abstract Class<?> getResponseType();
}
