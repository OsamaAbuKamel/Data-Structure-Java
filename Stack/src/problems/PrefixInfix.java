package problems;

import java.util.Scanner;
import stack.LStack;

public class PrefixInfix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String prefix_exp = "";
        System.out.println("Enter Prefix Expression");
        prefix_exp = sc.next();
        System.out.println("Equivalent infix Expression for " + prefix_exp + " is= " + prefixToInfix(prefix_exp));
    }

    public static String prefixToInfix(String exp) {
        // Create a stack to store the expression
        LStack<String> stack = new LStack<>();
        // Get the length of the expression
        int l = exp.length();
        // Loop through the expression from the end to the beginning
        for (int i = l - 1; i >= 0; i--) {
            // Get the character at the current index
            char c = exp.charAt(i);
            // Check if the character is an alphabet, number or parenthesis
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '(' || c == ')') {
                // If it is, push it to the stack
                stack.push(Character.toString(c));
            } else {
                // Otherwise, pop two elements from the stack and push the result of the
                // expression
                String op1 = stack.pop();
                String op2 = stack.pop();
                stack.push('(' + op1 + c + op2 + ')');
            }
        }
        // Return the expression in infix format
        return stack.peek();
    }
}