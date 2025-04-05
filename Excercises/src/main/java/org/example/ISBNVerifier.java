package org.example;

public class ISBNVerifier {
    public static void main(String[] args) {
        String isbn = "950-07-2749-8"; // Example ISBN-13
        boolean isValid = isValidISBN(isbn);
        System.out.println("Is the ISBN ("+isbn +") valid? " + isValid);
    }

    public static boolean isValidISBN(String isbn) {
        if (isbn.length() != 13) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            if (i % 2 == 0) {
                sum += digit;
            } else {
                sum += digit * 3;
            }
        }

        int checkDigit = (10 - (sum % 10)) % 10;
        return checkDigit == Character.getNumericValue(isbn.charAt(12));
    }
}
