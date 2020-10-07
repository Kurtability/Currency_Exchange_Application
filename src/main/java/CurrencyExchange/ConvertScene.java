package CurrencyExchange;

import CurrencyExchange.UIComponents.Header;
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
import javafx.scene.paint.Color;

public class ConvertScene {

    static Scene convertScene;

    static Scene getScene() {
        if (convertScene == null) {
            initScene();
        }

        return convertScene;
    }

    private static void initScene() {

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(20);
        layout.setHgap(15);

        Label lFrom = new Label("Select currency to convert from:");
        lFrom.setStyle("-fx-font-size: 1.25em; -fx-text-fill: #5F634F");
        GridPane.setConstraints(lFrom, 0, 0);

        ChoiceBox<String> cbFromCurrencies = new ChoiceBox<String>();
        cbFromCurrencies.getItems().addAll("USD", "AUD", "SGD");
        cbFromCurrencies.setItems(FXCollections.observableArrayList("USD", "AUD", "SGD"));
        cbFromCurrencies.setTooltip(new Tooltip("Select the currency to convert from"));
        GridPane.setConstraints(cbFromCurrencies, 0, 1);

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

        Label lTo = new Label("Select currency to convert to:");
        lTo.setStyle("-fx-font-size: 1.25em; -fx-text-fill: #5F634F");
        GridPane.setConstraints(lTo, 0, 3);

        ChoiceBox<String> cbToCurrencies = new ChoiceBox<String>();
        cbToCurrencies.getItems().addAll("USD", "AUD", "SGD");
        cbToCurrencies.setItems(FXCollections.observableArrayList("USD", "AUD", "SGD"));
        cbToCurrencies.setTooltip(new Tooltip("Select the currency to convert to"));
        GridPane.setConstraints(cbToCurrencies, 0, 4);

        layout.getChildren().addAll(cbFromCurrencies, tfFromAmount, lFrom, lTo, cbToCurrencies);

        BorderPane root = new BorderPane();
        root.setTop(Header.getHeader());
        root.setCenter(layout);
        root.setStyle("-fx-background-color: #99C24D; -fx-background-radius: 0 0 20 20");
        convertScene = new Scene(root, 640, 480);
        convertScene.setFill(Color.TRANSPARENT);
    }
    
}