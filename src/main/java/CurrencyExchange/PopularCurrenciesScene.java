package CurrencyExchange;

import CurrencyExchange.UIComponents.Header;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PopularCurrenciesScene {
    static Region convertLayout;
    private static List<String> cursName;

    private static TableView<CurrencyContainer> tableView = new TableView<CurrencyContainer>();



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




        //popular currencies bodies


        //get information
        //store name here
        cursName = Arrays.asList(
                "AUD","USD","HKD","JPY"
        );

        //store exchange rate in order !!!!!!!
        List<Double> precur1 = new ArrayList<Double>();
        List<Double> nowcur1 = new ArrayList<Double>();
        List<Double> precur2 = new ArrayList<Double>();
        List<Double> nowcur2 = new ArrayList<Double>();
        List<Double> precur3 = new ArrayList<Double>();
        List<Double> nowcur3 = new ArrayList<Double>();
        List<Double> precur4 = new ArrayList<Double>();
        List<Double> nowcur4 = new ArrayList<Double>();


        precur1.add(1.1);
        precur1.add(2.2);
        precur1.add(3.3);
        nowcur1.add(1.12);
        nowcur1.add(2.23);
        nowcur1.add(3.34);


        precur2.add(1.10);
        precur2.add(2.23);
        precur2.add(3.34);
        nowcur2.add(1.00);
        nowcur2.add(9.02);
        nowcur2.add(100.34);

        precur3.add(1.10);
        precur3.add(0.23);
        precur3.add(7.34);
        nowcur3.add(9.10);
        nowcur3.add(9.02);
        nowcur3.add(0.34);

        precur4.add(1.0);
        precur4.add(9.23);
        precur4.add(7.74);
        nowcur4.add(9.0);
        nowcur4.add(55.02);
        nowcur4.add(0.34);


        ObservableList<CurrencyContainer> currencyContainers = FXCollections.observableArrayList(
                new CurrencyContainer(
                        cursName.get(0),//name
                        "-",
                        getRateaAndSymbol(precur1.get(0),nowcur1.get(0)),
                        getRateaAndSymbol(precur1.get(1),nowcur1.get(1)),
                        getRateaAndSymbol(precur1.get(2),nowcur1.get(2))),
                new CurrencyContainer(
                        cursName.get(1),//name
                        getRateaAndSymbol(precur2.get(0),nowcur2.get(0)),
                        "-",
                        getRateaAndSymbol(precur2.get(1),nowcur2.get(1)),
                        getRateaAndSymbol(precur2.get(2),nowcur2.get(2))
                ),
                new CurrencyContainer(
                        cursName.get(2),//name
                        getRateaAndSymbol(precur3.get(0),nowcur3.get(0)),
                        getRateaAndSymbol(precur3.get(1),nowcur3.get(1)),
                        "-",
                        getRateaAndSymbol(precur3.get(2),nowcur3.get(2))
                ),
                new CurrencyContainer(
                        cursName.get(3),//name
                        getRateaAndSymbol(precur4.get(0),nowcur4.get(0)),
                        getRateaAndSymbol(precur4.get(1),nowcur4.get(1)),
                        getRateaAndSymbol(precur4.get(2),nowcur4.get(2))
                        ,"-")
        );


        // this below is to create column to store rate and name;
        TableColumn<CurrencyContainer,String> curs = new TableColumn("From/To");
        curs.setCellValueFactory(data->data.getValue().nameProperty());

        TableColumn<CurrencyContainer,String> currency1 = new TableColumn(cursName.get(0));
        currency1.setCellValueFactory(data->data.getValue().rate1Property());

        TableColumn<CurrencyContainer,String> currency2 = new TableColumn(cursName.get(1));
        currency2.setCellValueFactory(data->data.getValue().rate2Property());

        TableColumn<CurrencyContainer,String> currency3 = new TableColumn(cursName.get(2));
        currency3.setCellValueFactory(data->data.getValue().rate3Property());

        TableColumn<CurrencyContainer,String> currency4 = new TableColumn(cursName.get(3));
        currency4.setCellValueFactory(data->data.getValue().rate4Property());

        tableView.setItems(currencyContainers);
        tableView.getColumns().addAll(curs, currency1, currency2,currency3,currency4);

        final VBox vbox = new VBox();
        vbox.getChildren().addAll( tableView);


        //BorderPane root = new BorderPane();
        tableView.setFixedCellSize(55);//cell size should be changed
        tableView.prefWidthProperty().set(400);
        curs.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
        currency1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
        currency2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
        currency3.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
        currency4.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));

        

        layout.getChildren().addAll(vbox);

        convertLayout = layout;

    }

    private static String getRateaAndSymbol(double pre,double now) {
        if(pre >now){
            return now + "↑";
        }else if(pre<now){
            return now + "↓";
        }else{
            return now + "-";
        }
    }



}