package ro.praz.main;

import ro.praz.classes.Command;
import ro.praz.classes.IndexCommand;
import ro.praz.classes.SearchCommand;
import ro.praz.utils.Storage;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner cin = new Scanner(System.in);

    private static void welcomeText(){
        System.out.println("Text File Indexing Service\n");
        System.out.println("Command syntaxes: ");
        System.out.println("index followed by paths separated by space");
        System.out.println("search followed by word to be searched\n");
    }

    public static void main(String[] args) {
        welcomeText();
        System.out.print("Command (index/search/quit): ");
        String input = cin.nextLine();
        //reads one line at a time until the line contains a 0
        while(!input.equals("quit")){
            Command command = null;
            try {
                //command is either an index command or a search command
                command = Command.getCommand(input);
                //commands.add(command);
            } catch (InvalidAttributesException e) {
                e.printStackTrace();
            }
            //System.out.println(command);
            //execute the command, different behaviour depending on the command type
            if(command!=null)
                command.execute();

            System.out.println("Number of tokens: "+Storage.wordMap.size());
            System.out.print("Command (index/search/quit): ");
            input = cin.nextLine();
        }
        cin.close();
    }
}