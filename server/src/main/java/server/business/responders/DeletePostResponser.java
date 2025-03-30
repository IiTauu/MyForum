package server.business.responders;

import base.request.DeletePostRequest;
import base.response.DeletePostResponse;
import base.response.ServerResponse;
import server.repository.manager.PostManager;

public class DeletePostResponser implements Responser<DeletePostRequest> {
    @Override
    public ServerResponse respond(DeletePostRequest request) {
        try{
            DeletePostResponse response = new DeletePostResponse(request.getPostId());
            PostManager manager = new PostManager();
            boolean success =  manager.delete(request.getPostId());
            response.setSuccess(success);
            return response;
        }catch (Exception e){
            return new DeletePostResponse(request.getPostId());
        }
    }
}
