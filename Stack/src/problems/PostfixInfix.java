package problems;

import java.util.Scanner;

import stack.LStack;

public class PostfixInfix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String postfix_exp = "";
        System.out.println("Enter Postfix Expression");
        postfix_exp = sc.next();
        System.out.println("Equivalent infix Expression for " + postfix_exp + " is= " + postfixToInfix(postfix_exp));
    }

    private static String postfixToInfix(String exp) {
        String expiration = "";
        LStack<String> stack = new LStack<String>();
        for (char c : exp.toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
                stack.push(Character.toString(c));
            } else {
                String op2 = stack.pop();
                String op1 = stack.pop();
                expiration = '(' + op1 + c + op2 + ')';
                stack.push(expiration);
            }
        }
        return stack.peek();
    }
}
