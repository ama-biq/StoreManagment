package tableDefinition;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopDefinitionTest {

    @Test
    public void insertChain() throws SQLException {
//prepare the db
        ChainDefinition definitionChain = new ChainDefinition(21,"Fox kids");
        definitionChain.deleteSpecificChain(21);
        definitionChain.createChain();
        ShopDefinition definition = new ShopDefinition(2, 21, 111, 25, "Tel-Aviv");
        //the test
        int actual = definition.createShop();
//        //after test db cleaning
        definition.deleteSpecificShop(2);
        assertEquals(1, actual);
    }
    @Test
    public void getSpecificShop() throws SQLException {

        //prepare the db
        ShopDefinition emptyDefinition = new ShopDefinition();
        emptyDefinition.deleteSpecificShop(2);
        ShopDefinition definition = new ShopDefinition(2, 21, 111, 25, "Tel-Aviv");
        definition.createShop();
        //the test
        int actual = emptyDefinition.getSpecificShop(2);
        //after test db cleaning
        emptyDefinition.deleteSpecificShop(2);
        assertEquals(2, actual );
    }
    @Disabled
    @Test
    public void testPresentAllShopInCertainMall() throws SQLException {
        MallDefinition mallDefinition = new MallDefinition(777,"See mall", "Haifa",111111);
        mallDefinition.createMall();
        ShopDefinition shopDefinition = new ShopDefinition();
       preparedbToPresentShopInTheSameMall();
        //the test
        List<Integer> actual = shopDefinition.getAllShopInCertainMall(777);
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(2323);
        //after test db cleaning
        shopDefinition.deleteSpecificShop(2);
        shopDefinition.deleteSpecificShop(2323);
        shopDefinition.deleteSpecificShop(3232);
        mallDefinition.deleteSpecificMall(777);
        assertTrue(expected.equals(actual));
    }

    private void preparedbToPresentShopInTheSameMall() throws SQLException {
        //prepare the db
        ShopDefinition emptyDefinition = new ShopDefinition();
        emptyDefinition.deleteSpecificShop(2);
        emptyDefinition.deleteSpecificShop(2323);
        emptyDefinition.deleteSpecificShop(3232);
        ShopDefinition definition = new ShopDefinition(2, 21, 777, 77, "Haifa");
        ShopDefinition definition1 = new ShopDefinition(2323, 1, 777, 25, "Haifa");
        ShopDefinition definition2 = new ShopDefinition(3232, 21, 222, 25, "Ramle");
        definition.createShop();
        definition1.createShop();
        definition2.createShop();

    }
}