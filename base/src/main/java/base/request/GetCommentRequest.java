package base.request;

import base.response.GetCommentResponse;

public class GetCommentRequest extends ClientRequest{
    private final int fatherId;
    private final int lastId;
    private int count;

    public GetCommentRequest(int fatherId, int lastId,int count) {
        this.fatherId = fatherId;
        this.lastId = lastId;
        this.count = count;
    }

    //region getter and setter

    public int getFatherId() {
        return fatherId;
    }

    public int getLastId() {
        return lastId;
    }

    public int getCount() {
        return count;
    }

    //endregion

    @Override
    public Byte getRequestCode(){
        return RequestType.GET_COMMENT_REQUEST;
    }

    @Override
    public Class<?> getResponseType() {
        return GetCommentRequest.class;
    }
}
