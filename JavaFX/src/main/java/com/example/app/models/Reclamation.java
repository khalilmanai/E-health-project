package com.example.app.models;

import java.sql.Date;

public class Reclamation {

    private int refund_id;
    private int client_id;
    private int id_Facture;
    private int refundAmount;
    private String refund_Reason;

    private Date refund_date ;

    public Reclamation(){};
    public Reclamation(int i, int i1, String r, Date t) {
        this.client_id=i;
        this.refund_date=t;
        this.refund_Reason=r;
        this.refund_id=i1;
    }

    public int getId_Facture() {
        return id_Facture;
    }

    public void setId_Facture(int id_Facture) {
        this.id_Facture = id_Facture;
    }
    public int getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(int refund_id) {
        this.refund_id = refund_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(int refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefund_Reason() {
        return refund_Reason;
    }

    public void setRefund_Reason(String refund_Reason) {
        this.refund_Reason = refund_Reason;
    }

    public Date getRefund_date() {
        return refund_date;
    }

    public void setRefund_date(Date refund_date) {
        this.refund_date = refund_date;
    }

    @Override
    public String toString() {
        return "reclamation{" +
                "id_=" + refund_id +
                ",client_id =" + client_id +
                ", date='" + refund_date + ",Amount =" + refundAmount +",reason =" + refund_Reason + '\'' +

                "}\n";
    }
}