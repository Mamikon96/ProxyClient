package main;

import main.models.Math;
import main.models.MathProxy;
import main.models.MathService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientMain {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter args:");
            System.out.print("First: ");
            float firstArg = Float.parseFloat( br.readLine() );
            System.out.print("Second: ");
            float secondArg = Float.parseFloat( br.readLine() );

            System.out.println("\nResult from client:");
            Math math = new MathService();
            System.out.printf("%.2f\n", math.multiply(firstArg, secondArg));

            System.out.println("\nResult from server:");
            math = new MathProxy();
            System.out.printf("%.2f\n", math.multiply(firstArg, secondArg));

//            String[] arr = new String[] {"12", "34", "56", "78", "90"};
//            System.out.println(String.join("&", arr));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        /*Math math = new MathService();
        System.out.println("3 * 4 = " + math.multiply(3, 4));

        math = new MathProxy();
        System.out.println("3 * 4 = " + math.multiply(3, 4));*/
    }
}
