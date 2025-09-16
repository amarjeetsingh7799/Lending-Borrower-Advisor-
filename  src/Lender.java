package com.lendingadvisor;

public class Lender {
    String name;
    int maxLoanAmount;
    double interestRate;

    Lender(String name, int maxLoanAmount, double interestRate) {
        this.name = name;
        this.maxLoanAmount = maxLoanAmount;
        this.interestRate = interestRate;
    }

    public String toString() {
        return "Name: " + name + ", Max Loan: " + maxLoanAmount + ", Interest Rate: " + interestRate + "%";
    }
}
