package tableDefinition;

public class ChainDefinition {

    private int chainId;
    private int category;

    public ChainDefinition(int chainId, int category) {
        this.chainId = chainId;
        this.category = category;
    }

    public int getChainId() {
        return chainId;
    }

    public void setChainId(int chainId) {
        this.chainId = chainId;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
