package base.model;

public class Content {
    private String text;

    public Content(String text) {
        this.text = text;
    }

    //region getter and setter

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    //endregion
}
