package tableDefinition;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        MenuUtils menu = new MenuUtils();
        ConnectionToDb connObject = null;
        Connection connection = null;
        PrepareDbClass prepareDbClass = new PrepareDbClass();

        try {
            connObject = new ConnectionToDb();
            connection = connObject.getConnection();
            prepareDbClass.prepareDb(connection);
            int choice;
            do {
                choice = menu.getChoice();
                if (choice == 8) {
                    break;
                } else {
                    switch (choice) {
                        case 1:
                            menu.addNewChain(connection);
                            break;
                        case 2:
                            menu.addNewShop(connection);
                            break;
                        case 3:
                            menu.addNewEmployee(connection);
                            break;
                        case 4:
                            menu.presentAllShopsInMall(connection);
                            break;
                        case 5:
                            menu.presentAllShopsInMallGroup(connection);
                            break;
                        case 6:
                            menu.presentAllEmployeesInChain(connection);
                            break;
                        case 7:
                            menu.presentAllDetailsOfShop(connection);
                            break;
                        case 8:
                            return;
                        default:
                            System.out.println("Wrong choice, please choose again");
                            System.out.println("------------------------------------");
                    }
                }
            } while (choice != 8);

        } finally {
            connObject.close(connection);
        }
    }


}
