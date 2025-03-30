package base.response;

import base.model.PostDetails;

public class ThumbResponse extends ServerResponse {
    private final PostDetails postDetails;

    public ThumbResponse(PostDetails postDetails) {
        this.postDetails = postDetails;
    }

    //region getter and setter

    public PostDetails getPostDetails() {
        return postDetails;
    }

    //endregion

    @Override
    public Byte getResponseCode() {
        return ResponseType.THUMB_RESPONSE;
    }

    @Override
    public Class<?> getResponseType() {
        return ThumbResponse.class;
    }
}
