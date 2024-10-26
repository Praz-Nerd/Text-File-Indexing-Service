package ro.praz.classes;

import javax.naming.directory.InvalidAttributesException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IndexCommand extends Command{
    private List<Path> paths;

    public IndexCommand(String[] stringArr) throws InvalidAttributesException{
        super(stringArr[0]);
        paths = new ArrayList<>();
        //iterate through array, first string should be type of command the rest are paths
        //setType(inputArr[0]);
        for(int i = 1; i < stringArr.length; i++){
            //get absolute path
            addPath(Paths.get(stringArr[i]).toAbsolutePath());
        }
    }

    private void addPath(Path path) throws InvalidAttributesException {
        //every path provided must exist, else throws an exception
        if(Files.exists(path)){
            this.paths.add(path);
        }
        else {
            throw new InvalidAttributesException("Invalid path: " + path.toString());
        }
    }
}
