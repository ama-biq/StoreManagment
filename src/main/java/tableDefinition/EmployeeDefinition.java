package tableDefinition;

public class EmployeeDefinition {

    private int employeeId;
    private int shopId;
    private int chainId;

    public EmployeeDefinition(int employeeId, int shopId, int chainId) {
        this.employeeId = employeeId;
        this.shopId = shopId;
        this.chainId = chainId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getChainId() {
        return chainId;
    }

    public void setChainId(int chainId) {
        this.chainId = chainId;
    }
}
