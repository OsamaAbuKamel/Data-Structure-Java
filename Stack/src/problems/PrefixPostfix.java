package problems;

import java.util.Scanner;
import stack.LStack;

public class PrefixPostfix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String prefix_exp = "";
        System.out.println("Enter Prefix Expression");
        prefix_exp = sc.next();
        System.out.println("Equivalent Postfix Expression for " + prefix_exp + " is= " + prefixToPostfix(prefix_exp));
    }

    public static String prefixToPostfix(String exp) {
        // Create a new stack to store the expression
        LStack<String> stack = new LStack<>();
        // Get the length of the expression
        int l = exp.length();
        // Loop through the expression from the end
        for (int i = l - 1; i >= 0; i--) {
            // Get the character at the current index
            char c = exp.charAt(i);
            // Check if the character is an alphabet, number or parentheses
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '(' || c == ')') {
                // If so, push it to the stack
                stack.push(Character.toString(c));
            } else {
                // Otherwise, pop two elements from the stack and push the result of the
                // operation
                String op1 = stack.pop();
                String op2 = stack.pop();
                stack.push('(' + op1 + op2 + c + ')');
            }
        }
        // Return the expression in postfix format
        return stack.peek();
    }
}