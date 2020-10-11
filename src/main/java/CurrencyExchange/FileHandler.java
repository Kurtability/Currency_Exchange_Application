package CurrencyExchange;
import java.io.*;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class FileHandler {

    final static String file = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "currencies.txt";

    /*
    Returns an empty arraylist if the currency is not in the file. Otherwise returns an arraylist of sorted csv's of all instances of the currency.
    Records are sorted by date in ascending order. Format of the csv: <currency name>,<currency value>,<date and time>
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

        Collections.sort(result, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String[] a1 = s1.split(",");
                String[] a2 = s2.split(",");
                LocalDateTime t1 = LocalDateTime.parse(a1[2]);
                LocalDateTime t2 = LocalDateTime.parse(a2[2]);
                return t1.compareTo(t2);
            }
        });
        return result;
    }



    /*
    Stores the currency name, its value and the specified date as a csv in the file currencies.txt
    Format <currency name>,<currency value>,<date>
     */
    public static void add(String currency, double value, LocalDateTime dateTime) {
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

            writer.print(System.lineSeparator() + String.join(",", currency.toUpperCase(), Double.toString(value), dateTime.truncatedTo(ChronoUnit.SECONDS).toString()));
            writer.close();
        }
    }

    // add a currency name and value with the current date.
    public static void add(String currency, double value) {
        add(currency, value, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
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
        reader.close();
        return allCurrencies;
    }

    // update a currencies price that is currently stored
    public static void update(String currency) {

    }
    
    // remove a currency from the database
    public static void remove(String currency) {
        if(currency != null && !currency.isEmpty()) {
            try {
                ArrayList<String> database = new ArrayList<String>();
                Scanner reader = new Scanner(new File(file));
                boolean found = false;

                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    if (!line.split(",")[0].contains(currency)) {
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
                    if (count < database.size()) {
                        writer.print(database.get(count));
                        count++;
                    }
                    while (count < database.size()) {
                        writer.print(System.lineSeparator() + database.get(count));
                        count++;
                    }
                    writer.close();
                }

            } catch (FileNotFoundException e) {
                System.out.println("error opening currencies.txt");
                System.exit(1);
            }
        }
    }
}

