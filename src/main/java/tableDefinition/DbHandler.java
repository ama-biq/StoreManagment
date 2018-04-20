package tableDefinition;

import java.sql.SQLException;

public class DbHandler {
    public static void main(String[] args) throws SQLException {
        GroupMallDefinition mallGrp = new GroupMallDefinition(333333,"MallGrp 3");
        System.out.println(mallGrp.createMallGrp());
        System.out.println(mallGrp.getGroupMallId());
        System.out.println(mallGrp.deleteMallGrp());
    }
}
