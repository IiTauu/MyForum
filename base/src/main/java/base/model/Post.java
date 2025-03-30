package base.model;

public class Post {
    private int id;
    private Author author;
    private String title;
    private Content content;
    private PostDetails details;

    public Post(int id, Author author) {
        this.id = id;
        this.author = author;
    }

    //region getter ans setter

    public int getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public Content getContext() {
        return content;
    }

    public void setContext(Content content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    //endregion
}
