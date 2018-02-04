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
                    String length = results.getString("length");
                    String name = results.getString("name");


                    Songs songs = new Songs(id, length, name);
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
                    String length = results.getString("length");
                    String name = results.getString("name");

                    result = new Songs(id, length, name);
                }
            }
        } catch(SQLException resultsException){
            System.out.println("Database select by id error: " + resultsException.getMessage());
        }
        return result;
    }

    public static Songs selectByName(String nameToSearch, DatabaseConnection database) {

        Songs result = null;

        PreparedStatement statement = database.newStatement("SELECT * FROM Songs WHERE name = ?");

        try {
            if (statement != null) {
                statement.setString(1, nameToSearch);
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    int id = results.getInt("id");
                    String length = results.getString("length");
                    String name = results.getString("name");

                    result = new Songs(id, length, name);
                }
            }
        } catch(SQLException resultsException){
            System.out.println("Database select by id error: " + resultsException.getMessage());
        }
        return result;
    }

    public static void save(Songs songToSave, DatabaseConnection database) {
        Songs existingItem = null;

        if (songToSave.getName() != null) {
            existingItem = selectById(songToSave.getId(), database);
        }

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO Songs VALUES (null, ?, ?)");
                statement.setString(1, songToSave.getLength());
                statement.setString(2, songToSave.getName());
                database.executeUpdate(statement);
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE Songs SET, Length = ?, Name = ? WHERE id = ?");
                statement.setString(1, songToSave.getLength());
                statement.setString(2, songToSave.getName());
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

    public static void deleteByName(String name, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("DELETE FROM Songs WHERE name = ?");

        try {
            if (statement != null) {
                statement.setString(1, name);
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException){
            System.out.println("Database deletion error: " + resultsException.getMessage());
        }
    }


}
