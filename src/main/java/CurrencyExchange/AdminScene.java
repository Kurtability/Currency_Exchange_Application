package CurrencyExchange;

import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;


public class AdminScene {

    static Region adminLayout;

    public static Region getLayout() {
        initScene();
        return adminLayout;
    }

    private static void initScene() {

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(20);
        layout.setHgap(15);

        Button btn = new Button();
        btn.setText("SAVE");


        GridPane.setConstraints(btn,2,4);
        GridPane.setHalignment(btn, HPos.RIGHT);


        Label lFrom = new Label("Add Currency");
        lFrom.setStyle("-fx-font-size: 1.25em; -fx-text-fill: #5F634F");
        GridPane.setConstraints(lFrom, 2, 0);

        Label s_lForm = new Label("give currency --->");
        GridPane.setConstraints(s_lForm, 1, 2);




        TextField tfAddCurrency = new TextField();

        tfAddCurrency.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 4) {
                tfAddCurrency.setText(oldValue);
                System.out.println(tfAddCurrency.getText());
            }
        });


        
        tfAddCurrency.setPromptText("Upper Case Only");
        GridPane.setConstraints(tfAddCurrency, 2, 2);


        Label addV = new Label("Add Currency Rate (In AUD)");
        addV.setStyle("-fx-font-size: 1.25em; -fx-text-fill: #5F634F");
        GridPane.setConstraints(addV, 4, 0);

        TextField tfAddValue = new TextField();
        tfAddValue.textProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue,
                              String newValue) -> {
                            if (!newValue.matches("\\d*[.]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?")) {
                                tfAddValue.setText(oldValue);
                            }
                        }
                );
        tfAddValue.setPromptText("Currency rate");
        GridPane.setConstraints(tfAddValue, 4, 2);


        btn.setOnAction((event) -> {
            System.out.println("New currency rate saved!");

            try{
                double a = Double.parseDouble(tfAddValue.getText());
                FileHandler.add(tfAddCurrency.getText(), a);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        });

        layout.getChildren().addAll(tfAddCurrency, tfAddValue, lFrom, s_lForm, addV, btn);

        adminLayout = layout;

    }

}
