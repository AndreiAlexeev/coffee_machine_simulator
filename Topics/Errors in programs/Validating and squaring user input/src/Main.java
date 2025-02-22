import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read an integer from the user
        int number = scanner.nextInt();

        // Check if the number is negative
        int result;
        if (number < 0){
            System.out.println("Invalid input!");
            return;
        }else {
            result = (int)Math.pow(number, 2);
        }

        // Print the square of the number if it's non-negative
        System.out.println(result);

    }
}