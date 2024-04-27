public class Palindrome {
    public static void main(String[] args) {
        String input = "racecar";
        System.out.println(isPalindrome(input));
    }

    public static boolean isPalindrome(String input) {
        // If the input string is empty or has a length of 1, it is considered a palindrome
        if (input.length() == 0 || input.length() == 1){
            return true;
        }
        // If the first and last characters of the input string match,
        // the function calls itself recursively with a substring from 1 to the length of the input string minus 1
        if (input.charAt(0) == input.charAt(input.length()-1)) {
            return isPalindrome(input.substring(1, input.length()-1));
        }
        // If the first and last characters do not match, the input string is not considered a palindrome
        return false;
    }

}
