import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Created by JCval on 26/12/2017.
 */
public class Playback {

    private static MediaPlayer mediaPlayer;


    public static void playFile(String filePath) {
        File musicFile = new File(filePath);
        Media music = new Media(musicFile.toURI().toString());

        System.out.println(filePath + ", " + musicFile.toURI().toString());

        mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setVolume(1);
        mediaPlayer.setAutoPlay(true);
    }

    public static double getDuration(String filePath) {
        File musicFile = new File(filePath);
        Media music = new Media(musicFile.toURI().toString());

        return music.getDuration().toSeconds();
    }

    public static void pauseFile(){
        mediaPlayer.pause();
    }

    public static void killFile(){
        mediaPlayer.stop();
    }

    public static void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }

}