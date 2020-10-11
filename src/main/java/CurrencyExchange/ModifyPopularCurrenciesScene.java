package CurrencyExchange;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class ModifyPopularCurrenciesScene {
    static Region convertLayout;

    public static Region getLayout() {
        if (convertLayout == null) {
            initScene();
        }

        return convertLayout;
    }

    private static void initScene() {

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(20);
        layout.setHgap(15);

        Label lFrom = new Label("Select four currencies to add to top currencies: (NO DUPLICATES!)");
        lFrom.setStyle("-fx-font-size: 1.25em; -fx-text-fill: #5F634F");
        GridPane.setConstraints(lFrom, 0, 0);

        ChoiceBox<String> cbFromCurrencies1 = new ChoiceBox<String>();
        cbFromCurrencies1.getItems().addAll(FileHandler.getAllCurrencies());
        cbFromCurrencies1.setTooltip(new Tooltip("one of the top four"));
        GridPane.setConstraints(cbFromCurrencies1, 0, 1);

        ChoiceBox<String> cbFromCurrencies2 = new ChoiceBox<String>();
        cbFromCurrencies2.getItems().addAll(FileHandler.getAllCurrencies());
        cbFromCurrencies2.setTooltip(new Tooltip("one of the top four"));
        GridPane.setConstraints(cbFromCurrencies2, 0, 2);

        ChoiceBox<String> cbFromCurrencies3 = new ChoiceBox<String>();
        cbFromCurrencies3.getItems().addAll(FileHandler.getAllCurrencies());
        cbFromCurrencies3.setTooltip(new Tooltip("one of the top four"));
        GridPane.setConstraints(cbFromCurrencies3, 1, 1);

        ChoiceBox<String> cbFromCurrencies4 = new ChoiceBox<String>();
        cbFromCurrencies4.getItems().addAll(FileHandler.getAllCurrencies());
        cbFromCurrencies4.setTooltip(new Tooltip("one of the top four"));
        GridPane.setConstraints(cbFromCurrencies4, 1, 2);


        Button btn = new Button("SAVE");
        btn.setOnAction(event -> {
            System.out.println(cbFromCurrencies1.getSelectionModel().getSelectedItem());

            TopFour.add(cbFromCurrencies1.getSelectionModel().getSelectedItem(),cbFromCurrencies2.getSelectionModel().getSelectedItem(),cbFromCurrencies3.getSelectionModel().getSelectedItem(),cbFromCurrencies4.getSelectionModel().getSelectedItem());

        });

        GridPane.setConstraints(btn, 0, 6);
        GridPane.setHalignment(btn, HPos.CENTER);




        layout.getChildren().addAll(lFrom ,cbFromCurrencies1, cbFromCurrencies2,cbFromCurrencies3,cbFromCurrencies4, btn);

        convertLayout = layout;
    }


}
