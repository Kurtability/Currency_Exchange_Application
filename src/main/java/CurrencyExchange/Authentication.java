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

import java.io.*;
import java.util.Scanner;

public class Authentication {
    final static String file = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "authentification.txt";
    static Region loginLayout;
    
    public static Region getLayout() {
        if (loginLayout == null) {
            initScene();
        }
        return loginLayout;
    }

    private static void initScene() {
        Gridpane layout = new Gridpane();
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(20);
        layout.setHgap(15);

        Label adminLabel = new Label("Administration Login");
        Gridpane.setContstraints(adminLabel, 0, 0);

        Label uName = new Label("Username:");
        TextField EnterUser = new TextField("Enter Usernamme");
        Gridpane.setContstraints(uName, 2, 0);
        Gridpane.setContstraints(EnterUser, 3, 0);

        Label pwd = new Label("Password:");
        TextField EnterPwd = new TextField("Enter Password");
        Gridpane.setContstraints(pwd, 2, 1);
        Gridpane.setContstraints(EnterPwd, 3, 1);

        Button loginButton = new Button("Login");
        /*loginButton.setOnAction(event -> {

        });*/
        GridPane.setConstraints(loginButton, 2, 3);
        GridPane.setHalignment(loginButton, HPos.RIGHT);


        layout.getChildren().addAll();
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
