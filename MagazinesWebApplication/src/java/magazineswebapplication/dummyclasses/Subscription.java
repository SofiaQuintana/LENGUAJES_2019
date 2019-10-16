/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.dummyclasses;

import java.time.LocalDate;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author zofia
 */
public class Subscription {
    private String subscriptionId;
    private String username;
    private String idPost;
    private double charge;
    private LocalDate subscriptionDate;
    private double managerProfit;
    private double editorProfit;

    public Subscription(String subscriptionId,String username, String idPost, double charge, 
            LocalDate subscriptionDate, double managerProfit, double editorProfit) {
        this.subscriptionId = subscriptionId;
        this.username = username;
        this.idPost = idPost;
        this.charge = charge;
        this.subscriptionDate = subscriptionDate;
        this.managerProfit = managerProfit;
        this.editorProfit = editorProfit;
    }

    public Subscription(HttpServletRequest request, String idPost, double charge, LocalDate date) {
        this.subscriptionId = generateCode(request.getSession().getAttribute("User").toString(), idPost);   
        this.username = request.getSession().getAttribute("User").toString();
        this.idPost = idPost;
        this.charge = charge;
        this.subscriptionDate = date;
        this.managerProfit = 0;
        this.editorProfit = 0;
    }
    
    //Genera un codigo unico mediante un UUID, debido a que es de 128 bits, se divide
    //y se toma solo la segunda parte.
    public String generateCode(String username, String idPost) {
        String generatedID = UUID.randomUUID().toString();
        String[] parts = generatedID.split("-");
        String uniqueID = "Subscription-" + username + "-" + idPost;
        return uniqueID;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public LocalDate getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDate subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public double getManagerProfit() {
        return managerProfit;
    }

    public void setManagerProfit(double managerProfit) {
        this.managerProfit = managerProfit;
    }

    public double getEditorProfit() {
        return editorProfit;
    }

    public void setEditorProfit(double editorProfit) {
        this.editorProfit = editorProfit;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }
    
}
