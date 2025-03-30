package base.response;

public class DeletePostResponse extends ServerResponse {
    private final int postId;

    public DeletePostResponse(int postId) {
        this.postId = postId;
    }

    //region getter and setter

    public int getPostId() {
        return postId;
    }

    //endregion

    @Override
    public Byte getResponseCode() {
        return ResponseType.DELETE_POST_RESPONSE;
    }

    @Override
    public Class<?> getResponseType() {
        return DeletePostResponse.class;
    }
}
