package tableDefinition;

import java.sql.SQLException;

public class Main {
    private static ChainDefinition chainDefinition = new ChainDefinition(100, "Phones");
    private static  ShopDefinition shopDefinition = new ShopDefinition(2, 21, 111, 25, "Tel-Aviv");
    private static EmployeeDefinition employeeDefinition = new EmployeeDefinition(10,2, 21);



    public static void main(String[] args) throws SQLException {
        chainDefinition.createChain();
        if(chainDefinition.createChain()==1) {
            System.out.println(chainDefinition.getSpecificChain(100));
        }else {
            System.out.println("The chain already exists.");
        }
        if(shopDefinition.createShop()==1) {
            System.out.println(shopDefinition.getSpecificShop(2));
        }else {
            System.out.println("The shop already exists.");
        }
        if(employeeDefinition.createEmployee()==1) {
            System.out.println(employeeDefinition.getSpecificEmployee(10));
        }else {
            System.out.println("Employee already exists.");
        }
        cleanDbForMain();
    }
    private static void cleanDbForMain() throws SQLException {
        employeeDefinition.deleteSpecificEmployee(10);
        shopDefinition.deleteSpecificShop(2);
        chainDefinition.deleteSpecificChain(100);

    }
}
