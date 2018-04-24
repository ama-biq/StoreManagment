package tableDefinition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        int actual = emptyDefinition.getSpecificChain(connection, 31);
        assertEquals(31, actual);
    }

    @Test
    public void insertChain() throws SQLException {
        ChainDefinition definition = new ChainDefinition(222, "Fox kids");
        int actual = definition.createChain(connection);
        assertEquals(1, actual);
    }

    @Test
    public void deleteSpecificChain() throws SQLException {
        ChainDefinition definition = new ChainDefinition(222, "Fox Kids");
        definition.createChain(connection);
        int actual = emptyDefinition.deleteSpecificChain(connection, 222);
        assertEquals(1, actual);
    }
}

