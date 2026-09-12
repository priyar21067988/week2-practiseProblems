import java.util.Scanner;

/**
 * VowelConsonantCounter
 * ------------------------
 * A library orientation kiosk utility that counts vowels and
 * consonants in a submitted book title.
 *
 * Concepts covered: charAt(), length(), loops, case-insensitive
 * character comparison, checked exceptions.
 */
public class VowelConsonantCounter {

    static final String VOWEL_LETTERS = "aeiouAEIOU";

    // Custom CHECKED exception - the task assumes only letters and
    // spaces, so any other character is a business-rule violation
    // the caller is required to handle.
    static class InvalidCharacterException extends Exception {
        public InvalidCharacterException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a book title: ");
        String bookTitle = scanner.nextLine();

        try {
            countVowelsAndConsonants(bookTitle);
        } catch (InvalidCharacterException exception) {
            System.out.println("Invalid title: " + exception.getMessage());
        }

        scanner.close();
    }

    // =========================================================
    // Loops through the text using charAt(), counting vowels and
    // consonants separately while ignoring spaces. Declares a
    // CHECKED exception for any character that isn't a letter or
    // a space. Suggested method signature per the task.
    // =========================================================
    static void countVowelsAndConsonants(String text) throws InvalidCharacterException {
        int vowelCount = 0;
        int consonantCount = 0;

        for (int index = 0; index < text.length(); index++) {
            char currentCharacter = text.charAt(index);

            if (currentCharacter == ' ') {
                continue; // spaces are ignored
            }

            if (!Character.isLetter(currentCharacter)) {
                throw new InvalidCharacterException("'" + currentCharacter + "' is not a letter or space.");
            }

            if (isVowel(currentCharacter)) {
                vowelCount++;
            } else {
                consonantCount++;
            }
        }

        printVowelConsonantReport(vowelCount, consonantCount);
    }

    // Single-purpose, case-insensitive vowel check.
    static boolean isVowel(char character) {
        return VOWEL_LETTERS.indexOf(character) != -1;
    }

    // Single-purpose output method, kept separate from the counting logic.
    static void printVowelConsonantReport(int vowelCount, int consonantCount) {
        System.out.println("Vowels: " + vowelCount + " | Consonants: " + consonantCount);
    }
}
