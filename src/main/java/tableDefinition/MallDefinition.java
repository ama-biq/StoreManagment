package tableDefinition;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MallDefinition {

    private int mallId;
    private String mallName;
    private String mallAddress;
    private int grpMallId;
    private static final String insertMall = "insert into mall(Mall_Id, Address, Name, Group_Mall_Id) VALUES(?,?,?,?)";
    private static final String deleteSpacificMall = "delete from mall where Mall_Id=?";
    private static final String getAllMalls = "SELECT * from mall";



    public MallDefinition(int mallId, String mallName, String mallAddress, int grpMallId) {
        this.mallId = mallId;
        this.mallName = mallName;
        this.mallAddress = mallAddress;
        this.grpMallId = grpMallId;
    }

    public MallDefinition() {

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

    public List<Integer> getExistedMall(Connection connection) throws SQLException {

        List<Integer> mallList = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(getAllMalls);
            ResultSet rs = preparedStatement.executeQuery();
            ResultSet rsCopy = rs;
            while(rs.next()) {
                int i = 1;
                while (i <= numberOfColumns(rs)){
                    mallList.add(rs.getInt(1));
                    i=i+3;
                }
            }
        return mallList;
    }

    private int numberOfColumns(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        return metaData.getColumnCount();
    }


}
