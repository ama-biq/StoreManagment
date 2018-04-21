package tableDefinition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMallDefinition {

    private int groupMallId;
    private String groupMallName;

    private static final String getGroupMallById = "select * from mall_group where Group_Id = ?";
    private static final String deleteMallGrp = "delete from mall_group";
    private static final String insertGroupMallToTable = "insert into mall_group(Group_Id, Group_Name) VALUES(?,?)";

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
        PreparedStatement preparedStatement = connection.prepareStatement(insertGroupMallToTable);
        preparedStatement.setInt(1, groupMallId);
        preparedStatement.setString(2, groupMallName);
        status = preparedStatement.executeUpdate();
        return status;

    }
    public int getMallGrp() throws SQLException {
        int column =0;
        ConnectionToDb connObject = new ConnectionToDb();
        Connection connection = connObject.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(getGroupMallById);
        preparedStatement.setInt(1,4444);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            column=rs.getInt(1);
        }
        return column;

    }
    public int deleteMallGrp() throws SQLException {
        int status =0;
        ConnectionToDb connObject = new ConnectionToDb();
        Connection connection = connObject.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(deleteMallGrp);
        status = preparedStatement.executeUpdate();
        return status;

    }
}
