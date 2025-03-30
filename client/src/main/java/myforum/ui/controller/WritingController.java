package myforum.ui.controller;

import base.model.Author;
import base.model.Content;
import base.model.Post;
import base.model.PostDetails;
import base.request.PostRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import myforum.ui.ClientApplication;
import myforum.ui.utils.Generator;

import java.io.IOException;
import java.time.LocalDateTime;


public class WritingController {

    @FXML
    private TextArea contentInput;

    @FXML
    private HBox title;

    @FXML
    private TextField titleInput;

    @FXML
    void cancel(ActionEvent event) {
        title.getScene().getWindow().hide();
    }

    @FXML
    void post(ActionEvent event) throws IOException {
        String title = titleInput.getText();
        String content = contentInput.getText();
        Post post = new Post(0,new Author(ClientApplication.app.user()));
        post.setTitle(title);
        post.setContent(new Content(content));
        PostDetails postDetails = new PostDetails(0,0, LocalDateTime.now());
        post.setDetails(postDetails);
        PostRequest postRequest = new PostRequest(post);
        ClientApplication.app.channel().writeAndFlush(postRequest);
        Generator.turnToWaitingStage("请求发布中。。。", ClientApplication.app.getWritingStage());
    }
}
