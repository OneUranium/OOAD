import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WithdrawController {
    @FXML private TextField amountField;
    @FXML private Label messageLabel;
    @FXML private Button withdrawBtn;
    @FXML private Button backBtn;
    
    private Customer customer;
    private Account selectedAccount;
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
        if (selectedAccount != null) {
            messageLabel.setText("Withdraw from: " + selectedAccount.getAccountType() + " Account");
            if (selectedAccount instanceof SavingsAccount) {
                messageLabel.setText("Withdrawals not allowed from Savings accounts");
                withdrawBtn.setDisable(true);
            }
        }
    }
    
    @FXML
    private void handleWithdraw() {
        if (selectedAccount == null) {
            showError("No account selected");
            return;
        }
        
        if (selectedAccount instanceof SavingsAccount) {
            showError("Withdrawals are not allowed from Savings accounts");
            return;
        }
        
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0) {
                boolean success = selectedAccount.withdraw(amount);
                if (success) {
                    showSuccess("Withdrawal successful! P" + amount + " withdrawn from " + 
                               selectedAccount.getAccountType() + " account.");
                    amountField.clear();
                } else {
                    showError("Withdrawal failed - Insufficient funds");
                }
            } else {
                showError("Amount must be positive");
            }
        } catch (NumberFormatException ex) {
            showError("Please enter a valid amount");
        }
    }
    
    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerDashboard.fxml"));
            Parent root = loader.load();
            CustomerDashboardController controller = loader.getController();
            controller.setCustomer(customer);
            Stage stage = new Stage();
            stage.setTitle("Customer Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) backBtn.getScene().getWindow()).close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    private void showSuccess(String message) {
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
    }
    
    private void showError(String message) {
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
    }
}