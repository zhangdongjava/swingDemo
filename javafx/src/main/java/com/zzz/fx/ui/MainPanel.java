package com.zzz.fx.ui;

import com.zzz.fx.player.MediaControl;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by dell_2 on 2016/9/23.
 */
public class MainPanel extends Application {

    private static final String MEDIA_URL = "D:\\download/oow2010-2.flv";
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Embedded Media Player");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.show();

        // create media player
        Media media = new Media(new File(MEDIA_URL).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        // create mediaView and add media player to the viewer
        MediaView mediaView = new MediaView(mediaPlayer);
        ((Group) scene.getRoot()).getChildren().add(mediaView);

        MediaControl mediaControl = new MediaControl(mediaPlayer);
        scene.setRoot(mediaControl);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
