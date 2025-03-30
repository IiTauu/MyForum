package server.business.responders;

import base.model.PostDetails;
import base.request.ThumbRequest;
import base.response.ServerResponse;
import base.response.ThumbResponse;
import server.business.Translator;
import server.business.exceptions.OperationFailedException;
import server.repository.manager.PostManager;

public class ThumbResponser implements Responser<ThumbRequest> {
    @Override
    public ServerResponse respond(ThumbRequest request) {
        PostManager postManager = new PostManager();
        boolean success = false;
        try {
            if (request.isCancelRequest())
                success = postManager.decreaseThumbCount(request.getPostId());
            else
                success = postManager.increaseThumbCount(request.getPostId());
            PostDetails details = new Translator().dataToDetails(postManager.get(request.getPostId()));
            if (success && details == null)
                throw new OperationFailedException();
            ThumbResponse response = new ThumbResponse(details);
            response.setSuccess(true);
            return response;
        } catch (Exception e) {
            return new ThumbResponse(null);
        }
    }
}
