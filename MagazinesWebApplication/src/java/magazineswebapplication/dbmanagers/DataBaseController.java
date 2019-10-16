/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.dbmanagers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import magazineswebapplication.dummyclasses.Rate;

/**
 *
 * @author zofia
 */
public class DataBaseController {
    private Connection connection = null;
    
    public Connection connectToDataBase() {
        try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String user = "root";
                String password = "@Dopediamond?8";
                String urlConnection = "jdbc:mysql://localhost:3306/MagazineWebPage";

                //abrimos una coneccion a la DB usando una url, el usuario y password 
                connection = 
                           DriverManager.getConnection(urlConnection, user, password);

                System.out.println("conectado:" + connection.getCatalog());
        } catch (SQLException e) {
                System.out.println("error");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DataBaseController.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return this.connection;
    }
        
    public void updateElement(String query) {
        try {
            PreparedStatement object = connection.prepareStatement(query);
            object.execute();    
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public String concatenateDate(HttpServletRequest request) {
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        String dash = "-";
        String concatenatedDate = year + dash + month + dash + day;
        return concatenatedDate;
    }
    
    public LocalDate parseDate(HttpServletRequest request) {        
        String concatenatedDate = concatenateDate(request);
        LocalDate parsedDate = LocalDate.parse(concatenatedDate);
        return parsedDate;
    }
}
