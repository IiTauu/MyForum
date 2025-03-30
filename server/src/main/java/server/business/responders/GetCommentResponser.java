package server.business.responders;

import base.model.Comment;
import base.request.GetCommentRequest;
import base.response.GetCommentResponse;
import base.response.ServerResponse;
import server.business.Translator;
import server.repository.manager.PostManager;
import server.repository.model.PostData;

import java.util.ArrayList;
import java.util.List;

public class GetCommentResponser implements Responser<GetCommentRequest> {
    @Override
    public ServerResponse respond(GetCommentRequest request) {
        boolean success = true;
        try {
            List<Comment> comments = new ArrayList<>();
            PostManager manager = new PostManager();
            List<PostData> list = null;
            if (request.getLastId() == 0) {
                list = manager.getPosts(
                        request.getFatherId(),
                        request.getCount()
                );
            } else {
                list = manager.getPosts(
                        request.getFatherId(),
                        request.getCount(),
                        request.getLastId()
                );
            }
            Translator translator = new Translator();
            for (PostData postData : list) {
                Comment comment = translator.dataToComment(postData);
                if (comment != null)
                    comments.add(comment);
            }
            GetCommentResponse response = new GetCommentResponse(comments);
            response.setSuccess(success);
            return response;
        } catch (Exception e) {
            return new GetCommentResponse(null);
        }
    }
}
