package myforum.client.business.receivers;

import base.request.GetPostRequest;
import base.response.LoginResponse;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import myforum.ui.ClientApplication;
import myforum.ui.utils.Generator;
import myforum.ui.controller.HomeController;

import java.io.IOException;

public class LoginReceiver implements Receiver<LoginResponse> {
    @Override
    public void respond(LoginResponse response) {
        if (!response.isSuccess()) {
            Platform.runLater(() -> {
                Generator.popMessageStage("用户不存在或密码错误");
                ClientApplication.app.getMainStage().setScene(ClientApplication.app.getLoginScene());
            });
            return;
        }
        //应用程序绑定用户信息
        ClientApplication.app.setUser(response.getUser());

        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("home-view.fxml"));
            Stage mainStage = ClientApplication.app.getMainStage();
            try {
                ClientApplication.app.setHomeScene(new Scene(fxmlLoader.load()));

                HomeController homeController = fxmlLoader.getController();
                homeController.getUsernameLabel().setText(response.getUser().getUsername());

                mainStage.setTitle("MyForum Client");
                mainStage.setScene(ClientApplication.app.getHomeScene());
                mainStage.show();

                GetPostRequest getPostRequest = new GetPostRequest(0,5);
                ClientApplication.app.channel().writeAndFlush(getPostRequest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
