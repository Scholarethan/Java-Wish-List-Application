//Referenced from TellerApp example

package persistence;

import model.Item;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Reader class that reads wish list data from a file
public class Reader {
    public static final String DELIMITER = ",";

    //EFFECTS: returns a list of items parsed from file; throws IO exception
    // if an exception is raised when opening/reading from file
    public static List<Item> readWishList(File file) throws IOException {
        List<String> wishListContent = readFile(file);
        return parseContent(wishListContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of item parsed from list of strings
    // where each string contains data for one item
    private static List<Item> parseContent(List<String> fileContent) {
        List<Item> item = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            item.add(parseItem(lineComponents));
        }

        return item;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 5 where element 0 represents
    // the item name, element 1 represents the category, element 2 represents the cost,
    // element 3 represents the purchased status and element 4 represents the archived status
    // EFFECTS: returns an item constructed from components
    private static Item parseItem(List<String> components) {
        String name = components.get(0);
        String category = components.get(1);
        double cost = Double.parseDouble(components.get(2));
        boolean purchased = Boolean.parseBoolean(components.get(3));
        boolean archived = Boolean.parseBoolean(components.get(4));
        return new Item(name, category, cost, purchased, archived);
    }

}
