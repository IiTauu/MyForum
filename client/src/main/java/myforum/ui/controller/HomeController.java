package myforum.ui.controller;

import base.model.Post;
import base.request.GetPostRequest;
import io.netty.util.internal.SocketUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import myforum.ui.ClientApplication;

import java.io.IOException;
import java.util.List;


public class HomeController {

    @FXML
    private VBox postsList;

    @FXML
    private Label usernameLabel;

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    @FXML
    void displayListPage(MouseEvent event) throws IOException {

    }

    @FXML
    void displayUserPage(MouseEvent event) {
        //暂未实现
    }

    @FXML
    void popWritingStage(ActionEvent event) throws IOException {
        //是否已经创建
        if(ClientApplication.app.getWritingStage()!=null){
            Stage writingStage = ClientApplication.app.getWritingStage();
            writingStage.requestFocus();
            return;
        }
        Stage writingStage = new Stage();
//        System.out.println("Bing writing stage with app");
        ClientApplication.app.setWritingStage(writingStage);

        FXMLLoader loader = new FXMLLoader(ClientApplication.class.getResource("writing-view.fxml"));
        writingStage.setScene(new Scene(loader.load()));
        writingStage.setTitle("MyForum Writing");
        writingStage.setOnHidden(event1->{
//            System.out.println("Unbind writing stage");
            ClientApplication.app.setWritingStage(null);
        });
        writingStage.show();
    }

    @FXML
    void refresh(ActionEvent event) throws IOException {
        int lastId = ClientApplication.app.posts().getLast().getId();
        GetPostRequest getPostRequest = new GetPostRequest(lastId,5);
        ClientApplication.app.channel().writeAndFlush(getPostRequest);
    }
}
