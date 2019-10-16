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
public class Commentary {
    private String commentaryId;
    private String subscriptionId;
    private String commentary;
    private LocalDate commentaryDate;

    public Commentary(String commentaryId, String subscriptionId, String commentary, LocalDate commentaryDate) {
        this.commentaryId = commentaryId;
        this.subscriptionId = subscriptionId;
        this.commentary = commentary;
        this.commentaryDate = commentaryDate;
    }

    public Commentary(HttpServletRequest request, String username, String subscriptionId, LocalDate commentaryDate) {
        this.commentaryId = generateCode(username);
        this.subscriptionId = subscriptionId;
        this.commentary = request.getParameter("commentary");
        this.commentaryDate = commentaryDate;     
    }
    
     //Genera un codigo unico mediante un UUID, debido a que es de 128 bits, se divide
    //y se toma solo la segunda parte.
    public String generateCode(String username) {
        String generatedID = UUID.randomUUID().toString();
        String[] parts = generatedID.split("-");
        String uniqueID = username +"-"+ parts[1];
        return uniqueID;
    }

    public String getCommentaryId() {
        return commentaryId;
    }

    public void setCommentaryId(String commentaryId) {
        this.commentaryId = commentaryId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public LocalDate getCommentaryDate() {
        return commentaryDate;
    }

    public void setCommentaryDate(LocalDate commentaryDate) {
        this.commentaryDate = commentaryDate;
    }
}
