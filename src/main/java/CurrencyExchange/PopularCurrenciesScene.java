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
        cursName = TopFour.getTopFour();

        ArrayList<ArrayList<String>> results = TopFour.getValues();

        List<Double> firstvalues = getTwoRecent(results.get(0));
        List<Double> secondvalues = getTwoRecent(results.get(1));
        List<Double> thirdvalues = getTwoRecent(results.get(2));
       List<Double> fourthvalues = getTwoRecent(results.get(3));



        //store exchange rate in order !!!!!!!
        List<Double> precur1 = new ArrayList<Double>();
        List<Double> nowcur1 = new ArrayList<Double>();
        List<Double> precur2 = new ArrayList<Double>();
        List<Double> nowcur2 = new ArrayList<Double>();
        List<Double> precur3 = new ArrayList<Double>();
        List<Double> nowcur3 = new ArrayList<Double>();
        List<Double> precur4 = new ArrayList<Double>();
        List<Double> nowcur4 = new ArrayList<Double>();


        precur1.add(getRateForRow(firstvalues.get(0),secondvalues.get(0)));
        precur1.add(getRateForRow(firstvalues.get(0),thirdvalues.get(0)));
        precur1.add(getRateForRow(firstvalues.get(0),fourthvalues.get(0)));
        nowcur1.add(getRateForRow(firstvalues.get(1),secondvalues.get(1)));
        nowcur1.add(getRateForRow(firstvalues.get(1),thirdvalues.get(1)));
        nowcur1.add(getRateForRow(firstvalues.get(1),fourthvalues.get(1)));


        precur2.add(getRateForRow(secondvalues.get(0),firstvalues.get(0)));
        precur2.add(getRateForRow(secondvalues.get(0),thirdvalues.get(0)));
        precur2.add(getRateForRow(secondvalues.get(0),fourthvalues.get(0)));
        nowcur2.add(getRateForRow(secondvalues.get(1),firstvalues.get(1)));
        nowcur2.add(getRateForRow(secondvalues.get(1),thirdvalues.get(1)));
        nowcur2.add(getRateForRow(secondvalues.get(1),fourthvalues.get(1)));

        precur3.add(getRateForRow(thirdvalues.get(0),firstvalues.get(0)));
        precur3.add(getRateForRow(thirdvalues.get(0),secondvalues.get(0)));
        precur3.add(getRateForRow(thirdvalues.get(0),fourthvalues.get(0)));
        nowcur3.add(getRateForRow(thirdvalues.get(1),firstvalues.get(1)));
        nowcur3.add(getRateForRow(thirdvalues.get(1),secondvalues.get(1)));
        nowcur3.add(getRateForRow(thirdvalues.get(1),fourthvalues.get(1)));

        precur4.add(getRateForRow(fourthvalues.get(0),firstvalues.get(0)));
        precur4.add(getRateForRow(fourthvalues.get(0),secondvalues.get(0)));
        precur4.add(getRateForRow(fourthvalues.get(0),thirdvalues.get(0)));
        nowcur4.add(getRateForRow(fourthvalues.get(1),firstvalues.get(1)));
        nowcur4.add(getRateForRow(fourthvalues.get(1),secondvalues.get(1)));
        nowcur4.add(getRateForRow(fourthvalues.get(1),thirdvalues.get(1)));


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
        tableView.setFixedCellSize(57);//cell size should be changed
        tableView.prefWidthProperty().set(400);
        curs.prefWidthProperty().set(80);
        currency1.prefWidthProperty().set(82);
        currency2.prefWidthProperty().set(82);
        currency3.prefWidthProperty().set(82);
        currency4.prefWidthProperty().set(82);



        layout.getChildren().addAll(vbox);

        convertLayout = layout;

    }

    private static String getRateaAndSymbol(double pre,double now) {
        String return_string = ((String) Double.toString(now));
        return_string = String.format("%.2f",now);
        if(pre >now){
            return_string =  return_string + "↑";
        }else if(pre<now){
            return_string =  return_string + "↓";
        }else{
            return_string =  return_string+ "-";
        }
        return return_string;
    }
    private static List<Double> getTwoRecent(List<String> two){
        List<Double> return_value = null;
        if(two != null){
            return_value = new ArrayList<Double>();
            String first = two.get(0);
            String second = two.get(1);

            String[] string_first = first.split(",");
            String[] string_second = second.split(",");
            return_value.add(Double.parseDouble(string_first[1]));
            return_value.add(Double.parseDouble(string_second[1]));

        }


        return  return_value;

    }
    private static double getRateForRow(double row,double column){
        return (1/row)*column;

    }



}