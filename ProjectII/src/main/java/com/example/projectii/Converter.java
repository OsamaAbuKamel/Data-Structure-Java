package com.example.projectii;

public class Converter {
    // Method to determine the precedence of an operator
    private int getPrecedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return -1;
        }
    }

    // Method to check if the string is an operator
    private boolean isOperator(String s) {
        switch (s) {
            case "-":
            case "+":
            case "/":
            case "*":
            case "^":
                return true;
        }
        return false;
    }

    // Method to convert infix expression to postfix
    public String infixToPostfix(String infix, CStack<String> stack) {
        if (!infix.isEmpty()) {
            // create a new stack
            int h = stack.createStack();

            StringBuilder postfix = new StringBuilder();
            // split the infix expression
            String[] exp = infix.split(" ");
            for (String s : exp) {
                // check if the string is an operand
                if (!s.equals("(") && !s.equals(")") && !isOperator(s)) {
                    postfix.append(s + " ");
                } else {
                    switch (s) {
                        case "^":
                            // push the operator onto the stack
                            stack.push(s, h);
                            break;
                        case "+":
                        case "-":
                        case "*":
                        case "/":
                            // while the stack in not empty and the operator has a higher or equal
                            // precedence
                            while (!stack.isEmpty(h) && getPrecedence(s) <= getPrecedence(stack.peek(h))) {
                                // pop the top operator from the stack and append in postfix
                                postfix.append(stack.pop(h) + " ");
                            }
                            stack.push(s, h);
                            break;
                        case "(":
                            // push the opening parenthesis
                            stack.push(s, h);
                            break;
                        case ")":
                            while (!stack.isEmpty(h)) {
                                String top = stack.pop(h);
                                if (top.equals("(")) {
                                    break;
                                }
                                postfix.append(top + " ");
                            }
                            break;
                    }
                }
            }
            // pop any remaining operators from the stack and append in postfix
            while (!stack.isEmpty(h)) {
                postfix.append(stack.pop(h) + " ");
            }
            return postfix.toString();

        } else
            throw new IllegalArgumentException("Equation is empty");

    }

    // Method to evaluate postfix expression
    public double evaluatePostfix(String input, CStack<String> stack) {
        if (!input.isEmpty()) {
            int h = stack.createStack();
            // split the postfix expression
            String[] exp = input.split(" ");
            for (String s : exp) {
                // if the current string is an operand or the parenthesis
                if (!isOperator(s) || s.equals("(") || s.equals(")")) {
                    stack.push(s, h);
                } else {
                    // pop the top two operands and convert them to double
                    double num1 = Double.parseDouble(stack.pop(h));
                    double num2 = Double.parseDouble(stack.pop(h));
                    double result = 0;
                    switch (s) {
                        case "+":
                            result = num2 + num1;
                            ;
                            break;
                        case "-":
                            result = num2 - num1;
                            break;
                        case "*":
                            result = num2 * num1;
                            break;
                        case "/":
                            // if the num1 is 0, then throw an exception
                            if (num1 != 0)
                                result = num2 / num1;
                            else
                                throw new IllegalArgumentException("Cannot divide by zero");
                            break;
                        case "^":
                            result = Math.pow(num2, num1);
                        default:
                            break;
                    }
                    // push the result to the stack
                    stack.push(String.valueOf(result), h);
                }
            }
            return Double.parseDouble(stack.pop(h));
        } else
            throw new IllegalArgumentException("Input is empty");
    }

    // Method to convert postfix expression to prefix expression
    public String postfixToPrefix(String postfix, CStack<String> stack) {
        if (!postfix.isEmpty()) {
            int h = stack.createStack();
            // Split the postfix expression
            String[] exp = postfix.split(" ");
            for (String string : exp) {
                // if the current string is an operator
                if (isOperator(string)) {
                    // pop the top two operands
                    String op2 = stack.pop(h);
                    String op1 = stack.pop(h);
                    // construct the prefix expression
                    String exp1 = string + " " + op1 + op2;
                    // push the expression
                    stack.push(exp1, h);
                } else { // if the current string is an operand
                    // push the operand
                    stack.push(string + " ", h);
                }
            }
            return stack.peek(h);
        } else
            throw new IllegalArgumentException("Equation is empty");
    }

    // Method to evaluate a prefix expression
    public double evaluatePrefix(String prefix, CStack<String> stack) {
        if (!prefix.isEmpty()) {
            int h = stack.createStack();
            // split the prefix expression
            String[] exp = prefix.split(" ");
            // Iterate through the prefix expression in reverse order
            for (int i = exp.length - 1; i >= 0; i--) {
                String s = exp[i];
                // If the current string is an operator
                if (isOperator(s)) {
                    // pop the top two operands and convert them to double
                    double op1 = Double.parseDouble(stack.pop(h));
                    double op2 = Double.parseDouble(stack.pop(h));
                    double result = 0;
                    switch (s) {
                        case "+":
                            result = op1 + op2;
                            break;
                        case "-":
                            result = op1 - op2;
                            break;
                        case "*":
                            result = op1 * op2;
                            break;
                        case "/":
                            if (op2 != 0)
                                result = op1 / op2;
                            else
                                throw new IllegalArgumentException("Cannot divide by zero");
                            break;
                        case "^":
                            result = Math.pow(op1, op2);
                            break;
                        default:
                            break;
                    }
                    // push the result
                    stack.push(String.valueOf(result), h);
                } else {
                    // push the operand
                    stack.push(s, h);
                }
            }
            return Double.parseDouble(stack.peek(h));
        } else
            throw new IllegalArgumentException("Equation is empty");
    }
}
