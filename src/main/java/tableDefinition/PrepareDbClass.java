package tableDefinition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrepareDbClass {
    private static final String deleteFromEmployee ="DELETE FROM employee";
    private static final String deleteFromShop ="DELETE FROM shop";
    private static final String deleteFromChain ="DELETE FROM chain";
    private static final String deleteFromMall ="DELETE FROM mall";
    private static final String deleteFromMall_group ="DELETE FROM mall_group";

    private static final String insertMallGrp = "insert into mall_group VALUES (1, \"Mall_Grp1\")";
    private static final String insertMallGrp1 = "insert into mall_group VALUES (2, \"Mall_Grp2\")";
    private static final String insertMall = "INSERT INTO mall VALUES (111, \"Tel-Aviv\", \"Azrieli\", 1)";
    private static final String insertMall1 = "INSERT INTO mall VALUES (555, \"Petach tikva\", \"Canion Sirkin\", 2)";
    private static final String insertMall2 = "INSERT INTO mall VALUES (888, \"Eylat\", \"Mall Yam\", 1)";
    private static final String insertchain1 = "INSERT INTO chain  VALUES (31, \"Adidas\")";
    private static final String insertchain = "INSERT INTO chain  VALUES (21, \"Sport wear\");\n";

    private static final String insertShop = "INSERT INTO shop VALUES (79,\"Tel-Aviv\", 21, 555, 7);";
    private static final String insertShop1 = "INSERT INTO shop VALUES (89,\"Petach-Tikva\", 31, 888, 3)";
    private static final String insertEmployee = "INSERT INTO employee  VALUES (1977, 79, 21)";
    private static final String insertEmployee1 = "INSERT INTO employee  VALUES (1988, 89, 31 )";





    ConnectionToDb connObject = new ConnectionToDb();
    Connection connection = connObject.getConnection();

    public PrepareDbClass() throws SQLException {
    }

    void prepareDb(Connection connection) throws SQLException {
        PreparedStatement preparedStatementDelete = connection.prepareStatement(deleteFromEmployee);
        PreparedStatement preparedStatementDelete1 = connection.prepareStatement(deleteFromShop);
        PreparedStatement preparedStatementDelete2 = connection.prepareStatement(deleteFromChain);
        PreparedStatement preparedStatementDelete3 = connection.prepareStatement(deleteFromMall);
        PreparedStatement preparedStatementDelete4 = connection.prepareStatement(deleteFromMall_group);
        PreparedStatement preparedStatementInsert1 = connection.prepareStatement(insertMallGrp);
        PreparedStatement preparedStatementInsert2 = connection.prepareStatement(insertMallGrp1);
        PreparedStatement preparedStatementInsert3 = connection.prepareStatement(insertMall);
        PreparedStatement preparedStatementInsert4 = connection.prepareStatement(insertMall1);
        PreparedStatement preparedStatementInsert5 = connection.prepareStatement(insertMall2);
        PreparedStatement preparedStatementInsert6 = connection.prepareStatement(insertchain);
        PreparedStatement preparedStatementInsert7 = connection.prepareStatement(insertchain1);
        PreparedStatement preparedStatementInsert8 = connection.prepareStatement(insertShop);
        PreparedStatement preparedStatementInsert9 = connection.prepareStatement(insertShop1);
        PreparedStatement preparedStatementInsert10 = connection.prepareStatement(insertEmployee);
        PreparedStatement preparedStatementInsert11= connection.prepareStatement(insertEmployee1);

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


    }

}
