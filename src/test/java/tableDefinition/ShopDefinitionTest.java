package tableDefinition;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

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
    public void getSpecificChain() throws SQLException {

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
}