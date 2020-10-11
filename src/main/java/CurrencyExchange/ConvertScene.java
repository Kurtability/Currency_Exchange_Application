package CurrencyExchange;

import java.text.DecimalFormat;
import java.util.List;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class ConvertScene {

    static Region convertLayout;

    public static Region getLayout() {
        initScene();
        return convertLayout;
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
        cbFromCurrencies.getItems().addAll(FileHandler.getAllCurrencies());
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
        cbToCurrencies.setItems(FXCollections.observableArrayList(FileHandler.getAllCurrencies()));
        cbToCurrencies.setTooltip(new Tooltip("Select the currency to convert to"));
        GridPane.setConstraints(cbToCurrencies, 0, 4);

        Label result = new Label("Result");
        result.setMaxWidth(Double.MAX_VALUE);
        GridPane.setFillWidth(result, true);
        GridPane.setConstraints(result, 2, 4);
        GridPane.setHalignment(result, HPos.RIGHT);
        result.setStyle("-fx-font-size: 2em; -fx-padding: 5px;");
        result.setAlignment(Pos.CENTER_RIGHT);

        Button convertButton = new Button("Convert");
        convertButton.setOnAction(event -> {

            try {

                
                DecimalFormat df = new DecimalFormat("###.##");
                double res = convert(cbFromCurrencies.getSelectionModel().getSelectedItem(),
                                        cbToCurrencies.getSelectionModel().getSelectedItem(),
                                        Double.parseDouble(tfFromAmount.getText()));
                result.setText(
                    df.format(res)
                );


            } catch(Exception e) {
                e.printStackTrace();
            }

        });
        GridPane.setConstraints(convertButton, 0, 6);
        GridPane.setHalignment(convertButton, HPos.RIGHT);

        layout.getChildren().addAll(cbFromCurrencies, tfFromAmount, lFrom, lTo, cbToCurrencies, result, convertButton);

        convertLayout = layout;
    }

    static double convert(String from, String to, double amount) {
        
        List<String> toChoice = FileHandler.get(to);
        List<String> fromChoice = FileHandler.get(from);

        double rate = Double.parseDouble(fromChoice.get(fromChoice.size() - 1).split(",")[1]);

        rate = Double.parseDouble(toChoice.get(toChoice.size() - 1).split(",")[1]) / rate;

        return rate*amount;
    }
    
}