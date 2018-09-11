public class GameSettings {
    private static final int DEFAULT_LUXURY_TAX = 100;
    private static final int DEFAULT_INCOME_TAX = 200;
    private static final int DEFAULT_GO_COST = 200;
    private int goCost;
    private int luxuryTaxCost;
    private int incomeTaxCost;

    public int getGoCost() {
        return goCost;
    }

    public void setGoCost(int goCost) {
        this.goCost = goCost;
    }

    public int getLuxuryTaxCost() {
        return luxuryTaxCost;
    }

    public void setLuxuryTaxCost(int luxuryTaxCost) {
        this.luxuryTaxCost = luxuryTaxCost;
    }

    public int getIncomeTaxCost() {
        return incomeTaxCost;
    }

    public void setIncomeTaxCost(int incomeTaxCost) {
        this.incomeTaxCost = incomeTaxCost;
    }

    public GameSettings(int goCost, int luxuryTaxCost, int incomeTaxCost) {
        this.goCost = goCost;
        this.luxuryTaxCost = luxuryTaxCost;
        this.incomeTaxCost = incomeTaxCost;
    }

    public GameSettings() {
        this(DEFAULT_GO_COST, DEFAULT_INCOME_TAX, DEFAULT_LUXURY_TAX);
    }
}
