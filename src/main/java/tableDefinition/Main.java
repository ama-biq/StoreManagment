package tableDefinition;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.swing.text.DefaultEditorKit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {


    public static void main(String[] args) throws SQLException {
        ConnectionToDb connObject = null;
        Connection connection = null;
        try {
            connObject = new ConnectionToDb();
            connection = connObject.getConnection();
            int choice;
            do {
                choice = getChoice();
                if (choice == 99) {
                    break;
                } else {
                    switch (choice) {
                        case 1:
                            addNewChain(connection);
                            break;
                        case 2:
                            addNewShop(connection);
                            break;
                        case 3:
                            addNewEmployee(connection);
                            break;
                        case 4:
                            presentAllShopsInMall(connection);
                            break;
                        case 5:
                           // presentAllShopsInMallGroup(connection);
                            break;
                        case 6:
                            presentAllEmployeesInChain(connection);
                            break;
                        case 7:
                            //Present all details of a Shop
                            break;
                        case 8:
                            return;
                        default:
                            System.out.println("Wrong choice, please choose again");

                    }
                }

            } while (
            choice != 99);





        }finally {
            connObject.close(connection);
        }
    }

    private static void presentAllShopsInMall(Connection connection) throws SQLException {
        // Present all shops that are in the certain Shoping mall.
        ShopDefinition shopDefinition = new ShopDefinition();
        Scanner sc = new Scanner(System.in);
        MallDefinition mallDefinition = new MallDefinition();
        List<Integer>mallList = mallDefinition.getExistedMall(connection);
        System.out.println("Insert available mall Id, available Id's are: "+mallList.toString());
        int mallId = sc.nextInt();
        List<Integer> shopActualList = shopDefinition.getAllShopInCertainMall(mallId, connection);
        if (!shopActualList.isEmpty()) {
            System.out.println("The shop id's in Shoping mall "+mallId+" are: " + printShopIds(shopActualList));
        } else {
            System.out.println("Not found ID's of Shops");
        }
    }


    private static void presentAllEmployeesInChain(Connection connection) throws SQLException {
        // Get all employees of a certain chain.
       // createThreeEmployees(connection);
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
    }

    private static void addNewEmployee(Connection connection) throws SQLException {
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
        System.out.println("Insert chain id, available chains are : "+chainsList.toString());
        int chainIdForNewEmployee = scEmployee.nextInt();

//todo to check if possible to use one employee
        EmployeeDefinition employee = new EmployeeDefinition(employeeId,shopIdForNewEmployee, chainIdForNewEmployee);
        if(employee.createEmployee(connection)==1) {
            System.out.println("The ID of created Employee is: "+employee.getSpecificEmployee(employeeId));
        }else {
            System.out.println("Employee already exists.");
        }

    }

    private static void addNewShop(Connection connection) throws SQLException {
        //Create new shop
        ChainDefinition chainDefinition = new ChainDefinition();
        MallDefinition mallDefinition = new MallDefinition();
        ShopDefinition shopDefinition = new ShopDefinition();
        Set<Integer> storesList = chainDefinition.getExistedChains(connection);
        Scanner scShop = new Scanner(System.in);
        System.out.println("Insert new store id, existing stores are : "+storesList.toString());
        int shopId = scShop.nextInt();

        Set<Integer> chainsList = chainDefinition.getExistedChains(connection);
        System.out.println("Insert chain id from available list, available chains are : "+chainsList.toString());
        int chainIdForShop= scShop.nextInt();

        List<Integer> mallList= mallDefinition.getExistedMall(connection);
        System.out.println("Insert Mall id from available list, available malls are: " + mallList.toString());
        int mallId = scShop.nextInt();

        System.out.println("Insert Shop id in mall (should be int): ");
        int mallShopId = scShop.nextInt();

        System.out.println("Insert address (should be String): ");
        String address = scShop.next();

        //todo to check if possible to use one employee

        shopDefinition = new ShopDefinition(shopId, chainIdForShop, mallId, mallShopId, address);
        if(shopDefinition.createShop(connection)==1) {
            System.out.println("The ID of created Shop is: "+shopDefinition.getSpecificShop(shopId));
        }else {
            System.out.println("The shop already exists.");
        }
    }

    private static void addNewChain(Connection connection) throws SQLException {
        // Create new chain.
        Scanner scChain = new Scanner(System.in);
        ChainDefinition chainDefinition = new ChainDefinition();
        Set<Integer> chainsList = chainDefinition.getExistedChains(connection);
        System.out.println("Insert new chain id, existing chains are : "+chainsList.toString());
        int chainId=scChain.nextInt();
        System.out.println("Insert chain category (type string): ");
        String chainCategory = scChain.next();

        //todo to check if possible to use one ChainDefinition
         chainDefinition = new ChainDefinition(chainId, chainCategory);
        if(chainDefinition.createChain(connection)==1) {
            System.out.println("Chain created with id: "+chainDefinition.getSpecificChain(chainId));
        }else {
            System.out.println("The chain already exists.");
        }
    }

    private static int getChoice() {
        System.out.println("You have number of choices to perform on DB.");
        System.out.println("Pleas insert your choice accordignly to the list:");
        System.out.println("1. Create a new Chain.");
        System.out.println("2. Add a store to a Chain.");
        System.out.println("3. Add Employee to Chain .");
        System.out.println("4. Present all shops that are in a certain Shopping Mall.");
        System.out.println("5. Present all shops that are in a certain Shopping Mall Group.");
        System.out.println("6. Present all Employees of a certain Chain.");
        System.out.println("7. Present all details of a Shop.");
        System.out.println("8. Exit the menu.");
        Scanner scInit = new Scanner(System.in);
        return scInit.nextInt();
    }

    private static String printShopIds(List<Integer> shopActualList) {
        StringBuilder builder = new StringBuilder();
        for (Integer employee : shopActualList){
            builder.append(employee);
            builder.append(' ');
        }
        String list = builder.toString();
        return list;
    }

    private static void createThreeShopsOnlyTwoInTheSameMall(Connection connection) throws SQLException {
        MallDefinition mallDefinition = new MallDefinition(777,"See mall", "Haifa",111111);
        mallDefinition.createMall();
        ShopDefinition emptyDefinition = new ShopDefinition();
        emptyDefinition.deleteSpecificShop(2);
        emptyDefinition.deleteSpecificShop(2323);
        emptyDefinition.deleteSpecificShop(3232);
        ShopDefinition definition = new ShopDefinition(2, 21, 777, 77, "Haifa");
        ShopDefinition definition1 = new ShopDefinition(2323, 1, 777, 25, "Haifa");
        ShopDefinition definition2 = new ShopDefinition(3232, 21, 222, 25, "Ramle");
        definition.createShop(connection);
        definition1.createShop(connection);
        definition2.createShop(connection);
    }

    private static String printEmployeeId(List<Integer> emplList) {
        StringBuilder builder = new StringBuilder();
        for (Integer employee : emplList){
            builder.append(employee);
            builder.append(' ');
        }
        String list = builder.toString();
        return list;
    }

    public static void createThreeEmployees(Connection connection) throws SQLException {


    }
    private static void cleanDbForMain(int chainId, int shopId, int employeeId) throws SQLException {
        ChainDefinition chainDefinition = new ChainDefinition();
        ShopDefinition shopDefinition= new ShopDefinition();
        EmployeeDefinition employeeDefinition = new EmployeeDefinition();
        employeeDefinition.deleteSpecificEmployee(employeeId);
        shopDefinition.deleteSpecificShop(shopId);
        chainDefinition.deleteSpecificChain(chainId);

    }
}
