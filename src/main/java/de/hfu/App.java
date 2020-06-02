package de.hfu;
import java.util.Scanner;

/**
* App Klasse
* Eingaben werden als Großbuchstaben wieder ausgegeben
* Aufgabe: Praktikum 05 - Maven
*/

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
