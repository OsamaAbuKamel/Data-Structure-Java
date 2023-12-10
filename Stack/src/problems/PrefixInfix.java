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

    private static String prefixToInfix(String exp) {
        LStack<String> stack = new LStack<>();
        int l = exp.length();
        for (int i = l - 1; i >= 0; i--) {
            char c = exp.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
                stack.push(Character.toString(c));
            } else {
                String op1 = stack.pop();
                String op2 = stack.pop();
                stack.push('(' + op1 + c + op2 + ')');
            }
        }
        return stack.peek();
    }

}