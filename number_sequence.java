import java.util.Scanner;

public class NumberSequencingGame {

    private static final int INITIAL_LENGTH = 3;
    private static List<Integer> sequence;

    public static List<Integer> generateSequence() {
        sequence = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < INITIAL_LENGTH; i++) {
            sequence.add(random.nextInt(10));
        }
        return sequence;
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
        sequence = generateSequence();
        displaySequence(sequence);

        List<Integer> userGuess = new ArrayList<>();
        System.out.println("Enter your guess (3 numbers):");
        for (int i = 0; i < INITIAL_LENGTH; i++) {
            userGuess.add(scanner.nextInt());
        }

        if (sequence.equals(userGuess)) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The sequence was " + sequence);
        }
        scanner.close();
    }
}
