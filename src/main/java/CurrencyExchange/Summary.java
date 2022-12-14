package CurrencyExchange;

import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

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
        DatePicker dpStart = new DatePicker();
        GridPane.setConstraints(dpStart, 5, 0);
        
        Label tooDate = new Label("Too Date:");
        tooDate.setStyle("-fx-font-size: 1.00em; -fx-text-fill: #5F634F");
        GridPane.setConstraints(tooDate, 4, 1);

        TextField end = new TextField();
        end.setPromptText("YYYY-MM-DD HH:mm:ss");
        DatePicker dpEnd = new DatePicker();
        GridPane.setConstraints(dpEnd, 5, 1);

        Label results = new Label("");
        GridPane.setConstraints(results, 0, 4);

        Button getHistory = new Button("summarize");
        getHistory.setOnAction(event -> {
            String curr1 = select.getSelectionModel().getSelectedItem();
            String curr2 = too.getSelectionModel().getSelectedItem();
            if (curr1 == null || curr2 == null) {
                hint.setText("Please Select Currencies");
                start.setText("");
                end.setText("");
            } else {
                LocalDate startDate = dpStart.getValue();
                LocalDate endDate = dpEnd.getValue();
                if (startDate == null || endDate == null) {
                    hint.setText("Select date");
                    return;
                }
                if (startDate.compareTo(endDate) > 0) {
                    hint.setText("Start date can't be after end date.");
                    return;
                }

                try {
                    System.out.println(startDate + " " + endDate);
                    System.out.println(History.history_conversion(curr1, curr2, startDate, endDate));
                    results.setText(History.history_conversion(curr1, curr2, startDate, endDate));
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
        layout.getChildren().addAll(results, getHistory, dpStart, dpEnd, tooDate, fromDate, fromCurr, toCurr, select, too, hint);
        summaryLayout = layout;
    }
}
