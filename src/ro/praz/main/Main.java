package ro.praz.main;

import ro.praz.classes.Command;
import ro.praz.classes.IndexCommand;
import ro.praz.classes.SearchCommand;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner cin = new Scanner(System.in);

    private static List<Command> commands = new ArrayList<>();

    public static void main(String[] args) {
        String input = cin.nextLine();
        //reads one line at a time until the line contains a 0
        while(!input.equals("0")){
            Command command = null;
            try {
                //command is either an index command or a search command
                command = Command.getCommand(input);
                commands.add(command);
            } catch (InvalidAttributesException e) {
                e.printStackTrace();
            }
            System.out.println(command);
            input = cin.nextLine();
        }

        cin.close();
    }
}