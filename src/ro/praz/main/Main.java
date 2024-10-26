package ro.praz.main;

import ro.praz.classes.Command;

import javax.naming.directory.InvalidAttributesException;
import java.util.Scanner;

public class Main {
    private static Scanner cin = new Scanner(System.in);
    public static void main(String[] args) {
        String input = cin.nextLine();
        while(!input.equals("0")){
            Command command = null;
            try {
                command = new Command(input);
            } catch (InvalidAttributesException e) {
                e.printStackTrace();
            }
            System.out.println(command);
            input = cin.nextLine();
        }

        cin.close();
    }
}