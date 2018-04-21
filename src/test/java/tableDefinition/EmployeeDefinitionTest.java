package tableDefinition;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void getSpecificId() throws SQLException {
        //prepare db
        EmployeeDefinition definitionEmployee = new EmployeeDefinition(10,2, 21);
        definitionEmployee.createEmployee();
        //the test
        int actual = definitionEmployee.getSpecificEmployee(10);
        assertEquals(10, actual);
    }

    @Test
    public void getAllEmployeeOfCertainChain() throws SQLException {
        List<Integer> expected = new ArrayList<>();
        expected.add(10);
        expected.add(11);
        expected.add(12);
        //prepare db
        createThreeEmployees();
        //the test
        EmployeeDefinition definitionEmployeesInChain = new EmployeeDefinition();
        List<Integer> actual = definitionEmployeesInChain.getAllEmployeeInChain(21);
        assertTrue(expected.equals(actual));
    }

    private static void createThreeEmployees() throws SQLException {
        EmployeeDefinition definitionEmployee = new EmployeeDefinition(10,2, 21);
        definitionEmployee.createEmployee();
        EmployeeDefinition definitionEmployee1 = new EmployeeDefinition(11,2, 21);
        definitionEmployee1.createEmployee();
        EmployeeDefinition definitionEmployee2 = new EmployeeDefinition(12,2, 21);
        definitionEmployee2.createEmployee();
    }

    @AfterEach
    private void afterEmployeeHandle() throws SQLException {

        EmployeeDefinition definitionEmployee = new EmployeeDefinition();
        definitionEmployee.deleteSpecificEmployee(10);
        EmployeeDefinition definitionEmployee1 = new EmployeeDefinition();
        definitionEmployee1.deleteSpecificEmployee(11);
        EmployeeDefinition definitionEmployee2 = new EmployeeDefinition();
        definitionEmployee2.deleteSpecificEmployee(12);
        ShopDefinition shopDefinition = new ShopDefinition();
        shopDefinition.deleteSpecificShop(2);
        ChainDefinition chainDefinition = new ChainDefinition();
        chainDefinition.deleteSpecificChain(21);
    }
}