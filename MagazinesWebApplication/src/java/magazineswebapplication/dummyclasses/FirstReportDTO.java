/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.dummyclasses;

import java.time.LocalDate;

/**
 *
 * @author zofia
 */
public class FirstReportDTO {
    private String username;
    private String commentary;
    private String name;
    private LocalDate commentaryDate;

    public FirstReportDTO(String username, String commentary, String name, LocalDate commentaryDate) {
        this.username = username;
        this.commentary = commentary;
        this.name = name;
        this.commentaryDate = commentaryDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCommentaryDate() {
        return commentaryDate;
    }

    public void setCommentaryDate(LocalDate commentaryDate) {
        this.commentaryDate = commentaryDate;
    }
    
}
