package base.response;

public class TestResponse extends ServerResponse {
    private String text;

    public TestResponse(String text) {
        this.text = text;
    }

    //region getter and setter

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    //endregion

    @Override
    public Byte getResponseCode() {
        return ResponseType.TEST_RESPONSE;
    }

    @Override
    public Class<?> getResponseType() {
        return TestResponse.class;
    }
}
