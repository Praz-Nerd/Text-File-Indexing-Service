package ro.praz.classes;

import ro.praz.utils.Storage;

import javax.naming.directory.InvalidAttributesException;
import java.nio.file.Path;
import java.util.Map;

public class SearchCommand extends Command{
    private String searchWord;

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public SearchCommand(String[] stringArr) throws InvalidAttributesException {
        //expected only 2 strings, the name opf the command and the word to be searched
        super(stringArr[0]);
        this.searchWord = stringArr[1];
    }

    //search in the hashmap the word and print the list of files containing the word
    @Override
    public void execute() {
        if(Storage.wordMap.containsKey(searchWord)){
            System.out.println("Word is present in the following files:");
            //get the map attributed to the key which is the search word
            Map<Path, Integer> pathMap = Storage.wordMap.get(searchWord);
            //iterate through the map and print file paths and frequencies
            for(Path p : pathMap.keySet()){
                System.out.println("In " + p + " appears " + pathMap.get(p).toString() + " times");
            }
        }
        else{
            System.out.println("Word does not exist in indexed files");
        }
    }
}
