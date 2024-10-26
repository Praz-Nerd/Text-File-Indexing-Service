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
public class Command {
    private String type;
    private List<Path> paths;

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

    private void addPath(Path path) throws InvalidAttributesException{
        //every path provided must exist, else throws an exception
        if(Files.exists(path)){
            this.paths.add(path);
        }
        else {
            throw new InvalidAttributesException("Invalid path: " + path.toString());
        }
    }

    public Command(String input) throws InvalidAttributesException{
        paths = new ArrayList<>();
        //split the input string by spaces
        String[] inputArr = input.split("[ \\t]+");
        //iterate through array, first string should be type of command the rest are paths
        setType(inputArr[0]);
        for(int i = 1; i < inputArr.length; i++){
            addPath(Paths.get(inputArr[i]));
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Command{");
        sb.append("type=").append(type).append(", paths=");
        for(Path s : paths){
            sb.append(s.toString()).append(" ");
        }
        sb.append("}");
        return sb.toString();
    }
}
