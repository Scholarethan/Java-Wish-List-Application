//Citation: Teller App

package persistence;

import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILE = "./data/testWishList.txt";
    private Writer testWriter;
    private Item testItemOne;
    private Item testItemTwo;
    private Item testItemThree;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        testItemOne = new Item("Chrome Hearts Ring", "Jewellery", 200.00);
        testItemTwo = new Item("Nike Air Max", "Shoes", 150.00);
        testItemThree = new Item("Uniqlo Fleece", "Clothing", 30.00);
    }

    @Test
    void testWriteWishList() {
        testWriter.write(testItemOne);
        testWriter.write(testItemTwo);
        testWriter.write(testItemThree);
        testWriter.close();

        try {
            List<Item> wishList = Reader.readWishList(new File(TEST_FILE));
            Item testItemOneResult = wishList.get(0);
            Item testItemTwoResult = wishList.get(1);
            Item testItemThreeResult = wishList.get(2);

            assertEquals("Chrome Hearts Ring", testItemOneResult.getName());
            assertEquals("Nike Air Max", testItemTwoResult.getName());
            assertEquals("Uniqlo Fleece", testItemThreeResult.getName());

            assertEquals("Jewellery", testItemOneResult.getCategory());
            assertEquals("Shoes", testItemTwoResult.getCategory());
            assertEquals("Clothing", testItemThreeResult.getCategory());

            assertEquals(200.00, testItemOne.getCost());
            assertEquals(150.00, testItemTwo.getCost());
            assertEquals(30.00, testItemThree.getCost());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
