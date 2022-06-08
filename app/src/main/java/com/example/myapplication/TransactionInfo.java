package com.example.myapplication;

public class TransactionInfo {
    String transactionId;
    String amount;
    String date;
    String to;

    public  TransactionInfo(){}
    public TransactionInfo(String transactionId, String amount, String date, String to) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.date = date;
        this.to = to;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
