import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class Hangman {

    private static final String[] WORDS = {"java", "hangman", "programming", "developer", "code"};
    private static String wordToGuess;
    private static StringBuilder guessedWord;
    private static Set<Character> guessedLetters;
    private static Set<Character> incorrectGuesses;
    private static int attemptsRemaining;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeGame();

        while (attemptsRemaining > 0 && !guessedWord.toString().equals(wordToGuess)) {
            System.out.println("Word to guess: " + guessedWord);
            System.out.println("Incorrect guesses: " + incorrectGuesses);
            System.out.println("Attempts remaining: " + attemptsRemaining);
            System.out.print("Enter a letter (or type 'hint' for a hint): ");
            String input = scanner.next().toLowerCase();

            if (input.equals("hint")) {
                provideHint();
            } else {
                char guess = input.charAt(0);
                if (processGuess(guess)) {
                    System.out.println("Correct guess!");
                } else {
                    System.out.println("Incorrect guess.");
                    attemptsRemaining--;
                }
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
        guessedLetters = new HashSet<>();
        incorrectGuesses = new HashSet<>();
        attemptsRemaining = 6; // Number of attempts
    }

    private static boolean processGuess(char guess) {
        if (!isValidInput(guess)) {
            System.out.println("Invalid or repeated guess. Try again.");
            return false;
        }

        boolean correctGuess = false;
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess) {
                guessedWord.setCharAt(i, guess);
                correctGuess = true;
            }
        }
        if (!correctGuess) {
            incorrectGuesses.add(guess);
        } else {
            guessedLetters.add(guess);
        }
        return correctGuess;
    }

    private static boolean isValidInput(char guess) {
        return Character.isLetter(guess) && !guessedLetters.contains(guess) && !incorrectGuesses.contains(guess);
    }

    private static void provideHint() {
        Set<Character> remainingLetters = new HashSet<>();
        for (char c : wordToGuess.toCharArray()) {
            if (!guessedLetters.contains(c) && !incorrectGuesses.contains(c)) {
                remainingLetters.add(c);
            }
        }
        if (!remainingLetters.isEmpty()) {
            char hintLetter = remainingLetters.iterator().next();
            System.out.println("Hint: The word contains the letter '" + hintLetter + "'.");
        } else {
            System.out.println("No hints available.");
        }
    }
}
