package tableDefinition;

public class GroupMallDefinition {

    private int groupMallId;
    private String groupMallName;

    public GroupMallDefinition(int groupMallId, String groupMallName) {
        this.groupMallId = groupMallId;
        this.groupMallName = groupMallName;
    }

    public int getGroupMallId() {
        return groupMallId;
    }

    public void setGroupMallId(int groupMallId) {
        this.groupMallId = groupMallId;
    }

    public String getGroupMallName() {
        return groupMallName;
    }

    public void setGroupMallName(String groupMallName) {
        this.groupMallName = groupMallName;
    }
}
