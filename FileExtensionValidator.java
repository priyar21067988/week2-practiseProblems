import java.util.Scanner;

/**
 * FileExtensionValidator
 * -------------------------
 * An assignment-upload portal utility that checks whether an
 * uploaded filename has an accepted extension (pdf, docx, zip),
 * regardless of case.
 *
 * Concepts covered: lastIndexOf(), substring(), equalsIgnoreCase(),
 * conditional logic, checked exceptions.
 */
public class FileExtensionValidator {

    static final String[] ACCEPTED_EXTENSIONS = {"pdf", "docx", "zip"};

    // Custom CHECKED exception - a filename with no extension at all
    // cannot be validated, so the caller is required to handle it.
    static class MissingExtensionException extends Exception {
        public MissingExtensionException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter filename: ");
        String filename = scanner.nextLine();

        try {
            String result = validateFileExtension(filename);
            System.out.println(result);
        } catch (MissingExtensionException exception) {
            System.out.println("Rejected — " + exception.getMessage());
        }

        scanner.close();
    }

    // =========================================================
    // Finds the last '.' in the filename, extracts the extension,
    // and checks it case-insensitively against the accepted list.
    // Declares a CHECKED exception for a filename with no extension.
    // Suggested method signature per the task.
    // =========================================================
    static String validateFileExtension(String filename) throws MissingExtensionException {
        int lastDotIndex = filename.lastIndexOf('.');

        if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
            throw new MissingExtensionException("filename has no extension.");
        }

        String extension = filename.substring(lastDotIndex + 1);

        return isAcceptedExtension(extension) ? "Accepted" : "Rejected — invalid file type";
    }

    // Single-purpose, case-insensitive membership check against the
    // accepted extensions list.
    static boolean isAcceptedExtension(String extension) {
        for (String acceptedExtension : ACCEPTED_EXTENSIONS) {
            if (acceptedExtension.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }
}
