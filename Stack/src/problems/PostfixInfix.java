package problems;

import java.util.Scanner;

import stack.List.LStack;

public class PostfixInfix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String postfix_exp = "";
        System.out.println("Enter Postfix Expression");
        postfix_exp = sc.next();
        System.out.println("Equivalent infix Expression for " + postfix_exp + " is= " + postfixToInfix(postfix_exp));
    }

    public static String postfixToInfix(String exp) {
        // Declare a String to store the result
        String expiration = "";
        // Create a LStack to store the characters
        LStack<String> stack = new LStack<String>();
        // Loop through the characters of the String
        for (char c : exp.toCharArray()) {
            // Check if the character is a letter or number
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
                // If so, push it to the stack
                stack.push(Character.toString(c));
            } else {
                // Otherwise, pop two characters from the stack
                String op2 = stack.pop();
                String op1 = stack.pop();
                // Create the expression and push it to the stack
                expiration = '(' + op1 + c + op2 + ')';
                stack.push(expiration);
            }
        }
        // Return the result
        return stack.peek();
    }
}
