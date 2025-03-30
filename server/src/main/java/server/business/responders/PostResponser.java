package server.business.responders;

import base.model.Post;
import base.request.PostRequest;
import base.response.PostResponse;
import base.response.ServerResponse;
import server.business.Translator;
import server.business.exceptions.OperationFailedException;
import server.repository.manager.PostManager;
import server.repository.model.PostData;

public class PostResponser implements Responser<PostRequest>{
    @Override
    public ServerResponse respond(PostRequest request) {
        try{
            PostManager manager = new PostManager();
            Post post = request.getPost();
            PostResponse response = new PostResponse(post);
            PostData newData = new Translator().postToData(post);
            int id = manager.add(newData);
            if(id==0) {
                System.out.println("Distribute 0 to id");
                throw new OperationFailedException();
            }
            response.getPost().setId(id);
            response.setSuccess(true);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new PostResponse(null);
        }
    }
}
