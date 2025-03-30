package server.business.responders;

import base.request.TestRequest;
import base.response.ServerResponse;
import base.response.TestResponse;

public class TestResponser implements Responser<TestRequest> {
    @Override
    public ServerResponse respond(TestRequest request) {
        return new TestResponse("Receive test request");
    }
}
