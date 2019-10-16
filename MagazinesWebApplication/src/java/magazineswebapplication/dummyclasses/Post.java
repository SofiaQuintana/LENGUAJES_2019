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
public class Post {
    private String idPost;
    private String magazineId;
    private String username;
    private String pdfUrl;
    private String version;
    private LocalDate date;

    public Post(String idPost, String magazineId, String username, String pdfUrl, String version, LocalDate date) {
        this.idPost = idPost;
        this.magazineId = magazineId;
        this.username = username;
        this.pdfUrl = pdfUrl;
        this.version = version;
        this.date = date;
    }

    public Post(HttpServletRequest request, String magazineId, String username, String pdfUrl, LocalDate date) {
        this.idPost = generateCode();
        this.magazineId = magazineId;
        this.username = username;
        this.pdfUrl = pdfUrl;
        this.version = request.getParameter("version");
        this.date = date;
    }

    //Genera un codigo unico mediante un UUID, debido a que es de 128 bits, se divide
    //y se toma solo la segunda parte.
    public String generateCode() {
        String generatedID = UUID.randomUUID().toString();
        String[] parts = generatedID.split("-");
        String uniqueID = parts[1];
        return uniqueID;
    }
    
    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMagazineName() {
        return magazineId;
    }

    public void setMagazineName(String magazineName) {
        this.magazineId = magazineName;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}
