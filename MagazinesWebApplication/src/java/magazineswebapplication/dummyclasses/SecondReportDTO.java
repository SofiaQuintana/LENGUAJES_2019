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
public class SecondReportDTO {
    private String username;
    private String name;
    private String version;
    private LocalDate subscriptionDate;

    public SecondReportDTO(String username, String name, String version, LocalDate subscriptionDate) {
        this.username = username;
        this.name = name;
        this.version = version;
        this.subscriptionDate = subscriptionDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDate getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDate subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
    
}
