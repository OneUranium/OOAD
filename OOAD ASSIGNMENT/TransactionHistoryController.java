import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TransactionHistoryController {
    @FXML private TextArea historyArea;
    @FXML private Button backBtn;
    
    private Customer customer;
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
        displayTransactionHistory();
    }
    
    @FXML
    private void initialize() {
        historyArea.setEditable(false);
        if (customer != null) {
            displayTransactionHistory();
        }
    }
    
    @FXML
    private void handleBack() {
        ((Stage) backBtn.getScene().getWindow()).close();
    }
    
    private void displayTransactionHistory() {
        if (customer != null) {
            Bank bank = Bank.getInstance();
            String history = bank.getCustomerTransactionHistory(customer);
            historyArea.setText(history);
        } else {
            historyArea.setText("No customer data available.");
        }
    }
}