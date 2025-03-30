package server.business;

import base.request.*;
import base.response.ServerResponse;
import server.business.responders.*;

public class Dispatcher {
    private final Responser[] responsers = new Responser[] {
            new TestResponser(),
            new LoginResponser(),
            new VerificationResponser(),
            new RegistrationResponser(),
            new PostResponser(),
            new DeletePostResponser(),
            new CommentResponser(),
            new DeleteCommentResponser(),
            new ThumbResponser(),
            new ConnectionResponser(),
            new GetPostResponser(),
            new GetCommentResponser(),
    };

    public ServerResponse dispatch(ClientRequest request){

        var specific = request.getResponseType().cast(request);
        return responsers[request.getRequestCode()].respond(specific);
    }
}
