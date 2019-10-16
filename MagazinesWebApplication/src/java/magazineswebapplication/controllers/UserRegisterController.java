/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.controllers;

import magazineswebapplication.dbmanagers.DataBaseController;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import magazineswebapplication.dbmanagers.ProfileDBManager;
import magazineswebapplication.dbmanagers.UserDBManager;
import magazineswebapplication.dummyclasses.Profile;
import magazineswebapplication.dummyclasses.User;
import magazineswebapplication.exceptions.UserException;

/**
 *
 * @author zofia
 */

@WebServlet("/UserRegisterController")
public class UserRegisterController extends HttpServlet{
    private DataBaseController dbConnection = new DataBaseController();
    private Connection connection;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = dbConnection.connectToDataBase();
        User user = new User(request);
        ProfileDBManager profile = new ProfileDBManager(this.connection);
        Date date = Date.valueOf(dbConnection.parseDate(request));
        UserDBManager userRegister = new UserDBManager(this.connection);
        try {
            userRegister.addUser(user);
            Profile userProfile = new Profile(request, dbConnection.parseDate(request), "----");
            profile.validateProfileRegistration(userProfile, date);
            RequestDispatcher dispatcher = request.getRequestDispatcher("uploading-photo.jsp");
            dispatcher.forward(request, response);
        } catch(UserException exception) {
            request.setAttribute("error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            dbConnection.updateElement("DELETE FROM User WHERE Username = '" + user.getUsername() + "';");
            request.setAttribute("exception", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = dbConnection.connectToDataBase();
        User user = new User(request, "Manager");
        UserDBManager userRegister = new UserDBManager(connection);
        try {
            userRegister.addUser(user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("create-manager.jsp");
            dispatcher.forward(request, response);
        } catch(UserException e) {
            request.setAttribute("error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("create-manager.jsp");
            dispatcher.forward(request, response);
        }
    }
}
