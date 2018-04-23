package tableDefinition;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ChainDefinition {

    private int chainId;
    private String category;
    private ConnectionToDb connObject = new ConnectionToDb();

    private static final String GET_CHAIN_BY_ID = "SELECT * from chain WHERE Chain_Id = ?";
    private static final String DELETE_CHAIN_BY_ID = "delete from chain where Chain_Id=?";
    private static final String INSERT_CHAIN_TO_TABLE = "insert into chain(Chain_Id, Chain_Category) VALUES(?,?)";
    private static final String GET_ALL_CHAINS = "SELECT * from chain";


    ChainDefinition() {
    }

    ChainDefinition(int chainId, String category) {
        this.chainId = chainId;
        this.category = category;
    }

    int createChain(Connection connection) throws SQLException {
        int status;
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CHAIN_TO_TABLE);
        preparedStatement.setInt(1, chainId);
        preparedStatement.setString(2, category);
        status = preparedStatement.executeUpdate();
        return status;
    }

    int getSpecificChain(Connection connection, int specificChain) throws SQLException {
        int column = 0;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_CHAIN_BY_ID);
        preparedStatement.setInt(1, specificChain);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            column = rs.getInt(1);
        }
        return column;
    }

    int deleteSpecificChain(Connection connection, int specificChain) throws SQLException {
        int status;
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CHAIN_BY_ID);
        preparedStatement.setInt(1, specificChain);
        status = preparedStatement.executeUpdate();
        return status;
    }

    Set<Integer> getExistedChains(Connection connection) throws SQLException {
        Set<Integer> chainsList = new HashSet<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CHAINS);
        ResultSet rs = preparedStatement.executeQuery();
        ResultSet rsCopy = rs;
        while (rs.next()) {
            int i = 1;
            //in while loop we run on the first column and insert its value to arraylist
            //the values are the employee id
            // i+3 - because there are 3 columns in table
            while (i <= numberOfColumns(rsCopy)) {
                chainsList.add(rs.getInt(1));
                i = i + 3;
            }
        }
        return chainsList;
    }

    private int numberOfColumns(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        return metaData.getColumnCount();
    }
}

