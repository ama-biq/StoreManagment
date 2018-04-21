package tableDefinition;

public class MallDefinition {

    private int mallId;
    private String mallName;
    private String mallAddress;


    public MallDefinition(int mallId, String mallName, String mallAddress) {
        this.mallId = mallId;
        this.mallName = mallName;
        this.mallAddress = mallAddress;
    }

    public int getMallId() {
        return mallId;
    }

    public void setMallId(int mallId) {
        this.mallId = mallId;
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }

    public String getMallAddress() {
        return mallAddress;
    }

    public void setMallAddress(String mallAddress) {
        this.mallAddress = mallAddress;
    }
}
