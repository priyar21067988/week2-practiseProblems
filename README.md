# week2-practiseProblems
Week 2 — StringBuilder, StringBuffer & String Handling

Five Java programs covering mutable string handling, validation, and formatted output.

Programs
#	File	What it does	Key concepts
1	VowelConsonantCounter.java	Counts vowels/consonants in a title	charAt(), loops, case-insensitive check
2	CsvStudentRecordParser.java	Parses a CSV student record line	split(","), array length validation
3	FileExtensionValidator.java	Accepts/rejects a file by extension	lastIndexOf(), substring(), equalsIgnoreCase()
4	MaskedPhoneNumberFormatter.java	Masks a phone number, showing last 4 digits	StringBuilder.insert(), digit validation
5	BankTransactionReferenceValidator.java	Normalizes & validates a transaction reference	trim(), multi-stage validation, StringBuilder
Sample runs
"Java Programming"            -> Vowels: 5 | Consonants: 10
"Ananya Verma,RA...,CSE"      -> Name: Ananya Verma | Roll No: RA... | Dept: CSE
"Assignment1.PDF"             -> Accepted
"9876543210"                  -> XXXXXX-3210
" hdf03022600042 "            -> [HDF] DATE: 03/02/26 | SEQ: 00042
Design notes
Each program uses a custom checked exception for its specific invalid-input case, thrown from the core logic method and caught in main().
No logic lives directly in main() — reading, validating, processing, and printing are always separate methods.
StringBuilder (with .insert()/.append()) is used wherever the task calls for building or mutating a string, instead of plain + concatenation.
Run
bash
javac <FileName>.java
java <ClassName>
