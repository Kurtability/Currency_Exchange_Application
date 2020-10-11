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

        layout.gridLinesVisibleProperty();
        layout.getChildren().addAll(start, end, tooDate, fromDate, fromCurr, toCurr, select, too, hint);
        summaryLayout = layout;
    }
}