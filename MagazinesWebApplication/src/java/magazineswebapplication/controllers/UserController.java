/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.controllers;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import magazineswebapplication.dbmanagers.DataBaseController;
import magazineswebapplication.dbmanagers.PostMagazineDBManager;
import magazineswebapplication.dbmanagers.ProfileDBManager;
import magazineswebapplication.dbmanagers.UserDBManager;
import magazineswebapplication.dummyclasses.Rate;
import magazineswebapplication.dummyclasses.User;

/**
 *
 * @author zofia
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet{
     private DataBaseController dataBase = new DataBaseController();
     private Connection connection;
     private static final String SELECT_QUERY = "SELECT * FROM Magazine WHERE Charge > 0 AND BlockSubscription = 0;"; //Query
     private static final String SELECT_EDITOR = "SELECT m.MagazineId, m.Name, m.Autor, m.Description, m.Price, m.BlockLike, "
             + "m.BlockCommentary, m.BlockSubscription, m.CostoDia, m.Charge FROM Magazine m INNER JOIN Post p ON (m.MagazineId = p.MagazineId) "
             + "WHERE m.Charge > 0 AND m.BlockSubscription = 0 AND NOT p.Username = '";
     private static final String SELECT_BY_CHARGE = "SELECT * FROM Magazine WHERE Charge = 0;";
     private static final String SELECT_PROFILE_QUERY = "SELECT * FROM Profile WHERE Username = '";
     
     
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         this.connection = dataBase.connectToDataBase();
         UserDBManager manager = new UserDBManager(this.connection);
         ProfileDBManager profile = new ProfileDBManager(this.connection);
         PostMagazineDBManager magazine = new PostMagazineDBManager(this.connection);
         try {
            User user = manager.validateLogIn(request.getParameter("usernameLogIn"), request.getParameter("passwordLogIn"));
            request.getSession().setAttribute("User", user.getUsername());
            request.getSession().setAttribute("Type", profile.filterType(user.getType()));
            request.getSession().setAttribute("ProfileInformation", profile.getProfiles(SELECT_PROFILE_QUERY + user.getUsername() + "';"));
            switch(user.getType()) {
                case "Subscriber":
                    request.getSession().setAttribute("magazineList", magazine.getMagazines(SELECT_QUERY));
                    RequestDispatcher dispatcher = request.getRequestDispatcher("subscriber-home.jsp");
                    dispatcher.forward(request, response);
                break;
                case "Editor":
                    request.getSession().setAttribute("magazineList", magazine.getMagazines(SELECT_EDITOR + user.getUsername() + "';"));
                    dispatcher = request.getRequestDispatcher("editor-header.jsp");
                    dispatcher.forward(request, response);
                break;
                case "Manager":
                    Rate rate = magazine.getRate("SELECT * FROM Rate;");
                    request.getSession().setAttribute("globalCost", rate.getGlobalPrice());
                    request.getSession().setAttribute("globalDayCost", rate.getCostoDiaGlobal());
                    request.getSession().setAttribute("magazineList", magazine.getMagazines(SELECT_BY_CHARGE));
                    dispatcher = request.getRequestDispatcher("manager-home.jsp");
                    dispatcher.forward(request, response);
            }
         } catch(Exception e) {
            request.setAttribute("error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response); 
         }
     }
     
     
}
