package model;

//import java.util.*; *Add in date functionality at a future time*

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.Objects;

//Represents an item having a name and price in dollars. Holds status information
//such as whether or not the item has been purchased or archived and other details
//including when the item was added to the wish list.
public class Item implements Saveable {
    private String name;
    private String category;
    private double cost;
    private boolean purchased;
    private boolean archived;
//   private Date dateAdded;


    // REQUIRES: itemName has non-zero length, category has non-zero length, cost is positive
    //EFFECTS: constructs an item with name, category and price
    public Item(String name, String category, double cost) {
        this.name = name;
        this.category = category;
        this.cost = cost;
        //this.dateAdded = dateAdded;
        purchased = false;
        archived = false;
    }

    // REQUIRES: itemName has non-zero length, category has non-zero length, cost is positive
    //EFFECTS: constructs an item with name, category and price
    public Item(String name, String category, double cost, boolean purchased, boolean archived) {
        this.name = name;
        this.category = category;
        this.cost = cost;
        //this.dateAdded = dateAdded;
        this.purchased = purchased;
        this.archived = archived;
    }

    //EFFECTS: returns the name of the item
    public String getName() {
        return this.name;
    }

    //EFFECTS: returns the category of the item
    public String getCategory() {
        return this.category;
    }

    //EFFECTS: Returns the cost of the item
    public double getCost() {
        return this.cost;
    }

    //EFFECTS: returns whether or not the item has been purchased
    public Boolean getPurchased() {
        return purchased;
    }

    //EFFECTS: returns whether or not the item has been archived
    public Boolean getArchived() {
        return archived;
    }

    //MODIFIES: this
    //EFFECTS: Sets the item's purchased status to true
    public void setPurchased() {
        this.purchased = true;
    }

    //MODIFIES: this
    //EFFECTS: Sets the item's archived status to true
    public void setArchived() {
        this.archived = true;
    }

//    //MODIFIES: this
//    //EFFECTS: Sets the item's archived status to true
//    public void unArchived() {
//        this.archived = false;
//    }

    @Override
    public String toString() {
        return name; //stub for compilation purpose
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(name);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(category);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(cost);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(purchased);
        printWriter.print(Reader.DELIMITER);
        printWriter.println(archived);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Double.compare(item.cost, cost) == 0
                && Objects.equals(name, item.name)
                && Objects.equals(category, item.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, cost);
    }
}
