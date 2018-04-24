package tableDefinition;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class MallDefinition {

    private int mallId;
    private String mallName;
    private String mallAddress;
    private int grpMallId;
    private static final String INSERT_MALL = "insert into mall(Mall_Id, Address, Name, Group_Mall_Id) VALUES(?,?,?,?)";
    private static final String DELETE_SPECIFIC_MALL = "delete from mall where Mall_Id=?";
    private static final String GET_ALL_MALLS = "SELECT * from mall";


    public MallDefinition(int mallId, String mallName, String mallAddress, int grpMallId) {
        this.mallId = mallId;
        this.mallName = mallName;
        this.mallAddress = mallAddress;
        this.grpMallId = grpMallId;
    }

    MallDefinition() {

    }

    public int createMall(Connection connection) throws SQLException {
        int status = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MALL);
            preparedStatement.setInt(1, mallId);
            preparedStatement.setString(2, mallAddress);
            preparedStatement.setString(3, mallName);
            preparedStatement.setInt(4, grpMallId);

            status = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return status;
    }

    public int deleteSpecificMall(Connection connection, int specificMall) throws SQLException {
        int status = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SPECIFIC_MALL);
            preparedStatement.setInt(1, specificMall);
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return status;

    }

    Set<Integer> getExistedMall(Connection connection) throws SQLException {

        Set<Integer> mallList = new HashSet<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MALLS);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int i = 1;
            while (i <= numberOfColumns(rs)) {
                mallList.add(rs.getInt(1));
                i = i + 3;
            }
        }
         mallList.remove(0);
        return mallList;
    }

    private int numberOfColumns(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        return metaData.getColumnCount();
    }


}
