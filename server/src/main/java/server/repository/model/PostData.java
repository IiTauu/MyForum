package server.repository.model;

import java.time.LocalDateTime;

public class PostData extends ForumData {
    private final int isPost = 0;

    private String title = null;
    private String content;
    private int authorId;
    private int fatherId = isPost;
    private LocalDateTime postTime;
    private int commentCount;
    private int thumbCount;

    public PostData(int id, String title, String content, int authorId, int fatherId,LocalDateTime postTime, int commentCount, int thumbCount) {
        super(id);
        this.content = content;
        this.title = title;
        this.authorId = authorId;
        this.fatherId = fatherId;
        this.postTime = postTime;
        this.commentCount = commentCount;
        this.thumbCount = thumbCount;
    }

    //region getter and setter

    public int getIsPost() {
        return isPost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getFatherId() {
        return fatherId;
    }

    public void setFatherId(int fatherId) {
        this.fatherId = fatherId;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void increaseCommentCount() {
        this.commentCount++;
    }

    public void decreaseCommentCount() {
        this.commentCount--;
    }

    public int getThumbCount() {
        return thumbCount;
    }

    public void increaseThumbCount() {
        this.thumbCount++;
    }

    public void decreaseThumbCount() {
        this.thumbCount--;
    }

    //endregion
}
