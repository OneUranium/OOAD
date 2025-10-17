package controllers.models;

public class ChequeAccount extends Account implements Withdrawable {
    private String employmentInfo;

    // ✅ CORRECT Constructor - uses com.banking.controllers.models.Customer owner
    public ChequeAccount(String accountId, String accountType, String accountBranch,
                         String employmentInfo, Customer owner) {  
        super(accountId, accountType, 0.0, accountBranch, owner); // initial balance 0.0
        this.employmentInfo = employmentInfo; // store it
    }

    // Getter / Setter
    public String getEmploymentInfo() {
        return employmentInfo;
    }

    public void setEmploymentInfo(String employmentInfo) {
        this.employmentInfo = employmentInfo;
    }

    // Withdraw method
    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= getBalance()) {
            setBalance(getBalance() - amount);
            return true; // success
        }
        return false; // failed
    }

    // Cheque accounts do not earn interest
    @Override
    public double calculateInterest() {
        return 0.0;
    }

    // Deposit method
    @Override
    public boolean deposit(double amount) {
        return super.deposit(amount);
    }
}