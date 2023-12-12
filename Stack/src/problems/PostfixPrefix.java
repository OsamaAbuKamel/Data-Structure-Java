package problems;

import java.util.Scanner;

import stack.LStack;

public class PostfixPrefix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String postfix_exp = "";
        System.out.println("Enter Postfix Expression");
        postfix_exp = sc.next();
        System.out.println("Equivalent prefix Expression for " + postfix_exp + " is= " + postfixToPrefix(postfix_exp));
    }

    public static String postfixToPrefix(String exp) {
        // Create a new stack to store the postfix expression
        LStack<String> stack = new LStack<String>();
        // Loop through each character in the expression
        for (char c : exp.toCharArray()) {
            // Check if the character is a letter or number
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '(' || c == ')') {
                // If it is, push it to the stack
                stack.push(Character.toString(c));
            } else {
                // Otherwise, pop two elements from the stack and add the operator to the
                // expiration
                String op2 = stack.pop();
                String op1 = stack.pop();
                String expiration = c + op1 + op2;
                stack.push(expiration);
            }
        }
        // Return the expiration
        return stack.peek();
    }
}
