package myforum.client.business;

import base.response.ServerResponse;
import myforum.client.business.receivers.*;

import java.io.IOException;

public class Dispatcher {
    private final Receiver[] receiver = new Receiver[] {
            new TestReceiver(),
            new LoginReceiver(),
            new VerificationReceiver(),
            new RegistrationReceiver(),
            new PostReceiver(),
            new DeletePostReceiver(),
            new CommentReceiver(),
            new DeleteCommentReceiver(),
            new ThumbReceiver(),
            new ConnectionReceiver(),
            new GetPostReceiver(),
            new GetCommentReceiver(),
    };

    public void dispatch(ServerResponse response){
        var specific = response.getResponseType().cast(response);
        try {
            receiver[response.getResponseCode()].respond(specific);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
