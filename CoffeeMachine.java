package machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoffeeMachine {

    static Scanner sc = new Scanner(System.in);
    //coffee machine resources
    private int waterInMachine;
    private int milkInMachine;
    private int coffeeInMachine;
    private int cupsInMachine;
    private int moneyInMachine;
    private int whatBuy;
    private int coffeeCupsCounter = 0;

    public CoffeeMachine() {
        this.waterInMachine = 400;
        this.milkInMachine = 540;
        this.coffeeInMachine = 120;
        this.cupsInMachine = 9;
        this.moneyInMachine = 550;
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.processAction();
        //sc.close();
    }

    public void processAction() {
        while (true) {
            System.out.println("Write action (buy, fill, take, clean, remaining, exit):");
            String actionStr = sc.nextLine();
            switch (actionStr) {
                case "buy" -> {
                    coffeeCupsCounter();//numara cite cesti de cafea au fost preparate
                    buyCoffee();
                }
                case "fill" -> fillCoffeeMachine();
                case "take" -> takeMoney();
                case "clean" -> cleanCoffeeMachine();
                case "remaining" -> showCoffeeMachineResources();
                case "exit" -> System.exit(0);
            }
            System.out.println();
        }
    }

    public void makeCoffee(Coffee coffee) {
        if (hasEnoughCoffee(coffee)) {
            //scadem resursele
            waterInMachine -= coffee.getWaterNeeded();
            milkInMachine -= coffee.getMilkNeeded();
            coffeeInMachine -= coffee.getCoffeeBeansNeeded();
            cupsInMachine -= coffee.getCupNeeded();
            moneyInMachine += coffee.getCost();
            System.out.println("I have enough resources, making you a coffee!");
        } else {
            if (waterInMachine < coffee.getWaterNeeded()) {
                System.out.println("Sorry, not enough " + checkResourcesForCoffee() + "!");
            } else if (milkInMachine < coffee.getMilkNeeded()) {
                System.out.println("Sorry, not enough " + checkResourcesForCoffee() + "!");
            } else if (coffeeInMachine < coffee.getCoffeeBeansNeeded()) {
                System.out.println("Sorry, not enough " + checkResourcesForCoffee() + "!");
            } else if (cupsInMachine < coffee.getCupNeeded()) {
                System.out.println("Sorry, not enough " + checkResourcesForCoffee() + "!");
            }
        }
    }

    public boolean hasEnoughCoffee(Coffee coffee) {
        return waterInMachine >= coffee.getWaterNeeded() &&
                milkInMachine >= coffee.getMilkNeeded() &&
                coffeeInMachine >= coffee.getCoffeeBeansNeeded() &&
                cupsInMachine >= coffee.getCupNeeded();
    }

    //a person buys a coffee
    public void buyCoffee() {
        System.out.println("\nWhat do you want to buy? " +
                "1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String input = sc.nextLine();
        if (input.equals("back")) {
            System.out.println();
            processAction();
        } else if (input.matches("^[123]$")) {
            int chooseCoffee = Integer.parseInt(input);
            Coffee myCoffee = Coffee.whatTypeOfCoffee(chooseCoffee);
            makeCoffee(myCoffee);
            whatBuy = chooseCoffee;
        }
    }

    //a specific person loads the coffee machine
    public void fillCoffeeMachine() {
        List<String> questionList = new ArrayList<>();
        questionList.add("Write how many ml of water you want to add:");
        questionList.add("Write how many ml of milk you want to add:");
        questionList.add("Write how many grams of coffee beans you want to add:");
        questionList.add("Write how many disposable cups you want to add:");
        for (String s : questionList) {
            System.out.println("\n" + s);
            int toAdd = sc.nextInt();
            sc.nextLine();//curata tamponul
            if (s.contains("water")) {
                waterInMachine += toAdd;
            } else if (s.contains("milk")) {
                milkInMachine += toAdd;
            } else if (s.contains("coffee")) {
                coffeeInMachine += toAdd;
            } else if (s.contains("cups")) {
                cupsInMachine += toAdd;
            }
        }
    }

    //a specific person takes all money
    public void takeMoney() {
        System.out.println("I gave you $" + moneyInMachine + "\n");
        moneyInMachine = 0;//dupa ce sunt luati banii variabila ia valoarea zero
        processAction();
    }

    public void showCoffeeMachineResources() {
        System.out.println();
        System.out.printf("""
                        The coffee machine has:
                        %d ml of water
                        %d ml of milk
                        %d g of coffee beans
                        %d disposable cups
                        $%d of money\n"""
                , waterInMachine, milkInMachine, coffeeInMachine, cupsInMachine, moneyInMachine);
    }

    public String checkResourcesForCoffee() {
        String response = "";
        switch (whatBuy) {
            case 1 -> {
                if (waterInMachine < 250) {
                    response = "water";
                } else if (coffeeInMachine < 16) {
                    response = "coffee beans";
                }
            }
            case 2 -> {
                if (waterInMachine < 350) {
                    response = "water";
                } else if (milkInMachine < 75) {
                    response = "milk";
                } else if (coffeeInMachine < 20) {
                    response = "coffee beans";
                }
            }
            case 3 -> {
                if (waterInMachine < 200) {
                    response = "water";
                } else if (milkInMachine < 100) {
                    response = "milk";
                } else if (coffeeInMachine < 12) {
                    response = "coffee beans";
                }
            }
        }
        return response;
    }

    public void coffeeCupsCounter() {
        if (coffeeCupsCounter == 10) {
            System.out.println("I need cleaning!\n");
            processAction();
        } else {
            coffeeCupsCounter++;
        }
    }

    public void cleanCoffeeMachine() {
        coffeeCupsCounter = 0;
        System.out.println("I have been cleaned!");
    }
}