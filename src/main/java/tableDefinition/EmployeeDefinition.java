package tableDefinition;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDefinition {

    private int employeeId;
    private int shopId;
    private int chainId;
    private static final String getEmployeeById = "SELECT * from employee WHERE Employee_Id = ?";
    private static final String deleteEmployeeById = "delete from employee where Employee_Id=?";
    private static final String insertEmployeeToTable = "insert into employee(Employee_Id, Shop_Id, Chain_Id) VALUES(?,?,?)";


    public EmployeeDefinition(int employeeId, int shopId, int chainId) {
        this.employeeId = employeeId;
        this.shopId = shopId;
        this.chainId = chainId;
    }

    public EmployeeDefinition() {

    }


    public int createEmployee(Connection connection) throws SQLException {
        int status =0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertEmployeeToTable);
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setInt(2, shopId);
            preparedStatement.setInt(3, chainId);
            status = preparedStatement.executeUpdate();
        }catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return status;
    }

    public int deleteSpecificEmployee(int specificEmployee) throws SQLException {
        int status = 0;
        try {
            ConnectionToDb connObject = new ConnectionToDb();
            Connection connection = connObject.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteEmployeeById);
            preparedStatement.setInt(1, specificEmployee);
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return status;

    }

    public int getSpecificEmployee(int specificEmployee) throws SQLException {
        int column = 0;
        try {
            ConnectionToDb connObject = new ConnectionToDb();
            Connection connection = connObject.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getEmployeeById);
            preparedStatement.setInt(1, specificEmployee);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                column = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return column;

    }

    public List<Integer> getAllEmployeeInChain(int specificChain) throws SQLException {
        List <Integer>columnArrayList = new ArrayList<>();
        try {
            ConnectionToDb connObject = new ConnectionToDb();
            Connection connection = connObject.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from employee WHERE Chain_Id = ?");
            preparedStatement.setInt(1, specificChain);
            ResultSet rs = preparedStatement.executeQuery();
            ResultSet rsCopy = rs;
            while(rs.next()) {
                int i = 1;
                //in while loop we run on the first column and insert its value to arraylist
                //the values are the employee id
                // i+3 - because there are 3 columns in table
                while (i <= numberOfColumns(rsCopy)){
                    columnArrayList.add(rs.getInt(1));
                    i=i+3;
                }
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return columnArrayList;

    }

    public int numberOfColumns(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        return metaData.getColumnCount();
    }
}
