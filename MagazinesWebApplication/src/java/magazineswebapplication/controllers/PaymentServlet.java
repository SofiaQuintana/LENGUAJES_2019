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
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import magazineswebapplication.dbmanagers.DataBaseController;
import magazineswebapplication.dbmanagers.PostMagazineDBManager;
import magazineswebapplication.dbmanagers.SubscriptionDBManager;
import magazineswebapplication.dummyclasses.Magazine;
import magazineswebapplication.dummyclasses.Payment;
import magazineswebapplication.dummyclasses.Post;
import magazineswebapplication.dummyclasses.Subscription;

/**
 *
 * @author zofia
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet{
    private DataBaseController dataBase = new DataBaseController();
    private Connection connection;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = dataBase.connectToDataBase();
        SubscriptionDBManager subscriptions = new SubscriptionDBManager(this.connection);
        List<Magazine> magazines = (List<Magazine>) request.getSession().getAttribute("selectedMagazine");
        List<Post> posts = (List<Post>) request.getSession().getAttribute("selectedVersion");
        try {
            for(Magazine magazine : magazines) {
                for(Post post : posts) {
                    LocalDate date = dataBase.parseDate(request);
                    Date parsedDate = Date.valueOf(date);
                    Subscription subscription = new Subscription(request, post.getIdPost(), magazine.getCharge(), date);
                    subscriptions.addSubscription(subscription, parsedDate);
                    Payment payment = new Payment(subscription.getSubscriptionId(), dataBase.concatenateDate(request), magazine.getPrice());
                    subscriptions.addPayment(payment, parsedDate);
                }
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("subscriber-home.jsp");
            dispatcher.forward(request, response); 
        } catch(Exception e) {
            request.setAttribute("error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("payment.jsp");
            dispatcher.forward(request, response); 
        }
    }
}
