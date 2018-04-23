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
                if (choice == -99) {
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
                        default:
                            System.out.println("Wrong choice, please choose again");


                    }
                }

            } while (choice != -99);


// Get all employees of a certain chain.
            createThreeEmployees(connection);
            EmployeeDefinition employeeDefinition = new EmployeeDefinition();
            List<Integer> emplList = employeeDefinition.getAllEmployeeInChain(21);
            List<Integer> expectedEmplList = new ArrayList<>();
            expectedEmplList.add(10);
            expectedEmplList.add(11);
            expectedEmplList.add(12);
            if (emplList.equals(expectedEmplList)) {
                System.out.println("The ID's of Employees that works in Chain is: " + printEmployeeId(emplList));
            } else {
                System.out.println("Not found ID's of Employees");
            }

// Present all shops that are in the certain Shoping mall.
            createThreeShopsOnlyTwoInTheSameMall(connection);
            ShopDefinition shopDefinition = new ShopDefinition();
            List<Integer> shopActualList = shopDefinition.getAllShopInCertainMall(777);
            List<Integer> shopExpectedList = new ArrayList<>();
            shopExpectedList.add(2);
            shopExpectedList.add(2323);
            if (shopActualList.equals(shopExpectedList)) {
                System.out.println("The shop id's in Shoping mall 777 are: " + printShopIds(shopActualList));
            } else {
                System.out.println("Not found ID's of Shops");
            }
            // cleanDbForMain(chainId, shopId, employeeId);

        }finally {
            connObject.close(connection);
        }
    }

    private static void addNewEmployee(Connection connection) throws SQLException {
        // Create new employee.
        Scanner scEmployee = new Scanner(System.in);
        System.out.println("Insert new employee ID (should be int): ");
        int employeeId=scEmployee.nextInt();
        System.out.println("Insert new employee Shop id (should be int): ");
        int shopIdForNewEmployee = scEmployee.nextInt();
        System.out.println("Insert ChainId for new employee (should be int): ");
        int chainIdForNewEmployee = scEmployee.nextInt();
        EmployeeDefinition employeeDefinition = new EmployeeDefinition(employeeId,shopIdForNewEmployee, chainIdForNewEmployee);
        if(employeeDefinition.createEmployee(connection)==1) {
            System.out.println("The ID of created Employee is: "+employeeDefinition.getSpecificEmployee(employeeId));
        }else {
            System.out.println("Employee already exists.");
        }

    }

    private static void addNewShop(Connection connection) throws SQLException {
        //Create new shop
        ChainDefinition chainDefinition = new ChainDefinition();
        MallDefinition mallDefinition = new MallDefinition();
        Scanner scShop = new Scanner(System.in);
        System.out.println("Insert shop id (should be int): ");
        int shopId = scShop.nextInt();
        System.out.println("Insert address (should be string): ");

        Set<Integer> chainsList = chainDefinition.getExistedChains(connection);
        System.out.println("Insert chain id, available chains are : "+chainsList.toString());
        int chainIdForShop= scShop.nextInt();

        Set<Integer> mallList= mallDefinition.getExistedMall(connection);
        System.out.println("Insert Mall id, available malls are: " + mallList.toString());
        int mallId = scShop.nextInt();
        System.out.println("Insert Shop id in mall (should be int): ");
        int mallShopId = scShop.nextInt();
        System.out.println("Insert address (should be String): ");
        String address = scShop.next();
        ShopDefinition shopDefinition = new ShopDefinition(shopId, chainIdForShop, mallId, mallShopId, address);
        if(shopDefinition.createShop(connection)==1) {
            System.out.println("The ID of created Shop is: "+shopDefinition.getSpecificShop(shopId));
        }else {
            System.out.println("The shop already exists.");
        }
    }

    private static void addNewChain(Connection connection) throws SQLException {
        // Create new chain.
        Scanner scChain = new Scanner(System.in);
        System.out.println("Insert Chain id (should be int): ");
        int chainId=scChain.nextInt();
        System.out.println("Insert chain category (type string): ");
        String chainCategory = scChain.next();
        ChainDefinition chainDefinition = new ChainDefinition(chainId, chainCategory);
        if(chainDefinition.createChain(connection)==1) {
            System.out.println("Chain created with id: "+chainDefinition.getSpecificChain(chainId));
        }else {
            System.out.println("The chain already exists.");
        }
    }

    private static int getChoice() {
        System.out.println("You have number of choices to perform on DB.");
        System.out.println("Pleas insert your choice accordignly to the list:");
        System.out.println("1. Add new chain.");
        System.out.println("2. Add new shop.");
        System.out.println("3. Add new employee.");
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
        EmployeeDefinition definitionEmployee = new EmployeeDefinition(10,2, 21);
        definitionEmployee.createEmployee(connection);
        EmployeeDefinition definitionEmployee1 = new EmployeeDefinition(11,2, 21);
        definitionEmployee1.createEmployee(connection);
        EmployeeDefinition definitionEmployee2 = new EmployeeDefinition(12,2, 21);
        definitionEmployee2.createEmployee(connection);
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
