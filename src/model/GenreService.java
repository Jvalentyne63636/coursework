package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GenreService {

    public static void selectAll(List<Genre> targetList, DatabaseConnection database) {
        PreparedStatement statement = database.newStatement("SELECT * FROM Account");

        try {
            if (statement != null) {
                ResultSet results = database.executeQuery(statement);

                if (results == null) {
                    return;
                }

                while (results.next()){
                    int id = results.getInt("id");
                    String name = results.getString("name");


                    Genre genre = new Genre(id, name);
                    targetList.add(genre);
                }
            }
        } catch (SQLException resultsException){
            System.out.println("Database select all error: " + resultsException.getMessage());
        }

    }

    public static Genre selectById(int id, DatabaseConnection database) {

        Genre result = null;

        PreparedStatement statement = database.newStatement("SELECT * FROM Account WHERE id = ?");

        try {
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    id = results.getInt("id");
                    String name = results.getString("name");

                    result = new Genre(id, name);
                }
            }
        } catch(SQLException resultsException){
            System.out.println("Database select by id error: " + resultsException.getMessage());
        }
        return result;
    }

    public static void save(Genre GenreToSave, DatabaseConnection database) {
        Genre existingItem = null;

        if (GenreToSave.getId() != 0) {
            existingItem = selectById(GenreToSave.getId(), database);
        }

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO Account VALUES (null, ?, ?)");
                statement.setInt(1, GenreToSave.getId());
                statement.setString(2, GenreToSave.getName());
                database.executeUpdate(statement);
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE Account SET Id = ?, Name = ? WHERE id = ?");
                statement.setInt(1, GenreToSave.getId());
                statement.setString(2, GenreToSave.getName());
                database.executeUpdate(statement);
            }

        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }

    public static void deleteById(int id, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("DELETE FROM Account WHERE id = ?");

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
