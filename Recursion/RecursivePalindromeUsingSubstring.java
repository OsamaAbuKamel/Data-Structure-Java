public class RecursivePalindromeUsingSubstring {
    public static void main(String[] args) {
        System.out.println("Is moon a palindrome? "
                + isPalindrome("moon"));
        System.out.println("Is noon a palindrome? "
                + isPalindrome("noon"));
        System.out.println("Is a a palindrome? " + isPalindrome("a"));
        System.out.println("Is aba a palindrome? " +
                isPalindrome("aba"));
        System.out.println("Is ab a palindrome? " + isPalindrome("ab"));
    }
    public static boolean isPalindrome(String str) {
        if (str.length()<=1)
            return true;
        else if (str.charAt(0) != str.charAt(str.length() - 1))
            return false;
        else
            return isPalindrome(str.substring(1, str.length() - 1));
    }
}
