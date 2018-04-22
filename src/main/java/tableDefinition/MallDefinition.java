package tableDefinition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MallDefinition {

    private int mallId;
    private String mallName;
    private String mallAddress;
    private int grpMallId;
    private static final String insertMall = "insert into mall(Mall_Id, Address, Name, Group_Mall_Id) VALUES(?,?,?,?)";
    private static final String deleteSpacificMall = "delete from mall where Mall_Id=?";



    public MallDefinition(int mallId, String mallName, String mallAddress, int grpMallId) {
        this.mallId = mallId;
        this.mallName = mallName;
        this.mallAddress = mallAddress;
        this.grpMallId = grpMallId;
    }


    public int createMall() throws SQLException {
        int status =0;
        try {
            ConnectionToDb connObject = new ConnectionToDb();
            Connection connection = connObject.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertMall);
            preparedStatement.setInt(1, mallId);
            preparedStatement.setString(2, mallAddress);
            preparedStatement.setString(3, mallName);
            preparedStatement.setInt(4, grpMallId);

            status = preparedStatement.executeUpdate();
        }catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return status;
    }
    public int deleteSpecificMall(int specificMall) throws SQLException {
        int status = 0;
        try {
            ConnectionToDb connObject = new ConnectionToDb();
            Connection connection = connObject.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSpacificMall);
            preparedStatement.setInt(1, specificMall);
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return status;

    }
}
