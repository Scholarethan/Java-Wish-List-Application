package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WishListTest {
    private WishList testWishList;
    private Item testItem;
    private Item testItemTwo;
    private Item testItemThree;
    private ArrayList<Item> testArrayList;

    @BeforeEach
    void runBefore() {
        testWishList = new WishList();
        testItem = new Item("Chrome Hearts Ring", "Jewellery", 200.00);
        testItemTwo = new Item("Nike Air Max TN", "Footwear", 150.00);
        testItemThree = new Item("White Jeans", "Pants", 30.00);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testWishList.getArrayList().size());
    }

    @Test
    void testItemInWishList() {
        testWishList.addItem(testItem);
        assertTrue(testWishList.itemInWishList(testItem));
        assertFalse(testWishList.itemInWishList(testItemThree));
    }

    @Test
    void testGetItem() {
        Item testItemFour = testItem;
        assertTrue(testItemFour == testItem);
        assertFalse(testItemFour == testItemTwo);
    }

    @Test
    void testAddItem() {
        testWishList.addItem(testItem);
        testWishList.addItem(testItemTwo);
        testWishList.addItem(testItemThree);
        assertTrue(testWishList.itemInWishList(testItem));
        assertTrue(testWishList.itemInWishList(testItemTwo));
        assertTrue(testWishList.itemInWishList(testItemThree));
    }

    @Test
    void testRemoveItem() {
        testWishList.addItem(testItem);
        testWishList.addItem(testItemTwo);
        testWishList.addItem(testItemThree);
        testWishList.removeItem(testItem);
        testWishList.removeItem(testItemTwo);
        assertFalse(testWishList.itemInWishList(testItem));
        assertFalse(testWishList.itemInWishList(testItemTwo));
        assertTrue(testWishList.itemInWishList(testItemThree));
    }

    @Test
    void testArchiveItem() {
        testWishList.addItem(testItem);
        testWishList.archiveItem(testItem);
        assertTrue(testItem.getArchived());
    }

    @Test
    void testMarkAsPurchased() {
        testWishList.addItem(testItem);
        testWishList.markAsPurchased(testItem);
        assertTrue(testItem.getPurchased());
    }

    @Test
    void testGetArrayList() {
        testArrayList = testWishList.getArrayList();
        assertEquals(0,testArrayList.size());
    }

}
