package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Item testItem;
    private Item testItemTwo;
    private Item testItemThree;

    @BeforeEach
    void runBefore() {
        testItem = new Item("Chrome Hearts Ring", "Jewellery" , 200.00);
        testItemTwo = new Item("Nike Air Max TN", "Footwear" , 150.00);
        testItemThree = new Item("White Jeans", "Pants" , 30.00);
    }

    @Test
    void testConstructor() {
        assertEquals("Chrome Hearts Ring", testItem.getName());
        assertEquals("Jewellery", testItem.getCategory());
        assertEquals(200.00, testItem.getCost());
        assertFalse(testItem.getArchived());
        assertFalse(testItem.getPurchased());
        assertEquals("Nike Air Max TN", testItemTwo.getName());
        assertEquals("Footwear", testItemTwo.getCategory());
        assertEquals(150.00, testItemTwo.getCost());
        assertFalse(testItemTwo.getArchived());
        assertFalse(testItemTwo.getPurchased());
        assertEquals("White Jeans", testItemThree.getName());
        assertEquals("Pants", testItemThree.getCategory());
        assertEquals(30.00, testItemThree.getCost());
        assertFalse(testItemThree.getArchived());
        assertFalse(testItemThree.getPurchased());
    }

    @Test
    void testGetName() {
        assertEquals("Chrome Hearts Ring", testItem.getName());
        assertEquals("Nike Air Max TN", testItemTwo.getName());
        assertEquals("White Jeans", testItemThree.getName());
    }

    @Test
    void testGetCategory() {
        assertEquals("Jewellery", testItem.getCategory());
        assertEquals("Footwear", testItemTwo.getCategory());
        assertEquals("Pants", testItemThree.getCategory());
    }

    @Test
    void testGetCost() {
        assertEquals(200.00, testItem.getCost());
        assertEquals(150.00, testItemTwo.getCost());
        assertEquals(30.00, testItemThree.getCost());
    }

    @Test
    void testSetPurchased() {
        testItem.setPurchased();
        testItemTwo.setPurchased();
        testItemThree.setPurchased();
        assertTrue(testItem.getPurchased());
        assertTrue(testItemTwo.getPurchased());
        assertTrue(testItemThree.getPurchased());
    }

    @Test
    void testSetArchived() {
        testItem.setArchived();
        testItemTwo.setArchived();
        testItemThree.setArchived();
        assertTrue(testItem.getArchived());
        assertTrue(testItemTwo.getArchived());
        assertTrue(testItemThree.getArchived());
    }

    @Test
    void testGetPurchased() {
        assertFalse(testItem.getPurchased());
        assertFalse(testItemTwo.getPurchased());
        assertFalse(testItemThree.getPurchased());
    }

    @Test
    void testGetArchived() {
        assertFalse(testItem.getArchived());
        assertFalse(testItemTwo.getArchived());
        assertFalse(testItemThree.getArchived());
    }

    @Test
    void testToString() {
        assertEquals("Chrome Hearts Ring",testItem.toString());
        assertEquals("Nike Air Max TN",testItemTwo.toString());
        assertEquals("White Jeans",testItemThree.toString());
    }

    @Test
    void testEquals() {
        Item testItemFour = testItem;
        assertTrue(testItemFour.equals(testItem));
        assertFalse(testItemFour.equals(null));
        Item testItemFive = new Item("Chrome Hearts Ring", "Jewellery" , 200.00);
        assertTrue(testItemFive.equals(testItem));
        assertTrue(testItemFive.getName().equals(testItem.getName()));
        assertTrue(testItemFive.getCategory().equals(testItem.getCategory()));
        assertTrue(testItemFive.getCost() == testItem.getCost());
    }

    @Test
    void testHashCode() {
        Item testItemFour = new Item("Chrome Hearts Ring", "Jewellery" , 200.00);
        assertTrue(testItem.hashCode() == testItemFour.hashCode());
    }
}
