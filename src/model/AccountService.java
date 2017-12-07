package model;

import javax.jws.WebParam;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountService {

    public static void selectAll(List<Account> targetList, DatabaseConnection database) {
        PreparedStatement statement = database.newStatement("SELECT x, y, z FROM Table ORDER by X");

        try {
            if (statement != null) {
                ResultSet results = database.executeQuery(statement);

                if (results !=null){
                    while (results.next()){
                        targetList.add(new Account(results.getInt("x"), results.getString("Y"), results.getString("Z")));
                    }
                }
            }
        } catch (SQLException resultsException){
            System.out.println("Database select all error: " + resultsException.getMessage());
        }

    }

    public static Account selectById(int id, DatabaseConnection database) {
        Account result = null;
        PreparedStatement statement = database.newStatement("SELECT x, y, z FROM Table WHERE id = ?");
        try {
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    result = new Account(results.getInt("x"), results.getString("y"), results.getString("z"));
                }
            }
        } catch(SQLException resultsException){
            System.out.println("Database select by id error: " + resultsException.getMessage());
        }
        return result;
    }

    public static void save(Account account, DatabaseConnection database) {
        Account existingAccount = selectById(account.getAccountid(), database);

        if (existingAccount == null) {
            PreparedStatement statement = database.newStatement("INSERT INTO Table (a, b, c)) VALUES (?, ?, ?)");

            database.executeQuery(statement);
        }










        Account existingItem = null;
        if (itemToSave.getId() != 0) existingItem = selectById(itemToSave.getId(), database);

        if (existingItem == null) {
            PreparedStatement statement = database.newStatement("INSERT INTO Table (a, b, c)) VALUES (?, ?, ?)");
        }
    }
    public static void deleteById(int id, DatabaseConnection database) {

            PreparedStatement statement = database.newStatement("DELETE FROM TABLE WHERE id = ?");

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


