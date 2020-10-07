package CurrencyExchange;

import CurrencyExchange.UIComponents.Header;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class AdminScene {

    static Scene adminScene;

    static Scene getScene() {
        if (adminScene == null) {
            initScene();
        }

        return adminScene;
    }

    private static void initScene() {

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(20);
        layout.setHgap(15);

        Button btn = new Button();
        btn.setText("SAVE");
        btn.setOnAction((event) -> {
            System.out.println("New currency rate saved!");
        });
        GridPane.setConstraints(btn,4,4);


        Label lFrom = new Label("Add Currency");
        lFrom.setStyle("-fx-font-size: 1.25em; -fx-text-fill: #5F634F");
        GridPane.setConstraints(lFrom, 2, 0);

        Label s_lForm = new Label("give currency --->");
        GridPane.setConstraints(s_lForm, 1, 2);




        TextField tfAddCurrency = new TextField();
        tfAddCurrency.textProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue,
                              String newValue) -> {
                            if (!newValue.matches("[A-Za-z]?") && newValue.length() >4) {
                                tfAddCurrency.setText(oldValue);
                            }
                        }
                );

        tfAddCurrency.setPromptText("Currency type");
        GridPane.setConstraints(tfAddCurrency, 2, 2);





        Label addV = new Label("Add Currency Rate (In AUD)");
        addV.setStyle("-fx-font-size: 1.25em; -fx-text-fill: #5F634F");
        GridPane.setConstraints(addV, 4, 0);

        TextField tfAddValue = new TextField();
        tfAddValue.textProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue,
                              String newValue) -> {
                            if (!newValue.matches("\\d*[.]?[0-9]?[0-9]?")) {
                                tfAddValue.setText(oldValue);
                            }
                        }
                );
        tfAddValue.setPromptText("Currency rate");
        GridPane.setConstraints(tfAddValue, 4, 2);

        layout.getChildren().addAll(tfAddCurrency, tfAddValue, lFrom, s_lForm, addV, btn);

        BorderPane root = new BorderPane();
        root.setTop(Header.getHeader());
        root.setCenter(layout);
        root.setStyle("-fx-background-color: #99C24D; -fx-background-radius: 0 0 20 20");
        adminScene = new Scene(root, 640, 480);
        adminScene.setFill(Color.TRANSPARENT);
    }

}