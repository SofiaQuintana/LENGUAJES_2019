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
@WebServlet("/SubscribeServlet")
public class SubscribeServlet extends HttpServlet {
    private DataBaseController dataBase = new DataBaseController();
    private Connection connection;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String magazineId = request.getParameter("r");
        this.connection = dataBase.connectToDataBase();
        PostMagazineDBManager magazine = new PostMagazineDBManager(this.connection);
        String query = "SELECT * FROM Post WHERE MagazineId = '" + magazineId + "';";
        request.getSession().setAttribute("selectedVersion", magazine.getVersions(query));
        query = "SELECT * FROM Magazine WHERE MagazineId = '" + magazineId + "';";
        request.getSession().setAttribute("selectedMagazine", magazine.getMagazines(query));
        RequestDispatcher dispatcher = request.getRequestDispatcher("payment.jsp");
        dispatcher.forward(request, response);   
    }
}
