package ui;

//This class is responsible for launching the Wish List app and other app behaviour

import model.Item;
import model.WishList;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static ui.GUI.*;

public class App {
    private static final String WISH_LIST_FILE = "./data/WishListFileForApp.txt";
    private static String wishListString;
    private static WishList myWishList = new WishList();
    static final Runnable happySound = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.default");
    static final Runnable sadSound = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.hand");

    //EFFECTS: displays the entire wish list
    protected static void printWishList() {
        wishListString = "";
        for (Item item : myWishList.getArrayList()) {
            wishListString += "1: " + item.getName() + " 2: " + item.getCategory() + " 3: $" + item.getCost()
                    + " 4: " + item.getPurchased() + " 5: " + item.getArchived() + "\n";
        }
        wishListString += spaceCreator;
        listTextBox.setText(wishListString);
    }

    //EFFECTS: displays only the specified Item
    protected static void printItem(Item userItem) {
        String searchWishListString = "";
        for (Item item : myWishList.getArrayList()) {
            if (item.equals(userItem)) {
                searchWishListString += "1: " + item.getName() + " 2: " + item.getCategory() + " 3: $" + item.getCost()
                        + " 4: " + item.getPurchased() + " 5: " + item.getArchived();
            }
        }
        if (searchWishListString.length() == 0) {
            happySound.run();
            JOptionPane.showMessageDialog(frame, "Item not in wish list!\n",
                    "Notice!", JOptionPane.INFORMATION_MESSAGE);
            searchWishListString = wishListString;
        }
        searchWishListString += spaceCreator;
        listTextBox.setText(searchWishListString);
    }

    //EFFECTS: Reads user input and creates a new Item accordingly
    protected static Item promptUserForItem() {
        double cost = 0.0;
        String itemName = JOptionPane.showInputDialog("What is the name of the item?: ");
        if (itemName == null) {
            return null;
        }
        String category = JOptionPane.showInputDialog("What is the category of the item?: ");
        if (category == null) {
            return null;
        }
        String costString = JOptionPane.showInputDialog("What is the cost in $ of the item?: ");
        if (costString == null) {
            return null;
        }
        try {
            cost = Double.parseDouble(costString);
        } catch (Exception e) {
            sadSound.run();
            JOptionPane.showMessageDialog(frame, "Error, did not enter a valid price!\n"
                    + "Please enter a valid Item.", "Error!", JOptionPane.ERROR_MESSAGE, sadPic);
            return promptUserForItem();
        }
        return new Item(itemName,category,cost);
    }

    //MODIFIES: this
    //EFFECTS: adds a specified item to the wish list if it is not already in the wish list,
    //otherwise, prints an error message.
    protected static void addItemToWishList(Item itemToAdd) {
        if (myWishList.itemInWishList((itemToAdd))) {
            System.out.println("Error: item already in wish list");
            sadSound.run();
            JOptionPane.showMessageDialog(frame, "Error, item already in wish list!\n"
                    + "Please enter a new item.", "Error!", JOptionPane.ERROR_MESSAGE, sadPic);
            itemToAdd = promptUserForItem();
            if (itemToAdd == null) {
                return;
            }
            addItemToWishList(itemToAdd);
        } else {
            myWishList.addItem(itemToAdd);
            happySound.run();
            JOptionPane.showMessageDialog(frame, "Item added successfully!\n"
                    + "Great job, you rock!", "Success!", JOptionPane.INFORMATION_MESSAGE, happyPic);
        }
    }

    //MODIFIES: this
    //EFFECTS: marks a specified item in the wish list as archived if it is in the wish list,
    //otherwise, prints an error message.
    protected static void markAsArchived(Item itemToArchive) {
        if (myWishList.itemInWishList(itemToArchive)) {
            myWishList.archiveItem(itemToArchive);
            happySound.run();
            JOptionPane.showMessageDialog(frame, "Item archived successfully!\n"
                    + "Great job, you rock!", "Success!", JOptionPane.INFORMATION_MESSAGE, happyPic);
        } else {
            System.out.println("Error: item not in wish list");
            sadSound.run();
            JOptionPane.showMessageDialog(frame, "Error, item not in wish list!\n"
                    + "Please enter a new item.", "Error!", JOptionPane.ERROR_MESSAGE, sadPic);
            itemToArchive = promptUserForItem();
            if (itemToArchive == null) {
                return;
            }
            markAsArchived(itemToArchive);
        }
    }

    //MODIFIES: this
    //EFFECTS: marks a specified item in the wish list as purchased if it is in the wish list,
    //otherwise, prints an error message.
    protected static void markAsPurchasedInWishList(Item purchasedItem) {
        if (myWishList.itemInWishList(purchasedItem)) {
            myWishList.markAsPurchased(purchasedItem);
            happySound.run();
            JOptionPane.showMessageDialog(frame, "Item marked as purchased successfully!\n"
                    + "Great job, you rock!", "Success!", JOptionPane.INFORMATION_MESSAGE, happyPic);
        } else {
            System.out.println("Error: item not in wish list");
            sadSound.run();
            JOptionPane.showMessageDialog(frame, "Error, item not in wish list!\n"
                    + "Please enter a new item.", "Error!", JOptionPane.ERROR_MESSAGE, sadPic);
            purchasedItem = promptUserForItem();
            if (purchasedItem == null) {
                return;
            }
            markAsPurchasedInWishList(purchasedItem);
        }
    }

    //MODIFIES: this
    //EFFECTS: removes a specified item to the wish list if it is in the wish list,
    //otherwise, prints an error message.
    protected static void removeItemFromWishList(Item itemToRemove) { //In the future, I want to add more functionality
        if (myWishList.itemInWishList(itemToRemove)) {
            myWishList.removeItem(itemToRemove);
            happySound.run();
            JOptionPane.showMessageDialog(frame, "Item removed successfully!\n"
                    + "Great job, you rock!", "Success!", JOptionPane.INFORMATION_MESSAGE, happyPic);
        } else {
            System.out.println("Error: item not in wish list");
            sadSound.run();
            JOptionPane.showMessageDialog(frame, "Error, item not in wish list!\n"
                    + "Please enter a new item.", "Error!", JOptionPane.ERROR_MESSAGE, sadPic);
            itemToRemove = promptUserForItem();
            if (itemToRemove == null) {
                return;
            }
            removeItemFromWishList(itemToRemove);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads wish list from WISH_LIST_FILE, if that file exists;
    // otherwise initializes an empty wish list
    protected static void loadWishList() {
        try {
            List<Item> list = Reader.readWishList(new File(WISH_LIST_FILE));
            myWishList = new WishList();
            for (Item item : list) {
                myWishList.addItem(item);
            }
        } catch (IOException e) {
            sadSound.run();
            JOptionPane.showMessageDialog(frame, "no wish list saved to file!\n"
                    + "Making a new wish list.", "Error!", JOptionPane.ERROR_MESSAGE);
            printWishList();
        }
    }

    //EFFECTS: saves wish list to WISH_LIST_FILE
    protected static void saveWishList() {
        try {
            Writer writer = new Writer(new File(WISH_LIST_FILE));
            for (Item item : myWishList.getArrayList()) {
                writer.write(item);
            }
            writer.close();
            System.out.println("Wish list saved to file");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save wish list to " + WISH_LIST_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GUI();
    }

}
