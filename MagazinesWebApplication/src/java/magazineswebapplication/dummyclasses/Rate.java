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
public class Rate {
    private String username;
    private double globalPrice;
    private double costoDiaGlobal;
    
    public Rate(String username, double globalPrice, double costoDiaGlobal) {
        this.username = username;
        this.globalPrice = globalPrice;
        this.costoDiaGlobal = costoDiaGlobal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getGlobalPrice() {
        return globalPrice;
    }

    public void setGlobalPrice(double globalPrice) {
        this.globalPrice = globalPrice;
    }

    public double getCostoDiaGlobal() {
        return costoDiaGlobal;
    }

    public void setCostoDiaGlobal(double costoDiaGlobal) {
        this.costoDiaGlobal = costoDiaGlobal;
    }
    
}
