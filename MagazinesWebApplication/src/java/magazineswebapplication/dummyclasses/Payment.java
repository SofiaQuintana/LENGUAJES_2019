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
public class Payment {
    private String paymentId;
    private String subscriptionId;
    private LocalDate date;
    private double payment;

    public Payment(String paymentId, String subscriptionId, LocalDate date, double payment) {
        this.paymentId = paymentId;
        this.subscriptionId = subscriptionId;
        this.date = date;
        this.payment = payment;
    }

    public Payment(String subscriptionId, String date, double payment) {
        this.paymentId = generateId(subscriptionId, date);
        this.subscriptionId = subscriptionId;
        this.date = LocalDate.parse(date);
        this.payment = payment;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
    
    //Genera un Id unico, mediante distintos atributos que no se repetiran.
    public String generateId(String subscriptionId, String date) {
        String uniqueID = subscriptionId +"-"+ date;
        return uniqueID;
    }
}
