package myforum.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import myforum.ui.ClientApplication;



public class MessageController {

    @FXML
    private Label messageLabel;

    @FXML
    private Button closeButton;

    public Label getMessageLabel() {
        return messageLabel;
    }

    public Button getCloseButton() {
        return closeButton;
    }

    @FXML
    void close(ActionEvent event) {
        messageLabel.getScene().getWindow().hide();
    }
}
