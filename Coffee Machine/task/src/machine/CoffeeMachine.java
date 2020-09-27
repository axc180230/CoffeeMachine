package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private static Machine machine = new Machine();
    private static Status exit = Status.EXIT;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Machine.displayMenu();

        while(!machine.getStatus().equals(exit)) {
            // get user choice
            String userInput = scanner.nextLine();
            machine.takeUserInput(userInput);
        }
    } // end Main function
} // class coffeeMachine




