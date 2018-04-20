package tableDefinition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopDefinition {

    private int shopId;
    private int chainId;
    private int mallId;
    private int mallShopId;
    private String address;

    public ShopDefinition(int shopId, int chainId, int mallId, int mallShopId, String address) {
        this.shopId = shopId;
        this.address = address;
        this.chainId = chainId;
        this.mallId = mallId;
        this.mallShopId = mallShopId;
    }

    public ShopDefinition(int shopId, int chainId, String address) {
        this.shopId = shopId;
        this.chainId = chainId;
        this.address = address;
    }

    public ShopDefinition() {

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

    public int createShop() throws SQLException {
        int status = 0;
        try {
            ConnectionToDb connObject = new ConnectionToDb();
            Connection connection = connObject.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into shop(Shop_Id, Address, Chain_Id, Mall_Id, Mall_Shop_Id) VALUES(?,?,?,?,?)");
            preparedStatement.setInt(1, shopId);
            preparedStatement.setString(2, address);
            preparedStatement.setInt(3, chainId);
            preparedStatement.setInt(4, mallId);
            preparedStatement.setInt(5, mallShopId);
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {//todo handle exception
        }
        return status;
    }

    public int deleteSpecificShop(int specificShop) throws SQLException {
        int status = 0;
        try {
            ConnectionToDb connObject = new ConnectionToDb();
            Connection connection = connObject.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from shop where Shop_Id=?");
            preparedStatement.setInt(1, specificShop);
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {//todo handle exception
        }
        return status;

    }

    public int getSpecificShop(int specificShop) throws SQLException {
        int column = 0;
        try {
            ConnectionToDb connObject = new ConnectionToDb();
            Connection connection = connObject.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from shop WHERE Shop_Id = ?");
            preparedStatement.setInt(1, specificShop);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                column = rs.getInt(1);
            }
        } catch (SQLException e) {//todo handle exception
        }
        return column;

    }
}
