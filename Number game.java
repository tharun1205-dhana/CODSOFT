import java.util.Random;
import java.util.Scanner;

public class NumberGuesser {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        int totalScore = 0;
        boolean continuePlaying = true;

        System.out.println("=== Number Guesser Challenge ===");

        while (continuePlaying) {
            int min = 1;
            int max = 100;
            int targetNumber = rand.nextInt(max - min + 1) + min;
            int remainingTries = 7;
            boolean isGuessed = false;

            System.out.println("\nI've selected a number between " + min + " and " + max + ".");
            System.out.println("You have " + remainingTries + " chances to find it!");

            while (remainingTries > 0) {
                System.out.print("Your guess: ");
                int guess;

                if (!input.hasNextInt()) {
                    System.out.println("Invalid input. Enter a numeric value.");
                    input.next(); // discard invalid input
                    continue;
                }

                guess = input.nextInt();

                if (guess < min || guess > max) {
                    System.out.println("Out of range! Please pick a number between " + min + " and " + max + ".");
                    continue;
                }

                remainingTries--;

                if (guess == targetNumber) {
                    System.out.println("✅ Well done! You got it right.");
                    totalScore++;
                    isGuessed = true;
                    break;
                } else if (guess < targetNumber) {
                    System.out.println("Hint: Try a higher number.");
                } else {
                    System.out.println("Hint: Try a lower number.");
                }

                System.out.println("Tries left: " + remainingTries);
            }

            if (!isGuessed) {
                System.out.println("❌ No more tries left! The correct number was: " + targetNumber);
            }

            input.nextLine(); // clear newline
            System.out.print("Play again? (y/n): ");
            String answer = input.nextLine().trim().toLowerCase();
            continuePlaying = answer.equals("y");
        }

        System.out.println("\nThanks for playing! Your total score: " + totalScore);
        input.close();
    }
}
