package tableDefinition;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class GroupMallDefinition {

    private int groupMallId;
    private String groupMallName;

    private static final String getGroupMallById = "select * from mall_group where Group_Id = ?";
    private static final String getAllGroupMall = "select * from mall_group";
    private static final String deleteMallGrp = "delete from mall_group";
    private static final String insertGroupMallToTable = "insert into mall_group(Group_Id, Group_Name) VALUES(?,?)";

    public GroupMallDefinition(int groupMallId, String groupMallName) {
        this.groupMallId = groupMallId;
        this.groupMallName = groupMallName;
    }

    public GroupMallDefinition() {

    }


    public int createMallGrp(Connection connection) throws SQLException {
        int status =0;
        PreparedStatement preparedStatement = connection.prepareStatement(insertGroupMallToTable);
        preparedStatement.setInt(1, groupMallId);
        preparedStatement.setString(2, groupMallName);
        status = preparedStatement.executeUpdate();
        return status;

    }
    public Set<Integer> getMallGrp(Connection connection, int groupMallId) throws SQLException {
        Set<Integer> mallGrpList = new HashSet<>();
        PreparedStatement preparedStatement = connection.prepareStatement(getAllGroupMall);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            int i = 1;
            while (i <= numberOfColumns(rs)) {
                mallGrpList.add(rs.getInt(1));
                i = i + 5;
            }
        }

        return mallGrpList;
    }

    public int getAllMallGrp(Connection connection) throws SQLException {
        int column = 0;
        PreparedStatement preparedStatement = connection.prepareStatement(getAllGroupMall);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            column = rs.getInt(1);
        }
        return column;
    }
    public int deleteMallGrp(Connection connection) throws SQLException {
        int status =0;
        PreparedStatement preparedStatement = connection.prepareStatement(deleteMallGrp);
        status = preparedStatement.executeUpdate();
        return status;

    }

    public int deleteSpecificMallGrp(Connection connection, int groupMallId) throws SQLException {
        int status =0;
        PreparedStatement preparedStatement = connection.prepareStatement("delete from mall_group where Group_Id=?");
        preparedStatement.setInt(1,groupMallId);
        status = preparedStatement.executeUpdate();
        return status;

    }
    private int numberOfColumns(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        return metaData.getColumnCount();
    }

}
