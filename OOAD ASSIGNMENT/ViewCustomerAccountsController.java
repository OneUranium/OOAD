import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ViewCustomerAccountsController {
    @FXML private TextArea accountsArea;
    @FXML private Button backBtn;
    
    private Bank bank = Bank.getInstance(); // SINGLETON
    
    @FXML
    private void initialize() {
        displayAllCustomerAccounts();
    }
    
    @FXML
    private void handleBack() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("BankTellerDashboard.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Bank Teller Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) backBtn.getScene().getWindow()).close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    private void displayAllCustomerAccounts() {
        StringBuilder sb = new StringBuilder();
        for (Customer customer : bank.getCustomers()) {
            sb.append("Customer: ").append(bank.getCustomerName(customer))
              .append(" (ID: ").append(customer.getCustomerId()).append(")\n");
            sb.append("Email: ").append(customer.getEmailAddress()).append("\n");
            sb.append("Type: ").append(customer.getCustomerType()).append("\n");
            sb.append("Accounts:\n");
            
            for (Account account : bank.getCustomerAccounts(customer)) {
                sb.append("  - ").append(account.getAccountType())
                  .append(": P").append(String.format("%.2f", account.getBalance()))
                  .append(" (").append(account.getAccountId()).append(")\n");
            }
            sb.append("------------------------\n");
        }
        accountsArea.setText(sb.toString());
    }
}