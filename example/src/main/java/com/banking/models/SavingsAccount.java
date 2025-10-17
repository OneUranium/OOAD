package controllers.models;

public class SavingsAccount extends Account implements InterestBearing {
    private double interestRate = 0.0005; // Monthly interest rate

    //  uses com.banking.controllers.models.Customer owner
    public SavingsAccount(String accountId, String accountType, String accountBranch, 
                         double accountBalance, Customer owner) {
        super(accountId, accountType, accountBalance, accountBranch, owner);  
    }

    

    @Override
    public double calculateInterest() {
        return getBalance() * interestRate;
    }

    @Override
    public boolean withdraw(double amount) {
        System.out.println("Withdraw not allowed from Savings com.banking.controllers.models.Account!");
        return false;
    }

    public double getInterestRate() {
        return interestRate;
    }
}