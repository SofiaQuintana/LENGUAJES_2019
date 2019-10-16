/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.dummyclasses;

/**
 *
 * @author zofia
 */
public class FourthReportDTO {
    private String username;
    private String name;
    private String version;
    private double ingreso;
    private double profit;

    public FourthReportDTO(String username, String name, String version, double ingreso, double profit) {
        this.username = username;
        this.name = name;
        this.version = version;
        this.ingreso = ingreso;
        this.profit = profit;
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

    public double getIngreso() {
        return ingreso;
    }

    public void setIngreso(double ingreso) {
        this.ingreso = ingreso;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
    
}
