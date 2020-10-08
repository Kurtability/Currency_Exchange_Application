package CurrencyExchange;
import java.awt.List;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class FileHandler {

    final static String file = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "currencies.txt";

    /*
    Returns an empty arraylist if the currency is not in the file. Otherwise returns an arraylist of csv's of all instances of the currency.
    Format of the csv: <currency name>,<currency value>,<date and time>
     */
     public static ArrayList<String> get(String currency) {
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
    Stores the currency name, its value and the specified date as a csv in the file currencies.txt
    Format <currency name>,<currency value>,<date>
     */
    public static void add(String currency, float value, LocalDateTime dateTime) {
        boolean valid = true;

        if(value <= 0) {
            System.out.println("value must be greater than 0");
            valid = false;
        }

        if(currency == null || currency.isEmpty()) {
            System.out.println("Enter valid values for currency");
            valid = false;
        }

        if(dateTime.isAfter(LocalDateTime.now())) {
            System.out.println("Please don't enter future dates");
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

            writer.print(System.lineSeparator() + String.join(",", currency.toUpperCase(), Float.toString(value), dateTime.toString()));
            writer.close();
        }
    }

    // add a currency name and value with the current date.
    public static void add(String currency, float value) {
        add(currency, value, LocalDateTime.now());
    }

    // returns an arrayList of all currency names
    public static ArrayList<String> getAllCurrencies() {
        ArrayList<String> allCurrencies = new ArrayList<>();
        String[] line;
        Scanner reader = null;

        try {
            reader = new Scanner(new FileInputStream(file));
        }
        catch(FileNotFoundException e) {
            System.out.println("Problem opening currencies.txt");
            System.exit(1);
        }

        while(reader.hasNextLine()) {
            line = reader.nextLine().split(",");
            if(!allCurrencies.contains(line[0])) {
                allCurrencies.add(line[0]);
            }
        }
        System.out.println(allCurrencies.toString());
        return allCurrencies;
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
                if(count < database.size()) {
                    writer.print(database.get(count));
                    count++;
                }
                while (count < database.size()) {
                    writer.print(System.lineSeparator() + database.get(count));
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

