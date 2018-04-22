package tableDefinition;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChainDefinition {

    private int chainId;
    private String category;
    private ConnectionToDb connObject = new ConnectionToDb();

    private static final String getChainById = "SELECT * from chain WHERE Chain_Id = ?";
    private static final String deleteChainById = "delete from chain where Chain_Id=?";
    private static final String insertChainToTable = "insert into chain(Chain_Id, Chain_Category) VALUES(?,?)";
    private static final String getAllChains = "SELECT * from chain";


    public ChainDefinition() {
    }

    public ChainDefinition(int chainId, String category) {
        this.chainId = chainId;
        this.category = category;
    }


    public int createChain(Connection connection) throws SQLException {
        int status =0;
        try{
        PreparedStatement preparedStatement = connection.prepareStatement(insertChainToTable);
        preparedStatement.setInt(1, chainId);
        preparedStatement.setString(2, category);
        status = preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return status;

    }
    public int getSpecificChain(int specificChain) throws SQLException {
        int column =0;
        try{
        Connection connection = connObject.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(getChainById);
        preparedStatement.setInt(1, specificChain);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                column = rs.getInt(1);
            }
        }catch (SQLException e){
            System.out.print(e.getMessage());
             }
        return column;

    }
    public int deleteSpecificChain(int specificChain) throws SQLException {
        int status =0;
        try{
        ConnectionToDb connObject = new ConnectionToDb();
        Connection connection = connObject.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(deleteChainById);
        preparedStatement.setInt(1,specificChain);
        status = preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.print(e.getMessage());

        }
        return status;

    }

    public List<Integer> getExistedChains(Connection connection) {
        List <Integer>chainsList = new ArrayList<>();
        try{
                PreparedStatement preparedStatement = connection.prepareStatement(getAllChains);
                ResultSet rs = preparedStatement.executeQuery();
            ResultSet rsCopy = rs;
            while(rs.next()) {
                int i = 1;
                //in while loop we run on the first column and insert its value to arraylist
                //the values are the employee id
                // i+3 - because there are 3 columns in table
                while (i <= numberOfColumns(rsCopy)){
                    chainsList.add(rs.getInt(1));
                    i=i+3;
                }
            }
            }catch (SQLException e){
                System.out.print(e.getMessage());
            }
            return chainsList;
    }

    private int numberOfColumns(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        return metaData.getColumnCount();
    }
}

