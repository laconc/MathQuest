package team.mathquest;

import team.mathquest.model.Account;

import static javafx.application.Application.launch;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        mainStage.setTitle("MathQuest");

        showLogin();
    }

    /**
    * Displays the login window's UI.
    *
    */
    public void showLogin() {
        try {
            // prepares the Login Scene
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/Login.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Scene scene = new Scene(page);
            // scene.getStylesheets().add("/styles/Styles.css");

            // loads the Login scene
            mainStage.setScene(scene);

            // gives the controller access to the main app
            LoginController controller = loader.getController();
            controller.setMainApp(this);

            mainStage.show();

        } catch (IOException e) {
            System.out.println("Error loading the login window.");
        }
    }

    /**
    * Displays the main menu's UI.
    *
    * @param account the account that was used to log in
    */
    public void showMainMenu(Account account) {
        try {
            // prepares the Menu scene
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/Menu.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Scene scene = new Scene(page);

            // swaps to the Menu scene
            mainStage.close(); // closing it first to force it to re-center
            mainStage.setScene(scene);
            mainStage.show();

            // gives the controller access to the main app & passes the
            // account info
            MenuController controller = loader.getController();
            controller.setMainApp(this);
            controller.setAccount(account);

        } catch (IOException e) {
            System.out.println("Error loading the main menu.");
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
