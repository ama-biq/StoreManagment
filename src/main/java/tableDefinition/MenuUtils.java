package tableDefinition;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class MenuUtils {

     void presentAllShopsInMallGroup(Connection connection) throws SQLException {
        GroupMallDefinition groupMallDefinition = new GroupMallDefinition();
        ShopDefinition shopDefinition = new ShopDefinition();
        Set<Integer>setMallGroups = groupMallDefinition.getAllMallGrp(connection);


         System.out.println("Insert available Mall Group Id, available Id's are: " + setMallGroups.toString());
        Scanner scShop = new Scanner(System.in);
        int mallGrp = scShop.nextInt();
        Set<Integer>shopSet =  shopDefinition.getAllShopsInSpecificMallGroup(mallGrp);
        if(!shopSet.isEmpty()){
            System.out.println("The shops that belongs to specific Mall Group is: " + shopSet.toString());
        }else {
            System.out.println("No shops found on specific Mall Group.");
        }
         System.out.println("-------------------------------------------------------------");



    }

     void presentAllShopsInMall(Connection connection) throws SQLException {
        // Present all shops that are in the certain Shopping mall.
        ShopDefinition shopDefinition = new ShopDefinition();
        Scanner sc = new Scanner(System.in);
        MallDefinition mallDefinition = new MallDefinition();
        Set<Integer> mallList = mallDefinition.getExistedMall(connection);
        System.out.println("Insert available mall Id, available Id's are: "+mallList.toString());
        int mallId = sc.nextInt();
        List<Integer> shopActualList = shopDefinition.getAllShopInCertainMall(mallId, connection);
        if (!shopActualList.isEmpty()) {
            System.out.println("The shop id's in Shopping mall "+mallId+" are: " + printShopIds(shopActualList));
        } else {
            System.out.println("Not found ID's of Shops");
        }
         System.out.println("--------------------------------------------------");
    }

     void presentAllEmployeesInChain(Connection connection) throws SQLException {
        // Get all employees of a certain chain.
        ChainDefinition chainDefinition = new ChainDefinition();
        Set<Integer> chainsList = chainDefinition.getExistedChains(connection);
        System.out.println("Insert available chain Id, available Id's are: "+chainsList.toString());
        Scanner sc = new Scanner(System.in);
        int chainId = sc.nextInt();
        EmployeeDefinition employeeDefinition = new EmployeeDefinition();
        List<Integer> emplList = employeeDefinition.getAllEmployeeInChain(chainId);
        if (!emplList.isEmpty()) {
            System.out.println("The ID's of Employees that works in Chain is: " + printEmployeeId(emplList));
        } else {
            System.out.println("Not found ID's of Employees in chain");
        }
         System.out.println("--------------------------------------------------");
    }

     void addNewEmployee(Connection connection) throws SQLException {
        // Create new employee.
        Scanner scEmployee = new Scanner(System.in);
        EmployeeDefinition employeeDefinition = new EmployeeDefinition();
        Set<Integer> employeessList = employeeDefinition.getExistedEmployees(connection);
        System.out.println("Insert new employee ID, existing employees are: " + employeessList.toString());
        int employeeId=scEmployee.nextInt();

        ShopDefinition shopDefinition = new ShopDefinition();
        Set<Integer> shopsList = shopDefinition.getExistedShops(connection);
        System.out.println("Insert shop id from available list, available shops are : "+shopsList.toString());
        int shopIdForNewEmployee = scEmployee.nextInt();

        ChainDefinition chainDefinition = new ChainDefinition();
        Set<Integer> chainsList = chainDefinition.getExistedChains(connection);
        System.out.println("Insert chain id from available list, available chains are : "+chainsList.toString());
        int chainIdForNewEmployee = scEmployee.nextInt();

        EmployeeDefinition employee = new EmployeeDefinition(employeeId,shopIdForNewEmployee, chainIdForNewEmployee);
        if(employee.createEmployee(connection)==1) {
            System.out.println("The new employee has been created with ID: "+employee.getSpecificEmployee(employeeId));
        }else {
            System.out.println("Employee already exists.");
            }
         System.out.println("--------------------------------------------------");
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

        if(answer.equals("yes")) {

            Set<Integer> mallList = mallDefinition.getExistedMall(connection);
            System.out.println("Insert Mall id from available list, available malls are: " + mallList.toString());
            int mallId = scShop.nextInt();

            System.out.println("Insert Shop id in mall (should be int): ");
            int mallShopId = scShop.nextInt();

            shopDefinition = new ShopDefinition(shopId, chainIdForShop, mallId, mallShopId);
        }else {
            System.out.println("Insert address (should be String): ");
            String address = scShop.next();
            shopDefinition = new ShopDefinition(shopId, chainIdForShop,address);
        }
        if(shopDefinition.createShop(connection)==1) {
            System.out.println("The Shop has been created with ID: "+shopDefinition.getSpecificShop(shopId));
        }else {
            System.out.println("The shop already exists.");
        }
         System.out.println("--------------------------------------------------");
    }

     void addNewChain(Connection connection) throws SQLException {
        // Create new chain.
        Scanner scChain = new Scanner(System.in);
        ChainDefinition chainDefinition = new ChainDefinition();
        Set<Integer> chainsList = chainDefinition.getExistedChains(connection);
        System.out.println("Insert new chain id, existing chains are : "+chainsList.toString());
        int chainId=scChain.nextInt();

        System.out.println("Insert chain category (type string): ");
        String chainCategory = scChain.next();

        chainDefinition = new ChainDefinition(chainId, chainCategory);
        try {
            if (chainDefinition.createChain(connection) == 1) {
                System.out.println("The new chain has been created with ID: " + chainDefinition.getSpecificChain(chainId));
            } else {
                System.out.println("The chain already exists.");
            }
            System.out.println("--------------------------------------------------");
        }catch (MySQLIntegrityConstraintViolationException e){
            System.out.println("Chain id " + chainId + " already exists.");
        }
    }

     int getChoice() {
        System.out.println("You have number of choices to perform on DB.");
        System.out.println("Pleas insert your choice accordignly to the list:");
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

     String printShopIds(List<Integer> shopActualList) {
        StringBuilder builder = new StringBuilder();
        for (Integer employee : shopActualList){
            builder.append(employee);
            builder.append(' ');
        }
        String list = builder.toString();
        return list;
    }

     String printEmployeeId(List<Integer> emplList) {
        StringBuilder builder = new StringBuilder();
        for (Integer employee : emplList){
            builder.append(employee);
            builder.append(' ');
        }
        String list = builder.toString();
        return list;
    }


     void presentAllDetailsOfShop(Connection connection) throws SQLException {
         ShopDefinition shopDefinition = new ShopDefinition();
         Set<Integer> shopSet = shopDefinition.getExistedShops(connection);
         Scanner scShop = new Scanner(System.in);
         System.out.println("Insert desired store id from list, available stores are : " + shopSet.toString());
         int shopId = scShop.nextInt();
         ShopDefinition shopDetails = shopDefinition.presentAllDetailsOfAShop(shopId, connection);
         System.out.println(shopDetails.toString());

     }
}
