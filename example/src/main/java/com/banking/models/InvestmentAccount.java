package controllers.models;

public class InvestmentAccount extends Account implements InterestBearing, Withdrawable {
    private double interestRate = 0.05;
    private double minimumBalance = 500.00;

    
    public InvestmentAccount(String accountId, String accountType, String accountBranch, 
                            double initialDeposit, Customer owner) {
        super(accountId, accountType, 0.0, accountBranch, owner);

        // Check minimum deposit
        if (initialDeposit >= minimumBalance) {
            deposit(initialDeposit);
            System.out.println(" Investment account opened with P" + initialDeposit);
        } else {
            System.out.println(" ERROR: Minimum balance P" + minimumBalance + " required");
        }
    }

    @Override  // 
    public boolean deposit(double amount) {
        return super.deposit(amount);
    }

    @Override
    public double calculateInterest() {
        return getBalance() * interestRate;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= getBalance()) {
            setBalance(getBalance() - amount);
            return true;
        }
        return false;
    }

    // Getters specific to com.banking.controllers.models.InvestmentAccount
    public double getInterestRate() { return interestRate; }
    public double getMinimumBalance() { return minimumBalance; }
}