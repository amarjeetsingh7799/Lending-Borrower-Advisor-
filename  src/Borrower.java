package com.lendingadvisor;

public class Borrower {
    String name;
    int loanAmount;
    int creditScore;
    int income;
    int existingDebt;
    String riskLevel;
    double interestRate;

    Borrower(String name, int loanAmount, int creditScore, int income, int existingDebt) {
        this.name = name;
        this.loanAmount = loanAmount;
        this.creditScore = creditScore;
        this.income = income;
        this.existingDebt = existingDebt;
        this.riskLevel = getRiskLevel(creditScore);
        this.interestRate = calculateInterestRate(creditScore, loanAmount);
    }

    private String getRiskLevel(int creditScore) {
        if (creditScore > 750) {
            return "Low Risk";
        } else if (creditScore >= 600) {
            return "Medium Risk";
        } else {
            return "High Risk";
        }
    }

    private double calculateInterestRate(int creditScore, int loanAmount) {
        double baseRate = 10.0 - (creditScore - 500) / 30.0;
        if (loanAmount > 100000) {
            baseRate += 2;
        } else if (loanAmount > 50000) {
            baseRate += 1;
        }
        return Math.min(baseRate, 15.0);
    }

    public String toString() {
        return "Name: " + name + ", Loan: " + loanAmount + ", Credit Score: (between : 350-850)" + creditScore + ", Income: " + income + ", Debt: " + existingDebt + ", Risk: " + riskLevel + ", Interest Rate: " + interestRate + "%";
    }
}
