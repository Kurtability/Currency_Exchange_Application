package CurrencyExchange;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PopularCurrenciesTest {

   

    @Test
   public void TestgetgetRateForRow(){
        double aud =1.0;
        double usd = 0.72;
        double hkd = 5.61;
        double reslut = PopularCurrenciesScene.getRateForRow(usd,hkd);
        assertTrue(reslut == (1/0.72)*5.61);

    }

    @Test
    public void TestgetRateaAndSymbol(){
        //increasing
        double first1 = 1.00;
        double second1 = 1.02;
        String result1 = PopularCurrenciesScene.getRateaAndSymbol(first1,second1);
        assertEquals("1.02↑",result1);

        //decreasing
        double first2 = 5.11;
        double second2 = 5.10;
        String result2 = PopularCurrenciesScene.getRateaAndSymbol(first2,second2);
        assertEquals("5.10↓",result2);

        //increasing
        double first3 = 1.00;
        double second3 = 1.00;
        String result3 = PopularCurrenciesScene.getRateaAndSymbol(first3,second3);
        assertEquals("1.00-",result3);

        //floating
        double first4 = 1.00;
        double second4 = 1.0000;
        String result4 = PopularCurrenciesScene.getRateaAndSymbol(first4,second4);
        assertEquals("1.00-",result4);
        
    }
    @Test
    public void TestgetTwoRecent(){
        List<String> strings = Arrays.asList("USD,0.70,2020-09-05T11:00:00", "USD,0.71660,2020-10-05T11:00:00");
        System.out.println(strings);
        List<Double> result = PopularCurrenciesScene.getTwoRecent(strings);
        List<Double> expected = Arrays.asList(0.70,0.71660);
        assertEquals(expected,result);


    }




}
