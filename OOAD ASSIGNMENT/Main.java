import javafx.application.Application;  // ADD THIS IMPORT
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the RoleSelection screen first
            Parent root = FXMLLoader.load(getClass().getResource("RoleSelection.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Banking System");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
            
            // Show simple error window if FXML fails
            showErrorWindow(primaryStage, e);
        }
    }
    
    private void showErrorWindow(Stage primaryStage, Exception e) {
        try {
            javafx.scene.control.Label errorLabel = new javafx.scene.control.Label(
                "Error starting Banking System!\n\n" +
                "Problem: " + e.getMessage() + "\n\n" +
                "Make sure all FXML files are in the same folder:\n" +
                "• RoleSelection.fxml\n" +
                "• BankTellerLogin.fxml\n" + 
                "• CustomerLogin.fxml\n" +
                "• BankTellerDashboard.fxml\n" +
                "• CustomerDashboard.fxml\n" +
                "• CustomerRegistration.fxml\n" +
                "• ViewCustomerAccounts.fxml\n" +
                "• DepositScreen.fxml\n" +
                "• WithdrawScreen.fxml\n" +
                "• TransactionHistory.fxml"
            );
            errorLabel.setStyle("-fx-font-size: 12px; -fx-padding: 20px;");
            
            javafx.scene.layout.VBox root = new javafx.scene.layout.VBox(errorLabel);
            root.setStyle("-fx-alignment: center; -fx-spacing: 10px;");
            
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setTitle("Banking System - Error");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}