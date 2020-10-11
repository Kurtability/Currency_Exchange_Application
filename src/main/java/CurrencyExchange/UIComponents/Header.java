package CurrencyExchange.UIComponents;

import CurrencyExchange.AdminScene;
import CurrencyExchange.ConvertScene;
import CurrencyExchange.ModifyPopularCurrenciesScene;
import CurrencyExchange.PopularCurrenciesScene;
import CurrencyExchange.Authentication;
import CurrencyExchange.updateCurrencies;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Header {

    public static VBox header;
    public static HBox adminButtons;

    public static Region getHeader() {
        if (header == null) {
            initHeader();
        }

        return header;
    }

    private static void initHeader() {        
        Button close = new Button("X");
        close.setAccessibleHelp("Close the app.");
        close.setOnAction(e -> {
            ((Stage) close.getScene().getWindow()).close();
        });
        close.setStyle("-fx-background-color: #EA949A; -fx-font-size: 1.5em; -fx-border-width: 0;");

        Label label = new Label("Currency Converter");
        label.setStyle("-fx-font-size: 3em; -fx-background-color: #8AE4EB; -fx-background-radius: 10px; -fx-padding: 2em;  -fx-text-fill: #5F634F");

        HBox normalButtons = new HBox(20);
        adminButtons = new HBox(20);
        //HBox topfourButtons = new HBox(20);

        Button btn1 = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        Button btn4 = new Button();
        Button login = new Button();
        Button update = new Button();

        btn1.setText("Convert");
        btn1.setOnAction((event) -> {
            ((BorderPane) header.getParent()).getScene().setRoot(new BorderPane(ConvertScene.getLayout(), header, null, null, null));
            ((BorderPane) header.getParent()).setStyle("-fx-background-color: #99C24D;");
        });

        btn2.setText("Add Currency");
        btn2.setOnAction((event -> {
           System.out.println("Add Currency page");
           ((BorderPane) header.getParent()).getScene().setRoot(new BorderPane(AdminScene.getLayout(), header, null, null, null));
           ((BorderPane) header.getParent()).setStyle("-fx-background-color: #99C24D;");

        }));

        btn3.setText("Popular Currencies");
        btn3.setOnAction((event -> {
            ((BorderPane) header.getParent()).getScene().setRoot(new BorderPane(PopularCurrenciesScene.getLayout(), header, null, null, null));
            ((BorderPane) header.getParent()).setStyle("-fx-background-color: #99C24D;");

        }));
        btn4.setText(("Modify Popular Currencies"));
        btn4.setOnAction((event -> {
            System.out.println("Modify Popular Currencies page");
            ((BorderPane) header.getParent()).getScene().setRoot(new BorderPane(ModifyPopularCurrenciesScene.getLayout(), header, null, null, null));
            ((BorderPane) header.getParent()).setStyle("-fx-background-color: #99C24D;");

        }));


        login.setText("Admin Login");
        login.setOnAction((event -> {
            ((BorderPane) header.getParent()).getScene().setRoot(new BorderPane(Authentication.getLayout(), header, null, null, null));
            ((BorderPane) header.getParent()).setStyle("-fx-background-color: #99C24D;");
        }));

        update.setText("Update Currency");
        update.setOnAction((event -> {
            ((BorderPane) header.getParent()).getScene().setRoot(new BorderPane(updateCurrencies.getLayout(), header, null, null, null));
            ((BorderPane) header.getParent()).setStyle("-fx-background-color: #99C24D;");
        }));

        normalButtons.getChildren().addAll(btn1,btn3, login);
        normalButtons.setStyle("-fx-padding: 10px 0 10px 10px");
        adminButtons.getChildren().addAll(btn2,btn4, update);
        adminButtons.setStyle("-fx-padding: 0 0 0 10px");

        header = new VBox(label, normalButtons);

        header.setStyle("-fx-background-color: #8AE4EB; -fx-padding: 12px; -fx-background-radius:0 0 25px 25px;");
        header.setAlignment(Pos.TOP_CENTER);
        
    }

    public static void grantAdminAccess() {
        header.getChildren().add(adminButtons);
    }
    
}