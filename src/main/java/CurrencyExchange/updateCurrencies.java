package CurrencyExchange;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.control.ChoiceBox;

public class updateCurrencies {

    static Region updateLayout;
    
    public static Region getLayout() {
        initScene();
        return updateLayout;
    }
    // when a currency is updated, do all currency rates have to be changed or only one
    private static void initScene() {
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(20);
        layout.setHgap(20);
        
        //Label title = new Label("Update a Currency in the Database");
        //GridPane.setConstraints(title, 0, 0);

        Label s = new Label("Update AUD too:");
        s.setStyle("-fx-font-size: 1.00em; -fx-text-fill: #5F634F");
        GridPane.setConstraints(s, 0, 1);

        ChoiceBox<String> select = new ChoiceBox<String>();
        select.getItems().addAll(FileHandler.getAllCurrencies());
        GridPane.setConstraints(select, 1, 1);

        TextField rate = new TextField();
        rate.textProperty()
        .addListener((ObservableValue<? extends String> observable, String oldValue,
                      String newValue) -> {
                    if (!newValue.matches("\\d*[.]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?")) {
                        rate.setText(oldValue);
                    }
                }
        );
        rate.setPromptText("Enter Currency Rate");
        GridPane.setConstraints(rate, 1, 2);

        Label r = new Label("At the Exchange Rate:");
        GridPane.setConstraints(r, 0, 2);
        r.setStyle("-fx-font-size: 1.00em; -fx-text-fill: #5F634F");
        
        Label error = new Label("");
        error.setStyle("-fx-font-size: 1.00em; -fx-text-fill: #5F634F");
        GridPane.setConstraints(error, 2, 3);
        Button update = new Button("update");
        update.setOnAction(event -> {
            try {
                double exRate = Double.parseDouble(rate.getText());
                String currency = select.getSelectionModel().getSelectedItem();
                if (currency.equals(null)) {
                    error.setText("Enter a Valid Exchange Rate");
                } else {
                    FileHandler.add(currency, exRate);
                    error.setText("Updated Successfully!");
                }
                // does it matter if the currency is updated more than once a day???
            } catch (Exception e) {
                error.setText("Enter a Valid Exchange Rate");
            }
            rate.setText("");
            select.getSelectionModel().clearSelection();
        });
        GridPane.setConstraints(update, 1, 3);

        layout.getChildren().addAll(select, s, update, rate, r, error);
        updateLayout = layout;
    }

    // error: must update all currencies?
}
