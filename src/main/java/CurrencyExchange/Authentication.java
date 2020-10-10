package CurrencyExchange;

import java.io.*;
import java.util.Scanner;

import CurrencyExchange.UIComponents.Header; //UIComponents. ...


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

public class Authentication {
    
    final static String file = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "authentification.txt";
    static Region loginLayout;
    static boolean adminAccess = false; 
    
    public static Region getLayout() {
        if (loginLayout == null) {
            initScene();
        }
        return loginLayout;
    }

    public static boolean isAdmin() {
        return adminAccess;
    }

    private static void initScene() {
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);

        Label adminLabel = new Label("Administration Login");
        adminLabel.setStyle("-fx-font-size: 1.50em; -fx-text-fill: #5F634F; -fx-font-weight: bold");
        GridPane.setConstraints(adminLabel, 0, 0);

        Label uName = new Label("Username:");
        uName.setStyle("-fx-font-size: 1.25em; -fx-text-fill: #5F634F");
        GridPane.setConstraints(uName, 0, 1);
        Label pwd = new Label("Password:");
        pwd.setStyle("-fx-font-size: 1.25em; -fx-text-fill: #5F634F");
        GridPane.setConstraints(pwd, 0, 2);

        TextField enterUser = new TextField();
        enterUser.setPromptText("Enter Username");
        GridPane.setConstraints(enterUser, 1, 1);
        
        TextField enterPwd = new TextField();
        enterPwd.setPromptText("Enter Password"); 
        GridPane.setConstraints(enterPwd, 1, 2);
 

        Button loginButton = new Button("Login");
        Label message = new Label("");
        GridPane.setConstraints(message, 1, 5);
        loginButton.setOnAction(event -> {
            if (checkCredentials(enterUser.getText(), enterPwd.getText())) {
                adminAccess = true;
                Header.grantAdminAccess();
                message.setText("Login Successful!");
            } else {
                message.setText("Please Try Again.");
            }
            enterUser.setText("");
            enterPwd.setText(""); 
            
        });
        GridPane.setConstraints(loginButton, 0, 5);
        GridPane.setHalignment(loginButton, HPos.RIGHT);


        layout.getChildren().addAll(adminLabel, uName, pwd, loginButton, enterUser, enterPwd, message);
        loginLayout = layout;
    }

    private static boolean checkCredentials(String username, String password) {
        try {
            Scanner reader = new Scanner(new File(file));
            String[] user = null;
            String[] pwd = null;
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.contains("username")) {
                    user = line.split(":");
                } else if (line.contains("password")) {
                    pwd = line.split(":");
                }
            }
            reader.close();

            if (user[1].equals(username) && pwd[1].equals(password)) {
                return true;
            }

        } catch (FileNotFoundException e) {
            System.out.println("Authentification file not found");
            System.exit(1);
        }
        return false;
    }

}
