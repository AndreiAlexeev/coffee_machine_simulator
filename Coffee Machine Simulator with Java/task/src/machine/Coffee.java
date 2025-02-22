package machine;

public class Coffee {
    private int waterNeeded;
    private int milkNeeded;
    private int coffeeBeansNeeded;
    private int cupNeeded;
    private int cost;

    public Coffee(int waterNeeded, int milkNeeded, int coffeeBeansNeeded, int cost) {
        this.waterNeeded = waterNeeded;
        this.milkNeeded = milkNeeded;
        this.coffeeBeansNeeded = coffeeBeansNeeded;
        this.cupNeeded = 1;
        this.cost = cost;
    }

    public int getWaterNeeded() {
        return waterNeeded;
    }

    public int getMilkNeeded() {
        return milkNeeded;
    }

    public int getCoffeeBeansNeeded() {
        return coffeeBeansNeeded;
    }

    public int getCupNeeded() {
        return cupNeeded;
    }

    public int getCost() {
        return cost;
    }

    public static Coffee whatTypeOfCoffee(int yourCoffee) {
        return switch (yourCoffee) {
            case 1 -> new Coffee(250, 0, 16, 4); //espresso
            case 2 -> new Coffee(350, 75, 20, 7); //latte
            case 3 -> new Coffee(200, 100, 12, 6); //cappuccino
            default -> null;
        };
    }
}
