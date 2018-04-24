package tableDefinition;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class GroupMallDefinition {

    private static final String GET_ALL_GROUP_MALL = "select * from mall_group";

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

    private int numberOfColumns(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        return metaData.getColumnCount();
    }

}
