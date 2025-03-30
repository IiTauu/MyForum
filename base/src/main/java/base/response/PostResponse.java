package base.response;

import base.model.Post;

public class PostResponse extends ServerResponse {
    private final Post post;

    public PostResponse(Post post) {
        this.post = post;
    }

    //region getter and setter

    public Post getPost() {
        return post;
    }

    //endregion


    @Override
    public Byte getResponseCode() {
        return ResponseType.POST_RESPONSE;
    }

    @Override
    public Class<?> getResponseType() {
        return PostResponse.class;
    }
}
