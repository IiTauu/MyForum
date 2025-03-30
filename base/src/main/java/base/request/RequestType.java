package base.request;

public class RequestType {
    public static final Byte TEST_REQUEST = 0;
    public static final Byte LOGIN_REQUEST = 1;
    public static final Byte VERIFICATION_REQUEST = 2;
    public static final Byte REGISTRATION_REQUEST = 3;
    public static final Byte POST_REQUEST = 4;
    public static final Byte DELETE_POST_REQUEST = 5;
    public static final Byte COMMENT_REQUEST = 6;
    public static final Byte DELETE_COMMENT_REQUEST = 7;
    public static final Byte THUMB_REQUEST = 8;
    public static final Byte CONNECTION_REQUEST = 9;
    public static final Byte GET_POST_REQUEST = 10;
    public static final Byte GET_COMMENT_REQUEST = 11;

    private static final Class<? extends ClientRequest>[] typeList = new Class[]{
            TestRequest.class,
            LoginRequest.class,
            VerificationRequest.class,
            RegistrationRequest.class,
            PostRequest.class,
            DeletePostRequest.class,
            CommentRequest.class,
            DeleteCommentRequest.class,
            ThumbRequest.class,
            ConnectionRequest.class,
            GetPostRequest.class,
            GetCommentRequest.class,
    };

    public static Class<? extends ClientRequest> getRequestType(Byte requestCode) {
        return typeList[requestCode];
    }
}
