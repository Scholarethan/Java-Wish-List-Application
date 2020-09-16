package model;

import persistence.Saveable;

import java.util.ArrayList; //In the future, explore using a set instead of ArrayList

// Represents a wish list containing items
public class WishList {
    private ArrayList<Item> myWishList;

    //EFFECTS: constructs an empty wish list
    public WishList() {
        myWishList = new ArrayList<Item>();
    }

    //EFFECTS: returns an item in the wish list
    public Item getItem(Item itemToGet) {
        int index = 0;
        for (Item item : myWishList) {
            if (!item.equals(itemToGet)) {
                index++;
            } else {
                break;
            }
        }
        return myWishList.get(index);
    }

    //MODIFIES: this
    //EFFECTS: adds an item to the WishList
    public void addItem(Item itemToAdd) {
        myWishList.add(itemToAdd);
    }

    //MODIFIES: this
    //EFFECTS: removes an item from the WishList
    public void removeItem(Item itemToRemove) {
        myWishList.remove(itemToRemove);
    }

    //MODIFIES: this
    //EFFECTS: archives an item in the WishList
    public void archiveItem(Item itemToArchive) {
        Item itemToMark;
        itemToMark = getItem(itemToArchive);
        itemToMark.setArchived();
    }

    //MODIFIES: this
    //EFFECTS: marks an item as purchased in the WishList
    public void markAsPurchased(Item purchasedItem) {
        Item itemToMark;
        itemToMark = getItem(purchasedItem);
        itemToMark.setPurchased();
    }

    //EFFECTS: returns true if the item being searched for is in the WishList, otherwise returns false.
    public boolean itemInWishList(Item itemToFind) {
        if (myWishList.contains(itemToFind)) {
            return true;
        }
        return false;
    }

    //EFFECTS: returns the ArrayList of the WishList class
    public ArrayList<Item> getArrayList() {
        return myWishList;
    }

}
