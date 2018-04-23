package tableDefinition;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShopDefinition {

    private int shopId;
    private int chainId;
    private int mallId;
    private int mallShopId;
    private String address;

    private static final String getShopById = "SELECT * from shop WHERE Shop_Id = ?";
    private static final String getAllShopsByMall = "SELECT * FROM shop WHERE Mall_Id = ?";
    private static final String deleteShopById = "delete from shop where Shop_Id=?";
    private static final String insertShopToTable = "insert into shop(Shop_Id, Address, Chain_Id, Mall_Id, Mall_Shop_Id) VALUES(?,?,?,?,?)";
    private static final String getAllShopsFromMallGroup = "SELECT Shop_Id FROM shop, mall, mall_group where shop.Mall_Id = mall.Mall_Id and mall.Group_Mall_Id = mall_group.Group_Id and mall_group.Group_Id = ?";
    private static final String getAllShops ="SELECT * FROM shop";

    public ShopDefinition(int shopId, int chainId, int mallId, int mallShopId) {
        this.shopId = shopId;
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

    public int createShop(Connection connection) throws SQLException {
        int status = 0;
            PreparedStatement preparedStatement = connection.prepareStatement(insertShopToTable);
            preparedStatement.setInt(1, shopId);
            preparedStatement.setString(2, address);
            preparedStatement.setInt(3, chainId);
            preparedStatement.setInt(4, mallId);
            preparedStatement.setInt(5, mallShopId);
            status = preparedStatement.executeUpdate();

        return status;
    }


    public int deleteSpecificShop(int specificShop) throws SQLException {
        int status = 0;
            ConnectionToDb connObject = new ConnectionToDb();
            Connection connection = connObject.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteShopById);
            preparedStatement.setInt(1, specificShop);
            status = preparedStatement.executeUpdate();
        return status;

    }

    public int getSpecificShop(int specificShop) throws SQLException {
        int column = 0;
            ConnectionToDb connObject = new ConnectionToDb();
            Connection connection = connObject.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getShopById);
            preparedStatement.setInt(1, specificShop);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                column = rs.getInt(1);
            }
        return column;
    }

    public List<Integer> getAllShopInCertainMall(int specificMall, Connection connection) throws SQLException {
        List<Integer> columnArrayList = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(getAllShopsByMall);
            preparedStatement.setInt(1, specificMall);
            ResultSet rs = preparedStatement.executeQuery();
            ResultSet rsCopy = rs;
            while(rs.next()) {
                int i = 1;
                //in while loop we run on the first column and insert its value to arraylist
                //the values are the employee id
                // i+5 - because there are 5 columns in shop table
                while (i <= numberOfColumns(rsCopy)){
                    columnArrayList.add(rs.getInt(1));
                    i=i+5;
                }
            }

        return columnArrayList;
    }
    private int numberOfColumns(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        return metaData.getColumnCount();
    }

    public List<Integer> getAllShopsInSpecificMallGroup(int groupMallId) throws SQLException {
        List<Integer> shops = new ArrayList<>();
            ConnectionToDb connObject = new ConnectionToDb();
            Connection connection = connObject.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAllShopsFromMallGroup);
            preparedStatement.setInt(1,groupMallId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                shops.add(rs.getInt(1));
            }

            rs.close();
            connection.close();

        return shops;

    }

    public Set<Integer> getExistedShops(Connection connection) throws SQLException {
        Set<Integer> shopsList = new HashSet<>();
        PreparedStatement preparedStatement = connection.prepareStatement(getAllShops);
        ResultSet rs = preparedStatement.executeQuery();
        ResultSet rsCopy = rs;
        while(rs.next()) {
            int i = 1;
            while (i <= numberOfColumns(rsCopy)) {
                shopsList.add(rs.getInt(1));
                i = i + 5;
            }
        }

        return shopsList;
    }
}
