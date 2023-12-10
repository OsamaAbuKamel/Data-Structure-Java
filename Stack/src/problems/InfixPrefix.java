package problems;

import java.util.Scanner;

import stack.LStack;

public class InfixPrefix {
    public static void main(String[] args) {
Scanner sc=new Scanner(System.in);
		String infix_exp="";
		System.out.println("Enter Infix Expression");
		infix_exp=sc.next();
		System.out.println("Equivalent prefix Expression for "+infix_exp+" is= "+infixToPrefix(infix_exp));
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '=' || c == '-' || c == '*' || c == '/' || c == '^';
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
        LStack<Character> stack = new LStack<>();
        StringBuilder prefix = new StringBuilder();
        for (char c : exp.toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
                prefix.append(c);
            else if (c == '(') {
                stack.push(c);
            }
            else if (c==')') {
                while (!stack.isEmpty() && stack.peek()!='(') {
                    prefix.append(stack.pop());
                }
                if (stack.peek()=='(') {
                    stack.pop();
                }
            }
            else{
                if (stack.isEmpty()) {
                    stack.push(c);
                }else{
                    if (getPrecedence(c)>getPrecedence(stack.peek())) {
                        stack.push(c);
                    }
                    else if (getPrecedence(c) == getPrecedence(stack.peek())&&c=='^') {
                        while (getPrecedence(c) == getPrecedence(stack.peek())&&c=='^') {
                            prefix.append(stack.pop());
                        }
                        stack.push(c);
                    }else if (getPrecedence(c)<getPrecedence(stack.peek())) {
                        while ((!stack.isEmpty())&& (getPrecedence(c)<getPrecedence(stack.peek()))) {
                            prefix.append(stack.pop());
                        }
                        stack.push(c);
                    }else if (getPrecedence(c)==getPrecedence(stack.peek())) {
                        stack.push(c);
                    }
                }
            }

        }
        while (!stack.isEmpty()) {
            prefix.append(stack.pop());
        }

        return prefix.toString();
    }

}
