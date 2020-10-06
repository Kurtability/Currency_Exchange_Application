package CurrencyExchange;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class ConvertScene {

    static Scene convertScene;

    static Scene getScene() {
        if (convertScene == null) {
            initScene();
        }

        return convertScene;
    }

    private static void initScene() {

        Label label = new Label("Currency Converter");
        label.setStyle("-fx-font-size: 2em; -fx-background-color: #8AE4EB; -fx-background-radius: 10px; -fx-padding: 2em;  -fx-text-fill: #5F634F");
        StackPane header = new StackPane(label);
        header.setStyle("-fx-background-color: #8AE4EB; -fx-padding: 12px; -fx-background-radius:0 0 25px 25px;");
        header.setAlignment(Pos.TOP_CENTER);

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(20);
        layout.setHgap(15);
        GridPane.setConstraints(label, 0, 0);

        Label lFrom = new Label("Select from currency:");
        lFrom.setStyle("-fx-font-size: 1.25em; -fx-text-fill: #5F634F");
        GridPane.setConstraints(lFrom, 0, 0);

        ChoiceBox<String> cbCurrencies = new ChoiceBox<String>();
        cbCurrencies.getItems().addAll("USD", "AUD", "SGD");
        cbCurrencies.setItems(FXCollections.observableArrayList("USD", "AUD", "SGD"));
        cbCurrencies.setTooltip(new Tooltip("Select the currency to convert from"));
        GridPane.setConstraints(cbCurrencies, 0, 1);

        TextField tfFromAmount = new TextField();
        tfFromAmount.textProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, 
                    String newValue) -> {
                    if (!newValue.matches("\\d*[.]?[0-9]?[0-9]?")) {
                        tfFromAmount.setText(oldValue);
                    }
                }
            );
        tfFromAmount.setPromptText("From amount");
        GridPane.setConstraints(tfFromAmount, 2, 1);

        layout.getChildren().addAll(cbCurrencies, tfFromAmount, lFrom);

        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(layout);
        root.setStyle("-fx-background-color: #99C24D");
        convertScene = new Scene(root, 640, 480);
    }
    
}