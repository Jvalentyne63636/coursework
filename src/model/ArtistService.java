package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ArtistService {



    public static void selectAll(List<Artist> targetList, DatabaseConnection database) {
        PreparedStatement statement = database.newStatement("SELECT * FROM Artist");

        try {
            if (statement != null) {
                ResultSet results = database.executeQuery(statement);

                if (results == null) {
                    return;
                }

                while (results.next()){
                    int id = results.getInt("id");
                    String name = results.getString("name");


                    Artist artist = new Artist(id, name);
                    targetList.add(artist);
                }
            }
        } catch (SQLException resultsException){
            System.out.println("Database select all error: " + resultsException.getMessage());
        }

    }

    public static Artist selectById(int id, DatabaseConnection database) {

        Artist result = null;

        PreparedStatement statement = database.newStatement("SELECT * FROM Artist WHERE id = ?");

        try {
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    id = results.getInt("id");
                    String name = results.getString("name");


                    result = new Artist(id, name);
                }
            }
        } catch(SQLException resultsException){
            System.out.println("Database select by id error: " + resultsException.getMessage());
        }
        return result;
    }

    public static void save(Artist ArtistToSave, DatabaseConnection database) {
        Artist existingItem = null;

        if (ArtistToSave.getId() != 0) {
            existingItem = selectById(ArtistToSave.getId(), database);
        }

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO Artist VALUES (null, ?)");
                statement.setInt(2, ArtistToSave.getId());
                statement.setString(3, ArtistToSave.getName());
                database.executeUpdate(statement);
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE Artist SET 2 = ?, 3 = ? WHERE id = ?");
                statement.setInt(1, ArtistToSave.getId());
                statement.setString(2, ArtistToSave.getName());
                database.executeUpdate(statement);
            }

        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }

    public static void deleteById(int id, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("DELETE FROM Artist WHERE id = ?");

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
