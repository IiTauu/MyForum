package server.business.responders;

import base.model.Comment;
import base.request.CommentRequest;
import base.response.CommentResponse;
import base.response.ServerResponse;
import server.business.Translator;
import server.business.exceptions.OperationFailedException;
import server.repository.manager.PostManager;
import server.repository.model.PostData;

public class CommentResponser implements Responser<CommentRequest> {
    @Override
    public ServerResponse respond(CommentRequest request) {
        try {
            PostManager manager = new PostManager();
            Comment comment = request.getComment();
            PostData newData = new Translator().commentToData(comment);
            int id = manager.add(newData);
            if (id == 0)
                throw new OperationFailedException();
            comment.setId(id);
            manager.increaseCommentCount(comment.getFatherId());
            CommentResponse response = new CommentResponse(comment);
            response.setSuccess(true);
            return response;
        } catch (Exception e) {
            return new CommentResponse(null);
        }
    }
}
