package tableDefinition;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class MenuUtils {

    void presentAllShopsInMallGroup(Connection connection) {
        GroupMallDefinition groupMallDefinition = new GroupMallDefinition();
        ShopDefinition shopDefinition = new ShopDefinition();
    }

    void presentAllShopsInMall(Connection connection) throws SQLException {
        // Present all shops that are in the certain Shopping mall.
        ShopDefinition shopDefinition = new ShopDefinition();
        Scanner sc = new Scanner(System.in);
        MallDefinition mallDefinition = new MallDefinition();
        Set<Integer> mallList = mallDefinition.getExistedMall(connection);
        System.out.println("Insert available mall Id, available Id's are: " + mallList.toString());
        int mallId = sc.nextInt();
        List<Integer> shopActualList = shopDefinition.getAllShopInCertainMall(mallId, connection);
        if (!shopActualList.isEmpty()) {
            System.out.println("The shop id's in Shopping mall " + mallId + " are: " + printIntegerList(shopActualList));
        } else {
            System.out.println("Not found shops in Shopping Mall: " + mallId);
        }
    }

    void presentAllEmployeesInChain(Connection connection) throws SQLException {
        // Get all employees of a certain chain.
        ChainDefinition chainDefinition = new ChainDefinition();
        Set<Integer> chainsList = chainDefinition.getExistedChains(connection);
        System.out.println("Insert available chain Id, available Id's are: " + chainsList.toString());
        Scanner sc = new Scanner(System.in);
        int chainId = sc.nextInt();
        EmployeeDefinition employeeDefinition = new EmployeeDefinition();
        List<Integer> emplList = employeeDefinition.getAllEmployeeInChain(connection, chainId);
        if (!emplList.isEmpty()) {
            System.out.println("The ID's of Employees that works in Chain is: " + printIntegerList(emplList));
        } else {
            System.out.println("Not found employees in chain: " + chainId);
        }
    }

    void addNewEmployee(Connection connection) throws SQLException {
        // Create new employee.
        Scanner scEmployee = new Scanner(System.in);
        EmployeeDefinition employeeDefinition = new EmployeeDefinition();
        Set<Integer> employeessList = employeeDefinition.getExistedEmployees(connection);
        System.out.println("Insert new employee ID, existing employees are: " + employeessList.toString());
        int employeeId = scEmployee.nextInt();

        ShopDefinition shopDefinition = new ShopDefinition();
        Set<Integer> shopsList = shopDefinition.getExistedShops(connection);
        System.out.println("Insert shop id from available list, available shops are : " + shopsList.toString());
        int shopIdForNewEmployee = scEmployee.nextInt();

        ChainDefinition chainDefinition = new ChainDefinition();
        Set<Integer> chainsList = chainDefinition.getExistedChains(connection);
        System.out.println("Insert chain id from available list, available chains are : " + chainsList.toString());
        int chainIdForNewEmployee = scEmployee.nextInt();

//todo to check if possible to use one employee
        EmployeeDefinition employee = new EmployeeDefinition(employeeId, shopIdForNewEmployee, chainIdForNewEmployee);
        try {
            System.out.println("The ID of created Employee is: " + employee.getSpecificEmployee(connection, employeeId));
        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println("Employee with id: " + employeeId + " already exists.");
        }
    }

    void addNewShop(Connection connection) throws SQLException {
        //Create new shop
        ChainDefinition chainDefinition = new ChainDefinition();
        MallDefinition mallDefinition = new MallDefinition();
        ShopDefinition shopDefinition = new ShopDefinition();
        Set<Integer> storesList = shopDefinition.getExistedShops(connection);
        Scanner scShop = new Scanner(System.in);
        System.out.println("Is shop belong to mall? (yes / no )");
        String answer = scShop.next();
        System.out.println("Insert new store id, existing stores are : " + storesList.toString());
        int shopId = scShop.nextInt();

        Set<Integer> chainsList = chainDefinition.getExistedChains(connection);
        System.out.println("Insert chain id from available list, available chains are : " + chainsList.toString());
        int chainIdForShop = scShop.nextInt();

        if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
            Set<Integer> mallList = mallDefinition.getExistedMall(connection);
            System.out.println("Insert Mall id from available list, available malls are: " + mallList.toString());
            int mallId = scShop.nextInt();
            System.out.println("Insert Shop id in mall (should be int): ");
            int mallShopId = scShop.nextInt();
            //todo to check if possible to use one employee
            shopDefinition = new ShopDefinition(shopId, chainIdForShop, mallId, mallShopId);
        } else if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
            System.out.println("Insert address (should be String): ");
            String address = scShop.next();
            shopDefinition = new ShopDefinition(shopId, chainIdForShop, address);
        }
        try {
            System.out.println("The ID of created Shop is: " + shopDefinition.getSpecificShop(connection, shopId));
        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println("Shop with id: " + shopId + " already exists.");
        }
    }

    void addNewChain(Connection connection) throws SQLException {
        // Create new chain.
        Scanner scChain = new Scanner(System.in);
        ChainDefinition chainDefinition = new ChainDefinition();
        Set<Integer> chainsList = chainDefinition.getExistedChains(connection);
        System.out.println("Insert new chain id, existing chains are : " + chainsList.toString());
        int chainId = scChain.nextInt();

        System.out.println("Insert chain category (type string): ");
        String chainCategory = scChain.next();

        chainDefinition = new ChainDefinition(chainId, chainCategory);
        try {
            System.out.println("Chain created with id: " + chainDefinition.getSpecificChain(connection, chainId));
        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println("Chain with id: " + chainId + " already exists.");
        }
    }

    int getChoice() {
        System.out.println("You have number of choices to perform on DB.");
        System.out.println("Please insert your choice accordingly to the list:");
        System.out.println("1. Create a new Chain.");
        System.out.println("2. Add a store to a Chain.");
        System.out.println("3. Add Employee to Chain.");
        System.out.println("4. Present all shops that are in a certain Shopping Mall.");
        System.out.println("5. Present all shops that are in a certain Shopping Mall Group.");
        System.out.println("6. Present all Employees of a certain Chain.");
        System.out.println("7. Present all details of a Shop.");
        System.out.println("8. Exit the menu.");
        Scanner scInit = new Scanner(System.in);
        return scInit.nextInt();
    }

    private String printIntegerList(List<Integer> shopActualList) {
        StringBuilder builder = new StringBuilder();
        for (Integer employee : shopActualList) {
            builder.append(employee);
            builder.append(' ');
        }
        return builder.toString();
    }

}
