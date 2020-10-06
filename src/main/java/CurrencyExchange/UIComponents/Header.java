package CurrencyExchange.UIComponents;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Header {

    public static StackPane header;

    public static Region getHeader() {
        if (header == null) {
            initHeader();
        }

        return header;
    }

    private static void initHeader() {
        Button close = new Button("X");
        close.setAccessibleHelp("Close the app.");
        close.setOnAction(e -> {
            ((Stage) close.getScene().getWindow()).close();
        });
        close.setStyle("-fx-background-color: #EA949A; -fx-font-size: 1.5em; -fx-border-width: 0;");

        Label label = new Label("Currency Converter");
        label.setStyle("-fx-font-size: 3em; -fx-background-color: #8AE4EB; -fx-background-radius: 10px; -fx-padding: 2em;  -fx-text-fill: #5F634F");
        header = new StackPane(label, close);
        header.setStyle("-fx-background-color: #8AE4EB; -fx-padding: 12px; -fx-background-radius:0 0 25px 25px;");
        header.setAlignment(Pos.TOP_CENTER);
        StackPane.setAlignment(close, Pos.TOP_RIGHT);
    }
    
}