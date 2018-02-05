package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by JCval on 31/12/2017.
 */
public class PlaylistSongsService {

    public static void save(PlaylistSongs pairingToSave, DatabaseConnection database) {
        try {
            PreparedStatement statement = database.newStatement("INSERT INTO PlaylistSongs VALUES (?, ?)");
            statement.setInt(1, pairingToSave.getPlaylistid());
            statement.setInt(2, pairingToSave.getSongid());
            database.executeUpdate(statement);

        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }

    public static void selectByPlaylistName(ArrayList<String> targetList, String name, DatabaseConnection database) {
        PreparedStatement statement = database.newStatement("SELECT Songs.name FROM Songs INNER JOIN PlaylistSongs on PlaylistSongs.songid = Songs.id INNER JOIN Playlist on Playlist.id = PlaylistSongs.playlistid WHERE Playlist.name = ?");

        try {
            if (statement != null) {
                statement.setString(1, name);
                ResultSet results = database.executeQuery(statement);

                while (results.next()){
                    String songName = results.getString("name");
                    targetList.add(songName);
                }
            }
        } catch(SQLException resultsException){
            System.out.println("Database select by id error: " + resultsException.getMessage());
        }
    }

    public static void deleteBySongId(int songid, int playlistid, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("DELETE FROM PlaylistSongs WHERE songid = ? AND playlistid = ?");

        try {
            if (statement != null) {
                statement.setInt(1, songid);
                statement.setInt(2, playlistid);
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException){
            System.out.println("Database deletion error: " + resultsException.getMessage());
        }
    }

}
