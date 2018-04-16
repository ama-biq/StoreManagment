package tableDefinition;

public class ChainCategoryDefinition {

    private int chainCategoryId;
    private String chainCategory;

    public ChainCategoryDefinition(int chainCategoryId, String chainCategory) {
        this.chainCategoryId = chainCategoryId;
        this.chainCategory = chainCategory;
    }

    public int getChainCategoryId() {
        return chainCategoryId;
    }

    public void setChainCategoryId(int chainCategoryId) {
        this.chainCategoryId = chainCategoryId;
    }

    public String getChainCategory() {
        return chainCategory;
    }

    public void setChainCategory(String chainCategory) {
        this.chainCategory = chainCategory;
    }
}