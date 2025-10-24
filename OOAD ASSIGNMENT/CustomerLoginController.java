import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CustomerLoginController {
    @FXML private TextField emailField;
    @FXML private PasswordField passField;
    @FXML private Label messageLabel;
    @FXML private Button loginBtn;
    @FXML private Button backBtn;
    
    private Bank bank = Bank.getInstance();
    
    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passField.getText();
        
        if (email.isEmpty() || password.isEmpty()) {
            showError("Please enter both fields"); 
            return;
        }
        
        Customer customer = bank.authenticateCustomer(email, password);
        if (customer != null) {
            showSuccess("Login successful!");
            openCustomerDashboard(customer);
        } else {
            showError("Invalid email or password");
        }
    }
    
    @FXML
    private void handleBack() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("RoleSelection.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Banking System");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) backBtn.getScene().getWindow()).close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    private void openCustomerDashboard(Customer customer) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerDashboard.fxml"));
            Parent root = loader.load();
            CustomerDashboardController controller = loader.getController();
            controller.setCustomer(customer);
            Stage stage = new Stage();
            stage.setTitle("Customer Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) loginBtn.getScene().getWindow()).close();
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