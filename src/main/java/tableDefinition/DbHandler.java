package tableDefinition;

import java.sql.SQLException;

public class DbHandler {
    public static void main(String[] args) throws SQLException {
        ChainDefinition definition = new ChainDefinition(10, "Phones");
        definition.createChain();
        System.out.println(definition.getSpecificChain(10));

    }
}
