package com;

import controllers.models.ChequeAccount;
import controllers.models.IndividualCustomer;
import controllers.models.InvestmentAccount;
import controllers.models.SavingsAccount;

public class Main {
    public static void main(String[] args) {
        
        IndividualCustomer c1 = new IndividualCustomer(1, "123 Extention 12", "tshepo@email.com",
                                                      "Tshepo", "One", "Otse");

        
        SavingsAccount sa = new SavingsAccount("SA101", "Savings", "com.Main Branch", 1000.0, c1);
        InvestmentAccount ia = new InvestmentAccount("IA202", "Investment", "com.Main Branch", 2000.0, c1);
        ChequeAccount ca = new ChequeAccount("CA303", "Cheque", "com.Main Branch", "TechBots Ltd", c1);

        // Test account operations
        System.out.println("=== TESTING ACCOUNT OPERATIONS ===");
        System.out.println("Savings Interest: P" + sa.calculateInterest());  // Only savings account earns small interest
        System.out.println("Investment Interest: P" + ia.calculateInterest());  // Investment account earns more interest
        
        boolean withdrawSuccess = ca.withdraw(300);  // Cheque account can withdraw
        System.out.println("Cheque withdrawal successful: " + withdrawSuccess);

        // Show results
        System.out.println("\n=== ACCOUNT SUMMARIES ===");
        System.out.println("com.banking.controllers.models.Customer: " + c1.getCustomerName() + " " + c1.getCustomerSurname());
        System.out.println("Email: " + c1.getEmailAddress());
        System.out.println("Employment: " + c1.getEmploymentInfo());
        System.out.println("Savings balance: P" + sa.getBalance());
        System.out.println("Investment balance: P" + ia.getBalance());
        System.out.println("Cheque balance: P" + ca.getBalance());
        System.out.println("Cheque employment info: " + ca.getEmploymentInfo());
    }
}