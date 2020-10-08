package CurrencyExchange;

import CurrencyExchange.CurrencyContainer;
import CurrencyExchange.UIComponents.Header;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

public class PopularCurrenciesScene {
    static Scene convertScene;
    private static List<String> cursName;

    private static TableView<CurrencyContainer> tableView = new TableView<CurrencyContainer>();
    private static ObservableList<CurrencyContainer> currencyContainers;

    static Scene getScene() {
        if (convertScene == null) {
            initScene();
        }

        return convertScene;
    }

    private static void initScene() {

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(20);
        layout.setHgap(15);




        //popular currencies bodies
        //store information
        cursName = Arrays.asList(
                "AUD","USD","HKD","SDee"
        );
        currencyContainers = FXCollections.observableArrayList(
                new CurrencyContainer(cursName.get(0),"-","2.2","3.3","4.4"),
                new CurrencyContainer(cursName.get(1),"1.01","-","3.03","4.04"),
                new CurrencyContainer(cursName.get(2),"1.011","2.022","-","4.044"),
                new CurrencyContainer(cursName.get(3),"1.01","2.02","3.03","-")
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


        curs.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
        currency1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
        currency2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
        currency3.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
        currency4.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));


        tableView.setFixedCellSize(50);//cell size should be changed
        tableView.prefWidthProperty().bind(layout.widthProperty().multiply(0.8));
        tableView.prefHeightProperty().bind(layout.heightProperty().multiply(0.6));

        tableView.setItems(currencyContainers);
        tableView.getColumns().addAll(curs, currency1, currency2,currency3,currency4);
        final VBox vbox = new VBox();
        vbox.getChildren().addAll( tableView);


        BorderPane root = new BorderPane();
        root.setTop(Header.getHeader());
        root.setCenter(vbox);
        root.setStyle("-fx-background-color: #99C24D; -fx-background-radius: 0 0 20 20");
        convertScene = new Scene(root, 640, 480);
        convertScene.setFill(Color.TRANSPARENT);
    }
}
