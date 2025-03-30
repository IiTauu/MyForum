package myforum.client.business.receivers;

import base.response.RegistrationResponse;
import javafx.application.Platform;
import javafx.stage.Stage;
import myforum.ui.ClientApplication;
import myforum.ui.utils.Generator;

public class RegistrationReceiver implements Receiver<RegistrationResponse> {
    @Override
    public void respond(RegistrationResponse response) {
        if(!response.isSuccess()){
            Platform.runLater(()->{
                Generator.popMessageStage("""
            注册失败！
            
            可能原因：
            验证码错误或者还未请求验证码""");
            });
            return;
        }
        Platform.runLater(()->{
            Generator.popMessageStage("注册成功");
            Stage mainStage =  ClientApplication.app.getMainStage();
            mainStage.setScene(ClientApplication.app.getLoginScene());
            mainStage.show();
        });
    }
}
