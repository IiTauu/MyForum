package server.business.responders;

import base.response.ServerResponse;

public interface Responser<T> {
    public ServerResponse respond(T request);
}
