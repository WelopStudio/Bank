public class GameSettings {
    private static final int DEFAULT_LUXURY_TAX = 100;
    private static final int DEFAULT_INCOME_TAX = 200;
    private static final int DEFAULT_GO_COST = 200;
    private int goCost;
    private int luxuryTaxCost;
    private int incomeTaxCost;

    /**
     * Returns room's "GO" cost setting.
     * @return Room's "GO" cost setting.
     */
    public int getGoCost() {
        return goCost;
    }

    /**
     * Sets room's "GO" cost setting.
     * @param goCost Room's "GO" cost setting.
     */
    public void setGoCost(int goCost) {
        this.goCost = goCost;
    }

    /**
     * Returns room's Luxury tax setting.
     * @return Room's Luxury tax setting.
     */
    public int getLuxuryTaxCost() {
        return luxuryTaxCost;
    }

    /**
     * Sets room's Luxury tax setting.
     * @param luxuryTaxCost Room's Luxury tax setting.
     */
    public void setLuxuryTaxCost(int luxuryTaxCost) {
        this.luxuryTaxCost = luxuryTaxCost;
    }

    /**
     * Returns room's Income tax setting.
     * @return Room's Income tax setting.
     */
    public int getIncomeTaxCost() {
        return incomeTaxCost;
    }

    /**
     * Sets room's Luxury tax setting.
     * @param incomeTaxCost Room's Luxury tax setting.
     */
    public void setIncomeTaxCost(int incomeTaxCost) {
        this.incomeTaxCost = incomeTaxCost;
    }

    /**
     * Public constructor.
     * @param goCost Room's "GO" setting.
     * @param luxuryTaxCost Room's Luxury tax setting.
     * @param incomeTaxCost Room's Income tax setting.
     */
    public GameSettings(int goCost, int luxuryTaxCost, int incomeTaxCost) {
        this.goCost = goCost;
        this.luxuryTaxCost = luxuryTaxCost;
        this.incomeTaxCost = incomeTaxCost;
    }

    /**
     * Default constructor.
     */
    public GameSettings() {
        this(DEFAULT_GO_COST, DEFAULT_INCOME_TAX, DEFAULT_LUXURY_TAX);
    }
}
