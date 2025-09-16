package com.lendingadvisor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LendingAdvisor {
    public static List<Borrower> borrowers = new ArrayList<>();
    public static List<Lender> lenders = new ArrayList<>();
    static LoanApprovalAI ai = new LoanApprovalAI();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            ai.trainModel("data/loan_data.arff");
            System.out.println("AI Model Loaded Successfully!");
        } catch (Exception e) {
            System.out.println("AI Model Training Error: " + e.getMessage());
        }

        new Thread(() -> {
            new LendingAdvisorGUI();
        }).start();

        while (true) {
            System.out.println("\n1. Add Borrower\n2. Add Lender\n3. Match Loan\n4. View Data\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter Borrower Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Loan Amount: ");
                int loanAmount = scanner.nextInt();
                System.out.print("Enter Credit Score: ");
                int creditScore = scanner.nextInt();
                System.out.print("Enter Monthly Income: ");
                int income = scanner.nextInt();
                System.out.print("Enter Existing Debt: ");
                int existingDebt = scanner.nextInt();

                Borrower borrower = new Borrower(name, loanAmount, creditScore, income, existingDebt);
                borrowers.add(borrower);
                FileStorage.save(borrower.toString());

                try {
                    String loanDecision = ai.approveLoan(borrower);
                    System.out.println("Loan Status: " + loanDecision);
                } catch (Exception e) {
                    System.out.println("AI Prediction Error: " + e.getMessage());
                }
            } else if (choice == 2) {
                System.out.print("Enter Lender Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Max Loan Amount: ");
                int maxLoanAmount = scanner.nextInt();
                System.out.print("Enter Interest Rate: ");
                double interestRate = scanner.nextDouble();

                Lender lender = new Lender(name, maxLoanAmount, interestRate);
                lenders.add(lender);
                FileStorage.save(lender.toString());
            } else if (choice == 3) {
                matchLoan();
            } else if (choice == 4) {
                System.out.println("\nStored Data:");
                FileStorage.load();
            } else if (choice == 5) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }

    public static void matchLoan() {
        if (borrowers.isEmpty() || lenders.isEmpty()) {
            System.out.println("No data available to match loans.");
            return;
        }

        for (Borrower borrower : borrowers) {
            Lender bestLender = null;
            double bestRate = 100.0;
            for (Lender lender : lenders) {
                if (borrower.loanAmount <= lender.maxLoanAmount && lender.interestRate <= borrower.interestRate) {
                    if (lender.interestRate < bestRate) {
                        bestRate = lender.interestRate;
                        bestLender = lender;
                    }
                }
            }
            if (bestLender != null) {
                System.out.println("\nLoan Matched:");
                System.out.println(borrower.name + " gets a loan from " + bestLender.name + " at " + bestLender.interestRate + "% interest.");
            } else {
                System.out.println("No lender found for " + borrower.name);
            }
        }
    }
}
