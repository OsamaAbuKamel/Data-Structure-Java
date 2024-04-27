public class DecimalToBinary {
    public static void main(String[] args) {
        int decimal = 233;
        String binary = findBinary(decimal, "");
        System.out.println("Binary of " + decimal + " is " + binary); // Output: Binary of 10 is 1010
        
    }
    public static String findBinary(int decimal, String result){
        // Base case: if decimal is 0, return the result
        if (decimal == 0) {
            return result;
        }
        // Otherwise, append the remainder of decimal divided by 2 to the result
        result = decimal%2 + result;
        // Recursively call the function with decimal divided by 2 and the updated result
        return findBinary(decimal/2, result);
    }

}
