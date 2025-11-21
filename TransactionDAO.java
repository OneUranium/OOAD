import java.io.*;
import java.util.*;

public class TransactionDAO {
    private static final String TRANSACTIONS_FILE = "transactions.txt";
    
    // CREATE - Record new transaction
    public void recordTransaction(String accountId, String type, double amount, String description) {
        String transaction = String.join("|",
            java.time.LocalDate.now().toString(),
            accountId,
            type,
            String.valueOf(amount),
            description
        );
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(TRANSACTIONS_FILE, true))) {
            writer.println(transaction);
        } catch (IOException e) {
            System.out.println("Error recording transaction: " + e.getMessage());
        }
    }
    
    // READ - Get transactions by account
    public List<String> getTransactionsByAccount(String accountId) {
        List<String> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 2 && parts[1].equals(accountId)) {
                    transactions.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("No transaction data found.");
        }
        return transactions;
    }
    
    // READ - Get all transactions
    public List<String> getAllTransactions() {
        List<String> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(line);
            }
        } catch (IOException e) {
            System.out.println("No transaction data found.");
        }
        return transactions;
    }
}