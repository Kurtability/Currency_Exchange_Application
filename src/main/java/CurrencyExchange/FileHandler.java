package CurrencyExchange;
import java.awt.List;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class FileHandler {
    /*
    If a currency is already in the file currencies.txt, it will return its name and value as a csv. Otherwise it will return null.
     */
     public static String find(String currency) {
        String result = null;
        if(currency != null && !currency.isEmpty()) {
            String line;
            boolean found = false;
            Scanner currencies = null;

            try{
                currencies = new Scanner(new FileInputStream("currencies.txt"));
            }
            catch(FileNotFoundException e) {
                System.out.println("currencies.txt not found or could not be opened.");
                System.exit(1);
            }

            while(currencies.hasNextLine() && !found) {
                line = currencies.nextLine();
                if(line.contains(currency)) {
                    found = true;
                    result = line;
                }
            }
            currencies.close();
        }

        return result;
    }

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

        if(FileHandler.find(currency) != null) {
            System.out.println("currency already loaded in currencies.txt");
            valid = false;
        }

        if(valid) {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new FileOutputStream("currencies.txt", true));
            }
            catch(FileNotFoundException e) {
                System.out.println("error opening currencies.txt");
                System.exit(1);
            }

            writer.print(System.lineSeparator() + String.join(",", currency, Float.toString(value)));
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
            Scanner reader = new Scanner(new File("currencies.txt"));
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
                PrintWriter writer = new PrintWriter(new File("currencies.txt"));
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

