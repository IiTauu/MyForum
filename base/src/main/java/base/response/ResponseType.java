package base.response;

public class ResponseType {
    public static final Byte TEST_RESPONSE = 0;
    public static final Byte LOGIN_RESPONSE = 1;
    public static final Byte VERIFICATION_RESPONSE = 2;
    public static final Byte REGISTRATION_RESPONSE = 3;
    public static final Byte POST_RESPONSE = 4;
    public static final Byte DELETE_POST_RESPONSE = 5;
    public static final Byte COMMENT_RESPONSE = 6;
    public static final Byte DELETE_COMMENT_RESPONSE = 7;
    public static final Byte THUMB_RESPONSE = 8;
    public static final Byte CONNECTION_RESPONSE = 9;
    public static final Byte GET_POST_RESPONSE = 10;
    public static final Byte GET_COMMENT_RESPONSE = 11;

    private static final Class<? extends ServerResponse>[] typeList = new Class[]{
            TestResponse.class,
            LoginResponse.class,
            VerificationResponse.class,
            RegistrationResponse.class,
            PostResponse.class,
            DeletePostResponse.class,
            CommentResponse.class,
            DeleteCommentResponse.class,
            ThumbResponse.class,
            ConnectionResponse.class,
            GetPostResponse.class,
            GetCommentResponse.class,
    };

    public static Class<? extends ServerResponse> getResponseType(Byte responseCode) {
        return typeList[responseCode];
    }
}
