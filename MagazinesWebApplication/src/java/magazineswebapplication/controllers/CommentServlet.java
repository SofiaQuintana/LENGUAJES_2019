/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.controllers;

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
import magazineswebapplication.dbmanagers.DataBaseController;
import magazineswebapplication.dbmanagers.PostMagazineDBManager;
import magazineswebapplication.dbmanagers.SubscriptionDBManager;
import magazineswebapplication.dummyclasses.Commentary;
import magazineswebapplication.dummyclasses.Subscription;

/**
 *
 * @author zofia
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet{
    private DataBaseController dataBase = new DataBaseController();
    private Connection connection;
    private static final String SELECT_QUERY = "SELECT * FROM Subscription WHERE Username = '";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = dataBase.connectToDataBase();
        SubscriptionDBManager subscription = new SubscriptionDBManager(this.connection);
        String username = request.getSession().getAttribute("User").toString();
        String magazineId = request.getParameter("m");
        String query = SELECT_QUERY + username + "' AND IdPost = '" + magazineId + "';";
        try {
            Subscription sub = subscription.getSubscriptions(query).get(0);
            LocalDate date = dataBase.parseDate(request);
            Commentary commentary = new Commentary(request, username, sub.getSubscriptionId(), date);
            subscription.addCommentary(commentary, Date.valueOf(date));
            RequestDispatcher dispatcher = request.getRequestDispatcher("subscriptions.jsp");
            dispatcher.forward(request, response); 
        } catch(Exception e) {
            request.setAttribute("Error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("subscriptions.jsp");
            dispatcher.forward(request, response); 
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
}
