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

    private static final String GET_SHOP_BY_ID = "SELECT * from shop WHERE Shop_Id = ?";
    private static final String GET_ALL_SHOPS_BY_MALL = "SELECT * FROM shop WHERE Mall_Id = ?";
    private static final String DELETE_SHOP_BY_ID = "delete from shop where Shop_Id=?";
    private static final String INSERT_SHOP_TO_TABLE = "insert into shop(Shop_Id, Address, Chain_Id, Mall_Id, Mall_Shop_Id) VALUES(?,?,?,?,?)";
    private static final String GET_ALL_SHOPS_FROM_MALL_GROUP = "SELECT Shop_Id FROM shop, mall, mall_group where shop.Mall_Id = mall.Mall_Id and mall.Group_Mall_Id = mall_group.Group_Id and mall_group.Group_Id = ?";
    private static final String GET_ALL_SHOPS = "SELECT * FROM shop";

    ShopDefinition(int shopId, int chainId, int mallId, int mallShopId) {
        this.shopId = shopId;
        this.chainId = chainId;
        this.mallId = mallId;
        this.mallShopId = mallShopId;
    }

    ShopDefinition(int shopId, int chainId, String address) {
        this.shopId = shopId;
        this.chainId = chainId;
        this.address = address;
    }

    ShopDefinition() {

    }

    int createShop(Connection connection) throws SQLException {
        int status;
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SHOP_TO_TABLE);
        preparedStatement.setInt(1, shopId);
        preparedStatement.setString(2, address);
        preparedStatement.setInt(3, chainId);
        preparedStatement.setInt(4, mallId);
        preparedStatement.setInt(5, mallShopId);
        status = preparedStatement.executeUpdate();
        return status;
    }


    public int deleteSpecificShop(int specificShop) throws SQLException {
        int status;
        ConnectionToDb connObject = new ConnectionToDb();
        Connection connection = connObject.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SHOP_BY_ID);
        preparedStatement.setInt(1, specificShop);
        status = preparedStatement.executeUpdate();
        return status;
    }

    int getSpecificShop(Connection connection, int specificShop) throws SQLException {
        int column = -1;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_SHOP_BY_ID);
        preparedStatement.setInt(1, specificShop);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            column = rs.getInt(1);
        }
        return column;
    }

    List<Integer> getAllShopInCertainMall(int specificMall, Connection connection) throws SQLException {
        List<Integer> columnArrayList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SHOPS_BY_MALL);
        preparedStatement.setInt(1, specificMall);
        ResultSet rs = preparedStatement.executeQuery();
        ResultSet rsCopy = rs;
        while (rs.next()) {
            int i = 1;
            //in while loop we run on the first column and insert its value to arraylist
            //the values are the employee id
            // i+5 - because there are 5 columns in shop table
            while (i <= numberOfColumns(rsCopy)) {
                columnArrayList.add(rs.getInt(1));
                i = i + 5;
            }
        }
        return columnArrayList;
    }

    private int numberOfColumns(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        return metaData.getColumnCount();
    }

    public Set<Integer> getAllShopsInSpecificMallGroup(Connection connection, int groupMallId) throws SQLException {
        Set<Integer> shops = new HashSet<>();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SHOPS_FROM_MALL_GROUP);
            preparedStatement.setInt(1,groupMallId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                shops.add(rs.getInt(1));
            }
            rs.close();
        return shops;
    }

    Set<Integer> getExistedShops(Connection connection) throws SQLException {
        Set<Integer> shopsList = new HashSet<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SHOPS);
        ResultSet rs = preparedStatement.executeQuery();
        ResultSet rsCopy = rs;
        while (rs.next()) {
            int i = 1;
            while (i <= numberOfColumns(rsCopy)) {
                shopsList.add(rs.getInt(1));
                i = i + 5;
            }
        }
        return shopsList;
    }

    @Override
    public String toString() {
        return "The details of the Shop are {" +
                "shopId=" + shopId +
                ", chainId=" + chainId +
                ", mallId=" + mallId +
                ", mallShopId=" + mallShopId +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopDefinition that = (ShopDefinition) o;
        return shopId == that.shopId;
    }

    public ShopDefinition presentAllDetailsOfAShop(int shopId, Connection connection) throws SQLException {
        ShopDefinition retValShop = new ShopDefinition();
        List<Object> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SHOP_BY_ID);
            preparedStatement.setInt(1,shopId);
            ResultSet rs = preparedStatement.executeQuery();
            ResultSetMetaData metadata = rs.getMetaData();
            int cols = metadata.getColumnCount();
            while (rs.next()) {
                for(int i=1; i<=cols; ++i) {
                    list.add(rs.getObject(i));
                }
            }
            rs.close();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }

        if(list.isEmpty()){
            return null;
        }
        retValShop.shopId = (int) list.get(0);
        retValShop.address = (String) list.get(1);
        retValShop.chainId = (int) list.get(2);
        retValShop.mallId = (int) list.get(3);
        retValShop.mallShopId = (int) list.get(4);

        return retValShop;
    }

}
