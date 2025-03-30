package myforum.client.business.receivers;

import base.model.Post;
import base.response.GetPostResponse;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import myforum.ui.ClientApplication;
import myforum.ui.utils.Generator;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class GetPostReceiver implements Receiver<GetPostResponse> {
    @Override
    public void respond(GetPostResponse response) {
        if (!response.isSuccess()) {
            Platform.runLater(()-> {
                Generator.popMessageStage("无法获取更多帖子，请重试！");
            });
            return;
        }
        if(response.getPosts().isEmpty()) {
            Platform.runLater(()-> {
                Generator.popMessageStage("没有更多帖子了 |-。-！|");
            });
            return;
        }
        ClientApplication.app.posts().addAll(response.getPosts());
        Platform.runLater(()-> {
            VBox postList = (VBox) ClientApplication.app.getHomeScene().lookup("#postsList");
            for (Post post : response.getPosts()) {
                postList.getChildren().add(wrappedPost(post));
            }
        });
    }

    private VBox wrappedPost(Post post) {
        VBox singlePost = new VBox();
        singlePost.setSpacing(2);
        singlePost.setStyle("-fx-border-width: 1; -fx-border-color: lightgrey;");

        Label title = new Label(post.getTitle());
        title.setFont(Font.font("System", 14));
        singlePost.getChildren().add(title);

        HBox information = new HBox();
        information.setSpacing(10);
        Label author = new Label(post.getAuthor().getName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Label date = new Label(formatter.format(post.getDetails().getPostTime()));
        information.getChildren().add(author);
        information.getChildren().add(date);
        singlePost.getChildren().add(information);

        singlePost.setOnMouseClicked(event -> {
            try {
                Generator.popPostStage(post);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return singlePost;
    }
}


