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
import magazineswebapplication.dummyclasses.Profile;
import magazineswebapplication.exceptions.BlankSpaceException;

/**
 *
 * @author zofia
 */
public class ProfileDBManager {
    private Connection connection;
    private List<Profile> profiles = new ArrayList<>();
    private static final String SELECT_PROFILE_QUERY = "SELECT * FROM Profile WHERE PhotoUrl = '----' ";
    

    public ProfileDBManager(Connection connection) {
        this.connection = connection;
    }

    public List<Profile> getProfiles(String query) {
        profiles.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String username = result.getString("Username");
                String name = result.getString("Name");
                String lastName = result.getString("LastName");
                Date bornDate = result.getDate("BornDate");
                LocalDate date = bornDate.toLocalDate();
                String gender = result.getString("Gender");
                String description = result.getString("Description");
                String photo = result.getString("PhotoUrl");
                String preferences = result.getString("Preferences");
                String hobbies = result.getString("Hobbies");
                String topics = result.getString("FavoriteTopics");

                Profile profile = new Profile(username, name, lastName, date, gender, description, photo, 
                        preferences, hobbies, topics);
                profiles.add(profile);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return profiles;
    }
    
    public Profile getProfileInList() {
        Profile profile;
        this.profiles = getProfiles(SELECT_PROFILE_QUERY);
        if(this.profiles.isEmpty()) {
            profile = null;
            return profile;
        } else {
            profile = this.profiles.get(0);
            return profile;
        } 
    }
    
    public void validateProfileRegistration(Profile profile, Date date) throws Exception{
        if(profile.getName().equals("") || profile.getLastName().equals("")) {
            throw new BlankSpaceException("Ingrese todos los elementos marcados con *");
        } else {           
            if(date == null) {
                throw new Exception("Ingrese una fecha correcta");
            } else {
                addProfile(profile, date);
            }
        }
    }
    
    public void addProfile(Profile profile, Date bornDate) {
        try {
            String query = ("INSERT INTO Profile (Username, Name, LastName, BornDate, Gender, "
                    + "Description, PhotoUrl, Preferences, Hobbies, FavoriteTopics) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, profile.getUsername());
            object.setString(2, profile.getName());
            object.setString(3, profile.getLastName());
            object.setDate(4, bornDate);
            object.setString(5, profile.getGender());
            object.setString(6, profile.getDescription());
            object.setString(7, profile.getPhotoUrl());
            object.setString(8, profile.getPreferences());
            object.setString(9, profile.getHobbies());
            object.setString(10, profile.getFavoriteTopics());

            object.execute();

        } catch(SQLException e) {
        }
    }
    
    public int filterType(String type) {
        int filteredType = 0;
        switch(type) {
            case "Subscriber":
                filteredType = 1;
            break;
            case "Editor":
                filteredType = 2;
            break;
            case "Manager":
                filteredType = 3;
            break;
        }
        return filteredType;
    }
}
