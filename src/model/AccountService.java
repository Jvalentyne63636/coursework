package model;

import javax.jws.WebParam;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountService {

    public static void selectAll(List<Account> targetList, DatabaseConnection database) {
        PreparedStatement statement = database.newStatement("SELECT * FROM Account");

        try {
            if (statement != null) {
                ResultSet results = database.executeQuery(statement);

                if (results == null) {
                    return;
                }

                while (results.next()){
                    int id = results.getInt("id");
                    int artistid = results.getInt("artistid");
                    String firstName = results.getString("firstName");
                    String lastName = results.getString("lastName");
                    String email = results.getString("email");
                    String password = results.getString("password");

                    Account account = new Account(id, artistid, firstName, lastName, email, password);
                    targetList.add(account);
                }
            }
        } catch (SQLException resultsException){
            System.out.println("Database select all error: " + resultsException.getMessage());
        }

    }

    public static Account selectById(int id, DatabaseConnection database) {

        Account result = null;

        PreparedStatement statement = database.newStatement("SELECT * FROM Account WHERE id = ?");

        try {
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    int accountid = results.getInt("id");
                    int artistid = results.getInt("artistid");
                    String firstName = results.getString("firstName");
                    String lastName = results.getString("lastName");
                    String email = results.getString("email");
                    String password = results.getString("password");

                    result = new Account(id, artistid, firstName, lastName, email, password);
                }
            }
        } catch(SQLException resultsException){
            System.out.println("Database select by id error: " + resultsException.getMessage());
        }
        return result;
    }

    public static void save(Account accountToSave, DatabaseConnection database) {
        Account existingItem = null;

        if (accountToSave.getId() != 0) {
            existingItem = selectById(accountToSave.getId(), database);
        }

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO Account VALUES (null, Artistid = ?, FirstName = ?, LastName = ?, Email = ?, Password = ?)");
                statement.setInt(1, accountToSave.getArtistid());
                statement.setString(2, accountToSave.getFirstName());
                statement.setString(3, accountToSave.getLastName());
                statement.setString(4, accountToSave.getEmail());
                statement.setString(5, accountToSave.getPassword());
                database.executeUpdate(statement);
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE Account SET ArtistID = ?, FirstName = ?, LastName = ?, Email = ?, Password = ?  WHERE id = ?");
                statement.setInt(1, accountToSave.getArtistid());
                statement.setString(2, accountToSave.getFirstName());
                statement.setString(3, accountToSave.getLastName());
                statement.setString(4, accountToSave.getEmail());
                statement.setString(5, accountToSave.getPassword());
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


