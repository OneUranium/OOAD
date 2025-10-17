package controllers.models;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerRegistrationBoundary {

    @FXML private ComboBox<String> customerTypeCombo;
    @FXML private VBox individualBox;
    @FXML private TextField customerNameField;
    @FXML private TextField customerSurnameField;
    @FXML private TextField emailAddressField;
    @FXML private TextField customerAddressField;
    @FXML private TextField employmentInfoField;
    @FXML private VBox companyBox;
    @FXML private TextField companyNameField;
    @FXML private TextField registrationNumberField;
    @FXML private TextField companyEmailAddressField;
    @FXML private TextField companyCustomerAddressField;
    @FXML private Button createButton;
    @FXML private Button cancelButton;
    @FXML private Button returnButton;
    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        // Setup UI components only
        customerTypeCombo.getItems().addAll("Individual", "Company");
        customerTypeCombo.setValue("Individual");
        customerTypeCombo.setOnAction(e -> handleCustomerTypeChange());

        // Initialize UI state
        handleCustomerTypeChange();
    }

    private void handleCustomerTypeChange() {
        String type = customerTypeCombo.getValue();
        boolean isIndividual = "Individual".equals(type);

        // UI logic only - show/hide sections
        individualBox.setVisible(isIndividual);
        individualBox.setManaged(isIndividual);
        companyBox.setVisible(!isIndividual);
        companyBox.setManaged(!isIndividual);
    }

    @FXML
    private void handleCreateAccount() {
        // UI validation only - no business logic
        if (validateForm()) {
            messageLabel.setText("SIMULATION: Customer registration successful!");
            messageLabel.setStyle("-fx-text-fill: green;");

            // Navigate to welcome screen (UI navigation only)
            navigateToWelcome();
        }
    }

    @FXML
    private void handleCancel() {
        // UI navigation only
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleReturn() {
        try {
            // Return to log in screen (UI navigation only)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Banking System Login");
            stage.setScene(new Scene(root));
            stage.show();

            // Close registration window
            Stage registrationStage = (Stage) returnButton.getScene().getWindow();
            registrationStage.close();

        } catch (Exception e) {
            messageLabel.setText("Error returning to login");
        }
    }

    private boolean validateForm() {
        String type = customerTypeCombo.getValue();

        // UI validation only
        if ("Individual".equals(type)) {
            if (customerNameField.getText().isEmpty()) {
                showError("Please enter first name");
                return false;
            }
            if (customerSurnameField.getText().isEmpty()) {
                showError("Please enter last name");
                return false;
            }
            // ... more UI validation
        } else {
            if (companyNameField.getText().isEmpty()) {
                showError("Please enter company name");
                return false;
            }
            // ... more UI validation
        }
        return true;
    }

    private void showError(String message) {
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-text-fill: red;");
    }

    private void navigateToWelcome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/welcome.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Welcome");
            stage.setScene(new Scene(root));
            stage.show();

            // Close registration window
            Stage registrationStage = (Stage) createButton.getScene().getWindow();
            registrationStage.close();

        } catch (Exception e) {
            showError("Error navigating to welcome screen");
        }
    }
}