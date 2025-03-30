package server.business.responders;

import base.request.DeleteCommentRequest;
import base.response.DeleteCommentResponse;
import base.response.ServerResponse;
import server.repository.manager.PostManager;
import server.repository.model.PostData;

public class DeleteCommentResponser implements Responser<DeleteCommentRequest>{
    @Override
    public ServerResponse respond(DeleteCommentRequest request) {
        try{
            DeleteCommentResponse response = new DeleteCommentResponse(request.getCommentId());
            PostManager manager = new PostManager();
            PostData data = manager.get(request.getCommentId());
            boolean success = manager.delete(request.getCommentId());
            if(success){
                manager.decreaseCommentCount(data.getFatherId());
            }
            response.setSuccess(success);
            return response;
        }catch(Exception e){
            return new DeleteCommentResponse(request.getCommentId());
        }
    }
}
