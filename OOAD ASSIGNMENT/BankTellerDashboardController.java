import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BankTellerDashboardController {
    @FXML private Label infoLabel;
    @FXML private Button registerCustomerBtn;
    @FXML private Button viewCustomersBtn;
    @FXML private Button logoutBtn;
    
    @FXML
    private void handleRegisterCustomer() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CustomerRegistration.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Register New Customer");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) registerCustomerBtn.getScene().getWindow()).close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    @FXML
    private void handleViewCustomers() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ViewCustomerAccounts.fxml"));
            Stage stage = new Stage();
            stage.setTitle("All Customer Accounts");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) viewCustomersBtn.getScene().getWindow()).close();
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
}