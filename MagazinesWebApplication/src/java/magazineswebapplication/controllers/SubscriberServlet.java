/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
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
import magazineswebapplication.dummyclasses.Commentary;
import magazineswebapplication.dummyclasses.Magazine;
import magazineswebapplication.dummyclasses.Post;
import magazineswebapplication.dummyclasses.Subscription;

/**
 *
 * @author zofia
 */
@WebServlet("/SubscriberServlet")
public class SubscriberServlet extends HttpServlet{
    private DataBaseController dataBase = new DataBaseController();
    private Connection connection;
     private List<Subscription> subscriptions = new ArrayList<>();
    private List<Commentary> commentaries = new ArrayList<>();
     private List<Magazine> magazines = new ArrayList<>();
     private List<Post> posts = new ArrayList<>();
     private static final String SELECT_SUBSCRIPTIONS = "SELECT * FROM Subscription WHERE Username = '";
     private static final String SELECT_POST = "SELECT * FROM Post;";
     private static final String SELECT_MAGAZINE = "SELECT * FROM Magazine WHERE MagazineId = '";
     private static final String SELECT_POST_BY_ID = "SELECT * FROM Post WHERE MagazineId = '";
     private static final String SELECT_COMMENTARIES = "SELECT c.CommentaryId, c.SubscriptionId, c.Commentary, "
             + "c.CommentaryDate FROM Post p INNER JOIN Subscription s ON (p.IdPost = s.IdPost) "
             + "INNER JOIN Commentary c ON (s.SubscriptionId = c.SubscriptionId) WHERE p.MagazineId = '";
     
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = dataBase.connectToDataBase();
        SubscriptionDBManager subscription = new SubscriptionDBManager(this.connection);
        PostMagazineDBManager magazine = new PostMagazineDBManager(this.connection);
        String username = request.getSession().getAttribute("User").toString();
        this.subscriptions = subscription.getSubscriptions(SELECT_SUBSCRIPTIONS + username + "';");
        this.posts = magazine.getVersions(SELECT_POST);
        request.setAttribute("subscriptions", getPostBySubscription(subscriptions, posts, magazine)); 
        RequestDispatcher dispatcher = request.getRequestDispatcher("subscriptions.jsp");
        dispatcher.forward(request, response); 
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = dataBase.connectToDataBase();
        String magazineId = request.getParameter("r");
        PostMagazineDBManager magazine = new PostMagazineDBManager(this.connection);
        SubscriptionDBManager subscription = new SubscriptionDBManager(this.connection);
        this.magazines = magazine.getMagazines(SELECT_MAGAZINE + magazineId + "';");
        this.posts = magazine.getVersions(SELECT_POST_BY_ID + magazineId + "';");
        this.commentaries = subscription.getCommentaries(SELECT_COMMENTARIES + magazineId + "';");
        request.setAttribute("magazine", magazines);
        request.setAttribute("version", posts);
        request.setAttribute("commentaries", commentaries);
        RequestDispatcher dispatcher = request.getRequestDispatcher("magazine.jsp");
        dispatcher.forward(request, response); 
    }
    
                      
    public List<Magazine> getPostBySubscription(List<Subscription> subscriptions, List<Post> posts, 
        PostMagazineDBManager magazine) {
        List<Magazine> temporal = new ArrayList<>();
        for(Subscription subscription : subscriptions) {
            for(Post post : posts) {
                if(post.getIdPost().equals(subscription.getIdPost())) {
                temporal.add(magazine.getMagazineInList(post.getMagazineName()));
                }
            }
        }
        return temporal;
    }
}
