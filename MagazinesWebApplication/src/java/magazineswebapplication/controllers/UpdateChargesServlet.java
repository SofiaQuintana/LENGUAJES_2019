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
import magazineswebapplication.dummyclasses.Rate;

/**
 *
 * @author zofia
 */
@WebServlet("/UpdateChargesServlet")
public class UpdateChargesServlet extends HttpServlet{
     private DataBaseController dataBase = new DataBaseController();
     private Connection connection;
     private static final String SELECT_BY_CHARGE = "SELECT * FROM Magazine WHERE Charge = 0;";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = dataBase.connectToDataBase();
        PostMagazineDBManager magazine = new PostMagazineDBManager(this.connection);
        String globalPrice = request.getParameter("globalCost");
        String costByDay = request.getParameter("costoDia");
        try {
            updateValues(globalPrice, "GlobalPrice =");
            updateValues(costByDay, "CostoGlobalDia =");
            Rate rate = magazine.getRate("SELECT * FROM Rate;");
            request.getSession().setAttribute("globalCost", rate.getGlobalPrice());
            request.getSession().setAttribute("globalDayCost", rate.getCostoDiaGlobal());
            RequestDispatcher dispatcher = request.getRequestDispatcher("edit-global-cost.jsp");
            dispatcher.forward(request, response);
        } catch(Exception e) {
            request.setAttribute("error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("edit-global-cost.jsp");
            dispatcher.forward(request, response);
        }        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = dataBase.connectToDataBase();
        String magazineId = request.getParameter("magazineId");
        PostMagazineDBManager magazine = new PostMagazineDBManager(this.connection);
        Rate rate = magazine.getRate("SELECT * FROM Rate;");
        boolean charge = restrictions(request, "global-charge");
        boolean costoDia = restrictions(request, "global-cost");
        try {
            assignValues(request, charge, "cost", rate.getGlobalPrice(), "Charge = ", magazineId);
            assignValues(request, costoDia, "costoDia", rate.getCostoDiaGlobal(), "CostoDia = ", magazineId);
            request.getSession().setAttribute("magazineList", magazine.getMagazines(SELECT_BY_CHARGE));
            RequestDispatcher dispatcher = request.getRequestDispatcher("manager-home.jsp");
            dispatcher.forward(request, response);     
        }catch(Exception e) {
            request.setAttribute("error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("edit-magazine-charges.jsp");
            dispatcher.forward(request, response); 
        }
    }
    
    public boolean restrictions(HttpServletRequest request, String parameter) {
        String temporal = request.getParameter(parameter);
        boolean value = true;
        if(temporal == null) {
            value = false;
            return value;
        } else {
            return value;
        }
    }
    
    public void assignValues(HttpServletRequest request, boolean value, String parameter, 
            double globalValue, String column, String magazineId) throws Exception{
        double temporal;
        if(value == false) {
            temporal = Double.valueOf(request.getParameter(parameter));           
        } else {
            temporal = globalValue;
        }
        String query = "UPDATE Magazine SET ";
        dataBase.updateElement(query + column + temporal + " WHERE MagazineId = '" + magazineId + "';");
    }
    
    public void updateValues(String value, String column) throws Exception {
        double parsedValue;
        if(value.equals("") || value.equals(" ")) {
        } else {
            parsedValue = Double.parseDouble(value);
            String query = "UPDATE Rate SET " + column + parsedValue + ";";
            dataBase.updateElement(query);
        }
    }
} 
