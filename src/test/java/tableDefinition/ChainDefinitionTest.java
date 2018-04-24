package tableDefinition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ChainDefinitionTest {
    ChainDefinition emptyDefinition = new ChainDefinition();
    ConnectionToDb connObject = new ConnectionToDb();
    Connection connection = connObject.getConnection();
    PrepareDbClass prepareDbClass = new PrepareDbClass();
    @BeforeEach
    public void prepareDb() throws SQLException {
        prepareDbClass.prepareDb(connection);
    }

    ChainDefinitionTest() throws SQLException {
    }


    @Test
    public void getSpecificChain() throws SQLException {

        //prepare the db
//        emptyDefinition.deleteSpecificChain(222);
        ChainDefinition definition = new ChainDefinition();
        //definition.createChain(connection);
        //the test
        int actual = emptyDefinition.getSpecificChain(connection, 31);
        //after test db cleaning
        //emptyDefinition.deleteSpecificChain(222);
        assertEquals(31, actual );
    }
    @Test
    public void insertChain() throws SQLException {
     //prepare the db
         // emptyDefinition.deleteSpecificChain(21);
        ChainDefinition definition = new ChainDefinition(222,"Fox kids");
        //the test
        int actual = definition.createChain(connection);
        //after test db cleaning
        //definition.deleteSpecificChain(21);
        assertEquals(1, actual);
    }
    @Test
    public void deleteSpecificChain() throws SQLException {
        //prepare the db
        ChainDefinition definition = new ChainDefinition(222,"Fox Kids");
        definition.createChain(connection);
        //delete specific chain
        int actual = emptyDefinition.deleteSpecificChain(connection,222);
        assertEquals(1, actual);
    }
    }

