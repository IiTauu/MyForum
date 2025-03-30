package base.request;

import base.response.GetPostResponse;

public class GetPostRequest extends ClientRequest {
    private final int lastId;
    private int count;

    public GetPostRequest(int lastId,int count) {
        this.lastId = lastId;
        this.count = count;
    }

    //region getter and setter

    public int getLastId() {
        return lastId;
    }

    public int getCount() {
        return count;
    }

//endregion

    @Override
    public Byte getRequestCode() {
        return RequestType.GET_POST_REQUEST;
    }

    @Override
    public Class<?> getResponseType() {
        return GetPostRequest.class;
    }
}
