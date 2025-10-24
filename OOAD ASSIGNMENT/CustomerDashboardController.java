import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerDashboardController {
    @FXML private Label infoLabel;
    @FXML private Label balanceLabel;
    @FXML private TextArea accountsArea;
    @FXML private ComboBox<String> accountComboBox;
    @FXML private Button depositBtn;
    @FXML private Button withdrawBtn;
    @FXML private Button historyBtn;
    @FXML private Button logoutBtn;
    
    private Bank bank = Bank.getInstance(); // SINGLE instance
    private Customer customer;
    private Account selectedAccount;
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
        System.out.println("DEBUG: Customer set in dashboard: " + (customer != null ? customer.getCustomerId() : "NULL"));
        updateDashboard();
        setupAccountComboBox();
    }
    
    @FXML
    private void initialize() {
        System.out.println("DEBUG: Dashboard initialized, Bank instance: " + bank);
        accountComboBox.setOnAction(e -> {
            String selected = accountComboBox.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selectedAccount = getAccountFromSelection(selected);
                updateButtonStates();
            }
        });
    }
    
    private void setupAccountComboBox() {
        if (customer == null) {
            System.out.println("DEBUG: Customer is null, cannot setup accounts");
            return;
        }
        
        ObservableList<String> accountItems = FXCollections.observableArrayList();
        for (Account account : bank.getCustomerAccounts(customer)) {
            String accountInfo = account.getAccountType() + " - P" + 
                String.format("%.2f", account.getBalance()) + " (" + account.getAccountId() + ")";
            accountItems.add(accountInfo);
            System.out.println("DEBUG: Added account to combo: " + accountInfo);
        }
        
        accountComboBox.setItems(accountItems);
        
        if (!accountComboBox.getItems().isEmpty()) {
            accountComboBox.getSelectionModel().selectFirst();
            selectedAccount = getAccountFromSelection(accountComboBox.getValue());
        }
        updateButtonStates();
    }
    
    private Account getAccountFromSelection(String selection) {
        if (selection == null) return null;
        String accountId = selection.substring(selection.lastIndexOf("(") + 1, selection.lastIndexOf(")"));
        for (Account account : bank.getCustomerAccounts(customer)) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }
    
    private void updateButtonStates() {
        boolean hasAccountSelected = selectedAccount != null;
        depositBtn.setDisable(!hasAccountSelected);
        withdrawBtn.setDisable(!hasAccountSelected);
        historyBtn.setDisable(false);
        
        if (selectedAccount != null && selectedAccount instanceof SavingsAccount) {
            withdrawBtn.setDisable(true);
        }
    }
    
    @FXML
    private void handleDeposit() {
        if (selectedAccount == null) return;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DepositScreen.fxml"));
            Parent root = loader.load();
            DepositController controller = loader.getController();
            controller.setCustomer(customer);
            controller.setSelectedAccount(selectedAccount);
            Stage stage = new Stage();
            stage.setTitle("Make Deposit - " + selectedAccount.getAccountType());
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) depositBtn.getScene().getWindow()).close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    @FXML
    private void handleWithdraw() {
        if (selectedAccount == null) return;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WithdrawScreen.fxml"));
            Parent root = loader.load();
            WithdrawController controller = loader.getController();
            controller.setCustomer(customer);
            controller.setSelectedAccount(selectedAccount);
            Stage stage = new Stage();
            stage.setTitle("Make Withdrawal - " + selectedAccount.getAccountType());
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) withdrawBtn.getScene().getWindow()).close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    @FXML
    private void handleHistory() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TransactionHistory.fxml"));
            Parent root = loader.load();
            TransactionHistoryController controller = loader.getController();
            controller.setCustomer(customer);
            Stage stage = new Stage();
            stage.setTitle("Transaction History");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) historyBtn.getScene().getWindow()).close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    @FXML
    private void handleLogout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("RoleSelection.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Banking System");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) logoutBtn.getScene().getWindow()).close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    private void updateDashboard() {
        if (customer != null) {
            infoLabel.setText("Welcome, " + bank.getCustomerName(customer));
            double totalBalance = bank.getCustomerTotalBalance(customer);
            balanceLabel.setText("Total Balance: P" + String.format("%.2f", totalBalance));
            
            StringBuilder accountsInfo = new StringBuilder();
            for (Account account : bank.getCustomerAccounts(customer)) {
                accountsInfo.append(account.getAccountType())
                           .append(" (").append(account.getAccountId()).append("): P")
                           .append(String.format("%.2f", account.getBalance()))
                           .append("\n");
            }
            accountsArea.setText(accountsInfo.toString());
        }
    }
}