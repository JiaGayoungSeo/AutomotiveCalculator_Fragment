package com.example.automotivecalculator_fragment;

public class Auto {

    static final double STATE_TAX = 0.07;
    static final double INTEREST_RATE = 0.09;

    private double price;
    private double downPayment;
    private double totalCost;
    private int loanTerm;

    public void setPrice(double price){

        double mPrice = Double.parseDouble(String.format("%.2f",price));
        this.price = mPrice;
    }

    public double getPrice(){
        return price;
    }

    public void setDownPayment(double downPayment) {
        double mDownPayment = Double.parseDouble(String.format("%.2f",downPayment));
        this.downPayment = mDownPayment;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public void setLoanTerm(String loanTerm) {
        if(loanTerm.contains("2")) this.loanTerm = 2;
        if(loanTerm.contains("3")) this.loanTerm = 3;
        if(loanTerm.contains("4")) this.loanTerm = 4;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public double taxAmount(){
        double tax = Double.parseDouble(String.format("%.2f", price*STATE_TAX));
        return tax;
    }

    public double getTotalCost() {
        return getPrice() + taxAmount();
    }

    public double borrowedAmount(){
        return getTotalCost() - downPayment;
    }

    public double interestAmount(){

        double interest = Double.parseDouble(String.format("%.2f",borrowedAmount()*INTEREST_RATE));
        return interest;
    }

    public double monthlyPayment(){
        return Double.parseDouble(String.format("%.2f",borrowedAmount() / (loanTerm*12)));

    }

}
