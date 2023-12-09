package problems;

import java.util.Scanner;
import stack.LStack;

public class BalanceOfParenthesis {
    public static void main(String[] args) {
        String exp;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the Exp: ");
        exp = input.nextLine();
        balanceOfParenthesis(exp);
    }

    public static void balanceOfParenthesis(String exp) {
       //Create a new stack to store the parentheses
      LStack<Character> stack = new LStack<>();
        int i, length;
        char ch;
        length = exp.length();
        //Loop through the expression
        for (i = 0; i < length; i++) {
            ch = exp.charAt(i);
            //If the character is an opening parentheses, push it onto the stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            //If the character is a closing parentheses, check if the stack is empty or if the top of the stack matches the closing parentheses
            } else if (ch == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    System.out.println("\nUnbalanced Parentheses!");
                    return;
                }
            //If the character is a closing curly brace, check if the stack is empty or if the top of the stack matches the closing curly brace
            } else if (ch == '}') {
                if (stack.isEmpty() || stack.pop() != '{') {
                    System.out.println("\nUnbalanced Parentheses!");
                    return;
                }
            //If the character is a closing square bracket, check if the stack is empty or if the top of the stack matches the closing square bracket
            } else if (ch == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    System.out.println("\nUnbalanced Parentheses!");
                    return;
                }
            }
        }
        //If the stack is empty, the parentheses are balanced
        if (stack.isEmpty()) {
            System.out.println("\nBalanced Parentheses.");
        }
    }
}