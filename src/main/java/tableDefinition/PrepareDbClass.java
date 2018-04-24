package tableDefinition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrepareDbClass {
    private static final String DELETE_FROM_EMPLOYEE = "DELETE FROM employee";
    private static final String DELETE_FROM_SHOP = "DELETE FROM shop";
    private static final String DELETE_FROM_CHAIN = "DELETE FROM chain";
    private static final String DELETE_FROM_MALL = "DELETE FROM mall";
    private static final String DELETE_FROM_MALL_GROUP = "DELETE FROM mall_group";

    private static final String INSERT_MALL_GRP = "insert into mall_group VALUES (1, \"Mall_Grp1\")";
    private static final String INSERT_MALL_GRP_1 = "insert into mall_group VALUES (2, \"Mall_Grp2\")";
    private static final String INSERT_MALL_GRP2 = "insert into mall_group VALUES (0, \"Mall_Grp0\")";
    private static final String INSERT_MALL = "INSERT INTO mall VALUES (111, \"Tel-Aviv\", \"Azrieli\", 1)";
    private static final String INSERT_MALL_1 = "INSERT INTO mall VALUES (555, \"Petach tikva\", \"Canion Sirkin\", 2)";
    private static final String INSERT_MALL_2 = "INSERT INTO mall VALUES (888, \"Eylat\", \"Mall Yam\", 1)";
    private static final String INSERT_MALL3 = "INSERT INTO mall VALUES (0, \"\", \"\", 0)";
    private static final String INSERTCHAIN_1 = "INSERT INTO chain  VALUES (31, \"Adidas\")";
    private static final String INSERTCHAIN = "INSERT INTO chain  VALUES (21, \"Sport wear\");\n";

    private static final String INSERT_SHOP = "INSERT INTO shop VALUES (79,\"null\", 21, 555, 7);";
    private static final String INSERT_SHOP_1 = "INSERT INTO shop VALUES (89,\"null\", 31, 888, 3)";
    private static final String INSERT_EMPLOYEE = "INSERT INTO employee  VALUES (1977, 79, 21)";
    private static final String INSERT_EMPLOYEE_1 = "INSERT INTO employee  VALUES (1988, 89, 31 )";

    public PrepareDbClass() throws SQLException {
    }

    void prepareDb(Connection connection) throws SQLException {
        PreparedStatement preparedStatementDelete = connection.prepareStatement(DELETE_FROM_EMPLOYEE);
        PreparedStatement preparedStatementDelete1 = connection.prepareStatement(DELETE_FROM_SHOP);
        PreparedStatement preparedStatementDelete2 = connection.prepareStatement(DELETE_FROM_CHAIN);
        PreparedStatement preparedStatementDelete3 = connection.prepareStatement(DELETE_FROM_MALL);
        PreparedStatement preparedStatementDelete4 = connection.prepareStatement(DELETE_FROM_MALL_GROUP);
        PreparedStatement preparedStatementInsert1 = connection.prepareStatement(INSERT_MALL_GRP);
        PreparedStatement preparedStatementInsert2 = connection.prepareStatement(INSERT_MALL_GRP_1);
        PreparedStatement preparedStatementInsert12 = connection.prepareStatement(INSERT_MALL_GRP2);
        PreparedStatement preparedStatementInsert3 = connection.prepareStatement(INSERT_MALL);
        PreparedStatement preparedStatementInsert4 = connection.prepareStatement(INSERT_MALL_1);
        PreparedStatement preparedStatementInsert5 = connection.prepareStatement(INSERT_MALL_2);
        PreparedStatement preparedStatementInsert13 = connection.prepareStatement(INSERT_MALL3);
        PreparedStatement preparedStatementInsert6 = connection.prepareStatement(INSERTCHAIN);
        PreparedStatement preparedStatementInsert7 = connection.prepareStatement(INSERTCHAIN_1);
        PreparedStatement preparedStatementInsert8 = connection.prepareStatement(INSERT_SHOP);
        PreparedStatement preparedStatementInsert9 = connection.prepareStatement(INSERT_SHOP_1);
        PreparedStatement preparedStatementInsert10 = connection.prepareStatement(INSERT_EMPLOYEE);
        PreparedStatement preparedStatementInsert11 = connection.prepareStatement(INSERT_EMPLOYEE_1);


        preparedStatementDelete.executeUpdate();
        preparedStatementDelete1.executeUpdate();
        preparedStatementDelete2.executeUpdate();
        preparedStatementDelete3.executeUpdate();
        preparedStatementDelete4.executeUpdate();
        preparedStatementInsert1.executeUpdate();
        preparedStatementInsert2.executeUpdate();
        preparedStatementInsert3.executeUpdate();
        preparedStatementInsert4.executeUpdate();
        preparedStatementInsert5.executeUpdate();
        preparedStatementInsert6.executeUpdate();
        preparedStatementInsert7.executeUpdate();
        preparedStatementInsert8.executeUpdate();
        preparedStatementInsert9.executeUpdate();
        preparedStatementInsert10.executeUpdate();
        preparedStatementInsert11.executeUpdate();
        preparedStatementInsert12.executeUpdate();
        preparedStatementInsert13.executeUpdate();
    }

}
