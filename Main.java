import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DatabaseManager;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // ✅ Initialize database (creates tables if not exist)
        DatabaseManager.initDB();

        // ✅ Load the login screen
        Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
        primaryStage.setTitle("Assignment Tracker");
        primaryStage.setScene(new Scene(root, 400, 400)); // Optional fixed size
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
