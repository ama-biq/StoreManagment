package tableDefinition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ShopDefinitionTest {

    ChainDefinition emptyDefinition = new ChainDefinition();
    ConnectionToDb connObject = new ConnectionToDb();
    Connection connection = connObject.getConnection();
    PrepareDbClass prepareDbClass = new PrepareDbClass();
    @BeforeEach
    public void prepareDb() throws SQLException {
        prepareDbClass.prepareDb(connection);
    }

    ShopDefinitionTest() throws SQLException {
    }
    @Test
    public void insertChain() throws SQLException {

        ShopDefinition definition = new ShopDefinition(2,31, 555, 2 );
        int actual = definition.createShop(connection);
        assertEquals(1, actual);
    }

    @Test
    public void deleteSpecificShop() throws SQLException {
        //delete the next employee because he is working in shop iwant to delete
        EmployeeDefinition definitionEmployee = new EmployeeDefinition();
        definitionEmployee.deleteSpecificEmployee(connection, 1977);

        ShopDefinition definition = new ShopDefinition();
        int actual = definition.deleteSpecificShop(79);
        assertEquals(1, actual);

    }
//
//
//
    @Test
    public void passGetAllShopsInSpecificMallGroup() throws SQLException {



        ShopDefinition shopDefinition = new ShopDefinition();
        ShopDefinition shopDefinition1 = new ShopDefinition(99, 31, 888, 1);
        ShopDefinition shopDefinition2 = new ShopDefinition(100, 31, 888, 2);
        ShopDefinition shopDefinition3 = new ShopDefinition(200, 31, 888, 3);


        shopDefinition1.createShop(connection);
        shopDefinition2.createShop(connection);
        shopDefinition3.createShop(connection);

        Set<Integer> actual = shopDefinition.getAllShopsInSpecificMallGroup(connection, 1);


        assertEquals(4, actual.size() );
    }

    @Test
    public void passPresentAllDetailsOfAShop() throws SQLException {

        ShopDefinition shopDefinition = new ShopDefinition(79, 21, 555, 7, null);
        ShopDefinition shopDefinition1 = new ShopDefinition();
        ShopDefinition actual = shopDefinition1.presentAllDetailsOfAShop(79, connection);

        assertEquals(shopDefinition.toString(), actual.toString());
    }

}