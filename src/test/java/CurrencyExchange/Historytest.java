package CurrencyExchange;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;

public class Historytest {
    @Test
    void max_val_test(){
        ArrayList<Double> maxi= new ArrayList<>();
        maxi.add(1.0);
        maxi.add(2.0);
        maxi.add(10.0);
        maxi.add(8.0);
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
        assertEquals(History.average(avg_test),5.0 );
    }

    @Test
    void median_test_even(){
        ArrayList<Double> med_test_even= new ArrayList<>();
        med_test_even.add(1.00);
        med_test_even.add(2.00);
        med_test_even.add(10.00);
        med_test_even.add(8.00);
        assertEquals(History.median(med_test_even),5.0 );
    }
    @Test
    void median_test_odd(){
        ArrayList<Double> med_test_odd= new ArrayList<>();
        med_test_odd.add(1.0);
        med_test_odd.add(2.0);
        med_test_odd.add(10.0);
        assertEquals(History.median(med_test_odd),2.0 );
    }



    @Test
    void ssd_test(){
        ArrayList<Double> ssd_test= new ArrayList<>();
        ssd_test.add(1.00);
        ssd_test.add(2.00);
        ssd_test.add(10.00);
        ssd_test.add(8.00);
        assertEquals(History.standarddeviation(ssd_test),4.0 );
    }





}
