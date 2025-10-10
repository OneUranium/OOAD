public abstract class Account {
    // Protected data for subclass access
    private String accountId;
    private String accountType;
    private double accountBalance;
    private String accountBranch;
    private Customer owner;  

    // Constructor - FIXED owner parameter type
    public Account(String accountId, String accountType, double accountBalance, 
                   String accountBranch, Customer owner) {  //  Customer owner
        this.accountId = accountId;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.accountBranch = accountBranch;
        this.owner = owner;  //  stores Customer object
    }

    // Deposit method - shared by all account types
    public boolean deposit(double amount) {
        if (amount > 0) {
            accountBalance += amount;
            return true;
        }
        return false; // invalid deposit
    }

    // Abstract methods to be implemented in subclasses
    public abstract boolean withdraw(double amount);
    public abstract double calculateInterest();

    // Getters
    public String getAccountId() { return accountId; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return accountBalance; }
    public String getBranch() { return accountBranch; }
    public Customer getOwner() { return owner; }  //  Returns Customer object

    // Setter for subclasses to modify balance
    protected void setBalance(double accountBalance) { this.accountBalance = accountBalance; }
}