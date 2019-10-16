/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.dbmanagers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import magazineswebapplication.dummyclasses.Subscription;
import magazineswebapplication.dummyclasses.User;
import magazineswebapplication.exceptions.UserException;

/**
 *
 * @author zofia
 */
public class UserDBManager {
    private Connection connection;
    private List<User> users = new ArrayList<>();
    private static final String SELECT_USER_QUERY = "SELECT * FROM User WHERE Username = ";

    public UserDBManager(Connection connection) {
        this.connection = connection;
    }
    
    public List<User> getUsers(String query) {
        users.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String username = result.getString("Username");
                String password = result.getString("Password");
                String type = result.getString("Type");
                User user = new User(username, password, type);
                users.add(user);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    public User getUserInList(String username) {
        User user;
        String userQuery = SELECT_USER_QUERY + "'"+ username + "';";
        this.users = getUsers(userQuery);
        if(this.users.isEmpty()) {
            user = null;
            return user;
        } else {
            user = this.users.get(0);
            return user;
        } 
    }
    
    public User validateLogIn(String username, String password) throws Exception{
        User user = getUserInList(username);
        if(user.getPassword().equals(password)) {
            return user;
        } else {
            throw new Exception("Password Incorrecto, intentelo de nuevo");
        }
    }
    
    public void addUser(User user) throws UserException {
        try {
            String query = ("INSERT INTO User (Username, Password, Type) VALUES (?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, user.getUsername());
            object.setString(2, user.getPassword());
            object.setString(3, user.getType());
            object.execute();

        } catch(SQLException e) {
            throw new UserException("Este usuario ya existe, debe elegir uno nuevo.");
        }
    }
    
}

