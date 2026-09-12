import java.util.Scanner;

/**
 * BankTransactionReferenceValidator
 * ------------------------------------
 * A fintech onboarding utility that normalizes and validates bank
 * transaction reference codes (3-letter bank code + 6-digit date
 * ddMMyy + 5-digit sequence number = 14 characters total).
 *
 * Concepts covered: trim(), substring(), string concatenation,
 * Character.isLetter()/isDigit(), StringBuilder, multi-stage
 * validation, checked exceptions.
 */
public class BankTransactionReferenceValidator {

    static final int REQUIRED_REFERENCE_LENGTH = 14;
    static final int BANK_CODE_LENGTH = 3;
    static final int DATE_LENGTH = 6;

    // Custom CHECKED exception - each failed validation stage carries
    // its own specific reason in the message, and the caller is
    // required to handle it rather than getting a malformed result.
    static class ReferenceValidationException extends Exception {
        public ReferenceValidationException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter transaction reference: ");
        String rawReference = scanner.nextLine();

        String normalizedReference = normalizeReference(rawReference);

        try {
            String formattedLine = validateAndFormat(normalizedReference);
            System.out.println(formattedLine);
        } catch (ReferenceValidationException exception) {
            System.out.println("Invalid: " + exception.getMessage());
        }

        scanner.close();
    }

    // =========================================================
    // Normalizes the raw reference: trims stray spaces, then
    // uppercases only the first 3 characters (bank code) using
    // substring() + concatenation, leaving the rest untouched.
    // Suggested method signature per the task.
    // =========================================================
    static String normalizeReference(String raw) {
        String trimmedReference = raw.trim();

        if (trimmedReference.length() < BANK_CODE_LENGTH) {
            return trimmedReference.toUpperCase(); // too short to have a bank code; length check will reject it
        }

        String bankCodePart = trimmedReference.substring(0, BANK_CODE_LENGTH).toUpperCase();
        String remainingPart = trimmedReference.substring(BANK_CODE_LENGTH);

        return bankCodePart + remainingPart;
    }

    // =========================================================
    // Runs multi-stage validation (length, bank code letters, body
    // digits) and builds the formatted display line with
    // StringBuilder. Declares a CHECKED exception, with a specific
    // reason message per failed stage. Suggested method signature
    // per the task.
    // =========================================================
    static String validateAndFormat(String reference) throws ReferenceValidationException {
        if (reference.length() != REQUIRED_REFERENCE_LENGTH) {
            throw new ReferenceValidationException("wrong length (expected 14 characters, got "
                    + reference.length() + ")");
        }

        String bankCode = reference.substring(0, BANK_CODE_LENGTH);
        String bodyDigits = reference.substring(BANK_CODE_LENGTH); // date + sequence, 11 characters

        if (!isAllLetters(bankCode)) {
            throw new ReferenceValidationException("bank code must be 3 letters");
        }

        if (!isAllDigits(bodyDigits)) {
            throw new ReferenceValidationException("date and sequence number must be digits only");
        }

        String datePart = bodyDigits.substring(0, DATE_LENGTH);
        String sequencePart = bodyDigits.substring(DATE_LENGTH);
        String formattedDate = formatDateWithSlashes(datePart);

        return buildDisplayLine(bankCode, formattedDate, sequencePart);
    }

    // Single-purpose letter-check helper - no regex, per the task.
    static boolean isAllLetters(String text) {
        for (int index = 0; index < text.length(); index++) {
            if (!Character.isLetter(text.charAt(index))) {
                return false;
            }
        }
        return true;
    }

    // Single-purpose digit-check helper - no regex, per the task.
    static boolean isAllDigits(String text) {
        for (int index = 0; index < text.length(); index++) {
            if (!Character.isDigit(text.charAt(index))) {
                return false;
            }
        }
        return true;
    }

    // Converts "ddMMyy" into "dd/MM/yy" using StringBuilder.insert(),
    // inserting from the rightmost position first so earlier indices
    // stay valid.
    static String formatDateWithSlashes(String ddMMyy) {
        StringBuilder dateBuilder = new StringBuilder(ddMMyy);
        dateBuilder.insert(4, "/");
        dateBuilder.insert(2, "/");
        return dateBuilder.toString();
    }

    // Builds the final formatted display line with StringBuilder.
    static String buildDisplayLine(String bankCode, String formattedDate, String sequencePart) {
        StringBuilder displayBuilder = new StringBuilder();
        displayBuilder.append("[").append(bankCode).append("] DATE: ").append(formattedDate)
                .append(" | SEQ: ").append(sequencePart);
        return displayBuilder.toString();
    }
}
