package tableDefinition;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.swing.text.DefaultEditorKit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
   // private static ChainDefinition chainDefinition = new ChainDefinition(10011, "Phones");
//    private static  ShopDefinition shopDefinition = new ShopDefinition(211, 21, 111, 25, "Tel-Aviv");
  //  private static EmployeeDefinition employeeDefinition = new EmployeeDefinition(10,2, 21);




    public static void main(String[] args) throws SQLException {

// Create new chain.
        Scanner scChain = new Scanner(System.in);
        System.out.println("Insert Chain id: ");
        int chainId=scChain.nextInt();
        System.out.println("Insert chain category: ");
        String chainCategory = scChain.next();
        ChainDefinition chainDefinition = new ChainDefinition(chainId, chainCategory);
        if(chainDefinition.createChain()==1) {
            System.out.println("The ID of created Chain is: "+chainDefinition.getSpecificChain(chainId));
        }else {
            System.out.println("The chain already exists.");
        }

        //Create new shop
        Scanner scShop = new Scanner(System.in);
        System.out.println("Insert shop id: ");
        int shopId = scShop.nextInt();
        System.out.println("Insert address: ");
        String address = scChain.next();
        System.out.println("Insert chain id: ");
        int chainIdForShop= scShop.nextInt();
        System.out.println("Insert Mall id: ");
        int mallId = scShop.nextInt();
        System.out.println("Insert Shop id in mall: ");
        int mallShopId = scShop.nextInt();
        ShopDefinition shopDefinition = new ShopDefinition(shopId, chainIdForShop, mallId, mallShopId, address);
        if(shopDefinition.createShop()==1) {
            System.out.println("The ID of created Shop is: "+shopDefinition.getSpecificShop(shopId));
        }else {
            System.out.println("The shop already exists.");
        }
// Create new employee.
        Scanner scEmployee = new Scanner(System.in);
        System.out.println("Insert new employee ID: ");
        int employeeId=scEmployee.nextInt();
        System.out.println("Insert new employee Shop id: ");
        int shopIdForNewEmployee = scEmployee.nextInt();
        System.out.println("Insert ChainId for new employee: ");
        int chainIdForNewEmployee = scEmployee.nextInt();
        EmployeeDefinition employeeDefinition = new EmployeeDefinition(employeeId,shopIdForNewEmployee, chainIdForNewEmployee);
        if(employeeDefinition.createEmployee()==1) {
            System.out.println("The ID of created Employee is: "+employeeDefinition.getSpecificEmployee(employeeId));
        }else {
            System.out.println("Employee already exists.");
        }

// Get alla employees in specific chain.
        createThreeEmployees();
        List emplList= employeeDefinition.getAllEmployeeInChain(21);
        List<Integer> expectedEmplList = new ArrayList<>();
        expectedEmplList.add(10);
        expectedEmplList.add(11);
        expectedEmplList.add(12);
        if(emplList.equals(expectedEmplList)){
            System.out.println("The ID's of Employees that works in Chain is: "+printEmployeeId(emplList));
        }


        cleanDbForMain(chainId, shopId, employeeId);
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
