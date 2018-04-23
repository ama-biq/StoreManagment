package tableDefinition;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ChainDefinitionTest {
    ChainDefinition emptyDefinition = new ChainDefinition();
    ConnectionToDb connObject = new ConnectionToDb();
    Connection connection = connObject.getConnection();

    ChainDefinitionTest() throws SQLException {
    }


    @Test
    public void getSpecificChain() throws SQLException {

        //prepare the db
        emptyDefinition.deleteSpecificChain(222);
        ChainDefinition definition = new ChainDefinition(222,"Fox kids");
        definition.createChain(connection);
        //the test
        int actual = emptyDefinition.getSpecificChain(222);
        //after test db cleaning
        emptyDefinition.deleteSpecificChain(222);
        assertEquals(222, actual );
    }
    @Test
    public void insertChain() throws SQLException {
     //prepare the db
          emptyDefinition.deleteSpecificChain(21);
        ChainDefinition definition = new ChainDefinition(21,"Fox kids");
        //the test
        int actual = definition.createChain(connection);
        //after test db cleaning
        definition.deleteSpecificChain(21);
        assertEquals(1, actual);
    }
    @Test
    public void deleteSpecificChain() throws SQLException {
        //prepare the db
        ChainDefinition definition = new ChainDefinition(22,"Fox kids");
        definition.createChain(connection);
        //delete specific chain
        int actual = emptyDefinition.deleteSpecificChain(22);
        assertEquals(1, actual);
    }
    }

