/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import magazineswebapplication.dbmanagers.DataBaseController;
import magazineswebapplication.dbmanagers.PostMagazineDBManager;
import magazineswebapplication.dbmanagers.SubscriptionDBManager;

/**
 *
 * @author zofia
 */
@WebServlet("/ChargesServlet")
public class ChargesServlet extends HttpServlet{
    private DataBaseController dataBase = new DataBaseController();
    private Connection connection;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String magazineId = request.getParameter("charge");
        this.connection = dataBase.connectToDataBase();
        PostMagazineDBManager magazine = new PostMagazineDBManager(this.connection);
        String query = "SELECT * FROM Magazine WHERE MagazineId = '" + magazineId + "';";
        request.getSession().setAttribute("selectedMagazine", magazine.getMagazines(query));
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-magazine-charges.jsp");
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("manager-reports.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manager-reports.jsp");
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
                flag = 1;
                request.setAttribute("First",  manager.getReportCommentaries(query));
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
                String temporal = "SELECT SUM(p.Payment) as TotalIngreso, SUM((s.Charge)*(SELECT COUNT(Payment) "
                        + "FROM Payment WHERE SubscriptionId = s.SubscriptionId)) as TotalProfit FROM Magazine m "
                        + "INNER JOIN Post ON (m.MagazineId = Post.MagazineId) INNER JOIN Subscription s ON (Post.IdPost = s.IdPost) "
                        + "INNER JOIN Payment p ON (s.SubscriptionId = p.SubscriptionId)";
                 if((initialDate.equals("") || initialDate.equals(" ")) && (finalDate.equals("") || finalDate.equals(" "))) {
                    if(request.getParameter("filter").equals("") || request.getParameter("filter").equals(" ")) {
                        query = "SELECT s.Username, m.Name, p.Version, (SELECT sum(Payment) FROM Payment "
                                + "WHERE SubscriptionId = s.SubscriptionId) as Ingreso, ((s.Charge) * (SELECT count(Payment) "
                                + "FROM Payment WHERE SubscriptionId = s.SubscriptionId)) as Profit FROM Magazine m "
                                + "INNER JOIN Post p ON (m.MagazineId = p.MagazineId) INNER JOIN Subscription s ON (p.IdPost = s.IdPost) "
                                + "INNER JOIN Payment pay ON (s.SubscriptionId = pay.SubscriptionId);";
                        temporal += ";";
                    } else {
                        query = "SELECT s.Username, m.Name, p.Version, (SELECT sum(Payment) FROM Payment "
                                + "WHERE SubscriptionId = s.SubscriptionId) as Ingreso, ((s.Charge) * (SELECT count(Payment) "
                                + "FROM Payment WHERE SubscriptionId = s.SubscriptionId)) as Profit FROM Magazine m "
                                + "INNER JOIN Post p ON (m.MagazineId = p.MagazineId) INNER JOIN Subscription s ON (p.IdPost = s.IdPost) "
                                + "INNER JOIN Payment pay ON (s.SubscriptionId = pay.SubscriptionId) WHERE m.Name = '" 
                                + request.getParameter("filter") + "'";
                        temporal += " WHERE m.Name = '" + request.getParameter("filter") + "';";
                    }
                } else {
                    if(request.getParameter("filter").equals("") || request.getParameter("filter").equals(" ")) {
                        query = "SELECT s.Username, m.Name, p.Version, (SELECT sum(Payment) FROM Payment "
                                + "WHERE SubscriptionId = s.SubscriptionId) as Ingreso, ((s.Charge) * (SELECT count(Payment) "
                                + "FROM Payment WHERE SubscriptionId = s.SubscriptionId)) as Profit FROM Magazine m "
                                + "INNER JOIN Post p ON (m.MagazineId = p.MagazineId) INNER JOIN Subscription s ON (p.IdPost = s.IdPost) "
                                + "INNER JOIN Payment pay ON (s.SubscriptionId = pay.SubscriptionId) WHERE s.SubscriptionDate BETWEEN '" 
                                + initialDate + "' AND '" + finalDate + "'";
                        temporal += "WHERE s.SubscriptionDate BETWEEN '" 
                                + initialDate + "' AND '" + finalDate + "';";
                    } else {
                        query = "SELECT s.Username, m.Name, p.Version, (SELECT sum(Payment) FROM Payment "
                                + "WHERE SubscriptionId = s.SubscriptionId) as Ingreso, ((s.Charge) * (SELECT count(Payment) "
                                + "FROM Payment WHERE SubscriptionId = s.SubscriptionId)) as Profit FROM Magazine m "
                                + "INNER JOIN Post p ON (m.MagazineId = p.MagazineId) INNER JOIN Subscription s ON (p.IdPost = s.IdPost) "
                                + "INNER JOIN Payment pay ON (s.SubscriptionId = pay.SubscriptionId) WHERE m.Name = '"
                                + request.getParameter("filter") + "' AND s.SubscriptionDate BETWEEN '" + initialDate 
                                + "' AND '" + finalDate + "'";
                        temporal += " WHERE m.Name = '" + request.getParameter("filter") + "' AND s.SubscriptionDate BETWEEN '" 
                                + initialDate + "' AND '" + finalDate + "';";
                    }                   
                }
                flag = 3;
                request.setAttribute("Third", manager.getReportManagerProfit(query));
                request.setAttribute("flag", flag);
                request.setAttribute("total", manager.getReportManagerTotal(temporal));
            break;
            default:
                throw new Exception("Seleccione un reporte");
        }
    }
      
}
