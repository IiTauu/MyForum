package base.request;

import base.model.Post;

public class PostRequest extends ClientRequest {
    private final Post post;

    public PostRequest(Post post) {
        this.post = post;
    }

    //region getter ans setter

    public Post getPost() {
        return post;
    }

    //endregion

    @Override
    public Byte getRequestCode() {
        return RequestType.POST_REQUEST;
    }

    @Override
    public Class<?> getResponseType() {
        return PostRequest.class;
    }
}
