package server.business.responders;

import base.model.Post;
import base.request.GetPostRequest;
import base.response.GetPostResponse;
import base.response.ServerResponse;
import server.business.Translator;
import server.repository.manager.PostManager;
import server.repository.model.PostData;

import java.util.ArrayList;
import java.util.List;

public class GetPostResponser implements Responser<GetPostRequest> {
    @Override
    public ServerResponse respond(GetPostRequest request) {
        try {
            List<Post> posts = new ArrayList<>();
            PostManager manager = new PostManager();
            List<PostData> list = null;
            if (request.getLastId() == 0) {
                list = manager.getPosts(
                        0,
                        request.getCount()
                );
            } else {
                list = manager.getPosts(
                        0,
                        request.getCount(),
                        request.getLastId()
                );
            }
            Translator translator = new Translator();
            for (PostData postData : list) {
                Post post = translator.dataToPost(postData);
                if (post != null)
                    posts.add(post);
            }
            GetPostResponse response = new GetPostResponse(posts);
            response.setSuccess(true);
            return response;
        } catch (Exception e) {
            return new GetPostResponse(null);
        }
    }
}
