//Citation: TellerApp

package persistence;

import model.Item;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {

    @Test
    void testParseItemFile1() {
        try{
            List<Item> wishList = Reader.readWishList(new File("./data/testWishListFile1.txt"));
            Item testItemOne = wishList.get(0);
            Item testItemTwo = wishList.get(1);
            Item testItemThree = wishList.get(2);

            assertEquals("Chrome Hearts Ring", testItemOne.getName());
            assertEquals("Nike Air Max", testItemTwo.getName());
            assertEquals("Uniqlo Fleece", testItemThree.getName());

            assertEquals("Jewellery", testItemOne.getCategory());
            assertEquals("Shoes", testItemTwo.getCategory());
            assertEquals("Clothing", testItemThree.getCategory());

            assertEquals(200.00, testItemOne.getCost());
            assertEquals(150.00, testItemTwo.getCost());
            assertEquals(30.00, testItemThree.getCost());

            assertFalse(testItemOne.getPurchased());
            assertTrue(testItemTwo.getPurchased());
            assertFalse(testItemThree.getPurchased());

            assertTrue(testItemOne.getArchived());
            assertFalse(testItemTwo.getArchived());
            assertFalse(testItemThree.getArchived());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readWishList(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            //correct result as expected
        }
    }

}
