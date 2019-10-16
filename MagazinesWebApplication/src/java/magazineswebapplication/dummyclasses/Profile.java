/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.dummyclasses;

import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author zofia
 */
public class Profile {
    private String username;
    private String name;
    private String lastName;
    private LocalDate bornDate;
    private String gender;
    private String description;
    private String photoUrl;
    private String preferences;
    private String hobbies;
    private String favoriteTopics;

    public Profile(String username, String name, String lastName, LocalDate bornDate, String gender, 
            String description, String photoUrl, String preferences, String hobbies, String favoriteTopics) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.bornDate = bornDate;
        this.gender = gender;
        this.description = description;
        this.photoUrl = photoUrl;
        this.preferences = preferences;
        this.hobbies = hobbies;
        this.favoriteTopics = favoriteTopics;
    }

    public Profile(HttpServletRequest request, LocalDate bornDate, String photoUrl) {
        this.username = request.getParameter("username");
        this.name = request.getParameter("name");
        this.lastName = request.getParameter("lastName");
        this.bornDate = bornDate;
        this.gender = request.getParameter("gender");
        this.description = request.getParameter("description");
        this.photoUrl = photoUrl;
        this.preferences = request.getParameter("preferences");
        this.hobbies = request.getParameter("hobbies");
        this.favoriteTopics = request.getParameter("topics");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getFavoriteTopics() {
        return favoriteTopics;
    }

    public void setFavoriteTopics(String favoriteTopics) {
        this.favoriteTopics = favoriteTopics;
    }
    
}
