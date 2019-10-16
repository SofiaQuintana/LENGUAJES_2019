/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import magazineswebapplication.dbmanagers.DataBaseController;
import magazineswebapplication.dbmanagers.ProfileDBManager;
import magazineswebapplication.dummyclasses.Profile;

/**
 *
 * @author zofia
 */
@WebServlet("/ProfileController")
public class ProfileController extends HttpServlet {
    private DataBaseController dataBase = new DataBaseController();
    private Connection connection;
    private String query;
    private static final String UPDATE_QUERY = "UPDATE Profile SET ";
    private static final String WHERE = "' WHERE Username = '";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = dataBase.connectToDataBase();
        ProfileDBManager manager = new ProfileDBManager(this.connection);
        List<Profile> profile = (List<Profile>) request.getSession().getAttribute("ProfileInformation");
          
        for(Profile userProfile : profile) {                
            String user = userProfile.getUsername();
            if(!userProfile.getName().equals(request.getParameter("name"))) {
                query = UPDATE_QUERY + "Name = '" + request.getParameter("name") + WHERE +user+ "';";
                dataBase.updateElement(query);
            } 
            if(!userProfile.getLastName().equals(request.getParameter("lastName"))) {
                query = UPDATE_QUERY + "LastName = '" + request.getParameter("lastName") + WHERE + user + "';";
                dataBase.updateElement(query);
            } 
            if(!userProfile.getDescription().equals(request.getParameter("description"))) {
                query = UPDATE_QUERY + "Description = '" + request.getParameter("description") + WHERE + user + "';";
                dataBase.updateElement(query);
            } 
            if(!userProfile.getPreferences().equals(request.getParameter("preferences"))) {
                query = UPDATE_QUERY + "Preferences = '" + request.getParameter("preferences") + WHERE + user + "';";
                dataBase.updateElement(query);
            } 
            if(!userProfile.getFavoriteTopics().equals(request.getParameter("topics"))) {
                query = UPDATE_QUERY + "FavoriteTopics = '" + request.getParameter("topics") + WHERE + user + "';";
                dataBase.updateElement(query);
            } 
            if(!userProfile.getHobbies().equals(request.getParameter("hobbies"))) {
                query = UPDATE_QUERY + "Hobbies = '" + request.getParameter("hobbies") + WHERE + user + "';";
                dataBase.updateElement(query);
            }
            query = "SELECT * FROM Profile WHERE Username = '" + user + "';";           
            request.getSession().setAttribute("ProfileInformation", manager.getProfiles(query));
        }        
        RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
        dispatcher.forward(request, response);            
    }

}
