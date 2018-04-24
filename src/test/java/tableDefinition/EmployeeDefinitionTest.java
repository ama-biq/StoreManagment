package tableDefinition;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDefinitionTest {

    ChainDefinition emptyDefinition = new ChainDefinition();
    ConnectionToDb connObject = new ConnectionToDb();
    Connection connection = connObject.getConnection();
    PrepareDbClass prepareDbClass = new PrepareDbClass();

    EmployeeDefinitionTest() throws SQLException {
    }

    @BeforeEach
    public void prepareDb() throws SQLException {
        prepareDbClass.prepareDb(connection);
    }

    @Test
    public void insertEmployee() throws SQLException {

        EmployeeDefinition definitionEmployee = new EmployeeDefinition(10,79, 21);
        int actual = definitionEmployee.createEmployee(connection);
        assertEquals(1, actual);
    }

    @Test
    public void getSpecificEmployee() throws SQLException {

        EmployeeDefinition definitionEmployee = new EmployeeDefinition(1976,89, 31);
        definitionEmployee.createEmployee(connection);
        int actual = definitionEmployee.getSpecificEmployee(connection, 1976);
        assertEquals(1976, actual);
    }

    @Test
    public void getAllEmployeeOfCertainChain() throws SQLException {
        EmployeeDefinition definitionEmployeesInChain = new EmployeeDefinition();
        List<Integer> expected = new ArrayList<>();
        expected.add(1977);
        List<Integer> actual = definitionEmployeesInChain.getAllEmployeeInChain(connection, 21);
        assertTrue(expected.equals(actual));
    }
    @Test
    public void deleteEmployee() throws SQLException {
        EmployeeDefinition definitionEmployee = new EmployeeDefinition();
        int actual = definitionEmployee.deleteSpecificEmployee(connection, 1977);
        assertEquals(1, actual);
    }

}