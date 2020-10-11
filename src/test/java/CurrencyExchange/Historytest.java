package CurrencyExchange;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;

public class Historytest {
    @Test
    void max_val_test(){
        ArrayList<Double> maxi= new ArrayList<>();
        maxi.add(1.00);
        maxi.add(2.00);
        maxi.add(10.00);
        maxi.add(8.00);
        assertEquals(History.max_val(maxi),10.0 );
    }
    @Test
    void min_val_test(){
        ArrayList<Double> minn= new ArrayList<>();
        minn.add(1.00);
        minn.add(2.00);
        minn.add(10.00);
        minn.add(8.00);
        assertEquals(History.min(minn),1.0 );
    }
    @Test
    void avg_val_test(){
        ArrayList<Double> avg_test= new ArrayList<>();
        avg_test.add(1.00);
        avg_test.add(2.00);
        avg_test.add(10.00);
        avg_test.add(8.00);
        assertEquals(History.average(avg_test),21.0 );
    }

    @Test
    void median_test(){
        ArrayList<Double> med_test= new ArrayList<>();
        med_test.add(1.00);
        med_test.add(2.00);
        med_test.add(10.00);
        med_test.add(8.00);
        assertEquals(History.median(med_test),5 );
    }


    @Test
    void ssd_test(){
        ArrayList<Double> ssd_test= new ArrayList<>();
        ssd_test.add(1.00);
        ssd_test.add(2.00);
        ssd_test.add(10.00);
        ssd_test.add(8.00);
        assertEquals(History.standarddeviation(ssd_test),2.8 );
    }





}
