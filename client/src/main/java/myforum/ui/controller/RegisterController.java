package myforum.ui.controller;

import base.request.RegistrationRequest;
import base.request.VerificationRequest;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import myforum.ui.ClientApplication;


public class RegisterController {

    @FXML
    private TextField accountInput;

    @FXML
    private PasswordField confirmPasswordInput;

    @FXML
    private Label messageLabel;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameInput;

    @FXML
    private TextField verificationCodeInput;

    @FXML
    private Button verifyButton;

    public void initialize() {
        accountInput.textProperty().addListener((observable, oldValue, newValue) -> {
            verifyButton.setDisable(false);
        });
        passwordInput.textProperty().addListener((observable, oldValue, newValue) -> {
            checkPassword();
        });
        confirmPasswordInput.textProperty().addListener((observable, oldValue, newValue) -> {
            checkPassword();
        });
    }

    private void checkPassword() {
        if (!passwordInput.getText().equals(confirmPasswordInput.getText())) {
            messageLabel.setText("两次输入的密码不一致");
            registerButton.setDisable(true);
        } else {
            messageLabel.setText("");
            registerButton.setDisable(false);
        }
    }

    @FXML
    void register(ActionEvent event) {
        if (accountInput.getText().isEmpty()) {
            messageLabel.setText("邮箱不能为空");
            return;
        }
        if (passwordInput.getText().isEmpty()) {
            messageLabel.setText("密码不能为空");
            return;
        }
        if (usernameInput.getText().isEmpty()) {
            messageLabel.setText("用户名不能为空");
            return;
        } else if (usernameInput.getText().length() > 20) {
            messageLabel.setText("用户名长度不能超过20个字符");
            return;
        }
        if (verificationCodeInput.getText().isEmpty()) {
            messageLabel.setText("验证码不能为空");
            return;
        }

        RegistrationRequest registrationRequest = new RegistrationRequest(
                accountInput.getText(),
                passwordInput.getText(),
                verificationCodeInput.getText(),
                usernameInput.getText()
        );
        ClientApplication.app.channel().writeAndFlush(registrationRequest);
    }

    @FXML
    void verify(ActionEvent event) {
        if (accountInput.getText().isEmpty()) {
            messageLabel.setText("邮箱不能为空");
            return;
        }
        Button verifyButton = (Button) ClientApplication.app.getMainStage().getScene().lookup("#verifyButton");
        verifyButton.setDisable(true);
        VerificationRequest verificationRequest = new VerificationRequest(accountInput.getText());
        ClientApplication.app.channel().writeAndFlush(verificationRequest);
    }

    @FXML
    void back(ActionEvent event) {
        back();
    }

    public void back() {
        Stage mainStage = ClientApplication.app.getMainStage();
        mainStage.setScene(ClientApplication.app.getLoginScene());
        mainStage.show();
    }
}
