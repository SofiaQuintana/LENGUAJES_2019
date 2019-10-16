/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.dbmanagers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import magazineswebapplication.dummyclasses.Commentary;
import magazineswebapplication.dummyclasses.FirstReportDTO;
import magazineswebapplication.dummyclasses.FourthReportComplementDTO;
import magazineswebapplication.dummyclasses.FourthReportDTO;
import magazineswebapplication.dummyclasses.Payment;
import magazineswebapplication.dummyclasses.SecondReportDTO;
import magazineswebapplication.dummyclasses.Subscription;
import magazineswebapplication.dummyclasses.ThirdReportDTO;

/**
 *
 * @author zofia
 */
public class SubscriptionDBManager {
    private Connection connection;
    private List<Subscription> subscriptions = new ArrayList<>();
    private List<FirstReportDTO> reportCommentaries = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();
    private List<Commentary> commentaries = new ArrayList<>();
    private List<SecondReportDTO> reportSubscriptions = new ArrayList<>();
    private List<ThirdReportDTO> reportProfitEditor = new ArrayList<>();
    private List<FourthReportDTO> reportProfitManager = new ArrayList<>();
    private List<FourthReportComplementDTO> complement = new ArrayList<>();


    public SubscriptionDBManager(Connection connection) {
        this.connection = connection;
    }
    
    public List<Subscription> getSubscriptions(String query) {
        subscriptions.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String subscriptionId = result.getString("SubscriptionId");
                String username = result.getString("Username");
                String idPost = result.getString("IdPost");
                double charge = result.getDouble("Charge");
                Date subscriptionDate = result.getDate("SubscriptionDate");
                LocalDate date = subscriptionDate.toLocalDate();
                double managerProfit = result.getDouble("ManagerProfit");
                double editorProfit = result.getDouble("EditorProfit");
                Subscription subscription = new Subscription(subscriptionId, username, idPost, charge, date, managerProfit, editorProfit);
                subscriptions.add(subscription);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return subscriptions;
    }
    
    public List<FirstReportDTO> getReportCommentaries(String query) throws SQLException{
        reportCommentaries.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String username = result.getString("Username");
                String commentary = result.getString("Commentary");
                String name = result.getString("Name");
                Date commentaryDate = result.getDate("CommentaryDate");
                LocalDate date = commentaryDate.toLocalDate();
                FirstReportDTO report = new FirstReportDTO(username, commentary, name, date);
                reportCommentaries.add(report);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reportCommentaries;
    }
    
    public List<SecondReportDTO> getReportSubscriptions(String query) {
        reportSubscriptions.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String username = result.getString("Username");
                String name = result.getString("Name");
                String version = result.getString("Version");
                Date subscriptionDate = result.getDate("SubscriptionDate");
                LocalDate date = subscriptionDate.toLocalDate();
                SecondReportDTO subscription = new SecondReportDTO(username, name, version, date);
                reportSubscriptions.add(subscription);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reportSubscriptions;
    }
    
    public List<ThirdReportDTO> getReportEditorProfit(String query) {
        reportProfitEditor.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String username = result.getString("Username");
                String name = result.getString("Name");
                String version = result.getString("Version");
                Double profit = result.getDouble("Profit");
                ThirdReportDTO subscription = new ThirdReportDTO(username, name, version, profit);
                reportProfitEditor.add(subscription);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reportProfitEditor;
    }
    
    public double getEditorTotalProfit(String query) {
        double total = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                total = result.getDouble("Total");
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
    }
    
    public List<FourthReportDTO> getReportManagerProfit(String query) {
        reportProfitManager.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String username = result.getString("Username");
                String name = result.getString("Name");
                String version = result.getString("Version");
                Double ingreso = result.getDouble("Ingreso");
                Double profit = result.getDouble("Profit");
                FourthReportDTO subscription = new FourthReportDTO(username, name, version, ingreso, profit);
                reportProfitManager.add(subscription);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reportProfitManager;
    }
    
    public List<FourthReportComplementDTO> getReportManagerTotal(String query) {
        complement.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                Double ingreso = result.getDouble("TotalIngreso");
                Double profit = result.getDouble("TotalProfit");
                FourthReportComplementDTO subscription = new FourthReportComplementDTO(ingreso, profit);
                complement.add(subscription);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return complement;
    }
    
    public List<Payment> getPayments(String query) {
        payments.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String paymentId = result.getString("PaymentId");
                String subscriptionId = result.getString("SubscriptionId");
                Date date = result.getDate("Date");
                LocalDate parsedDate = date.toLocalDate();
                double pay = result.getDouble("Payment");
                Payment payment = new Payment(subscriptionId, subscriptionId, parsedDate, pay);
                payments.add(payment);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return payments;
    }
    
    public List<Commentary> getCommentaries(String query) {
        commentaries.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                String commentaryId = result.getString("CommentaryId");
                String subscriptionId = result.getString("SubscriptionId");
                String commentary = result.getString("Commentary");
                Date date = result.getDate("CommentaryDate");
                LocalDate parsedDate = date.toLocalDate();
                Commentary temporal = new Commentary(commentaryId, subscriptionId, commentary, parsedDate);
                commentaries.add(temporal);
            }
            result.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commentaries;
    }
    
    public void addSubscription(Subscription subscription, Date date) {
        try {
            String query = ("INSERT INTO Subscription (SubscriptionId, Username, IdPost, Charge, "
                    + "SubscriptionDate, ManagerProfit, EditorProfit) VALUES (?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, subscription.getSubscriptionId());
            object.setString(2, subscription.getUsername());
            object.setString(3, subscription.getIdPost());
            object.setDouble(4, subscription.getCharge());
            object.setDate(5, date);
            object.setDouble(6, subscription.getManagerProfit());
            object.setDouble(7, subscription.getEditorProfit());
            object.execute();

        } catch(SQLException e) {
        }
    }
    
    public void addPayment(Payment payment, Date date) throws Exception {
        try {
            String query = ("INSERT INTO Payment (PaymentId, SubscriptionId, Date, Payment) VALUES (?, ?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, payment.getPaymentId());
            object.setString(2, payment.getSubscriptionId());
            object.setDate(3, date);
            object.setDouble(4, payment.getPayment());
            object.execute();

        } catch(SQLException e) {
            throw new Exception("Este pago ya existe, debe elegir uno nuevo.");
        }
    }
    
    public void addCommentary(Commentary commentary, Date date) throws Exception {
        try {
            String query = ("INSERT INTO Commentary (CommentaryId, SubscriptionId, Commentary, CommentaryDate) VALUES (?, ?, ?, ?)");
            PreparedStatement object = connection.prepareStatement(query);
            object.setString(1, commentary.getCommentaryId());
            object.setString(2, commentary.getSubscriptionId());
            object.setString(3, commentary.getCommentary());
            object.setDate(4, date);
            object.execute();

        } catch(SQLException e) {
            throw new Exception("Este comentario ya existe.");
        }
    }
}

