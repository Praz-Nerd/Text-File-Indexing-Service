package ro.praz.classes;

import javax.naming.directory.InvalidAttributesException;

//class representing an abstract command, only knows the type of the command
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
        setType(type);
    }

    //abstract method to be implemented for each type of command
    public abstract void execute();

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
