package Utile;

import java.util.Scanner;

//  citire din consola
public final class Input {
    private static final Scanner SC = new Scanner(System.in);
    private Input() {}

    public static String  citeste(String msg) {
        System.out.print(msg + " ");
        return SC.nextLine();
    }
    public static int citesteInt(String msg)  { return Integer.parseInt(citeste(msg)); }
    public static double citesteDouble(String msg) { return Double.parseDouble(citeste(msg)); }
}
