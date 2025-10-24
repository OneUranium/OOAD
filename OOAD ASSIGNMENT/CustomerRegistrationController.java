import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CustomerRegistrationController {
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField passField;
    @FXML private Label messageLabel;
    @FXML private Button registerBtn;
    @FXML private Button backBtn;
    
    private Bank bank = Bank.getInstance();
    
    @FXML
    private void handleRegister() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passField.getText();
        
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showError("Please fill all fields"); 
            return;
        }
        
        bank.addCustomer(name, email, password);
        
        // Print all customers for debugging
        System.out.println("=== AFTER REGISTRATION - ALL CUSTOMERS ===");
        for (Customer customer : bank.getCustomers()) {
            if (customer instanceof IndividualCustomer) {
                IndividualCustomer ind = (IndividualCustomer) customer;
                System.out.println("Name: " + ind.getCustomerName() + " " + ind.getCustomerSurname());
                System.out.println("Email: " + customer.getEmailAddress());
                System.out.println("ID: " + customer.getCustomerId());
                System.out.println("---");
            }
        }
        
        showSuccess("Customer registered successfully!\n\nLogin with:\nEmail: " + email + "\nPassword: " + password);
        
        nameField.clear();
        emailField.clear();
        passField.clear();
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
    
    private void showSuccess(String message) {
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
    }
    
    private void showError(String message) {
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
    }
}