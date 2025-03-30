package base.response;

public class DeleteCommentResponse extends ServerResponse{
    private final int commentId;

    public DeleteCommentResponse(int commentId) {
        this.commentId = commentId;
    }

    //region getter and setter

    public int getCommentId() {
        return commentId;
    }

    //endregion


    @Override
    public Byte getResponseCode() {
        return ResponseType.DELETE_COMMENT_RESPONSE;
    }

    @Override
    public Class<?> getResponseType() {
        return DeleteCommentResponse.class;
    }
}
