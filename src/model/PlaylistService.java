package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PlaylistService {

    public static void selectAll(List<Playlist> targetList, DatabaseConnection database) {
        PreparedStatement statement = database.newStatement("SELECT * FROM Playlist");

        try {
            if (statement != null) {
                ResultSet results = database.executeQuery(statement);

                if (results == null) {
                    return;
                }

                while (results.next()){
                    int id = results.getInt("id");
                    String name = results.getString("name");
                    int accountid = results.getInt("accountid");


                    Playlist playlist = new Playlist(id, name, accountid);
                    targetList.add(playlist);
                }
            }
        } catch (SQLException resultsException){
            System.out.println("Database select all error: " + resultsException.getMessage());
        }

    }

    public static Playlist selectById(int id, DatabaseConnection database) {

        Playlist result = null;

        PreparedStatement statement = database.newStatement("SELECT * FROM Playlist WHERE id = ?");

        try {
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    id = results.getInt("id");
                    String name = results.getString("name");
                    int accountid = results.getInt("accountid");

                    result = new Playlist(id, name, accountid);
                }
            }
        } catch(SQLException resultsException){
            System.out.println("Database select by id error: " + resultsException.getMessage());
        }
        return result;
    }

    public static void save(Playlist PlaylistToSave, DatabaseConnection database) {
        Playlist existingItem = null;

        if (PlaylistToSave.getId() != 0) {
            existingItem = selectById(PlaylistToSave.getId(), database);
        }

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO Playlist VALUES (null, ?, ?, ?)");
                statement.setInt(1, PlaylistToSave.getId());
                statement.setString(2, PlaylistToSave.getName());
                statement.setInt(3, PlaylistToSave.getAccountid());
                database.executeUpdate(statement);
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE Playlist SET Id = ?, Name = ?, Accountid = ? WHERE id = ?");
                statement.setInt(1, PlaylistToSave.getId());
                statement.setString(2, PlaylistToSave.getName());
                statement.setInt(3, PlaylistToSave.getAccountid());
                database.executeUpdate(statement);
            }

        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }

    public static void deleteById(int id, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("DELETE FROM Playlist WHERE id = ?");

        try {
            if (statement != null) {
                statement.setInt(1, id);
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException){
            System.out.println("Database deletion error: " + resultsException.getMessage());
        }
    }

}
