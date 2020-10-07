package CurrencyExchange;
import java.awt.List;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class FileHandler {

    final static String file = "src" + File.separator + "main" + File.separator + "java" + File.separator + "CurrencyExchange" + File.separator +"currencies.txt";

    /*
    Returns an empty arraylist if the currency is not in the file. Otherwise returns an arraylist of csv's of all instances of the currency
     */
     public static ArrayList<String> find(String currency) {
        ArrayList<String> result = new ArrayList<>();
        if(currency != null && !currency.isEmpty()) {
            String line;
            Scanner currencies = null;

            try{
                currencies = new Scanner(new FileInputStream(file));
            }
            catch(FileNotFoundException e) {
                System.out.println("currencies.txt not found or could not be opened.");
                System.exit(1);
            }

            while(currencies.hasNextLine()) {
                line = currencies.nextLine();
                if(line.contains(currency.toUpperCase())) {
                    result.add(line);
                }
            }
            currencies.close();
        }
        return result;
    }

    /*
    Stores the currency name, its value and the date it was stored as a csv in the file currencies.txt
     */
    public static void add(String currency, float value) {
        boolean valid = true;

        if(value <= 0) {
            System.out.println("value must be greater than 0");
            valid = false;
        }

        if(currency == null || currency.isEmpty()) {
            System.out.println("Enter valid values for currency");
            valid = false;
        }

        if(valid) {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new FileOutputStream(file, true));
            }
            catch(FileNotFoundException e) {
                System.out.println("error opening currencies.txt");
                System.exit(1);
            }

            writer.print(System.lineSeparator() + String.join(",", currency.toUpperCase(), Float.toString(value), LocalDateTime.now().toString()));
            writer.close();
        }
    }
    
    // update a currencies price that is currently stored
    public static void update(String currency) {

    }
    
    // remove a currency from the database
    public static void remove(String currency) {
        try {
            ArrayList<String> database = new ArrayList<String>();
            Scanner reader = new Scanner(new File(file));
            boolean found = false;

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (!line.contains(currency)) {
                    database.add(line);
                } else {
                    found = true;
                }
            }
            reader.close();
            if (found == false) {
                System.out.println("Currency could not be removed as it does not currently exist in the databse");
            } else {
                PrintWriter writer = new PrintWriter(new File(file));
                int count = 0;
                while (count < database.size()) {
                    writer.println(database.get(count));
                    count++;
                }
                writer.close();
            }

        } catch(FileNotFoundException e) {
            System.out.println("error opening currencies.txt");
            System.exit(1);
        }
    }
}

