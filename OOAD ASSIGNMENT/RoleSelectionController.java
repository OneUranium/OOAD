import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RoleSelectionController {
    @FXML private Button bankTellerBtn;
    @FXML private Button customerBtn;
    
    @FXML
    private void handleBankTellerLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("BankTellerLogin.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Bank Teller Login");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) bankTellerBtn.getScene().getWindow()).close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    @FXML
    private void handleCustomerLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CustomerLogin.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Customer Login");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) customerBtn.getScene().getWindow()).close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}