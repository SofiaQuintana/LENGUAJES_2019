/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import magazineswebapplication.dbmanagers.DataBaseController;
import magazineswebapplication.dbmanagers.PostMagazineDBManager;
import magazineswebapplication.dummyclasses.Post;
import magazineswebapplication.exceptions.BlankSpaceException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author zofia
 */
@WebServlet("/PdfServlet")
public class PdfServlet extends HttpServlet {
    private static final String PDF_PATH = "/home/zofia/PDF/";
    private static final String UPDATE_QUERY = "UPDATE Post SET PdfUrl = '";
    private static final String SELECT_QUERY= "SELECT * FROM Post WHERE PdfUrl = '----';";
    private DataBaseController connection = new DataBaseController();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostMagazineDBManager manager = new PostMagazineDBManager(connection.connectToDataBase());
        List<Post> list = manager.getVersions(SELECT_QUERY);
        Post post = list.get(0);
        try {
            String name = processRequest(request, post);
            String query = UPDATE_QUERY + name + "' WHERE IdPost = '" + post.getIdPost() + "';";
            connection.updateElement(query);
            request.setAttribute("message", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("editor-header.jsp");
            dispatcher.forward(request, response);    
        } catch(BlankSpaceException e) {
            request.setAttribute("Error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("upload-pdf.jsp");
            dispatcher.forward(request, response);    
        }
        
    }
    
    private String processRequest(HttpServletRequest request, Post post) throws BlankSpaceException{
        String name = "";
        if(ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                
                for(FileItem fileItem : multiparts) {
                    if(!fileItem.isFormField()) {
                        if(fileItem.getName().equals("") || fileItem.getName().equals(" ")) {
                            throw new BlankSpaceException("Debe seleccionar un PDF.");
                        } else {
                            String folder = post.getUsername();
                            createDirectory(folder);
                            name = new File(fileItem.getName()).getName();
                            fileItem.write(new File(PDF_PATH+ File.separator + folder + File.separator + name));
                        }                      
                    } 
                }          
           } catch(Exception e) {
                System.out.println(e.getMessage());
           }
        }
        return name;
    }
    
    private void createDirectory(String username) {
        File file = new File(PDF_PATH + username);
        if(file.exists() && file.isDirectory()) {
        } else {
            file.mkdirs();
        }
    }
}
