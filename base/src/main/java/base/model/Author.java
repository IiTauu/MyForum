package base.model;

public class Author {
    private int id;
    private String name;

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(int id) {
        this.id = id;
    }

    public Author(User user){
        this.id = user.getUserId();
        this.name = user.getUsername();
    }

    //region getter and setter

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    //endregion
}
