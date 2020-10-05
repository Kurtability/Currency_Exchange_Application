package CurrencyExchange;
import java.io.*;
import java.util.Scanner;

public class FileHandler {
    /*
    If a currency is already in the file currencies.txt, it will return its value. Otherwise it will return null.
     */
    public static String find(String currency) {
        String result = null;
        String line = null;
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
            if(line.contains(currency.toUpperCase())) {
                found = true;
                result = line;
            }
        }

        return result;
    }

    public static void add(String currency, float value) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream("currencies.txt", true));
        }
        catch(FileNotFoundException e) {
            System.out.println("error opening currencies.txt");
            System.exit(1);
        }

        writer.println(String.join(",", currency, Float.toString(value)));
        writer.close();
    }
    
    // remove a currency from the database
    public static void remove(String currency) {


    }
}

