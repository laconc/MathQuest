package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.Controller;

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
        String resource = "/fxml/Login.fxml";
        swapScene(resource, null);
    }

    /**
    * Displays the main menu's UI.
    *
    * @param account the account that was used to log in
    */
    public void showMainMenu(Account account) {
        String resource = "/fxml/Menu.fxml";
        swapScene(resource, account);
    }
    
    // TODO: Write the relevant class and resource
    public void showLevelSelect(Account account) {
        String resource = "/fxml/LevelSelect.fxml";
        swapScene(resource, account);
    }
    
    // TODO: Write the relevant class and resource
    public void showGame(Account account) {
        String resource = "/fxml/Game.fxml";
        swapScene(resource, account);
    }
    
    /**
    * Displays the Options screen.
    *
    * @param account the account that was used to log in
    */
    public void showOptions(Account account) {
        String resource = "/fxml/Options.fxml";
        swapScene(resource, account);
    }
    
    // TODO: Write the relevant class and resource
    public void showAdminTools(Account account) {
        String resource = "/fxml/AdminTools.fxml";
        swapScene(resource, account);
    }
    
    // TODO: Write the relevant class and resource
    public void showQuitGame(Account account) {
        String resource = "/fxml/QuitGame.fxml";
        swapScene(resource, account);
    }
    
    // TODO: Write the relevant class and resource
    public void showStats(Account account) {
        String resource = "/fxml/Stats.fxml";
        swapScene(resource, account);
    }
    
    /**
    * Changes the active scene and controller.
    * 
    * @param resource the FXML file to load
    * @param account the account that was used to log in
    */
    private void swapScene(String resource, Account account) {
        try {
            // prepares the new scene
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(resource));
            AnchorPane page = (AnchorPane) loader.load();
            Scene scene = new Scene(page);
            // scene.getStylesheets().add("/styles/Styles.css");

            // swaps to the new scene
            mainStage.close(); // closing it first to force it to re-center
            mainStage.setScene(scene);
            mainStage.show();

            // gives the controller access to the main app & passes the
            // account info
            Controller controller = loader.getController();
            controller.setMainApp(this);
            controller.start(account);

        } catch (IOException e) {
            System.out.println("Error swapping the scene.");
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
