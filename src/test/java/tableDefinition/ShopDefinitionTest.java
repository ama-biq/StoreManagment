package tableDefinition;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopDefinitionTest {

    @Test
    public void insertChain() throws SQLException {
//prepare the db
        ChainDefinition definitionChain = new ChainDefinition(21,"Fox kids");
        definitionChain.deleteSpecificChain(21);
        definitionChain.createChain();
        ShopDefinition definition = new ShopDefinition(2, 21, 111, 25, "Tel-Aviv");
        //the test
        int actual = definition.createShop();
//        //after test db cleaning
        definition.deleteSpecificShop(2);
        assertEquals(1, actual);
    }
    @Test
    public void getSpecificShop() throws SQLException {

        //prepare the db
        ShopDefinition emptyDefinition = new ShopDefinition();
        emptyDefinition.deleteSpecificShop(2);
        ShopDefinition definition = new ShopDefinition(2, 21, 111, 25, "Tel-Aviv");
        definition.createShop();
        //the test
        int actual = emptyDefinition.getSpecificShop(2);
        //after test db cleaning
        emptyDefinition.deleteSpecificShop(2);
        assertEquals(2, actual );
    }


    private void preparedbToPresentShopInTheSameMall() throws SQLException {
        //prepare the db
        ShopDefinition emptyDefinition = new ShopDefinition();
        emptyDefinition.deleteSpecificShop(2);
        emptyDefinition.deleteSpecificShop(2323);
        emptyDefinition.deleteSpecificShop(3232);
        ShopDefinition definition = new ShopDefinition(2, 21, 777, 77, "Haifa");
        ShopDefinition definition1 = new ShopDefinition(2323, 1, 777, 25, "Haifa");
        ShopDefinition definition2 = new ShopDefinition(3232, 21, 222, 25, "Ramle");
        definition.createShop();
        definition1.createShop();
        definition2.createShop();

    }

    @Test
    public void passGetAllShopsInSpecificMallGroup() throws SQLException {

        //prepare the db

        ChainDefinition testedChainDefinitionRecord = new ChainDefinition(444,"FOX");


        GroupMallDefinition testedGroupMallDefinitionRecord1 = new GroupMallDefinition(777,"Azrieli");
        GroupMallDefinition testedGroupMallDefinitionRecord2 = new GroupMallDefinition(555,"Ofer");

        MallDefinition testedMallRecord1 = new MallDefinition(444,"Tel Aviv","Hashalom", 777);
        MallDefinition testedMallRecord2 = new MallDefinition(555,"Ness Ziona","Hashayetet", 777);
        MallDefinition testedMallRecord3 = new MallDefinition(666,"Kenyoter","Hashiryion", 555);
        MallDefinition testedMallRecord4 = new MallDefinition(777,"7 Stars","Herzelia", 555);

        ShopDefinition shopObjectUsedForQuery = new ShopDefinition();

        ShopDefinition testedShopRecord1 = new ShopDefinition(333, 444, "Yaffo");
        ShopDefinition testedShopRecord2 = new ShopDefinition(444, 444,"Yarka");
        ShopDefinition testedShopRecord3 = new ShopDefinition(555, 444,"Omer");
        ShopDefinition testedShopRecord4 = new ShopDefinition(666, 555,"Reim");
        ShopDefinition testedShopRecord5 = new ShopDefinition(777, 666,666,44,"Kfar-avraham");
        ShopDefinition testedShopRecord6 = new ShopDefinition(888, 777,777,54,"Ken ha kukia");



        testedShopRecord1.deleteSpecificShop(333);
        testedShopRecord2.deleteSpecificShop(444);
        testedShopRecord3.deleteSpecificShop(555);
        testedShopRecord4.deleteSpecificShop(666);
        testedShopRecord5.deleteSpecificShop(777);
        testedShopRecord6.deleteSpecificShop(888);

        testedMallRecord1.deleteSpecificMall(444);
        testedMallRecord2.deleteSpecificMall(555);
        testedMallRecord3.deleteSpecificMall(666);
        testedMallRecord4.deleteSpecificMall(777);

        testedChainDefinitionRecord.deleteSpecificChain(444);

        testedGroupMallDefinitionRecord1.deleteSpecificMallGrp(777);
        testedGroupMallDefinitionRecord2.deleteSpecificMallGrp(555);

        testedGroupMallDefinitionRecord1.createMallGrp();
        testedGroupMallDefinitionRecord2.createMallGrp();

        testedChainDefinitionRecord.createChain();

        testedMallRecord1.createMall();
        testedMallRecord2.createMall();
        testedMallRecord3.createMall();
        testedMallRecord4.createMall();

        testedShopRecord1.createShop();
        testedShopRecord2.createShop();
        testedShopRecord3.createShop();
        testedShopRecord4.createShop();
        testedShopRecord5.createShop();
        testedShopRecord6.createShop();

        //the test
        List<Integer> actual = shopObjectUsedForQuery.getAllShopsInSpecificMallGroup(555);
        //after test db cleaning
        testedShopRecord1.deleteSpecificShop(333);
        testedShopRecord2.deleteSpecificShop(444);
        testedShopRecord3.deleteSpecificShop(555);
        testedShopRecord4.deleteSpecificShop(666);
        testedShopRecord5.deleteSpecificShop(777);
        testedShopRecord6.deleteSpecificShop(888);

        testedMallRecord1.deleteSpecificMall(444);
        testedMallRecord2.deleteSpecificMall(555);
        testedMallRecord3.deleteSpecificMall(666);
        testedMallRecord4.deleteSpecificMall(777);

        testedChainDefinitionRecord.deleteSpecificChain(444);

        testedGroupMallDefinitionRecord1.deleteSpecificMallGrp(777);
        testedGroupMallDefinitionRecord2.deleteSpecificMallGrp(555);

        assertEquals(2, actual.size() );
    }
}