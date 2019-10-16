/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
import magazineswebapplication.dbmanagers.UserDBManager;
import magazineswebapplication.dummyclasses.FirstReportDTO;
import magazineswebapplication.dummyclasses.Magazine;
import magazineswebapplication.dummyclasses.User;

/**
 *
 * @author zofia
 */
@WebServlet("/EditorServlet")
public class EditorServlet extends HttpServlet{
    private List<FirstReportDTO> reportCommentaries = new ArrayList<>();
    private DataBaseController dataBase = new DataBaseController();
    private Connection connection;
    private static final String MAGAZINES_QUERY = "SELECT * FROM Post WHERE Username = '";   
    private static final String QUERY = "SELECT * FROM Magazine;";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = dataBase.connectToDataBase();
        List<Magazine> temporal;
        PostMagazineDBManager magazine = new PostMagazineDBManager(this.connection);
        UserDBManager manager = new UserDBManager(this.connection);
        User user = manager.getUserInList(request.getSession().getAttribute("User").toString());
        String query = MAGAZINES_QUERY + user.getUsername() + "';";
        temporal = magazine.getMagazineByPost(magazine.getVersions(query), magazine.getMagazines(QUERY));
        request.setAttribute("UploadedMagazines", temporal);
        RequestDispatcher dispatcher = request.getRequestDispatcher("uploaded-magazines.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = dataBase.connectToDataBase();
        String firstDate = request.getParameter("initialDate");
        String lastDate = request.getParameter("finalDate");
        try {
            int selectedReport = Integer.valueOf(request.getParameter("report"));
            getSelectedReport(request, selectedReport, firstDate, lastDate, request.getSession().getAttribute("User").toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher("editor-reports.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("editor-reports.jsp");
            dispatcher.forward(request, response); 
        }
        
    }
    
    public void getSelectedReport(HttpServletRequest request, int selectedReport, String initialDate, String finalDate, String username) throws SQLException, Exception {
        SubscriptionDBManager manager = new SubscriptionDBManager(this.connection);
        String query;
        int flag;
        switch(selectedReport) {
            case 1:
                if((initialDate.equals("") || initialDate.equals(" ")) && (finalDate.equals("") || finalDate.equals(" "))) {
                    if(request.getParameter("filter").equals("") || request.getParameter("filter").equals(" ")) {
                        query = "SELECT s.Username, c.Commentary, m.Name, c.CommentaryDate FROM "
                                + "Magazine m INNER JOIN Post p ON (m.MagazineId = p.MagazineId) INNER JOIN Subscription s ON (p.IdPost = s.IdPost) "
                                + "INNER JOIN Commentary c ON (s.SubscriptionId = c.SubscriptionId) WHERE p.Username = '" + username + "';";
                    } else {
                        query = "SELECT s.Username, c.Commentary, m.Name, c.CommentaryDate FROM Magazine m "
                                + "INNER JOIN Post p ON (m.MagazineId = p.MagazineId) INNER JOIN Subscription s ON (p.IdPost = s.IdPost) "
                                + "INNER JOIN Commentary c ON (s.SubscriptionId = c.SubscriptionId) WHERE m.Name = '" + request.getParameter("filter") 
                                + "' AND p.Username = '" + username + "';";
                    }
                } else {
                    if(request.getParameter("filter").equals("") || request.getParameter("filter").equals(" ")) {
                        query = "SELECT s.Username, c.Commentary, m.Name, c.CommentaryDate FROM "
                                + "Magazine m INNER JOIN Post p ON (m.MagazineId = p.MagazineId) INNER JOIN Subscription s ON (p.IdPost = s.IdPost) "
                                + "INNER JOIN Commentary c ON (s.SubscriptionId = c.SubscriptionId) WHERE c.CommentaryDate "
                                + "BETWEEN '" + initialDate + "' AND '" + finalDate + "' AND p.Username = '" + username + "';";
                    } else {
                        query = "SELECT s.Username, c.Commentary, m.Name, c.CommentaryDate FROM Magazine m "
                                + "INNER JOIN Post p ON (m.MagazineId = p.MagazineId) INNER JOIN Subscription s ON (p.IdPost = s.IdPost) "
                                + "INNER JOIN Commentary c ON (s.SubscriptionId = c.SubscriptionId) WHERE m.Name = '"
                                + request.getParameter("filter") + "' AND c.CommentaryDate BETWEEN '" + initialDate + "' AND '" + finalDate 
                                + "' AND p.Username = '" + username + "';";
                    }                   
                }
                this.reportCommentaries = manager.getReportCommentaries(query);
                flag = 1;
                request.setAttribute("First", this.reportCommentaries);
                request.setAttribute("flag", flag);
            break;
            case 2:
                if((initialDate.equals("") || initialDate.equals(" ")) && (finalDate.equals("") || finalDate.equals(" "))) {
                    if(request.getParameter("filter").equals("") || request.getParameter("filter").equals(" ")) {
                        query = "SELECT s.Username, m.Name, p.Version, s.SubscriptionDate FROM "
                                + "Magazine m INNER JOIN Post p ON (m.MagazineId = p.MagazineId) INNER JOIN "
                                + "Subscription s ON (p.IdPost = s.IdPost) WHERE p.Username = '" + username + "';";
                    } else {
                        query = "SELECT s.Username, m.Name, p.Version, s.SubscriptionDate FROM Magazine m "
                                + "INNER JOIN Post p ON (m.MagazineId = p.MagazineId) INNER JOIN Subscription s ON (p.IdPost = s.IdPost) "
                                + "WHERE m.Name = '" + request.getParameter("filter") + "' AND p.Username = '" + username + "';";
                    }
                } else {
                    if(request.getParameter("filter").equals("") || request.getParameter("filter").equals(" ")) {
                        query = "SELECT s.Username, m.Name, p.Version, s.SubscriptionDate FROM "
                                + "Magazine m INNER JOIN Post p ON (m.MagazineId = p.MagazineId) INNER JOIN Subscription s ON (p.IdPost = s.IdPost) "
                                + "WHERE s.SubscriptionDate BETWEEN '" + initialDate + "' AND '" + finalDate 
                                + "' AND p.Username = '" + username + "';";
                    } else {
                        query = "SELECT s.Username, m.Name, p.Version, s.SubscriptionDate FROM Magazine m "
                                + "INNER JOIN Post p ON (m.MagazineId = p.MagazineId) INNER JOIN Subscription s ON (p.IdPost = s.IdPost) "
                                + "WHERE m.Name = '"+ request.getParameter("filter") + "' AND s.SubscriptionDate BETWEEN '" 
                                + initialDate + "' AND '" + finalDate + "' AND p.Username = '" + username + "';";
                    }                   
                }
                flag = 2;
                request.setAttribute("Second", manager.getReportSubscriptions(query));
                request.setAttribute("flag", flag);
            break;
            case 3:
                String temporal = "SELECT sum((p.Payment - s.Charge)*(SELECT count(Payment) FROM Payment "
                        + "WHERE SubscriptionId = s.SubscriptionId)) as Total FROM Post INNER JOIN Subscription s ON (Post.IdPost = s.IdPost) "
                        + "INNER JOIN Payment p ON (s.SubscriptionId = p.SubscriptionId) INNER JOIN Magazine m ON (m.MagazineId = Post.MagazineId) "
                        + "WHERE Post.Username = '";
                 if((initialDate.equals("") || initialDate.equals(" ")) && (finalDate.equals("") || finalDate.equals(" "))) {
                    if(request.getParameter("filter").equals("") || request.getParameter("filter").equals(" ")) {
                        query = "SELECT s.Username, m.Name, p.Version, ((pay.Payment - s.Charge)* (SELECT count(Payment) "
                                + "FROM Payment WHERE SubscriptionId = s.SubscriptionId)) as Profit FROM Magazine m "
                                + "INNER JOIN Post p ON (m.MagazineId = p.MagazineId) INNER JOIN Subscription s ON (p.IdPost = s.IdPost) "
                                + "INNER JOIN Payment pay ON (s.SubscriptionId = pay.SubscriptionId) WHERE p.Username = '" + username + "';";
                        temporal += username + "';";
                    } else {
                        query = "SELECT s.Username, m.Name, p.Version, ((pay.Payment - s.Charge)* (SELECT count(Payment) "
                                + "FROM Payment WHERE SubscriptionId = s.SubscriptionId)) as Profit FROM Magazine m "
                                + "INNER JOIN Post p ON (m.MagazineId = p.MagazineId) INNER JOIN Subscription s ON (p.IdPost = s.IdPost) "
                                + "INNER JOIN Payment pay ON (s.SubscriptionId = pay.SubscriptionId) WHERE m.Name = '" 
                                + request.getParameter("filter") + "' AND p.Username = '" + username + "';";
                        temporal += username + "' AND m.Name = '" + request.getParameter("filter") + "';";
                    }
                } else {
                    if(request.getParameter("filter").equals("") || request.getParameter("filter").equals(" ")) {
                        query = "SELECT s.Username, m.Name, p.Version, ((pay.Payment - s.Charge)* (SELECT count(Payment) "
                                + "FROM Payment WHERE SubscriptionId = s.SubscriptionId)) as Profit FROM Magazine m "
                                + "INNER JOIN Post p ON (m.MagazineId = p.MagazineId) INNER JOIN Subscription s ON (p.IdPost = s.IdPost) "
                                + "INNER JOIN Payment pay ON (s.SubscriptionId = pay.SubscriptionId) WHERE s.SubscriptionDate BETWEEN '" 
                                + initialDate + "' AND '" + finalDate + "' AND p.Username = '" + username + "';";
                        temporal += username + "' AND s.SubscriptionDate BETWEEN '" 
                                + initialDate + "' AND '" + finalDate + "';";
                    } else {
                        query = "SELECT s.Username, m.Name, p.Version, ((pay.Payment - s.Charge)* (SELECT count(Payment) "
                                + "FROM Payment WHERE SubscriptionId = s.SubscriptionId)) as Profit FROM Magazine m "
                                + "INNER JOIN Post p ON (m.MagazineId = p.MagazineId) INNER JOIN Subscription s ON (p.IdPost = s.IdPost) "
                                + "INNER JOIN Payment pay ON (s.SubscriptionId = pay.SubscriptionId) WHERE m.Name = '"
                                + request.getParameter("filter") + "' AND s.SubscriptionDate BETWEEN '" + initialDate 
                                + "' AND '" + finalDate + "' AND p.Username = '" + username + "';";
                        temporal += username + "' AND m.Name = '" + request.getParameter("filter") + "' AND s.SubscriptionDate BETWEEN '" 
                                + initialDate + "' AND '" + finalDate + "';";
                    }                   
                }
                flag = 3;
                request.setAttribute("Third", manager.getReportEditorProfit(query));
                request.setAttribute("flag", flag);
                request.setAttribute("total", manager.getEditorTotalProfit(temporal));
            break;
            default:
                throw new Exception("Seleccione un reporte");
        }
    }
       
}
