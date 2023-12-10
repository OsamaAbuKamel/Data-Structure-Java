package problems;

import java.util.Scanner;
import stack.LStack;

public class InfixPrefix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String infix_exp = "";
        System.out.println("Enter Infix Expression");
        infix_exp = sc.next();
        System.out.println("Equivalent prefix Expression for " + infix_exp + " is= " + infixToPrefix(infix_exp));
    }

    private static int getPrecedence(char c) {
        switch (c) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return -1;
        }
    }

    public static String infixToPrefix(String exp) {
        // Create a new LStack to store the expression
        LStack<Character> stack = new LStack<>();
        // Create a StringBuilder to store the prefix expression
        StringBuilder prefix = new StringBuilder();
        // Loop through each character in the expression
        for (char c : exp.toCharArray()) {
            // If the character is a letter or an uppercase letter, add it to the prefix
            // expression
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
                prefix.append(c);
            // If the character is an opening bracket, push it onto the stack
            else if (c == '(') {
                stack.push(c);
            }
            // If the character is a closing bracket, pop the stack until the opening
            // bracket is found
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    prefix.append(stack.pop());
                }
                // If the opening bracket is found, pop the stack
                if (stack.peek() == '(') {
                    stack.pop();
                }
            }
            // If the character is an operator, check the precedence of the operator and the
            // stack
            else {
                // If the stack is empty, push the operator onto the stack
                if (stack.isEmpty()) {
                    stack.push(c);
                    // If the stack is not empty, check the precedence of the operator and the stack
                } else {
                    // If the operator has a higher precedence than the stack, push the operator
                    // onto the stack
                    if (getPrecedence(c) > getPrecedence(stack.peek())) {
                        stack.push(c);
                    }
                    // If the operator has the same precedence as the stack, check if the operator
                    // is an exponent
                    else if (getPrecedence(c) == getPrecedence(stack.peek()) && c == '^') {
                        // If the operator is an exponent, pop the stack until the exponent operator is
                        // found
                        while (getPrecedence(c) == getPrecedence(stack.peek()) && c == '^') {
                            prefix.append(stack.pop());
                        }
                        // If the exponent operator is found, push the operator onto the stack
                        stack.push(c);
                        // If the operator has a lower precedence than the stack, pop the stack until
                        // the operator's precedence is found
                    } else if (getPrecedence(c) < getPrecedence(stack.peek())) {
                        while ((!stack.isEmpty()) && (getPrecedence(c) < getPrecedence(stack.peek()))) {
                            prefix.append(stack.pop());
                        }
                        // If the operator's precedence is found, push the operator onto the stack
                        stack.push(c);
                        // If the operator has the same precedence as the stack, push the operator onto
                        // the stack
                    } else if (getPrecedence(c) == getPrecedence(stack.peek())) {
                        stack.push(c);
                    }
                }
            }
        }
        // Loop through the stack and add the operators to the prefix expression
        while (!stack.isEmpty()) {
            prefix.append(stack.pop());
        }
        // Return the prefix expression
        return prefix.toString();
    }
}
