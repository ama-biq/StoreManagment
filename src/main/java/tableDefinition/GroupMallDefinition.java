package tableDefinition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMallDefinition {

    private int groupMallId;
    private String groupMallName;

    public GroupMallDefinition(int groupMallId, String groupMallName) {
        this.groupMallId = groupMallId;
        this.groupMallName = groupMallName;
    }

    public int getGroupMallId() {
        return groupMallId;
    }

    public void setGroupMallId(int groupMallId) {
        this.groupMallId = groupMallId;
    }

    public String getGroupMallName() {
        return groupMallName;
    }

    public void setGroupMallName(String groupMallName) {
        this.groupMallName = groupMallName;
    }

    public int createMallGrp() throws SQLException {
        int status =0;
        ConnectionToDb connObject = new ConnectionToDb();
        Connection connection = connObject.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into mall_group(Group_Id, Group_Name) VALUES(?,?)");
        preparedStatement.setInt(1, groupMallId);
        preparedStatement.setString(2, groupMallName);
        status = preparedStatement.executeUpdate();
        return status;

    }
    public int getMallGrp() throws SQLException {
        int status =0;
        ConnectionToDb connObject = new ConnectionToDb();
        Connection connection = connObject.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from mall_group");
        ResultSet rs = preparedStatement.getResultSet();
        return rs.getInt(1);

    }
    public int deleteMallGrp() throws SQLException {
        int status =0;
        ConnectionToDb connObject = new ConnectionToDb();
        Connection connection = connObject.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from mall_group");
        status = preparedStatement.executeUpdate();
        return status;

    }
}
