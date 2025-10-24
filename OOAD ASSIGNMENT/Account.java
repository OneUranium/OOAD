public abstract class Account {
    private String accountId;
    private String accountType;
    private double accountBalance;
    private String accountBranch;
    private Customer owner;

    public Account(String accountId, String accountType, double accountBalance, String accountBranch, Customer owner) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.accountBranch = accountBranch;
        this.owner = owner;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            accountBalance += amount;
            return true;
        }
        return false;
    }

    public abstract boolean withdraw(double amount);
    public abstract double calculateInterest();

    public String getAccountId() { return accountId; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return accountBalance; }
    public String getBranch() { return accountBranch; }
    public Customer getOwner() { return owner; }

    protected void setBalance(double accountBalance) { this.accountBalance = accountBalance; }
}