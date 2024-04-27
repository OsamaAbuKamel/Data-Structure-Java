public class ReverseString {
    public static void main(String[] args) {
        String str = "Hello";
        // Calls the reverseString method and stores the returned value in reversedStr
        String reversedStr = reverseString(str);
        // Prints the reversedStr value
        System.out.println("Reversed String: " + reversedStr);
        
    }
    // Takes a string as input and returns the reversed version of the string
    public static String reverseString(String str) {
        // If the input string is empty, return an empty string
        if (str.equals("")) {
            return "";
        }
        // Call the reverseString method, passing the substring of the input string starting
        // from the second character and ending with the last character
        return reverseString(str.substring(1)) + str.charAt(0);
    }

}


