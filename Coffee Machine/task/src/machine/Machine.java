package machine;

import java.util.HashMap;
import java.util.Map;

public class Machine {
    private double moneyInMachine = 550;
    private int cupsInMachine = 9;
    private int waterInMachine = 400;
    private int milkInMachine = 540;
    private int beansInMachine = 120;
    private String coffeeLeastCupsIngredient;
    private int coffeeLeastCupsAmount;

    private Status choosingAction = Status.CHOOSINGACTION;
    private Status choosingCoffeeType = Status.CHOOSINGCOFFEETYPE;
    private Status fillingWater = Status.FILLINGWATER;
    private Status fillingMilk = Status.FILLINGMILK;
    private Status fillingBeans = Status.FILLINGBEANS;
    private Status fillingCups = Status.FILLINGCUPS;
    private Status exit = Status.EXIT;
    private Coffees espresso = Coffees.ESPRESSO;
    private Coffees latte = Coffees.LATTE;
    private Coffees cappuccino = Coffees.CAPPUCCINO;

    private Status status = choosingAction;
    private Coffees coffee;

    public Status getStatus() {
        return status;
    }

    public void printCurrentInventory() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(waterInMachine + " of water");
        System.out.println(milkInMachine + " of milk");
        System.out.println(beansInMachine + " of coffee beans");
        System.out.println(cupsInMachine + " of disposable cups");
        System.out.println("$" + (int) moneyInMachine + " of money");
    }

    public void giveMoney() {
        System.out.println("\nI gave you $" + (int) moneyInMachine);
        moneyInMachine = 0.0;
    }

    public void deductCoffeeInventoryItems() {
        this.waterInMachine -= coffee.getWaterPerCup();
        this.milkInMachine -= coffee.getMilkPerCup();
        this.beansInMachine -= coffee.getBeansPerCup();
        this.moneyInMachine += coffee.getCost();
        this.cupsInMachine -= 1;
    }

    public static void displayMenu() {
        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
    }

    public void takeUserInput (String input) {
        switch (status) {
            case CHOOSINGACTION:
                handleActionChoice(input);
                break;
            case CHOOSINGCOFFEETYPE:
                takeOrder(input);
                break;
            case FILLINGWATER:
                int waterToAdd = Integer.valueOf(input);
                waterInMachine += waterToAdd;
                System.out.println("Write how many ml of milk do you want to add:");
                status = fillingMilk;
                break;
            case FILLINGMILK:
                int milkToAdd = Integer.valueOf(input);
                milkInMachine += milkToAdd;
                System.out.println("Write how many grams of coffee beans the coffee machine has:");
                status = fillingBeans;
                break;
            case FILLINGBEANS:
                int beansToAdd = Integer.valueOf(input);
                beansInMachine += beansToAdd;
                System.out.println("Write how many grams of coffee beans do you want to add:");
                status = fillingCups;
                break;
            case FILLINGCUPS:
                int cupsToAdd = Integer.valueOf(input);
                cupsInMachine += cupsToAdd;
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                displayMenu();
                status = choosingAction;
                break;
            default:
                throw new IllegalArgumentException("Invalid Status Type: " + status.toString());
        }
    }

    public void handleActionChoice(String action) {
        switch (action.toLowerCase())  {
            case "buy":
                System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, " +
                        "3 - cappuccino, back - to main menu: ");
                status = choosingCoffeeType;
                break;
            case "fill":
                status = fillingWater;
                System.out.println("\nWrite how many ml of water do you want to add:");
                break;
            case "take":
                giveMoney();
                displayMenu();
                status = choosingAction;
                break;
            case "remaining":
                printCurrentInventory();
                displayMenu();
                status = choosingAction;
                break;
            case "exit":
                status = exit;
                break;
            default:
                throw new IllegalArgumentException("Invalid choice: " + action);
        }
    }

    public void takeOrder(String userChoice) {

        switch (userChoice) {
            case "back":
                displayMenu();
                status = choosingAction;
                return;
            case "1":
                coffee = espresso;
                break;
            case "2":
                coffee = latte;
                break;
            case "3":
                coffee = cappuccino;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + userChoice);
        }

        updateCupsAbleToMake();
        printIfAbleToMakeCups(1,coffeeLeastCupsAmount, coffeeLeastCupsIngredient);

        if (coffeeLeastCupsAmount >= 1) {
            deductCoffeeInventoryItems();
        }

        displayMenu();
        status = choosingAction;
    }

    public void updateCupsAbleToMake() {
        int cupsAbleToMakeBasedOnWater = waterInMachine / coffee.getWaterPerCup();
        int cupsAbleToMakeBasedOnMilk = (coffee.getMilkPerCup() == 0 ? Integer.MAX_VALUE :
                milkInMachine / coffee.getMilkPerCup());    // To handle Espressos not using milk and avoid divide by 0
        int cupsAbleToMakeBasedOnBeans = beansInMachine / coffee.getBeansPerCup();
        int cupsAbleToMakeBasedOnCups = cupsInMachine;

        Map<String, Integer> map = new HashMap<>();
        map.put("water", cupsAbleToMakeBasedOnWater);
        map.put("milk", cupsAbleToMakeBasedOnMilk);
        map.put("beans", cupsAbleToMakeBasedOnBeans);
        map.put("cups", cupsAbleToMakeBasedOnCups);

        coffeeLeastCupsAmount = getLeastCupsAmount(map);
        coffeeLeastCupsIngredient = getLeastCupsIngredient(map);

    }

    public static String getLeastCupsIngredient(Map<String, Integer> cupsAbleToMake){
        String leastCupsIngredient = "";
        int leastCupsAmount = Integer.MAX_VALUE;

        for (Map.Entry<String, Integer> entry : cupsAbleToMake.entrySet()) {
            if (entry.getValue() < leastCupsAmount){
                leastCupsIngredient = entry.getKey();
                leastCupsAmount = entry.getValue();
            }
        }

        return leastCupsIngredient;
    }

    public static int getLeastCupsAmount(Map<String, Integer> cupsAbleToMake){
        int leastCupsAmount = Integer.MAX_VALUE;

        for (Map.Entry<String, Integer> entry : cupsAbleToMake.entrySet()) {
            if (entry.getValue() < leastCupsAmount){
                leastCupsAmount = entry.getValue();
            }
        }

        return leastCupsAmount;
    }

    public static void printIfAbleToMakeCups(int cupsOfCoffeeNeeded, int maxCupsAbleToMake, String ingredient) {
        // Reply to user with Results
        if (cupsOfCoffeeNeeded > maxCupsAbleToMake) {
            System.out.println("Sorry, not enough " + ingredient + "!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
        }
    }

}
