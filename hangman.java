import java.util.Scanner;
import java.util.Random;

public class Hangman {

    private static final String[] WORDS = {"java", "hangman", "programming", "developer", "code"};
    private static String wordToGuess;
    private static StringBuilder guessedWord;
    private static int attemptsRemaining;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeGame();

        while (attemptsRemaining > 0 && !guessedWord.toString().equals(wordToGuess)) {
            System.out.println("Word to guess: " + guessedWord);
            System.out.println("Attempts remaining: " + attemptsRemaining);
            System.out.print("Enter a letter: ");
            char guess = scanner.next().charAt(0);

            if (processGuess(guess)) {
                System.out.println("Correct guess!");
            } else {
                System.out.println("Incorrect guess.");
                attemptsRemaining--;
            }
        }

        if (guessedWord.toString().equals(wordToGuess)) {
            System.out.println("Congratulations! You've guessed the word: " + wordToGuess);
        } else {
            System.out.println("Game over! The word was: " + wordToGuess);
        }
        scanner.close();
    }

    private static void initializeGame() {
        Random random = new Random();
        int index = random.nextInt(WORDS.length);
        wordToGuess = WORDS[index];
        guessedWord = new StringBuilder("_".repeat(wordToGuess.length()));
        attemptsRemaining = 6; // Number of attempts
    }

    private static boolean processGuess(char guess) {
        boolean correctGuess = false;
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess) {
                guessedWord.setCharAt(i, guess);
                correctGuess = true;
            }
        }
        return correctGuess;
    }
}
