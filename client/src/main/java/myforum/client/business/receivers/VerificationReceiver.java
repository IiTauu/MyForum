package myforum.client.business.receivers;

import base.response.VerificationResponse;
import javafx.application.Platform;
import javafx.scene.control.Button;
import myforum.ui.ClientApplication;
import myforum.ui.utils.Generator;

public class VerificationReceiver implements Receiver<VerificationResponse> {
    @Override
    public void respond(VerificationResponse response) {
        if(!response.isSuccess()){
            Platform.runLater(()->{
                Generator.popMessageStage("""
                验证码请求失败！
                
                可能原因：
                1. 此邮箱地址错误或无法收信；
                2. 此邮箱已经注册了账号"""
                );
                Button verifyButton = (Button) ClientApplication.app.getMainStage().getScene().lookup("#verifyButton");
                verifyButton.setDisable(false);
            });
            return;
        }

    }
}
