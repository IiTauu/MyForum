package myforum.client.business.receivers;

import base.response.PostResponse;
import javafx.application.Platform;
import myforum.ui.ClientApplication;
import myforum.ui.utils.Generator;

public class PostReceiver implements Receiver<PostResponse> {
    @Override
    public void respond(PostResponse response) {
        if(!response.isSuccess()){
            Platform.runLater(()-> {
                Generator.popMessageStage("发布失败！");
            });
            return;
        }
        ClientApplication.app.posts().add(response.getPost());
        Platform.runLater(()-> {
            ClientApplication.app.getWritingStage().close();
            Generator.popMessageStage("发布成功！");
        });
    }
}
