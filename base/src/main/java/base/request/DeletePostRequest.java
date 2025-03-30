package base.request;

import base.response.DeletePostResponse;

public class DeletePostRequest extends ClientRequest {
    private final int postId;

    public DeletePostRequest(int postId) {
        this.postId = postId;
    }

    //region getter and setter

    public int getPostId() {
        return postId;
    }

    //endregion

    @Override
    public Byte getRequestCode() {
        return RequestType.DELETE_POST_REQUEST;
    }

    @Override
    public Class<?> getResponseType() {
        return DeletePostRequest.class;
    }
}
