package tableDefinition;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class GroupMallDefinitionTest {
    private int expectedInsertStatus = 1;
    @Disabled
    @Test
    public void createMallGrp() throws SQLException {
        GroupMallDefinition mallGrp = new GroupMallDefinition(4444,"Mall Grp 4");
     // int actualGrpStatus = mallGrp.createMallGrp();
       int actualGrpStatus= mallGrp.getMallGrp();
        mallGrp.deleteMallGrp();
        assertEquals(expectedInsertStatus,actualGrpStatus);
    }

}