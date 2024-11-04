package ro.praz.classes;

import ro.praz.utils.Storage;

import javax.naming.directory.InvalidAttributesException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class IndexCommand extends Command{
    private static String REGEX = "[\\p{Punct}\\s]";
    private List<Path> paths;

    public IndexCommand(String[] stringArr) throws InvalidAttributesException{
        super(stringArr[0]);
        paths = new ArrayList<>();
        //iterate through array, first string should be type of command the rest are paths
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
            throw new InvalidAttributesException("Invalid path: " + path);
        }
    }

    private void tokenize(Path filePath){
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filePath.toFile())))) {
            String line = br.readLine();
            //read file line by line
            while(line!=null){
                //instantiate tokenizer, with delimiters, punctuation marks
                //iterate through the words
                String[] words = line.split(REGEX);
                for(String word : words){
                    Map<Path, Integer> pathMap = null;
                    //if the word exists in the map, get the path map
                    if(Storage.wordMap.containsKey(word)){
                        pathMap = Storage.wordMap.get(word);
                        //update frequency, or add a new pair to the map
                        if(pathMap.containsKey(filePath)){
                            pathMap.put(filePath, pathMap.get(filePath)+1);
                        }
                        else {
                            pathMap.put(filePath, 1);
                        }
                    }
                    //add new key entry and instantiate path map for it
                    else{
                        pathMap = new HashMap<>();
                        pathMap.put(filePath, 1);
                        Storage.wordMap.put(word, pathMap);
                    }
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute() {
        //recreate hashmap
        Storage.wordMap = new HashMap<>();
        if(paths.size() != 0){
            for(Path p : paths){
                //recursively get all paths of all files in a folder, or just one if the path is a file
                try(Stream<Path> pathStream = Files.walk(p)) {
                    //get all absolute paths and check if a path is not a directory and is a .txt file
                    pathStream.filter(path -> !Files.isDirectory(path))
                            .filter(path -> path.getFileName().toString().endsWith("txt"))
                            .map(Path::toAbsolutePath)
                            //when the program gets here, the stream only contains absolute paths of .txt files
                            //call the tokenize method for each file
                            .forEach(this::tokenize);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("All text (.txt) files have been indexed");
        }
        else {
            System.out.println("No paths provided");
        }
    }
}
