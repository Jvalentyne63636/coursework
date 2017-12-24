package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SongsService {

    public static void selectAll(List<Songs> targetList, DatabaseConnection database) {
        PreparedStatement statement = database.newStatement("SELECT * FROM Songs");

        try {
            if (statement != null) {
                ResultSet results = database.executeQuery(statement);

                if (results == null) {
                    return;
                }

                while (results.next()){
                    int id = results.getInt("id");
                    int artistid = results.getInt("artistid");
                    String length = results.getString("length");
                    String name = results.getString("name");
                    int genreid = results.getInt("genreid");

                    Songs songs = new Songs(id, artistid, length, name, genreid);
                    targetList.add(songs);
                }
            }
        } catch (SQLException resultsException){
            System.out.println("Database select all error: " + resultsException.getMessage());
        }

    }

    public static Songs selectById(int id, DatabaseConnection database) {

        Songs result = null;

        PreparedStatement statement = database.newStatement("SELECT * FROM Songs WHERE id = ?");

        try {
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    int accountid = results.getInt("id");
                    int artistid = results.getInt("artistid");
                    String length = results.getString("length");
                    String name = results.getString("name");
                    int genreid = results.getInt("genreid");

                    result = new Songs(id, artistid, length, name, genreid);
                }
            }
        } catch(SQLException resultsException){
            System.out.println("Database select by id error: " + resultsException.getMessage());
        }
        return result;
    }

    public static void save(Songs SongsToSave, DatabaseConnection database) {
        Songs existingItem = null;

        if (SongsToSave.getId() != 0) {
            existingItem = selectById(SongsToSave.getId(), database);
        }

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO Songs VALUES (null, ?, ?, ?, ?)");
                statement.setInt(1, SongsToSave.getArtistid());
                statement.setString(2, SongsToSave.getLength());
                statement.setString(3, SongsToSave.getName());
                statement.setInt(4, SongsToSave.getGenreid());
                database.executeUpdate(statement);
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE Songs SET ArtistID = ?, Length = ?, Name = ?, GenreID = ? WHERE id = ?");
                statement.setInt(1, SongsToSave.getArtistid());
                statement.setString(2, SongsToSave.getLength());
                statement.setString(3, SongsToSave.getName());
                statement.setInt(4, SongsToSave.getGenreid());
                database.executeUpdate(statement);
            }

        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }

    public static void deleteById(int id, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("DELETE FROM Songs WHERE id = ?");

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
