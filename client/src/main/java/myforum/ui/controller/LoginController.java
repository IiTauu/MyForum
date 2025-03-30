package myforum.ui.controller;

import base.request.LoginRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import myforum.ui.ClientApplication;
import myforum.ui.utils.Generator;


import java.io.IOException;

public class LoginController {

    @FXML
    private TextField accountInput;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Button registerButton;

    @FXML
    private Label messageLabel;

    @FXML
    void checkAccount(ActionEvent event) {
        //整合到login中
    }

    @FXML
    void checkPassword(ActionEvent event) {
        //整合到login中
    }

    private boolean check() {
        if (accountInput.getText().isEmpty()) {
            messageLabel.setText("邮箱不能为空");
            return false;
        } else if (!accountInput.getText().contains(String.valueOf('@'))
                || !accountInput.getText().contains(String.valueOf('.'))
        ) {
            messageLabel.setText("请输入正确的邮箱");
            return false;
        }
        if (passwordInput.getText().isEmpty()) {
            messageLabel.setText("密码不能为空");
            return false;
        }
        return true;
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        if (!check())
            return;
        LoginRequest loginRequest = new LoginRequest(accountInput.getText(), passwordInput.getText());
        ClientApplication.app.channel().writeAndFlush(loginRequest);
        Generator.turnToWaitingStage("登录中，请稍候。。。",ClientApplication.app.getMainStage());
    }

    @FXML
    void register(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("register-view.fxml"));
        Scene registerScene = new Scene(fxmlLoader.load());

        Stage registrationStage = ClientApplication.app.getMainStage();
        registrationStage.setScene(registerScene);
        registrationStage.show();
    }
}
