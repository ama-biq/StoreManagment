package tableDefinition;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDefinitionTest {

    @BeforeEach
    private void beforeEmployeeHandle() throws SQLException {
        ChainDefinition chainDefinition = new ChainDefinition(21,"Fox kids");
        chainDefinition.createChain();
        ShopDefinition definition = new ShopDefinition(2, 21, 111, 25, "Tel-Aviv");
        definition.deleteSpecificShop(2);
        definition.createShop();
    }
    @Test
    public void insertEmployee() throws SQLException {

        EmployeeDefinition definitionEmployee = new EmployeeDefinition(10,2, 21);
        int actual = definitionEmployee.createEmployee();
        assertEquals(1, actual);
    }

    @AfterEach
    private void afterEmployeeHandle() throws SQLException {

        EmployeeDefinition definitionEmployee = new EmployeeDefinition();
        definitionEmployee.deleteSpecificEmployee(10);
        ShopDefinition shopDefinition = new ShopDefinition();
        shopDefinition.deleteSpecificShop(2);
        ChainDefinition chainDefinition = new ChainDefinition();
        chainDefinition.deleteSpecificChain(21);
    }
}