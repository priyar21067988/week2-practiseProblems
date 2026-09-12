import java.util.Scanner;

/**
 * MaskedPhoneNumberFormatter
 * ----------------------------
 * A student-support call center utility that displays a partially
 * masked phone number, revealing only the last 4 digits, built
 * using StringBuilder.
 *
 * Concepts covered: String length/digit validation, substring(),
 * StringBuilder insert(), masking patterns, checked exceptions.
 */
public class MaskedPhoneNumberFormatter {

    static final int REQUIRED_PHONE_LENGTH = 10;
    static final int VISIBLE_DIGIT_COUNT = 4;
    static final String MASK_PATTERN = "XXXXXX";

    // Custom CHECKED exception - a phone number that isn't exactly
    // 10 numeric digits is a business-rule violation the caller
    // must handle.
    static class InvalidPhoneNumberException extends Exception {
        public InvalidPhoneNumberException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        try {
            String maskedNumber = maskPhoneNumber(phoneNumber);
            System.out.println(maskedNumber);
        } catch (InvalidPhoneNumberException exception) {
            System.out.println(exception.getMessage());
        }

        scanner.close();
    }

    // =========================================================
    // Validates the phone number, then builds the masked version
    // using StringBuilder.insert(). Declares a CHECKED exception for
    // invalid input. Suggested method signature per the task.
    // =========================================================
    static String maskPhoneNumber(String phone) throws InvalidPhoneNumberException {
        validatePhoneNumber(phone);

        String lastFourDigits = phone.substring(phone.length() - VISIBLE_DIGIT_COUNT);

        StringBuilder maskedNumberBuilder = new StringBuilder(MASK_PATTERN + lastFourDigits);
        maskedNumberBuilder.insert(MASK_PATTERN.length(), "-"); // insert "-" between mask and last 4 digits

        return maskedNumberBuilder.toString();
    }

    // Declares a CHECKED exception so the caller must handle a
    // phone number that isn't exactly 10 numeric digits.
    static void validatePhoneNumber(String phone) throws InvalidPhoneNumberException {
        if (phone.length() != REQUIRED_PHONE_LENGTH || !containsOnlyDigits(phone)) {
            throw new InvalidPhoneNumberException("Invalid phone number");
        }
    }

    // Single-purpose digit-validation helper.
    static boolean containsOnlyDigits(String text) {
        for (int index = 0; index < text.length(); index++) {
            if (!Character.isDigit(text.charAt(index))) {
                return false;
            }
        }
        return true;
    }
}
