package base.response;

import base.model.Post;

import java.util.List;

public class GetPostResponse extends ServerResponse {
    private final List<Post> posts;

    public GetPostResponse(List<Post> posts) {
        this.posts = posts;
    }

    //region getter and setter

    public List<Post> getPosts() {
        return posts;
    }

    //endregion

    @Override
    public Byte getResponseCode() {
        return ResponseType.GET_POST_RESPONSE;
    }

    @Override
    public Class<?> getResponseType() {
        return GetPostResponse.class;
    }
}
