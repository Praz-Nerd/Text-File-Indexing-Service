package ro.praz.classes;

import javax.naming.directory.InvalidAttributesException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//class representing a command
/*
the constructor takes a string read in the console and breaks it down
into a command type and a list of paths to directories and files
 */
public abstract class Command {
    protected String type;

    public String getType() {
        return type;
    }

    public void setType(String type) throws InvalidAttributesException {
        //type must be either search or index, else throws an exception
        type = type.toLowerCase();
        if(type.equals("search") || type.equals("index")){
            this.type = type;
        }
        else {
            throw new InvalidAttributesException("Invalid type");
        }
    }

    protected Command(String type) throws InvalidAttributesException{
        this.type = type;
    }

    //abstract method to be implemented for each type of command
    //abstract void execute();

    public static Command getCommand(String input) throws InvalidAttributesException{
        //splits input by spaces
        String[] inputArr = input.split("[ \\t]+");
        //first element is the type
        inputArr[0] = inputArr[0].toLowerCase();
        Command command = null;
        //depending on the type, instantiate different command
        if(inputArr[0].equals("index")){
            command = new IndexCommand(inputArr);
        }
        else if(inputArr[0].equals("search")){
            command = new SearchCommand(inputArr);
        }
        return command;
    }


}
