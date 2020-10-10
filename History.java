package CurrencyExchange;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
public class History {
    final static String file_rates_dates = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "topfour.txt";


    public static String history_conversion(String currency1, String currency2, LocalDateTime date_start, LocalDateTime date_end){

        // 1)check if both currency exists
        boolean isValid=false;
        boolean currency1_exists=false;
        boolean currency2_exists=false;
        ArrayList<String> currency1_result1=FileHandler.get(currency1);
        ArrayList<String> currency2_result1=FileHandler.get(currency2);
        if(currency1_result1.size()>0 && currency1_result1.size()>0){
            currency1_exists=true;
            currency2_exists=true;
        }

        //check if time is ok
        LocalDateTime timeDate=null;
        DateTimeFormatter format= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:s");
        boolean start_time_exists= false;
        boolean end_time_exists= false;
        for(int i=0; i<currency1_result1.size(); i++){
            String[] s=currency1_result1[i].split(",");
            timeDate=LocalDateTime.parse(s[2], format);
            if(timeDate.equals(date_start)){
                start_time_exists=true;
            }
            if(timeDate.equals(date_start)){
                end_time_exists=true;
            }
        }
        if(end_time_exists==true && start_time_exists==true && currency1_exists==true && currency2_exists==true){
            isValid=true;
        }

        ArrayList<int> values_of_Currency1=getValuesindaterange(currency1_result1,date_start, date_end);
        ArrayList<int> values_of_Currency2=getValuesindaterange(currency2_result1,date_start, date_end);
        double average_currency1= average(values_of_Currency1);
        double average_currency2= average(values_of_Currency2);
        double median_currency1=median(values_of_Currency1);
        double median_currency2=median(values_of_Currency2);
        double max_currency1=max(values_of_Currency1);
        double max_currency2=max(values_of_Currency2);
        double min_currency1=min(values_of_Currency1);
        double min_currency2=min(values_of_Currency2);
        double ssd_currency1=standarddeviation(values_of_Currency1);
        double ssd_currency2=standarddeviation(values_of_Currency2);
        String summary1=" "+currency1 + "average:"+ average_currency1+", median:"+ median_currency1+ ", maximum:"+max_currency1+", minimum:"+min_currency1+, "standard deviation:"+ssd_currency1;
        String summary2=" "+currency2 + "average:"+ average_currency2+", median:"+ median_currency2+ ", maximum:"+max_currency2+", minimum:"+min_currency2+, "standard deviation:"+ssd_currency2;
        String summary=summary1+ summary2;
        return summary


        }


    }

    //method to get list of rates in range of the given time
    public static ArrayList<int> getValuesindaterange(ArrayList<String> currencyinstances, LocalDateTime date_start, LocalDateTime date_end ){
        ArrayList<int> result_of_dateRange = new ArrayList<int>();
        DateTimeFormatter format= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:s");
        for(int y=0; y<currencyinstances.size(); y++){
            String currency_instance= currencyinstances[y];
            String[] currency_split= currency_instance.split(",");
            LocalDateTime duration=LocalDateTime.parse(currency_split[2],format);
            if(duration.equals(date_start) || (duration.isAfter(date_start) && duration.isBefore(date_end) || duration.isEqual(date_end))){
                result_of_dateRange.add(Integer.parseInt(currency_split[1]));
            }

        }
        return result_of_dateRange;
    }

    public static double max(ArrayList<int> values)  {
        double max_num=0;
        for(int i=0; i<values.size(); i++){
            if(max_num< values[i]){
                max_num=values[i];
            }
            return max_num;
        }
    }

    public static double min(ArrayList<int> values){
        double min_num=1000000;
        for(int i=0; i<values.size(); i++){
            if(min_num>values[i]){
                min_num=values[i];
            }
            return min_num;
        }
    }

    public static double average(ArrayList<int> values){
        int n=values.size();
        int total=0;
        for(int i=0; i< values.size(); i++){
            total+=values[i];
        }
        return total/n;
    }

    public static double standarddeviation(ArrayList<int> values){
        int mean=average(values);
        double num=0;
        for(int i=0; i<values.size(); i++){
            num+=Math.pow(values[i]-mean);
        }
        double var=num/values.size();
        double ssd=Math.sqrt(var);
        return ssd;
    }

    public static int median(ArrayList<int> values) {
        Collections.sort(values);
        int list_size = values.size();
        double size_divide = list_size / 2;
        if (list_size % 2 == 0) {
            double val = (values[size_divide] + values[size_divide]) / 2;
            return val;
        } else {
            double val = (values[size_divide + 0.5]);
            return val;
        }
    }

}

