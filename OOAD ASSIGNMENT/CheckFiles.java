import java.io.File;

public class CheckFiles {
    public static void main(String[] args) {
        File dir = new File(".");
        System.out.println("Current directory: " + dir.getAbsolutePath());
        System.out.println("\nFXML Files found:");
        
        for (File file : dir.listFiles()) {
            if (file.getName().toLowerCase().endsWith(".fxml")) {
                System.out.println("✓ " + file.getName());
            }
        }
        
        System.out.println("\nJava Files found:");
        for (File file : dir.listFiles()) {
            if (file.getName().toLowerCase().endsWith(".java")) {
                System.out.println("✓ " + file.getName());
            }
        }
    }
}