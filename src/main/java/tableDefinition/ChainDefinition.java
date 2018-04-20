package tableDefinition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChainDefinition {

    private int chainId;
    private String category;

    public ChainDefinition() {
    }

    public ChainDefinition(int chainId, String category) {
        this.chainId = chainId;
        this.category = category;
    }

    public int getChainId() {
        return chainId;
    }

    public void setChainId(int chainId) {
        this.chainId = chainId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int createChain() throws SQLException {
        int status =0;
        ConnectionToDb connObject = new ConnectionToDb();
        Connection connection = connObject.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into chain(Chain_Id, Chain_Category) VALUES(?,?)");
        preparedStatement.setInt(1, chainId);
        preparedStatement.setString(2, category);
        status = preparedStatement.executeUpdate();
        return status;

    }
    public int getSpecificChain(int specificChain) throws SQLException {
        int column =0;
        ConnectionToDb connObject = new ConnectionToDb();
        Connection connection = connObject.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from chain WHERE Chain_Id = ?");
        preparedStatement.setInt(1, specificChain);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            column=rs.getInt(1);
        }
        return column;

    }
    public int deleteSpecificChain(int specificChain) throws SQLException {
        int status =0;
        ConnectionToDb connObject = new ConnectionToDb();
        Connection connection = connObject.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from chain where Chain_Id=?");
        preparedStatement.setInt(1,specificChain);
        status = preparedStatement.executeUpdate();
        return status;

    }
}
