package base.model;

public class Comment {
    private int id;
    private final Author author;
    private final int fatherId;
    private String content;
    private PostDetails details;

    public Comment(int id, Author author, int fatherId) {
        this.id = id;
        this.author = author;
        this.fatherId = fatherId;
    }

    //region getter and setter

    public int getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public int getFatherId() {
        return fatherId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PostDetails getDetails() {
        return details;
    }

    public void setDetails(PostDetails details) {
        this.details = details;
    }

    public void setId(int id) {
        this.id = id;
    }

    //endregion
}
