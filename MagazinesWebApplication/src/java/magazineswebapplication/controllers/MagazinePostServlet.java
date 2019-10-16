/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import magazineswebapplication.dbmanagers.DataBaseController;
import magazineswebapplication.dbmanagers.PostMagazineDBManager;
import magazineswebapplication.dummyclasses.Catalog;
import magazineswebapplication.dummyclasses.Magazine;
import magazineswebapplication.dummyclasses.Post;

/**
 *
 * @author zofia
 */

@WebServlet("/MagazinePostServlet")
public class MagazinePostServlet extends HttpServlet {
    private Connection connection;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataBaseController connectDataBase = new DataBaseController();
        this.connection = connectDataBase.connectToDataBase();
        PostMagazineDBManager posting = new PostMagazineDBManager(connection);
        boolean likes = restrictions(request, "likes");
        boolean commentaries = restrictions(request, "commentaries");
        
        try {
            Magazine magazine = new Magazine(request, likes, commentaries);
            Post post = new Post(request, magazine.getMagazineId(), request.getSession().getAttribute("User").toString(), 
                "----", connectDataBase.parseDate(request));
            LocalDate date = connectDataBase.parseDate(request);
            Date postDate = Date.valueOf(date);
            posting.addMagazine(magazine);
            posting.addVersion(post, "----", postDate);
            fillCategories(request, magazine.getMagazineId(), posting);
            fillTags(request, magazine.getMagazineId(), posting);
            RequestDispatcher dispatcher = request.getRequestDispatcher("upload-pdf.jsp");
            dispatcher.forward(request, response); 
        } catch(Exception e) {
            request.setAttribute("error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("information-magazine.jsp");
            dispatcher.forward(request, response); 
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
        
    public void fillCategories(HttpServletRequest request, String magazineId, PostMagazineDBManager post) {
        Catalog catalog; 
        if(request.getParameter("category").contains(",")) {
            String[] categories = request.getParameter("category").split(",");
            for (String categorie : categories) {
                catalog = new Catalog(magazineId, categorie, "Categoria");
                post.addTag(catalog);
            }
        } else if(request.getParameter("category").equals("")) {
        } else {
            String category = request.getParameter("category");
            catalog = new Catalog(magazineId, category, "Categoria");
            post.addTag(catalog);
        }            
    }
    
    public void fillTags(HttpServletRequest request, String magazineId, PostMagazineDBManager post) {
        Catalog catalog; 
        if(request.getParameter("tags").contains(",")) {
            String[] tags = request.getParameter("tags").split(",");
            for(String tag : tags) {
                catalog = new Catalog(magazineId, tag, "Etiqueta");
                post.addTag(catalog);
            }
        } else if(request.getParameter("tags").equals("")) {
        } else {
            String tag = request.getParameter("tags");
            catalog = new Catalog(magazineId, tag, "Etiqueta");
            post.addTag(catalog);
        }
    }
}
