package CurrencyExchange;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
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
            String[] s=currency1_result1.get(i).split(",");
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
        ArrayList<Double> curr1_curr2=new ArrayList<>();

        ArrayList<Double> values_of_Currency1=getValuesindaterange(currency1_result1,date_start, date_end);
        ArrayList<Double> values_of_Currency2=getValuesindaterange(currency2_result1,date_start, date_end);
        for(int n=0; n<values_of_Currency1.size(); n++){
            double rate=values_of_Currency1.get(n)/values_of_Currency1.get(n);
            curr1_curr2.add(rate);
        }
        double average_currency1= average(curr1_curr2);
       // double average_currency2= average(values_of_Currency2);
        double median_currency1=median(curr1_curr2);
       // double median_currency2=median(values_of_Currency2);
        double max_currency1=max_val(curr1_curr2);
       // double max_currency2=max_val(values_of_Currency2);
        double min_currency1=min(curr1_curr2);
       // double min_currency2=min(values_of_Currency2);
        double ssd_currency1=standarddeviation(curr1_curr2);
      //  double ssd_currency2=standarddeviation(values_of_Currency2);
        String summary1= (currency1 +" to "+ currency2 + "average rate:"+ average_currency1+ " , median:"+ median_currency1+ " , maximum:"+max_currency1+ ", minimum:"+min_currency1+ ", standard deviation:"+ ssd_currency1);
      //  String summary2=(currency2 + "average:"+ average_currency2+" , median:"+ median_currency2+ ", maximum:"+max_currency2+", minimum:"+min_currency2+ ", standard deviation:"+ssd_currency2);
        //String summary=summary1+ summary2;
        //new String summary= String();

        //return (currency1 + "average:"+ average_currency1+ " , median:"+ median_currency1+ " , maximum:"+max_currency1+ ", minimum:"+min_currency1+ ", standard deviation:"+ ssd_currency1);
        if(isValid){
            return summary1;
        }
        else if(currency1_exists==false || currency2_exists==false){
            return ("Currency doesnt exist");
        }
        else{
            return ("Proper Date not entered");
        }



    }


    public static double max_val(ArrayList<Double> values)  {
        double max_num=0.0;
        for(int i=0; i<values.size(); i++){
            if(max_num< values.get(i)){
                max_num=values.get(i);
            }

        }
        return max_num;
    }

    public static double min(ArrayList<Double> values){
        double min_num=1000000.0;
        for(int i=0; i<values.size(); i++){
            if(min_num>values.get(i)){
                min_num=values.get(i);
            }

        }
        return min_num;
    }

    public static double average(ArrayList<Double> values){
        int n=values.size();
        int total=0;
        for(int i=0; i< values.size(); i++){
            total+=values.get(i);
        }
        return total/n;
    }

    public static double standarddeviation(ArrayList<Double> values){
        double mean=average(values);
        double num=0.0;
        for(int i=0; i<values.size(); i++){
            double avg_diff=Math.pow(values.get(i)-mean, 2);
            num+=avg_diff;
        }
        double var=num/values.size();
        double ssd=Math.round((Math.sqrt(var)));
        return ssd;
    }

    public static double median(ArrayList<Double> values) {
        Collections.sort(values);
        int list_size = values.size();
        double size_divide = list_size / 2;
        if (list_size % 2 == 0) {
            int sze=(int)size_divide;
            double val = (values.get(sze-1) + values.get(sze)) / 2;
            return val;
        } else {
            int sze=(int)(size_divide-0.5);
            double val = (values.get(sze));
            return val;
        }
    }
    //method to get list of rates in range of the given time
    public static ArrayList<Double> getValuesindaterange(ArrayList<String> currencyinstances, LocalDateTime date_start, LocalDateTime date_end ){
        ArrayList<Double> result_of_dateRange = new ArrayList<>();
        DateTimeFormatter format= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:s");
        for(int y=0; y<currencyinstances.size(); y++){
            String currency_instance= currencyinstances.get(y);
            String[] currency_split= currency_instance.split(",");
            LocalDateTime duration=LocalDateTime.parse(currency_split[2],format);
            if(duration.equals(date_start) || (duration.isAfter(date_start) && duration.isBefore(date_end) || duration.isEqual(date_end))){
                result_of_dateRange.add(Double.parseDouble(currency_split[1]));
            }

        }
        return result_of_dateRange;
    }


}

