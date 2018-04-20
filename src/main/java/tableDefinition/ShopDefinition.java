package tableDefinition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

//    public int createChain() throws SQLException {
//        int status =0;
//        try{
//            ConnectionToDb connObject = new ConnectionToDb();
//            Connection connection = connObject.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("insert into shop(Shop_Id, Address, Chain_Id, Mall_Id, Mall_Shop_Id) VALUES(?,?,?,?,?)");
//            preparedStatement.setInt(1, chainId);
//            preparedStatement.setString(2, category);
//            status = preparedStatement.executeUpdate();
//        }catch (SQLException e){//todo handle exception
//        }
//        return status;

   // }
}
