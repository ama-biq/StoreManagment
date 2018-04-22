package tableDefinition;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.swing.text.DefaultEditorKit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws SQLException {

// Create new chain.
        Scanner scChain = new Scanner(System.in);
        System.out.println("Insert Chain id (should be int): ");
        int chainId=scChain.nextInt();
        System.out.println("Insert chain category (type string): ");
        String chainCategory = scChain.next();
        ChainDefinition chainDefinition = new ChainDefinition(chainId, chainCategory);
        if(chainDefinition.createChain()==1) {
            System.out.println("The ID of created Chain is: "+chainDefinition.getSpecificChain(chainId));
        }else {
            System.out.println("The chain already exists.");
        }

        //Create new shop
        Scanner scShop = new Scanner(System.in);
        System.out.println("Insert shop id (should be int): ");
        int shopId = scShop.nextInt();
        System.out.println("Insert address (should be string): ");
        String address = scChain.next();
        System.out.println("Insert chain id (should be int): ");
        int chainIdForShop= scShop.nextInt();
        System.out.println("Insert Mall id (should be int): ");
        int mallId = scShop.nextInt();
        System.out.println("Insert Shop id in mall (should be int): ");
        int mallShopId = scShop.nextInt();
        ShopDefinition shopDefinition = new ShopDefinition(shopId, chainIdForShop, mallId, mallShopId, address);
        if(shopDefinition.createShop()==1) {
            System.out.println("The ID of created Shop is: "+shopDefinition.getSpecificShop(shopId));
        }else {
            System.out.println("The shop already exists.");
        }
// Create new employee.
        Scanner scEmployee = new Scanner(System.in);
        System.out.println("Insert new employee ID (should be int): ");
        int employeeId=scEmployee.nextInt();
        System.out.println("Insert new employee Shop id (should be int): ");
        int shopIdForNewEmployee = scEmployee.nextInt();
        System.out.println("Insert ChainId for new employee (should be int): ");
        int chainIdForNewEmployee = scEmployee.nextInt();
        EmployeeDefinition employeeDefinition = new EmployeeDefinition(employeeId,shopIdForNewEmployee, chainIdForNewEmployee);
        if(employeeDefinition.createEmployee()==1) {
            System.out.println("The ID of created Employee is: "+employeeDefinition.getSpecificEmployee(employeeId));
        }else {
            System.out.println("Employee already exists.");
        }

// Get alla employees of a certain chain.
        createThreeEmployees();
        List <Integer>emplList= employeeDefinition.getAllEmployeeInChain(21);
        List<Integer> expectedEmplList = new ArrayList<>();
        expectedEmplList.add(10);
        expectedEmplList.add(11);
        expectedEmplList.add(12);
        if(emplList.equals(expectedEmplList)){
            System.out.println("The ID's of Employees that works in Chain is: "+printEmployeeId(emplList));
        }else {
            System.out.println("Not found ID's of Employees");
        }

// Present all shops that are in the certain Shoping mall.
        createThreeShopsOnlyTwoInTheSameMall();
        List <Integer>shopActualList= shopDefinition.getAllShopInCertainMall(777);
        List<Integer> shopExpectedList = new ArrayList<>();
        shopExpectedList.add(2);
        shopExpectedList.add(2323);
        if(shopActualList.equals(shopExpectedList)){
            System.out.println("The shop id's in Shoping mall 777 are: "+printShopIds(shopActualList));
        }else {
            System.out.println("Not found ID's of Shops");
        }


       cleanDbForMain(chainId, shopId, employeeId);
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

    private static void createThreeShopsOnlyTwoInTheSameMall() throws SQLException {
        MallDefinition mallDefinition = new MallDefinition(777,"See mall", "Haifa",111111);
        mallDefinition.createMall();
        ShopDefinition emptyDefinition = new ShopDefinition();
        emptyDefinition.deleteSpecificShop(2);
        emptyDefinition.deleteSpecificShop(2323);
        emptyDefinition.deleteSpecificShop(3232);
        ShopDefinition definition = new ShopDefinition(2, 21, 777, 77, "Haifa");
        ShopDefinition definition1 = new ShopDefinition(2323, 1, 777, 25, "Haifa");
        ShopDefinition definition2 = new ShopDefinition(3232, 21, 222, 25, "Ramle");
        definition.createShop();
        definition1.createShop();
        definition2.createShop();
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

    public static void createThreeEmployees() throws SQLException {
        EmployeeDefinition definitionEmployee = new EmployeeDefinition(10,2, 21);
        definitionEmployee.createEmployee();
        EmployeeDefinition definitionEmployee1 = new EmployeeDefinition(11,2, 21);
        definitionEmployee1.createEmployee();
        EmployeeDefinition definitionEmployee2 = new EmployeeDefinition(12,2, 21);
        definitionEmployee2.createEmployee();
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
