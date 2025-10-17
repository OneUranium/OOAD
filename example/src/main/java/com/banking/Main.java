import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Try different paths - one should work:
        
        // Option A: If files are in root
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        
        // Option B: If files are in same directory as Main.java
        // Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        
        // Option C: Full path
        // Parent root = FXMLLoader.load(getClass().getResource("/com/banking/views/LoginView.fxml"));

        primaryStage.setTitle("Banking System - TechBots Ltd");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}