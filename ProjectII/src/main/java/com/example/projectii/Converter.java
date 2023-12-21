package com.example.projectii;

import java.util.Stack;

public class Converter {
    public static int getPrecedence(char x) {

        if (x == '^') { // highest precedence
            return 2;
        } else if (x == '*' || x == '/') {
            return 1; // second highest precedence
        } else if (x == '+' || x == '-') {
            // lowest precedence

            return 0;
        }
        return -1; // not an operator
    }

    public String infixToPostfix(String infix, CStack<String> stack) {
        int h = stack.createStack();
        StringBuilder postfix = new StringBuilder();
        for (char c : infix.toCharArray()) {
            if (Character.isDigit(c)) {
                postfix.append(c);
            } else {
                switch (c) {
                    case '^':
                        stack.push(String.valueOf(c), h);
                        break;
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        while (!stack.isEmpty(h) && getPrecedence(c) <= getPrecedence(stack.peek(h).charAt(0))) {
                            postfix.append(stack.pop(h));
                        }
                        stack.push(String.valueOf(c), h);
                        break;
                    case '(':
                        stack.push(String.valueOf(c), h);
                        break;
                    case ')':
                        String top = stack.pop(h);
                        while (top != null && top.charAt(0) != '(') {
                            postfix.append(top);
                            top = stack.pop(h);
                        }
                        break;
                }
            }
        }
        while (!stack.isEmpty(h)) {
            postfix.append(stack.pop(h));
        }
        return postfix.toString();
    }

    public int evaluatePostfix(String input, CStack<String> stack) {
        input = input.trim();
        int h = stack.createStack();
        for (char c : input.toCharArray()) {
            if (Character.isLetterOrDigit(c) || c == '(' || c == ')') {
                stack.push(String.valueOf(c), h);
            } else {
                int num1 = Integer.parseInt(stack.pop(h));
                int num2 = Integer.parseInt(stack.pop(h));
                int result = 0;
                switch (c) {
                    case '+':
                        result = num2 + num1;
                        ;
                        break;
                    case '-':
                        result = num2 - num1;
                        break;
                    case '*':
                        result = num2 * num1;
                        break;
                    case '/':
                        result = num2 / num1;
                        break;
                    case '^':
                        result = (int) Math.pow(num2, num1);
                    default:
                        break;

                }
                stack.push(String.valueOf(result), h);
            }
        }
        return Integer.parseInt(stack.pop(h));
    }

    public String postfixToPrefix(String postfix, CStack<String> stack) {
        postfix = postfix.trim();
        int h = stack.createStack();
        for (char c : postfix.toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '(' || c == ')') {
                // If it is, push it to the stack
                stack.push(Character.toString(c), h);
            } else {
                String op2 = stack.pop(h);
                String op1 = stack.pop(h);
                String exp = c + op1 + op2;
                stack.push(exp, h);
            }
        }
        return stack.peek(h);
    }

    public double evaluatePrefix(String prefix, CStack<String> stack) {
        int h = stack.createStack();
        double result = 0;
        for (int i = prefix.length()-1; i >=0; i--) {
            char c= prefix.charAt(i);
            if (Character.isLetterOrDigit(c) || c == '(' || c == ')') {
                stack.push(Character.toString(c), h);
            } else {
                double op1 = Double.parseDouble(stack.pop(h));
                double op2 = Double.parseDouble(stack.pop(h));
                switch (c) {
                    case '+':
                        result = op1 + op2;
                        break;
                    case '-':
                        result = op1 - op2;
                        break;
                    case '*':
                        result = op1 * op2;
                        break;
                    case '/':
                        result = op1 / op2;
                        break;
                    case '^':
                        result = Math.pow(op1, op2);
                        break;
                    default:
                        break;
                }
            }
            stack.push(Double.toString(result), h);
        }
        return Double.parseDouble(stack.peek(h));
    }

}
