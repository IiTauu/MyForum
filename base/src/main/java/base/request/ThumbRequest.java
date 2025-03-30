package base.request;

import base.response.ThumbResponse;

public class ThumbRequest extends ClientRequest {
    private final int postId;
    private boolean cancelRequest = false;

    public ThumbRequest(int postId) {
        this.postId = postId;
    }

    //region getter and setter

    public int getPostId() {
        return postId;
    }

    public boolean isCancelRequest() {
        return cancelRequest;
    }

    public void setCancelRequest(boolean cancelRequest) {
        this.cancelRequest = cancelRequest;
    }

    //endregion

    @Override
    public Byte getRequestCode() {
        return RequestType.THUMB_REQUEST;
    }

    @Override
    public Class<?> getResponseType() {
        return ThumbRequest.class;
    }
}
