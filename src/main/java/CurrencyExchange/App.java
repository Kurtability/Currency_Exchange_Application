/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package CurrencyExchange;

import CurrencyExchange.UIComponents.Header;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setMinHeight(350);
        primaryStage.setMinWidth(350);
        // For rounded bottom corners
        // primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Currency Exchange");

        BorderPane root = new BorderPane();
        root = new BorderPane(ConvertScene.getLayout(), Header.getHeader(), null, null, null);
        root.setStyle("-fx-background-color: #99C24D;"); // -fx-background-radius: 0 0 20 20");

        Scene mainPage = new Scene(root, 640, 480);

        primaryStage.setScene(mainPage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
