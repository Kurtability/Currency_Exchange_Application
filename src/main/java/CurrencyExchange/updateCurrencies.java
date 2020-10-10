package CurrencyExchange;

import java.io.*;
import java.util.Scanner;

import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

public class updateCurrencies {

    static Region updateLayout;
    
    public static Region getLayout() {
        initScene();
        return updateLayout;
    }
    // when a currency is updated, do all currency rates have to be changed or only one
    private static void initScene() {
        Label title = new Label("Select a Currency to Update");
    }

    // error: must update all currencies
}
