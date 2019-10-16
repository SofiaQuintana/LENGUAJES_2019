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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import magazineswebapplication.dbmanagers.DataBaseController;
import magazineswebapplication.dbmanagers.ProfileDBManager;
import magazineswebapplication.dummyclasses.Profile;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author zofia
 */
@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024*1024*2, maxFileSize=1024*1024*10, maxRequestSize=1024*1024*50)
public class UploadServlet extends HttpServlet {
    private static final String IMAGE_PATH = "/home/zofia/";
    private static final String UPDATE_QUERY = "UPDATE Profile SET PhotoUrl = '";
    private DataBaseController connection = new DataBaseController();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProfileDBManager profileManager = new ProfileDBManager(connection.connectToDataBase());
        Profile profile = profileManager.getProfileInList();
        String name = processRequest(request, profile);
        String query = UPDATE_QUERY + name + "' WHERE Username = '" + profile.getUsername() +"';";
        try {
            connection.updateElement(query);
            request.setAttribute("message", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } catch(Exception e) {
            
        }       
    }
    
    private String processRequest(HttpServletRequest request, Profile profile) {
        String name = "";
        if(ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                
                for(FileItem fileItem : multiparts) {
                    if(!fileItem.isFormField()) {
                        if(fileItem.getName().equals("") || fileItem.getName().equals(" ")) {
                            name = "vacio";
                        } else {
                            String folder = profile.getUsername();
                            createDirectory(folder);
                            name = new File(fileItem.getName()).getName();
                            fileItem.write(new File(IMAGE_PATH+ File.separator + folder + File.separator + name));
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
        File file = new File(IMAGE_PATH + username);
        if(file.exists() && file.isDirectory()) {
        } else {
            file.mkdirs();
        }
    }
    
    
}
