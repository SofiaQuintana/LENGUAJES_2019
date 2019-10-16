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
public class FourthReportComplementDTO {
    private double totalIngreso;
    private double totalProfit;

    public FourthReportComplementDTO(double totalIngreso, double totalProfit) {
        this.totalIngreso = totalIngreso;
        this.totalProfit = totalProfit;
    }

    public double getTotalIngreso() {
        return totalIngreso;
    }

    public void setTotalIngreso(double totalIngreso) {
        this.totalIngreso = totalIngreso;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }
    
}
