package myforum.ui.utils;

import base.model.Post;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import myforum.ui.ClientApplication;
import myforum.ui.controller.MessageController;
import myforum.ui.controller.PostController;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class Generator {

    public static void turnToWaitingStage(String message,Stage mainStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("message-view.fxml"));
        Scene waittingScene = new Scene(fxmlLoader.load());

        MessageController messageController = fxmlLoader.getController();
        messageController.getCloseButton().setVisible(false);
        messageController.getMessageLabel().setText(message);

        mainStage.setScene(waittingScene);
        mainStage.show();
    }

    public static void turnToErrorStage(String message,Stage mainStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("message-view.fxml"));
        Scene errorScene = new Scene(fxmlLoader.load());

        MessageController messageController = fxmlLoader.getController();
        messageController.getMessageLabel().setText(message);

        mainStage.setScene(errorScene);
        mainStage.setTitle("Error");
        mainStage.show();
    }

    public static void popMessageStage(String message) {
        try {
            Stage popMessageStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("message-view.fxml"));
            Scene messageScene = new Scene(fxmlLoader.load());

            MessageController messageController = fxmlLoader.getController();
            messageController.getMessageLabel().setText(message);

            popMessageStage.setScene(messageScene);
            popMessageStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void popPostStage(Post post) throws IOException {
        Stage popPostStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("post-view.fxml"));
        popPostStage.setTitle(post.getTitle());
        popPostStage.setScene(new Scene(fxmlLoader.load()));
        popPostStage.show();

        PostController postController = fxmlLoader.getController();
        postController.getTitleLabel().setText(post.getTitle());
        postController.getContentLabel().setText(post.getContent().getText());
        postController.getAuthorLabel().setText(post.getAuthor().getName());
        postController.getDateLabel().setText(
                DateTimeFormatter.ofPattern("yyyy-MM-dd").format(
                        post.getDetails().getPostTime())
        );
    }
}
