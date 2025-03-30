package myforum.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import myforum.ui.ClientApplication;


public class PostController {

    @FXML
    private Label authorLabel;

    @FXML
    private Button commentLabel;

    @FXML
    private VBox commentList;

    @FXML
    private TextArea contentLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Button thumbButton;

    @FXML
    private Label titleLabel;

    @FXML
    void comment(ActionEvent event) {

    }

    @FXML
    void thumb(ActionEvent event) {

    }

    @FXML
    void refresh(ActionEvent event) {

    }

    public Label getAuthorLabel() {
        return authorLabel;
    }

    public TextArea getContentLabel() {
        return contentLabel;
    }

    public Label getDateLabel() {
        return dateLabel;
    }

    public Label getTitleLabel() {
        return titleLabel;
    }
}