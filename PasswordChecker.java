import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class checks the strength of passwords
 * Code written by Nicholas Cicalese on 2/17/2025
 */
public class PasswordChecker {

    /**
     * This method checks if a character is in between two ASCII values (inclusive)
     * @param c the character you would like to check
     * @param firstIndex the first ASCII value
     * @param secondIndex the second ASCII value
     * @return true if it is inbetween the two ASCII values, false otherwise
     */
    private static boolean characterBetweenIndices(char c, int firstIndex, int secondIndex){
        return c >= firstIndex && c <= secondIndex;
    }

    /**
     * This method counts the amount of uppercase letters in a password
     * @param password The password you would like to count the uppercase letters of
     * @return the amount of uppercase letters in the password
     */
    static int countUppercase(String password) {
        int count = 0;

        for  (int i = 0; i < password.length(); i++){
            // Check if the character is a number
            if(characterBetweenIndices(password.charAt(i), 65, 90)){
                count++;
            }
        }
        return count;
    }

    /**
     * This method counts the amount of numbers in a password
     * @param password The password you would like to count the numbers of
     * @return the amount of numbers in the password
     */
    static int countNumbers(String password){
        int count = 0;

        for  (int i = 0; i < password.length(); i++){
            // Check if the character is a number
            if(characterBetweenIndices(password.charAt(i), 48, 57)){
                count++;
            }
        }
        return count;
    }

    /**
     * This method counts the amount of special characters in a password
     * @param password The password you would like to count the special characters of
     * @return the amount of special characters in the password
     */
    static int countSpecialCharacters(String password){
        int count = 0;

        for  (int i = 0; i < password.length(); i++){
            char currentChar = password.charAt(i);

            // Check if the character is a special character
            if(characterBetweenIndices(currentChar, 33, 47) || characterBetweenIndices(currentChar, 58, 64) ||
                    characterBetweenIndices(currentChar, 91, 96) || characterBetweenIndices(currentChar, 123, 126)){
                count++;
            }
        }
        return count;
    }

    /**
     * This method checks the length of a password
     * @param password The password you would like to check
     * @return true if long enough, false otherwise
     */
    static boolean checkLength(String password) {
        return password.length() > 12;
    }

    static String checkPassword(String password){
        ArrayList<String> reasons = new ArrayList<>();

        int specialCharCount = countSpecialCharacters(password);
        int numberCount = countNumbers(password);
        int uppercaseCount = countUppercase(password);

        if(!checkLength(password)){
            reasons.add("is too short");
        }
        if(!(specialCharCount > 0)){
            reasons.add("does not contain a special character");
        }
        if(!(numberCount > 0)) {
            reasons.add("does not contain a number");
        }
        if(!(uppercaseCount > 0)) {
            reasons.add("does not contain a uppercase character");
        }

        if (reasons.isEmpty()) {
            return "Your password is very strong.";
        } else if(reasons.size() == 1) {
            return "Your password is strong, but it " + reasons.get(0) + ".";
        } else if(reasons.size() == 2) {
            return "Your password is weak: it " + reasons.get(0) + " and " + reasons.get(1) + ".";
        } else if(reasons.size() == 3) {
            return "Your password is very weak: it " + reasons.get(0) + ", " + reasons.get(1) + ", and " + reasons.get(2) + ".";
        } else {
            return "Your password is extremely weak: it "+ reasons.get(0) + ", " + reasons.get(1) + ", " + reasons.get(2) + ", and " + reasons.get(3) + ".";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your password: ");

        String password = scanner.nextLine();

        String response = PasswordChecker.checkPassword(password);

        System.out.println(response);
    }
}
