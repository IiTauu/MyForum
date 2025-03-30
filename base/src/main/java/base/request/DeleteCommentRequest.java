package base.request;

public class DeleteCommentRequest extends ClientRequest {
    private final int commentId;

    public DeleteCommentRequest(int commentId) {
        this.commentId = commentId;
    }

    //region getter and setter

    public int getCommentId() {
        return commentId;
    }

    //endregion

    @Override
    public Byte getRequestCode() {
        return RequestType.DELETE_COMMENT_REQUEST;
    }

    @Override
    public Class<?> getResponseType() {
        return DeleteCommentRequest.class;
    }
}
