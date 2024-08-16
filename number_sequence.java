import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class NumberSequencingGame {

    private static final int INITIAL_LENGTH = 3;
    private static List<Integer> sequence;

    public static List<Integer> generateSequence(int length) {
        List<Integer> seq = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            seq.add(random.nextInt(10));
        }
        return seq;
    }

    public static void displaySequence(List<Integer> sequence) {
        System.out.println("Memorize this sequence:");
        for (Integer num : sequence) {
            System.out.print(num + " ");
        }
        System.out.println();
        try {
            Thread.sleep(2000); // 2 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.print("\033[H\033[2J"); // Clear screen
        System.out.flush();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = INITIAL_LENGTH;
        boolean continuePlaying = true;

        while (continuePlaying) {
            sequence = generateSequence(length);
            displaySequence(sequence);

            List<Integer> userGuess = new ArrayList<>();
            System.out.println("Enter your guess (" + length + " numbers):");

            for (int i = 0; i < length; i++) {
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter an integer.");
                    scanner.next(); // clear the invalid input
                }
                userGuess.add(scanner.nextInt());
            }

            if (sequence.equals(userGuess)) {
                System.out.println("Correct! Moving to the next round.");
                length++;
            } else {
                System.out.println("Incorrect. The sequence was " + sequence);
                System.out.println("Game Over. Would you like to play again? (yes/no)");
                String response = scanner.next().toLowerCase();
                if (!response.equals("yes")) {
                    continuePlaying = false;
                }
                length = INITIAL_LENGTH; // Reset
            }
        }
        scanner.close();
    }
}
