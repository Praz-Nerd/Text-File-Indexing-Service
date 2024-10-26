package ro.praz.classes;

import javax.naming.directory.InvalidAttributesException;

public class SearchCommand extends Command{
    private String searchWord;

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public SearchCommand(String[] stringArr) throws InvalidAttributesException {
        super(stringArr[0]);
        this.searchWord = stringArr[1];
    }
}
