import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.*;

public class Main extends Application {

    DatabaseConnection database;
    ListView songsList;
    ListView playlistList;
    ListView allSongsList;

    File musicFolder = new File("C:\\Users\\JCval\\Desktop\\coursework-master\\playbacksource");
    File[] musiclist = musicFolder.listFiles();

    @Override
    public void start(Stage stage) throws Exception {
        database = new DatabaseConnection("C:\\Users\\JCval\\Desktop\\coursework-master\\Coursework.db");

        Pane root = new Pane();
        root.setStyle("-fx-background: #222222;");
        Scene scene = new Scene(root, 1024, 768);
        stage.setTitle("Elixr");
        stage.getIcons().add(new Image("http://files.coinmarketcap.com.s3-website-us-east-1.amazonaws.com/static/img/coins/200x200/monero.png"));
        stage.setScene(scene);

        Button btnChangePath = new Button("Change path source");
        btnChangePath.setLayoutX(850);
        btnChangePath.setLayoutY(720);
        btnChangePath.setFont(new Font("Arial", 14));
        root.getChildren().add(btnChangePath);

        btnChangePath.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser file = new DirectoryChooser();
                file.setTitle("Select a new file path");
                musicFolder = file.showDialog(stage);

//                System.out.println(musicfolder.getAbsolutePath());

//                File[] musiclist = musicfolder.listFiles();

            }
        });


        Button btnPlay = new Button("Play");
        btnPlay.setPrefSize(100, 35);
        btnPlay.setLayoutX(462);
        btnPlay.setLayoutY(662.5);
        root.getChildren().add(btnPlay);

        Button btnStop = new Button("Stop");
        btnStop.setPrefSize(100, 35);
        btnStop.setLayoutX(462);
        btnStop.setLayoutY(697.5);
        root.getChildren().add(btnStop);

        Button btnPrevious = new Button("Previous");
        btnPrevious.setPrefSize(100, 35);
        btnPrevious.setLayoutX(362);
        btnPrevious.setLayoutY(680);
        root.getChildren().add(btnPrevious);

        Button btnNext = new Button("Next");
        btnNext.setPrefSize(100, 35);
        btnNext.setLayoutX(562);
        btnNext.setLayoutY(680);
        root.getChildren().add(btnNext);

        Button btnAddSong = new Button("Add Song to Playlist");
        btnAddSong.setPrefSize(150, 35);
        btnAddSong.setLayoutX(380);
        btnAddSong.setLayoutY(500);
        root.getChildren().add(btnAddSong);

        Button btnDeleteSong = new Button("Delete Song from Playlist");
        btnDeleteSong.setPrefSize(150, 35);
        btnDeleteSong.setLayoutX(540);
        btnDeleteSong.setLayoutY(500);
        root.getChildren().add(btnDeleteSong);

        songsList = new ListView();
        songsList.setLayoutX(415);
        songsList.setLayoutY(90);
        root.getChildren().add(songsList);

        Slider mySlider1 = new Slider();
        mySlider1.setMin(0);
        mySlider1.setMax(1);
        root.getChildren().add(mySlider1);
        mySlider1.setBlockIncrement(0.1);
        mySlider1.setValue(0.5);
        mySlider1.setLayoutX(80);
        mySlider1.setLayoutY(690);

        Label lblPlaylists = new Label();
        lblPlaylists.setText("Your Playlists");
        lblPlaylists.setLayoutX(20);
        lblPlaylists.setLayoutY(20);
        lblPlaylists.setFont(new Font("Arial", 40));
        lblPlaylists.setTextFill(Color.web("#13f7b3"));
        root.getChildren().add(lblPlaylists);

        Label lblSongs = new Label("Selected Playlist");
        lblSongs.setLayoutX(380);
        lblSongs.setLayoutY(20);
        lblSongs.setFont(new Font("Arial", 40));
        lblSongs.setTextFill(Color.web("#13f7b3"));
        root.getChildren().add(lblSongs);

        Label lblVolume = new Label();
        lblVolume.setText("Volume");
        lblVolume.setLayoutX(125);
        lblVolume.setLayoutY(660);
        root.getChildren().add(lblVolume);

        Button btnNewPlaylist = new Button("Create new Playlist");
        btnNewPlaylist.setFont(new Font("Arial", 14));
        btnNewPlaylist.setLayoutX(90);
        btnNewPlaylist.setLayoutY(500);
        root.getChildren().add(btnNewPlaylist);

        Button btnDelPlaylist = new Button("Delete Playlist");
        btnDelPlaylist.setFont(new Font("Arial", 14));
        btnDelPlaylist.setLayoutX(105);
        btnDelPlaylist.setLayoutY(550);
        root.getChildren().add(btnDelPlaylist);

        Button btnRefreshSongs = new Button("Refresh Songs");
        btnRefreshSongs.setLayoutX(800);
        btnRefreshSongs.setLayoutY(500);
        btnRefreshSongs.setPrefSize(150, 35);
        root.getChildren().add(btnRefreshSongs);

        Label lblAllSongs = new Label();
        lblAllSongs.setText("All Songs");
        lblAllSongs.setLayoutX(800);
        lblAllSongs.setLayoutY(20);
        lblAllSongs.setFont(new Font("Arial", 40));
        lblAllSongs.setTextFill(Color.web("#13f7b3"));
        root.getChildren().add(lblAllSongs);

        ListView allSongsList = new ListView();
        allSongsList.setLayoutX(750);
        allSongsList.setLayoutY(90);
        root.getChildren().add(allSongsList);

        ListView playlistList = new ListView();
        playlistList.setLayoutX(30);
        playlistList.setLayoutY(90);
        root.getChildren().add(playlistList);


        btnDelPlaylist.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = playlistList.getSelectionModel().getSelectedItem().toString();
                Playlist playlist = PlaylistService.selectByName(name, database);
                int playlistId = playlist.getId();
                PlaylistService.deleteById(playlistId, database);
                playlistList.getItems().clear();

                ArrayList<Playlist> playlistArray = new ArrayList<>();
                PlaylistService.selectAll(playlistArray, database);
                for (int i = 0; i < playlistArray.size(); i++) {
                    String tempItem = playlistArray.get(i).getName();
                    playlistList.getItems().add(tempItem);
                }
            }
        });

        playlistList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (playlistList.getSelectionModel().getSelectedItem() == null) {
                    return;
                }

                String tempPlaylistName = playlistList.getSelectionModel().getSelectedItem().toString();
                openPlaylist(tempPlaylistName);
            }
        });

        btnAddSong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String songName = allSongsList.getSelectionModel().getSelectedItem().toString();
                ArrayList<String> choiceList = new ArrayList<String>();
                for (int i = 0; i < playlistList.getItems().size(); i++){
                    String playlistName = playlistList.getItems().get(i).toString();
                    choiceList.add(playlistName.toString());
                }
                ChoiceDialog choiceDialog = new ChoiceDialog(playlistList.getItems().get(0), choiceList);
                choiceDialog.setHeaderText(null);
                choiceDialog.setContentText("Choose a playlist: ");
                choiceDialog.setTitle("Elixr");
                String badResult = choiceDialog.showAndWait().toString();
                String playlistName = badResult.replace("Optional", "").replace("[","").replace("]","");
                System.out.println(songName + " " + playlistName);

                Songs song = SongsService.selectByName(songName, database);
                Playlist playlist = PlaylistService.selectByName(playlistName, database);
                PlaylistSongs pairing = new PlaylistSongs(playlist.getId(), song.getId());
                PlaylistSongsService.save(pairing, database);

                String tempPlaylistName = playlistList.getSelectionModel().getSelectedItem().toString();
                openPlaylist(tempPlaylistName);
            }
        });

        btnDeleteSong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectedSong = songsList.getSelectionModel().getSelectedItem().toString();
                String selectedPlaylist = playlistList.getSelectionModel().getSelectedItem().toString();
                Songs song = SongsService.selectByName(selectedSong, database);
                Playlist playlist = PlaylistService.selectByName(selectedPlaylist, database);
                int songId = song.getId();
                int playlistId = playlist.getId();

                PlaylistSongsService.deleteBySongId(songId, playlistId, database);

                String tempPlaylistName = playlistList.getSelectionModel().getSelectedItem().toString();
                openPlaylist(tempPlaylistName);
            }
        });

        ArrayList<Playlist> playlistarray = new ArrayList<>();
        PlaylistService.selectAll(playlistarray, database);

        for (int i = 0; i < playlistarray.size(); i++){

            playlistList.getItems().add(playlistarray.get(i).getName());
        }

        btnNewPlaylist.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setContentText("Enter playlist name");
                dialog.setTitle("Playlist");
                dialog.setHeaderText(null);
                Optional<String> result = dialog.showAndWait();
                String name = result.toString().replace("Optional","").replace("[","").replace("]","");
                playlistList.getItems().add(name);



                Playlist playlist = new Playlist(-1, name, "");
                if (PlaylistService.selectByName(name, database) == null){
                    PlaylistService.save(playlist, database);
                }


            }
        });

        mySlider1.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Playback.setVolume(newValue.doubleValue());
            }
        });

        btnPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playSelectedItem();
            }
        });

        btnStop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Playback.pauseFile();
            }
        });

        btnPrevious.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               Playback.pauseFile();

                int indexToSelect = songsList.getSelectionModel().getSelectedIndex() - 1;

                songsList.getSelectionModel().select(indexToSelect);
                songsList.getFocusModel().focus(indexToSelect);

                playSelectedItem();
            }
        });

        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Playback.pauseFile();
                int indexToSelect = songsList.getSelectionModel().getSelectedIndex() + 1;

                if (indexToSelect < musiclist.length) {
                    songsList.getSelectionModel().select(indexToSelect);
                    songsList.getFocusModel().focus(indexToSelect);
                }

                playSelectedItem();
            }
        });


        btnRefreshSongs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File[] musicList = musicFolder.listFiles();
                allSongsList.getItems().clear();
                for (int i = 0; i < musicList.length; i++){
                    File file = musicList[i];

                    if (file.isDirectory()) {
                        continue;
                    }

                    String name = file.getName();
                    double duration = Playback.getDuration(file.getAbsolutePath());


                    Songs song = new Songs(-1, -1, String.valueOf(duration), name, -1);
                    if (SongsService.selectByName(name, database) == null){
                        SongsService.save(song, database);
                    }

                    allSongsList.getItems().add(name);
                }
            }
        });

        stage.show();
    }

    public void openPlaylist(String name) {
        //String selectedPlaylistName = playlistList.getSelectionModel().getSelectedItem().toString();

        ArrayList<String> songNames = new ArrayList<>();
        PlaylistSongsService.selectByPlaylistName(songNames, name, database);

        songsList.getItems().clear();

        for (int i = 0; i < songNames.size(); i++) {
            songsList.getItems().add(songNames.get(i));
        }

    }

    public void playSelectedItem() {
        if (songsList.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        String fileName = fileName = songsList.getSelectionModel().getSelectedItem().toString();
        String musicPath = (musicFolder.getAbsolutePath().toString() + "\\" + fileName);
        Playback.playFile(musicPath);
    }

        public static void main(String[] args) {
        launch(args);
    }
    }

