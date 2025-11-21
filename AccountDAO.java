import java.io.*;
import java.util.*;

public class AccountDAO {
    private static final String ACCOUNTS_FILE = "accounts.txt";
    private CustomerDAO customerDAO = new CustomerDAO();
    
    // CREATE - Add new account
    public void saveAccount(Account account) {
        List<Account> accounts = getAllAccounts();
        accounts.add(account);
        saveAllAccounts(accounts);
    }
    
    // READ - Get all accounts
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        List<Customer> customers = customerDAO.getAllCustomers();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Account account = parseAccount(line, customers);
                if (account != null) {
                    accounts.add(account);
                }
            }
        } catch (IOException e) {
            System.out.println("No existing account data found.");
        }
        return accounts;
    }
    
    // READ - Get accounts by customer
    public List<Account> getAccountsByCustomer(Customer customer) {
        return getAllAccounts().stream()
                .filter(a -> a.getOwner().getCustomerId() == customer.getCustomerId())
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
    
    // READ - Get account by ID
    public Account getAccountById(String accountId) {
        return getAllAccounts().stream()
                .filter(a -> a.getAccountId().equals(accountId))
                .findFirst()
                .orElse(null);
    }
    
    // UPDATE - Update account
    public void updateAccount(Account updatedAccount) {
        List<Account> accounts = getAllAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountId().equals(updatedAccount.getAccountId())) {
                accounts.set(i, updatedAccount);
                break;
            }
        }
        saveAllAccounts(accounts);
    }
    
    // DELETE - Remove account
    public void deleteAccount(String accountId) {
        List<Account> accounts = getAllAccounts();
        accounts.removeIf(a -> a.getAccountId().equals(accountId));
        saveAllAccounts(accounts);
    }
    
    // Helper method to save all accounts
    private void saveAllAccounts(List<Account> accounts) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ACCOUNTS_FILE))) {
            for (Account account : accounts) {
                writer.println(convertAccountToString(account));
            }
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }
    
    // Helper method to parse account from string
    private Account parseAccount(String line, List<Customer> customers) {
        String[] parts = line.split("\\|");
        if (parts.length >= 5) {
            String accountId = parts[0];
            String accountType = parts[1];
            double balance = Double.parseDouble(parts[2]);
            String branch = parts[3];
            int customerId = Integer.parseInt(parts[4]);
            
            Customer owner = customers.stream()
                    .filter(c -> c.getCustomerId() == customerId)
                    .findFirst()
                    .orElse(null);
                    
            if (owner != null) {
                switch (accountType) {
                    case "Savings":
                        return new SavingsAccount(accountId, accountType, branch, balance, owner);
                    case "Investment":
                        return new InvestmentAccount(accountId, accountType, branch, balance, owner);
                    case "Cheque":
                        ChequeAccount chequeAccount = new ChequeAccount(accountId, accountType, branch, "Unknown", owner);
                        chequeAccount.deposit(balance);
                        return chequeAccount;
                }
            }
        }
        return null;
    }
    
    // Helper method to convert account to string
    private String convertAccountToString(Account account) {
        return String.join("|",
            account.getAccountId(),
            account.getAccountType(),
            String.valueOf(account.getBalance()),
            account.getBranch(),
            String.valueOf(account.getOwner().getCustomerId())
        );
    }
}