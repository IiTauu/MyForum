package server.business.responders;

import base.request.ConnectionRequest;
import base.response.ConnectionResponse;
import base.response.ServerResponse;

public class ConnectionResponser implements Responser<ConnectionRequest>{
    @Override
    public ServerResponse respond(ConnectionRequest request) {
        ConnectionResponse response = new ConnectionResponse();
        response.setSuccess(true);
        return response;
    }
}
