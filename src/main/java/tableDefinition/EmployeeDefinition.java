package tableDefinition;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeDefinition {

    private int employeeId;
    private int shopId;
    private int chainId;
    private static final String GET_EMPLOYEE_BY_ID = "SELECT * from employee WHERE Employee_Id = ?";
    private static final String DELETE_EMPLOYEE_BY_ID = "delete from employee where Employee_Id=?";
    private static final String INSERT_EMPLOYEE_TO_TABLE = "insert into employee(Employee_Id, Shop_Id, Chain_Id) VALUES(?,?,?)";
    private static final String GET_ALL_EMPLOYEES = "select * from employee";
    private static final String GET_EMPLOYEE_BY_CHAIN_ID = "SELECT * from employee WHERE Chain_Id = ?";


    EmployeeDefinition(int employeeId, int shopId, int chainId) {
        this.employeeId = employeeId;
        this.shopId = shopId;
        this.chainId = chainId;
    }

    EmployeeDefinition() {
    }

    int createEmployee(Connection connection) throws SQLException {
        int status = 0;
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_TO_TABLE);
        preparedStatement.setInt(1, employeeId);
        preparedStatement.setInt(2, shopId);
        preparedStatement.setInt(3, chainId);
        status = preparedStatement.executeUpdate();

        return status;
    }

    public int deleteSpecificEmployee(Connection connection, int specificEmployee) throws SQLException {
        int status = 0;
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_BY_ID);
        preparedStatement.setInt(1, specificEmployee);
        status = preparedStatement.executeUpdate();
        return status;
    }

    int getSpecificEmployee(Connection connection, int specificEmployee) throws SQLException {
        int column = 0;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEE_BY_ID);
        preparedStatement.setInt(1, specificEmployee);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            column = rs.getInt(1);
        }
        return column;
    }

    List<Integer> getAllEmployeeInChain(Connection connection, int specificChain) throws SQLException {
        List<Integer> columnArrayList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEE_BY_CHAIN_ID);
        preparedStatement.setInt(1, specificChain);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int i = 1;
            //in while loop we run on the first column and insert its value to arraylist
            //the values are the employee id
            // i+3 - because there are 3 columns in table
            while (i <= numberOfColumns(rs)) {
                columnArrayList.add(rs.getInt(1));
                i = i + 3;
            }
        }
        return columnArrayList;
    }

    Set<Integer> getExistedEmployees(Connection connection) throws SQLException {
        Set<Integer> employeesList = new HashSet<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EMPLOYEES);
        ResultSet rs = preparedStatement.executeQuery();
        ResultSet rsCopy = rs;
        while (rs.next()) {
            int i = 1;
            while (i <= numberOfColumns(rsCopy)) {
                employeesList.add(rs.getInt(1));
                i = i + 3;
            }
        }
        return employeesList;
    }


    int numberOfColumns(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        return metaData.getColumnCount();
    }
}
