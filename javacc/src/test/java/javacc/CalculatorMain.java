package javacc;


import com.javacc.calculator.Calculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * 四则运算；
 *
 *
 */
public class CalculatorMain {

    public void testCalc() throws Exception {
        boolean isBeak = false;
        BufferedReader reader;
        String expr ="";
        Calculator calculator ;
        while (!isBeak){
            System.out.println("please input four arithmetic expressions , input quit exit");
            reader = new BufferedReader(new InputStreamReader(System.in));
            expr = reader.readLine();
            if(!"quit".equals(expr)){
                calculator = new Calculator(expr);
                double res = calculator.calc();
                System.out.println(res);
            }else {
                isBeak =true;
            }

        }
    }

    public static void main(String[] args) {
        CalculatorMain calculatorTest = new CalculatorMain();
        try {
            calculatorTest.testCalc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}