package ro.praz.utils;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Storage {
    //hashmap that contains every word, the files it appears in and the number of appearances
    //the key is the word and the value is a path map where each key is a path and the values are frequencies of the word
    public static Map<String, Map<Path, Integer>> wordMap = new HashMap<>();
}
