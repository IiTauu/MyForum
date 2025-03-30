package base.response;

import base.model.Comment;

import java.util.List;

public class GetCommentResponse extends ServerResponse {
    private final List<Comment> comments;

    public GetCommentResponse(List<Comment> comments) {
        this.comments = comments;
    }

    //region getter and setter

    public List<Comment> getComments() {
        return comments;
    }

    //endregion

    @Override
    public Byte getResponseCode(){
        return ResponseType.GET_COMMENT_RESPONSE;
    }

    @Override
    public Class<?> getResponseType() {
        return GetCommentResponse.class;
    }
}
