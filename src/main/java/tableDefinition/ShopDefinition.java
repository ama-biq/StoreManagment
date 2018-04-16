package tableDefinition;

public class ShopDefinition {

    private int shopId;
    private int chainId;
    private int mallId;
    private int mallShopId;
    private String address;

    public ShopDefinition(int shopId, int chainId, int mallId, int mallShopId, String address) {
        this.shopId = shopId;
        this.chainId = chainId;
        this.mallId = mallId;
        this.mallShopId = mallShopId;
        this.address = address;
    }

    public ShopDefinition(int shopId, int chainId, String address) {
        this.shopId = shopId;
        this.chainId = chainId;
        this.address = address;
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

    public int getMallId() {
        return mallId;
    }

    public void setMallId(int mallId) {
        this.mallId = mallId;
    }

    public int getMallShopId() {
        return mallShopId;
    }

    public void setMallShopId(int mallShopId) {
        this.mallShopId = mallShopId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
