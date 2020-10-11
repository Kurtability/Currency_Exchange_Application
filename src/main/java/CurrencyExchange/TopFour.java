package CurrencyExchange;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TopFour {
    final static String file = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "topfour.txt";

    /*
    Ensures the topfour.txt file has no violations.
     */
    public static boolean validate() {
        boolean valid = true;
        ArrayList<String> top = new ArrayList<>();
        Scanner reader = null;
        String line;

        try {
            reader = new Scanner(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("Problems opening topfour.txt");
            System.exit(1);
        }

        while(reader.hasNextLine()) {
            line = reader.nextLine();
            if(!top.contains(line)) {   // only add unique entries
                top.add(line);
            }
        }
        reader.close();

        // make sure there are 4 unique entries
        if(top.size() != 4) {
            System.out.println("topfour.txt should have 4 unique entries");
            valid = false;
        }

        // All entries in topfour.txt should be entries in currencies.txt
        ArrayList<String> currencies = FileHandler.getAllCurrencies();
        for(String s : top) {
            if(!currencies.contains(s)) {
                System.out.println("There is an inconsistency in the files: " + s + " is not in currencies.txt");
                valid = false;
            }
        }
        return valid;
    }

    /*
    Get the names of the top four currencies and return it as an arraylist
     */
    public static ArrayList<String> getTopFour() {
        ArrayList<String> result = new ArrayList<>();
        Scanner reader = null;
        try {
            reader = new Scanner(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("Problem opening topfour.txt");
            System.exit(1);
        }
        while(reader.hasNextLine()) {
            result.add(reader.nextLine());
        }
        reader.close();
        return result;
    }

    /*
    Returns an arraylist of arraylists. The members of the inner list contain the two most recent committed values of
    each top four currency.
     */
    public static ArrayList<ArrayList<String>> getValues() {
        ArrayList<String> currencies = getTopFour();
        ArrayList<ArrayList<String>> result = new ArrayList<>();

        for(int i=0; i<currencies.size(); i++) {
            result.add(FileHandler.get(currencies.get(i)));
        }

        for(int i=0; i<result.size(); i++) {
            while(result.get(i).size() > 2) {
                result.get(i).remove(0);
            }
        }
        return result;
    }

    public static void add(String currency1, String currency2, String currency3, String currency4) {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(file, false));

        } catch (FileNotFoundException e) {
            System.out.println("Problems opening topfour.txt");
            System.exit(1);
        }
        writer.print(currency1.toUpperCase()+"\n"+ currency2.toUpperCase()+"\n"+ currency3.toUpperCase()+"\n"+ currency4.toUpperCase());
        writer.close();
    }

    public static void remove(String currency) {
        ArrayList<String> temp = new ArrayList<>();
        currency = currency.toUpperCase();
        String  line;

        Scanner reader = null;

        try {
            reader = new Scanner(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("Problems opening the file topfour.txt");
            System.exit(1);
        }

        if(reader.hasNextLine()) {
            line = reader.nextLine();
            if(!line.equals(currency)) {
                temp.add(line);
            }
        }
        while(reader.hasNextLine()) {
            line = reader.nextLine();
            if(!line.equals(currency)) {
                temp.add(System.lineSeparator() + line);
            }
        }
        reader.close();

        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("Problems opening topfour.txt");
            System.exit(1);
        }

        for(String s : temp) {
            writer.print(s);
        }
        writer.close();
    }
}
