package base.response;

import base.model.Comment;

public class CommentResponse extends ServerResponse {
    private final Comment comment;

    public CommentResponse(Comment comment) {
        this.comment = comment;
    }

    //region getter and setter

    public Comment getComment() {
        return comment;
    }

    //endregion

    @Override
    public Byte getResponseCode() {
        return ResponseType.COMMENT_RESPONSE;
    }

    @Override
    public Class<?> getResponseType() {
        return CommentResponse.class;
    }
}
