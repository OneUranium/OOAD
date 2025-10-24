public class ChequeAccount extends Account implements Withdrawable {
    private String employmentInfo;

    public ChequeAccount(String accountId, String accountType, String accountBranch, String employmentInfo, Customer owner) {
        super(accountId, accountType, 0.0, accountBranch, owner);
        this.employmentInfo = employmentInfo;
    }

    public String getEmploymentInfo() {
        return employmentInfo;
    }

    public void setEmploymentInfo(String employmentInfo) {
        this.employmentInfo = employmentInfo;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= getBalance()) {
            setBalance(getBalance() - amount);
            return true;
        }
        return false;
    }

    @Override
    public double calculateInterest() {
        return 0.0;
    }

    @Override
    public boolean deposit(double amount) {
        return super.deposit(amount);
    }
}