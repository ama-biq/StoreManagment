package tableDefinition;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class GroupMallDefinition {

    private int groupMallId;
    private String groupMallName;

    private static final String GET_GROUP_MALL_BY_ID = "select * from mall_group where Group_Id = ?";
    private static final String GET_ALL_GROUP_MALL = "select * from mall_group";
    private static final String DELETE_MALL_GRP = "delete from mall_group";
    private static final String INSERT_GROUP_MALL_TO_TABLE = "insert into mall_group(Group_Id, Group_Name) VALUES(?,?)";
    private static final String DELETE_MALL_GROUP_BY_ID = "delete from mall_group where Group_Id=?";

    public GroupMallDefinition(int groupMallId, String groupMallName) {
        this.groupMallId = groupMallId;
        this.groupMallName = groupMallName;
    }

    GroupMallDefinition() {

    }

    public int createMallGrp(Connection connection) throws SQLException {
        int status;
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GROUP_MALL_TO_TABLE);
        preparedStatement.setInt(1, groupMallId);
        preparedStatement.setString(2, groupMallName);
        status = preparedStatement.executeUpdate();
        return status;
    }

    public Set<Integer> getMallGrp(Connection connection, int groupMallId) throws SQLException {
        Set<Integer> mallGrpList = new HashSet<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_GROUP_MALL);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int i = 1;
            while (i <= numberOfColumns(rs)) {
                mallGrpList.add(rs.getInt(1));
                i = i + 5;
            }
        }
        return mallGrpList;
    }

    public Set<Integer> getAllMallGrp(Connection connection) throws SQLException {
        Set<Integer>setMallGroups = new HashSet<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_GROUP_MALL);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            int i = 1;
            while (i <= numberOfColumns(rs)) {
                setMallGroups.add(rs.getInt(1));
                i = i + 5;
            }
        }
         setMallGroups.remove(0);
        return setMallGroups;
    }

    public int deleteMallGrp(Connection connection) throws SQLException {
        int status;
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MALL_GRP);
        status = preparedStatement.executeUpdate();
        return status;
    }

    public int deleteSpecificMallGrp(Connection connection, int groupMallId) throws SQLException {
        int status;
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MALL_GROUP_BY_ID);
        preparedStatement.setInt(1, groupMallId);
        status = preparedStatement.executeUpdate();
        return status;

    }

    private int numberOfColumns(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        return metaData.getColumnCount();
    }

}
