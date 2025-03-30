package base.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PostDetails {
    private int  thumbCount;
    private int commentCount;
    private LocalDateTime postTime;

    public PostDetails(int thumbCount, int commentCount, LocalDateTime postTime) {
        this.thumbCount = thumbCount;
        this.commentCount = commentCount;
        this.postTime = postTime;
    }

    //region getter and setter

    public int getThumbCount() {
        return thumbCount;
    }

    public void setThumbCount(int thumbCount) {
        this.thumbCount = thumbCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }

    //endregion
}
