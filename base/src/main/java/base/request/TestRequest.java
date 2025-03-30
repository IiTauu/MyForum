package base.request;

import base.response.TestResponse;

public class TestRequest extends ClientRequest {
    private String text;

    public TestRequest(String text) {
        super();
        this.text = text;
    }

    //region getter and setter

    public String getText() {
        return text;
    }

    //endregion

    @Override
    public Byte getRequestCode() {
        return RequestType.TEST_REQUEST;
    }

    @Override
    public Class<?> getResponseType() {
        return TestRequest.class;
    }
}
