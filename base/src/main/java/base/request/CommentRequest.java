package base.request;

import base.model.Comment;

public class CommentRequest extends ClientRequest{
    private final Comment comment;

    public CommentRequest(Comment comment) {
        this.comment = comment;
    }

    //region getter and setter

    public Comment getComment() {
        return comment;
    }

    //endregion

    @Override
    public Byte getRequestCode(){
        return RequestType.COMMENT_REQUEST;
    }

    @Override
    public Class<?> getResponseType() {
        return CommentRequest.class;
    }
}
