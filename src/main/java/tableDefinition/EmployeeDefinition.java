package tableDefinition;

import java.lang.ref.PhantomReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDefinition {

    private int employeeId;
    private int shopId;
    private int chainId;

    public EmployeeDefinition(int employeeId, int shopId, int chainId) {
        this.employeeId = employeeId;
        this.shopId = shopId;
        this.chainId = chainId;
    }

    public EmployeeDefinition() {

    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getChainId() {
        return chainId;
    }

    public void setChainId(int chainId) {
        this.chainId = chainId;
    }

    public int createEmployee() throws SQLException {
        int status =0;
        try {
            ConnectionToDb connObject = new ConnectionToDb();
            Connection connection = connObject.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into employee(Employee_Id, Shop_Id, Chain_Id) VALUES(?,?,?)");
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setInt(2, shopId);
            preparedStatement.setInt(3, chainId);
            status = preparedStatement.executeUpdate();
        }catch (Exception e) {//todo handle exception
        }
        return status;
    }

    public int deleteSpecificEmployee(int specificEmployee) throws SQLException {
        int status = 0;
        try {
            ConnectionToDb connObject = new ConnectionToDb();
            Connection connection = connObject.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from employee where Employee_Id=?");
            preparedStatement.setInt(1, specificEmployee);
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {//todo handle exception
        }
        return status;

    }

    public int getSpecificEmployee(int specificEmployee) throws SQLException {
        int column = 0;
        try {
            ConnectionToDb connObject = new ConnectionToDb();
            Connection connection = connObject.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from employee WHERE Employee_Id = ?");
            preparedStatement.setInt(1, specificEmployee);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                column = rs.getInt(1);
            }
        } catch (SQLException e) {//todo handle exception
        }
        return column;

    }
}
