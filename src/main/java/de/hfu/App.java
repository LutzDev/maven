package de.hfu;
import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println( "Deine eingabe" );
        String input = scanner.next();
        String uppercase = input.toUpperCase();
        System.out.println(uppercase);
    }
}
