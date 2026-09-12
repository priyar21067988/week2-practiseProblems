import java.util.Scanner;

/**
 * CsvStudentRecordParser
 * -------------------------
 * A T&P team utility that parses a CSV line of student registration
 * data into Name, Roll Number, and Department fields, and prints a
 * formatted record.
 *
 * Concepts covered: split(), array length validation, string
 * concatenation, formatted output, checked exceptions.
 */
public class CsvStudentRecordParser {

    static final int REQUIRED_FIELD_COUNT = 3;

    // Custom CHECKED exception - a CSV line with the wrong number
    // of fields is a business-rule violation the caller must handle,
    // not a bug to crash on.
    static class InvalidRecordException extends Exception {
        public InvalidRecordException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter CSV line (Name,RollNumber,Department): ");
        String csvLine = scanner.nextLine();

        try {
            parseStudentRecord(csvLine);
        } catch (InvalidRecordException exception) {
            System.out.println(exception.getMessage());
        }

        scanner.close();
    }

    // =========================================================
    // Splits the CSV line into fields, validates the field count,
    // and prints the formatted record. Declares a CHECKED exception
    // for a malformed line. Suggested method signature per the task.
    // =========================================================
    static void parseStudentRecord(String csvLine) throws InvalidRecordException {
        String[] fields = csvLine.split(",");

        if (fields.length != REQUIRED_FIELD_COUNT) {
            throw new InvalidRecordException("Invalid Record");
        }

        String studentName = fields[0];
        String rollNumber = fields[1];
        String department = fields[2];

        printFormattedRecord(studentName, rollNumber, department);
    }

    // Single-purpose output method, kept separate from parsing logic.
    static void printFormattedRecord(String studentName, String rollNumber, String department) {
        System.out.println("Name: " + studentName + " | Roll No: " + rollNumber + " | Dept: " + department);
    }
}
