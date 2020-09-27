package machine;

public enum Coffees {
    ESPRESSO(4, 250, 0, 16),
    LATTE(7, 350, 75, 20),
    CAPPUCCINO(6, 200, 100, 12);

    private double cost;
    private int waterPerCup;
    private int milkPerCup;
    private int beansPerCup;

    Coffees(int cost, int waterPerCup, int milkPerCup, int beansPerCup) {
        this.cost = cost;
        this.waterPerCup = waterPerCup;
        this.milkPerCup = milkPerCup;
        this. beansPerCup = beansPerCup;
    }

    public double getCost() {
        return this.cost;
    }

    public int getWaterPerCup() {
        return this.waterPerCup;
    }

    public int getMilkPerCup() {
        return this.milkPerCup;
    }

    public int getBeansPerCup() {
        return this.beansPerCup;
    }
}
