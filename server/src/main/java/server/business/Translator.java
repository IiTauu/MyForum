package server.business;

import base.model.*;
import server.repository.manager.UserManager;
import server.repository.manager.exception.NotFoundException;
import server.repository.model.PostData;
import server.repository.model.UserData;


public class Translator {
    public PostDetails dataToDetails(PostData data){
        PostDetails details = null;
        try{
            details = new PostDetails(
                    data.getThumbCount(),
                    data.getCommentCount(),
                    data.getPostTime()
            );
        }catch (Exception e){
            return null;
        }
        return details;
    }

    public Post dataToPost(PostData data) {
        Post post = null;
        try {
            UserManager userManager = new UserManager();
            UserData userData = userManager.get(data.getAuthorId());
            post =  new Post(
                    data.getId(),
                    new Author(dataToUser(userData))
            );
            post.setTitle(data.getTitle());
            post.setContent(new Content(data.getContent()));
            PostDetails details = new PostDetails(
                    data.getThumbCount(),
                    data.getCommentCount(),
                    data.getPostTime()
            );
            post.setDetails(details);
        } catch (NotFoundException e) {
            return null;
        }
        return post;
    }

    public server.repository.model.PostData postToData(Post post) {
        return new PostData(
                post.getId(),
                post.getTitle(),
                post.getContent().getText(),
                post.getAuthor().getId(),
                0,
                post.getDetails().getPostTime(),
                post.getDetails().getCommentCount(),
                post.getDetails().getThumbCount()
        );
    }

    public Comment dataToComment(PostData data) {
        Comment comment = null;
        try {
            UserManager userManager = new UserManager();
            UserData userData = userManager.get(data.getAuthorId());
            comment =  new Comment(
                    data.getId(),
                    new Author(dataToUser(userData)),
                    data.getFatherId()
            );
        } catch (NotFoundException e) {
            return null;
        }
        return comment;
    }

    public server.repository.model.PostData commentToData(Comment comment){
        return new PostData(
                comment.getId(),
                null,
                comment.getContent(),
                comment.getAuthor().getId(),
                comment.getFatherId(),
                comment.getDetails().getPostTime(),
                comment.getDetails().getCommentCount(),
                comment.getDetails().getThumbCount()
        );
    }

    public User dataToUser(UserData data){
        User user = new User(
                data.getAccount(),
                data.getUsername(),
                data.getId()
        );
        user.setLastLoginTime(data.getLastLoginTime());
        user.setRegisterDate(data.getRegistrationDate());
        return user;
    }

    public UserData userToData(User user){
        return new UserData(
                user.getUserId(),
                user.getUsername(),
                user.getAccount(),
                user.getLastLoginTime(),
                user.getRegisterDate()
        );
    }

}
