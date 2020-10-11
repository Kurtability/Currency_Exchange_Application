package CurrencyExchange;

import java.time.LocalDateTime;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.control.ChoiceBox;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Summary {
    
    static Region summaryLayout;

    public static Region getLayout() {
        initScene();
        return summaryLayout;
    }

    private static void initScene() {
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20));
        //layout.setAlignment(Pos.CENTER);
        layout.setVgap(20);
        layout.setHgap(15);

        Label fromCurr = new Label("Select History From Currency:");
        fromCurr.setStyle("-fx-font-size: 1.00em; -fx-text-fill: #5F634F");
        GridPane.setConstraints(fromCurr, 0, 0);

        ChoiceBox<String> select = new ChoiceBox<String>();
        select.getItems().addAll(FileHandler.getAllCurrencies());
        GridPane.setConstraints(select, 2, 0);

        Label toCurr = new Label("Too Currency");
        toCurr.setStyle("-fx-font-size: 1.00em; -fx-text-fill: #5F634F");
        GridPane.setConstraints(toCurr, 0, 1);

        ChoiceBox<String> too = new ChoiceBox<String>();
        too.getItems().addAll(FileHandler.getAllCurrencies());
        GridPane.setConstraints(too, 2, 1);

        Label fromDate = new Label("From date:");
        fromDate.setStyle("-fx-font-size: 1.00em; -fx-text-fill: #5F634F");
        GridPane.setConstraints(fromDate, 4, 0);

        Label hint = new Label("e.g. 2007-12-03T10:15:30");
        hint.setStyle("-fx-font-size: 0.75em; -fx-text-fill: #5F634F");
        GridPane.setConstraints(hint, 5, 2);

        TextField start = new TextField();
        start.setPromptText("YYYY-MM-DD HH:mm:ss");
        GridPane.setConstraints(start, 5, 0);
        
        Label tooDate = new Label("Too Date:");
        tooDate.setStyle("-fx-font-size: 1.00em; -fx-text-fill: #5F634F");
        GridPane.setConstraints(tooDate, 4, 1);

        TextField end = new TextField();
        end.setPromptText("YYYY-MM-DD HH:mm:ss");
        GridPane.setConstraints(end, 5, 1);

        Label results = new Label("");
        GridPane.setConstraints(results, 0, 4);

        Button getHistory = new Button("summarize");
        getHistory.setOnAction(event -> {
            String curr1 = select.getSelectionModel().getSelectedItem();
            String curr2 = too.getSelectionModel().getSelectedItem();
            if (curr1.equals(null) || curr2.equals(null)) {
                hint.setText("Please Select Currencies");
                start.setText("");
                end.setText("");
            } else {
                try {
                    DateTimeFormatter format= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime a = LocalDateTime.parse(start.getText(), format);
                    LocalDateTime b = LocalDateTime.parse(end.getText(), format);
                    System.out.println(a + " " + b);
                    System.out.println(History.history_conversion(curr1, curr2, a, b));
                    results.setText(History.history_conversion(curr1, curr2, a, b));
                    System.out.println("fucking works");

                } catch (Exception e) {
                    e.printStackTrace();
                    hint.setText("Please enter date in valid format");
                    start.setText("");
                    end.setText("");
                }
            }

            

            /*String time1 = "2017-10-06 17:48:23";
            // convert String to LocalDateTime
            LocalDateTime localDateTime = LocalDateTime.parse(time1);
            // parse it to a specified format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
            System.out.println(localDateTime.format(formatter));*/
            
        });
        GridPane.setConstraints(getHistory, 3, 3);
        hint.setText("e.g. 2007-12-03T10:15:30");

        layout.gridLinesVisibleProperty();
        layout.getChildren().addAll(results, getHistory, start, end, tooDate, fromDate, fromCurr, toCurr, select, too, hint);
        summaryLayout = layout;
    }
}
