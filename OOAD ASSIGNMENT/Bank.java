import java.util.ArrayList;
import java.util.List;

public class Bank {
    private static Bank instance;
    private List<Customer> customers;
    private List<Account> accounts;
    private int customerIdCounter = 1;
    
    // Singleton method
    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }
    
    public Bank() {
        this.customers = new ArrayList<>();
        this.accounts = new ArrayList<>();
        createSampleData();
    }
    
    private void createSampleData() {
        IndividualCustomer john = new IndividualCustomer(customerIdCounter++, "123 Main St", "john@email.com", "John", "Doe", "Software Engineer");
        IndividualCustomer jane = new IndividualCustomer(customerIdCounter++, "456 Oak Ave", "jane@email.com", "Jane", "Smith", "Teacher");
        
        customers.add(john);
        customers.add(jane);
        
        createAccountsForCustomer(john);
        createAccountsForCustomer(jane);
    }
    
    private void createAccountsForCustomer(Customer customer) {
        String baseId = "ACC" + customer.getCustomerId();
        
        System.out.println("DEBUG: Creating accounts for customer " + customer.getCustomerId());
        
        SavingsAccount savings = new SavingsAccount(baseId + "-SAV", "Savings", "Main Branch", 1500.0, customer);
        InvestmentAccount investment = new InvestmentAccount(baseId + "-INV", "Investment", "Main Branch", 2000.0, customer);
        ChequeAccount cheque = new ChequeAccount(baseId + "-CHQ", "Cheque", "Main Branch", "Tech Corp", customer);
        
        // Force set balances
        savings.deposit(1500.0);
        investment.deposit(2000.0);
        cheque.deposit(1000.0);
        
        accounts.add(savings);
        accounts.add(investment);
        accounts.add(cheque);
        
        System.out.println("DEBUG: Created accounts - Savings: P" + savings.getBalance() + 
                          ", Investment: P" + investment.getBalance() +
                          ", Cheque: P" + cheque.getBalance());
    }
    
    public boolean authenticateTeller(String username, String password) {
        return ("teller1".equals(username) && "password123".equals(password)) ||
               ("teller2".equals(username) && "bank2025".equals(password));
    }
    
    public Customer authenticateCustomer(String email, String password) {
        System.out.println("DEBUG: Authenticating - Email: " + email + ", Password: " + password);
        System.out.println("DEBUG: Total customers: " + customers.size());
        
        for (Customer customer : customers) {
            System.out.println("DEBUG: Checking: " + getCustomerName(customer) + " - " + customer.getEmailAddress());
            
            if (customer.getEmailAddress().equalsIgnoreCase(email)) {
                if (customer instanceof IndividualCustomer) {
                    IndividualCustomer ind = (IndividualCustomer) customer;
                    System.out.println("DEBUG: Customer surname: " + ind.getCustomerSurname());
                    
                    if (ind.getCustomerSurname().equalsIgnoreCase(password)) {
                        System.out.println("DEBUG: ✅ Login SUCCESS for " + getCustomerName(customer));
                        return customer;
                    } else {
                        System.out.println("DEBUG: ❌ Password mismatch");
                    }
                }
            }
        }
        System.out.println("DEBUG: ❌ Login FAILED - no matching customer");
        return null;
    }
    
    public void addCustomer(String name, String email, String password) {
        String[] names = name.split(" ");
        String firstName = names[0];
        String lastName = names.length > 1 ? names[1] : password; // Use password as surname if no last name
        
        IndividualCustomer newCustomer = new IndividualCustomer(
            customerIdCounter++, 
            "Unknown Address", 
            email, 
            firstName, 
            lastName, // This becomes the login password
            "Unknown"
        );
        customers.add(newCustomer);
        createAccountsForCustomer(newCustomer);
        
        System.out.println("DEBUG: Registered new customer: " + name);
        System.out.println("DEBUG: Email: " + email);
        System.out.println("DEBUG: Login password (surname): " + lastName);
        System.out.println("DEBUG: Customer ID: " + newCustomer.getCustomerId());
        
        // Print all customers for debugging
        printAllCustomers();
    }
    
    public void addCustomer(Customer customer) {
        customers.add(customer);
        createAccountsForCustomer(customer);
    }
    
    // Debug method to see all customers
    public void printAllCustomers() {
        System.out.println("=== ALL REGISTERED CUSTOMERS ===");
        for (Customer customer : customers) {
            if (customer instanceof IndividualCustomer) {
                IndividualCustomer ind = (IndividualCustomer) customer;
                System.out.println("Name: " + ind.getCustomerName() + " " + ind.getCustomerSurname());
                System.out.println("Email: " + customer.getEmailAddress());
                System.out.println("ID: " + customer.getCustomerId());
                System.out.println("Login Password (surname): " + ind.getCustomerSurname());
                System.out.println("---");
            }
        }
    }
    
    public List<Account> getCustomerAccounts(Customer customer) {
        List<Account> result = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getOwner().equals(customer)) {
                result.add(account);
            }
        }
        return result;
    }
    
    public boolean depositToCustomerAccount(Customer customer, double amount) {
        List<Account> customerAccounts = getCustomerAccounts(customer);
        if (!customerAccounts.isEmpty() && amount > 0) {
            for (Account account : customerAccounts) {
                boolean success = account.deposit(amount);
                if (success) return true;
            }
        }
        return false;
    }
    
    public boolean withdrawFromCustomerAccount(Customer customer, double amount) {
        List<Account> customerAccounts = getCustomerAccounts(customer);
        if (!customerAccounts.isEmpty() && amount > 0) {
            for (Account account : customerAccounts) {
                if (account instanceof Withdrawable) {
                    Withdrawable withdrawableAccount = (Withdrawable) account;
                    boolean success = withdrawableAccount.withdraw(amount);
                    if (success) return true;
                }
            }
        }
        return false;
    }
    
    public String getCustomerName(Customer customer) {
        if (customer instanceof IndividualCustomer) {
            IndividualCustomer ind = (IndividualCustomer) customer;
            return ind.getCustomerName() + " " + ind.getCustomerSurname();
        } else if (customer instanceof CompanyCustomer) {
            CompanyCustomer comp = (CompanyCustomer) customer;
            return comp.getCompanyName();
        }
        return "Unknown Customer";
    }
    
    public double getCustomerTotalBalance(Customer customer) {
        double total = 0;
        for (Account account : getCustomerAccounts(customer)) {
            total += account.getBalance();
        }
        return total;
    }
    
    public String getCustomerTransactionHistory(Customer customer) {
        StringBuilder history = new StringBuilder();
        history.append("=== TRANSACTION HISTORY ===\n\n");
        history.append("Customer: ").append(getCustomerName(customer)).append("\n");
        history.append("Customer ID: ").append(customer.getCustomerId()).append("\n");
        history.append("Email: ").append(customer.getEmailAddress()).append("\n\n");
        
        List<Account> customerAccounts = getCustomerAccounts(customer);
        
        if (customerAccounts.isEmpty()) {
            history.append("No accounts found for this customer.");
            return history.toString();
        }
        
        history.append("ACCOUNT SUMMARY:\n");
        history.append("----------------\n");
        
        double totalBalance = 0;
        for (Account account : customerAccounts) {
            history.append(account.getAccountType()).append(" Account\n");
            history.append("  Account ID: ").append(account.getAccountId()).append("\n");
            history.append("  Balance: P").append(String.format("%.2f", account.getBalance())).append("\n");
            totalBalance += account.getBalance();
            
            if (account instanceof InterestBearing) {
                double interest = ((InterestBearing) account).calculateInterest();
                history.append("  Monthly Interest: P").append(String.format("%.2f", interest)).append("\n");
            }
            
            if (account instanceof SavingsAccount) {
                history.append("  [No withdrawals allowed]\n");
            } else if (account instanceof InvestmentAccount) {
                history.append("  [5% monthly interest, min balance P500.00]\n");
            } else if (account instanceof ChequeAccount) {
                history.append("  [Unlimited transactions, no interest]\n");
            }
            history.append("\n");
        }
        
        history.append("RECENT TRANSACTIONS:\n");
        history.append("--------------------\n");
        history.append("2024-01-15: Account Opening\n");
        history.append("2024-01-20: Deposit       +P1,000.00\n");
        history.append("2024-01-25: Withdrawal    -P500.00\n");
        history.append("2024-02-01: Monthly Interest\n");
        
        double savingsInterest = 0;
        double investmentInterest = 0;
        for (Account account : customerAccounts) {
            if (account instanceof SavingsAccount) {
                savingsInterest = account.getBalance() * 0.0005;
            } else if (account instanceof InvestmentAccount) {
                investmentInterest = account.getBalance() * 0.05;
            }
        }
        
        if (savingsInterest > 0) {
            history.append("  Savings Interest   +P").append(String.format("%.2f", savingsInterest)).append("\n");
        }
        if (investmentInterest > 0) {
            history.append("  Investment Interest +P").append(String.format("%.2f", investmentInterest)).append("\n");
        }
        
        history.append("\nTOTAL BALANCE: P").append(String.format("%.2f", totalBalance));
        history.append("\n\n========================================\n");
        
        return history.toString();
    }
    
    public List<Customer> getCustomers() { return customers; }
    public List<Account> getAccounts() { return accounts; }
}