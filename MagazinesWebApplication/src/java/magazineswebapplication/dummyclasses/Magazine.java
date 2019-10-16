/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.dummyclasses;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author zofia
 */
public class Magazine {
    private String magazineId;
    private String name;
    private String autor;
    private String description;
    private double price;
    private boolean blockLike;
    private boolean blockCommentary;
    private boolean blockSubscription;
    private double costoDia;
    private double charge;

    public Magazine(String magazineId, String name, String autor, String description, double price,  
            boolean blockLike, boolean blockCommentary, boolean blockSuscription, double costoDia, double charge) {
        this.magazineId = magazineId;
        this.name = name;
        this.autor = autor;
        this.description = description;
        this.price = price;
        this.blockLike = blockLike;
        this.blockCommentary = blockCommentary;
        this.blockSubscription = blockSuscription;
        this.costoDia = costoDia;
        this.charge = charge;
    }

    public Magazine(HttpServletRequest request, boolean blockLike, boolean blockCommentary) {
        this.magazineId = generateCode(request.getSession().getAttribute("User").toString());
        this.name = request.getParameter("MagazineName");
        this.autor = request.getParameter("autor");
        this.description = request.getParameter("description");
        this.price = Double.valueOf(request.getParameter("price"));
        this.blockLike = blockLike;
        this.blockCommentary = blockCommentary;
        this.blockSubscription = false;
        this.costoDia = 0;
        this.charge = 0;

    }
    
    //Genera un codigo unico mediante un UUID, debido a que es de 128 bits, se divide
    //y se toma solo la segunda parte.
    public String generateCode(String username) {
        String generatedID = UUID.randomUUID().toString();
        String[] parts = generatedID.split("-");
        String uniqueID = "Magazine-" + username +"-"+ parts[1];
        return uniqueID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public boolean isBlockLike() {
        return blockLike;
    }

    public void setBlockLike(boolean blockLike) {
        this.blockLike = blockLike;
    }

    public boolean isBlockCommentary() {
        return blockCommentary;
    }

    public void setBlockCommentary(boolean blockCommentary) {
        this.blockCommentary = blockCommentary;
    }

    public String getMagazineId() {
        return magazineId;
    }

    public void setMagazineId(String magazineId) {
        this.magazineId = magazineId;
    }

    public boolean isBlockSubscription() {
        return blockSubscription;
    }

    public void setBlockSubscription(boolean blockSubscription) {
        this.blockSubscription = blockSubscription;
    }

    public double getCostoDia() {
        return costoDia;
    }

    public void setCostoDia(double costoDia) {
        this.costoDia = costoDia;
    }
}
