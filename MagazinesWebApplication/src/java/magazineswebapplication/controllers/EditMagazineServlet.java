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

/**
 *
 * @author zofia
 */
@WebServlet("/EditMagazineServlet")
public class EditMagazineServlet extends HttpServlet {
    private String magazineId;
    private DataBaseController dataBase = new DataBaseController();
    private Connection connection;
    private static final String SELECT_MAGAZINE = "SELECT * FROM Magazine WHERE MagazineId = '";
    private static final String UPDATE_QUERY = "UPDATE Magazine SET ";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = dataBase.connectToDataBase();
        PostMagazineDBManager magazine = new PostMagazineDBManager(this.connection);
        this.magazineId = request.getParameter("r");
        request.setAttribute("editMagazine", magazine.getMagazines(SELECT_MAGAZINE + magazineId + "';")); 
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-magazine.jsp");
        dispatcher.forward(request, response); 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean commentaries = restrictions(request, "commentaries");
        boolean likes = restrictions(request, "likes");
        boolean subscriptions = restrictions(request, "subscriptions");
        try {
            updatingRestrictions(commentaries, "BlockCommentary = ");
            updatingRestrictions(likes, "BlockLike = ");
            updatingRestrictions(subscriptions, "BlockSubscription = ");
            RequestDispatcher dispatcher = request.getRequestDispatcher("uploaded-magazines.jsp");
            dispatcher.forward(request, response); 
        } catch(IOException | ServletException e) {
            
        }
    }
    
    public boolean restrictions(HttpServletRequest request, String parameter) {
        String temporal = request.getParameter(parameter);
        boolean restriction = true;
        if(temporal == null) {
            restriction = false;
            return restriction;
        } else {
            return restriction;
        }
    }
    
    public void updatingRestrictions(boolean value, String column) {
        String query;
        int parsedValue;
        if(value == true) {
            parsedValue = 1;
            query = UPDATE_QUERY + column + parsedValue + " WHERE MagazineId = '" + magazineId + "';";
            dataBase.updateElement(query);
        }  else {
            parsedValue = 0;
            query = UPDATE_QUERY + column + parsedValue + " WHERE MagazineId = '" + magazineId + "';";
            dataBase.updateElement(query);
        }
    }
}
