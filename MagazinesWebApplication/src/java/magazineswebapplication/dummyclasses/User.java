/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.dummyclasses;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author zofia
 */
public class User {
    private String username;
    private String password;
    private String type;

    public User(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public User(HttpServletRequest request) {
        this.username = request.getParameter("username");
        this.password = request.getParameter("password");
        this.type = request.getParameter("type");
    }
    
    public User(HttpServletRequest request, String type) {
        this.username = request.getParameter("username");
        this.password = request.getParameter("password");
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
