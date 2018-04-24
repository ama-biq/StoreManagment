package tableDefinition;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class MallDefinition {

    private static final String GET_ALL_MALLS = "SELECT * from mall";

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
