package tableDefinition;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static ChainDefinition chainDefinition = new ChainDefinition(10011, "Phones");
    private static  ShopDefinition shopDefinition = new ShopDefinition(211, 21, 111, 25, "Tel-Aviv");
    private static EmployeeDefinition employeeDefinition = new EmployeeDefinition(10,2, 21);




    public static void main(String[] args) throws SQLException {
        if(chainDefinition.createChain()==1) {
            System.out.println("The ID of created Chain is: "+chainDefinition.getSpecificChain(10011));
        }else {
            System.out.println("The chain already exists.");
        }
        if(shopDefinition.createShop()==1) {
            System.out.println("The ID of created Shop is: "+shopDefinition.getSpecificShop(211));
        }else {
            System.out.println("The shop already exists.");
        }
        if(employeeDefinition.createEmployee()==1) {
            System.out.println("The ID of created Employee is: "+employeeDefinition.getSpecificEmployee(10));
        }else {
            System.out.println("Employee already exists.");
        }

        createThreeEmployees();
        List<Integer> emplList = new ArrayList<>();
        emplList= employeeDefinition.getAllEmployeeInChain(21);
        List<Integer> expectedEmplList = new ArrayList<>();
        expectedEmplList.add(10);
        expectedEmplList.add(11);
        expectedEmplList.add(12);
        if(emplList.equals(expectedEmplList)){
            System.out.println("The ID's of Employees that works in Chain is: "+printEmployeeId(emplList));
        }


        cleanDbForMain();
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

    private static void createThreeEmployees() throws SQLException {
        EmployeeDefinition definitionEmployee = new EmployeeDefinition(10,2, 21);
        definitionEmployee.createEmployee();
        EmployeeDefinition definitionEmployee1 = new EmployeeDefinition(11,2, 21);
        definitionEmployee1.createEmployee();
        EmployeeDefinition definitionEmployee2 = new EmployeeDefinition(12,2, 21);
        definitionEmployee2.createEmployee();
    }
    private static void cleanDbForMain() throws SQLException {
        employeeDefinition.deleteSpecificEmployee(10);
        shopDefinition.deleteSpecificShop(211);
        chainDefinition.deleteSpecificChain(10011);

    }
}
