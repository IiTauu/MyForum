package myforum.ui;

import base.model.Post;
import base.model.User;
import io.netty.channel.Channel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import myforum.client.ForumClient;
import myforum.ui.utils.Generator;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientApplication extends Application {

    private static String host = "localhost";
    private static int port = 8080;

    public static void main(String givenHost, int givenPort) {
        host = givenHost;
        port = givenPort;
        launch();
    }

    public static ClientApplication app = null;

    @Override
    public void start(Stage mainStage) throws IOException {
        app = this;
        this.mainStage = mainStage;
        connect();

        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("login-view.fxml"));
        loginScene = new Scene(fxmlLoader.load());
        mainStage.setTitle("MyForum Login");
        mainStage.setScene(loginScene);
        mainStage.setOnHidden(event -> {
            closeOtherStages();
        });
        mainStage.show();
    }

    private void connect() {
        new Thread(() -> {
            try {
                //开始连接
                ForumClient client = new ForumClient(host, port);
                client.start();
            } catch (Exception e) {
                Platform.runLater(() -> {
                    try {
                        Generator.turnToErrorStage("连接失败，请检查网络。",ClientApplication.app.getMainStage());
                    } catch (IOException ex) {
                        ClientApplication.app.mainStage.close();
                    }
                });
            }
        }).start();
    }

    @FXML
    public void stop() {
        if(mainStage != null && mainStage.isShowing())
            mainStage.close();
        if (channel != null && channel.isActive()){
            channel.close();
        }
        closeOtherStages();
        System.out.println("Application is closed");
    }

    //region attribute user
    private User user = null;

    public void setUser(User user) {
        this.user = user;
    }

    public User user() {
        return user;
    }
    //endregion

    //region attribute channel

    private Channel channel = null;

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Channel channel() {
        return channel;
    }

    //endregion

    //region Stages
    private Stage mainStage = null;
    private Stage writingStage = null;
    private Stage messageStage = null;
    private Stage postStage = null;

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public Stage getMessageStage() {
        return messageStage;
    }

    public void setMessageStage(Stage messageStage) {
        this.messageStage = messageStage;
    }

    public Stage getWritingStage() {
        return writingStage;
    }

    public void setWritingStage(Stage writingStage) {
        this.writingStage = writingStage;
    }

    public Stage getPostStage() {
        return postStage;
    }

    public void setPostStage(Stage postStage) {
        this.postStage = postStage;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void closeOtherStages() {
        if(messageStage != null && messageStage.isShowing())
            messageStage.close();
        if(writingStage != null && writingStage.isShowing())
            writingStage.close();
        if(postStage != null && postStage.isShowing())
            postStage.close();
    }

//endregion

    //region Scenes
    private Scene loginScene = null;
    private Scene homeScene = null;
    private Scene listScene = null;

    public Scene getLoginScene() {
        return loginScene;
    }

    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }

    public Scene getHomeScene() {
        return homeScene;
    }

    public void setHomeScene(Scene homeScene) {
        this.homeScene = homeScene;
    }

    public Scene getListScene() {
        return listScene;
    }

    public void setListScene(Scene listScene) {
        this.listScene = listScene;
    }
    //endregion

    //region attribute posts
    private List<Post> posts = new ArrayList<>();

    public List<Post> posts() {
        return posts;
    }
    //endregion
}