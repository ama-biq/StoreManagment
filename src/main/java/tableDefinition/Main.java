package tableDefinition;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ChainDefinition definition = new ChainDefinition(10, "Phones");
        definition.createChain();
        System.out.println(definition.getSpecificChain(10));
        ShopDefinition shopDefinition = new ShopDefinition(2, 21, 111, 25, "Tel-Aviv");
        shopDefinition.createShop();
        System.out.println(shopDefinition.getSpecificShop(2));

    }
}
