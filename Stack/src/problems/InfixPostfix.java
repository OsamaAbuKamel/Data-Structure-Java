package problems;

import stack.LStack;

public class InfixPostfix {
    public static void main(String[] args) {
        String infixExpression = "( 2 - 3 + 4 ) * ( 5 + 6 * 7 )";
        String postfixExpression = infixToPostfix(infixExpression);
        System.out.println("Infix expression: " + infixExpression);
        System.out.println("Postfix expression: " + postfixExpression);
    }

   private static boolean isOperator(char c) {
        //Check if the character is a valid operator
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private static int getPrecedence(char c) {
        //Check the precedence of the operator
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

    public static String infixToPostfix(String exp) {
        //Create a stack to store the parentheses
        LStack<Character> stack = new LStack<>();
        //Create a string builder to store the postfix expression
        StringBuilder postfix = new StringBuilder();
        //Loop through each character in the expression
        for (char c : exp.toCharArray()) {
            //If the character is a letter or digit, add it to the postfix expression
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            //If the character is an opening parentheses, push it onto the stack
            } else if (c == '(') {
                stack.push(c);
            //If the character is a closing parentheses, pop all characters from the stack until the opening parentheses is found
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                //If the opening parentheses is found, pop the opening parentheses from the stack
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
            //If the character is an operator, pop all characters from the stack until the operator's precedence is greater than the current operator
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && getPrecedence(stack.peek()) >= getPrecedence(c)) {
                    postfix.append(stack.pop());
                }
                //Push the operator onto the stack
                stack.push(c);
            }
        }
        //If there are still characters in the stack, add them to the postfix expression
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        //Return the postfix expression
        return postfix.toString();
    }
    public static int calc(int operand1, int operand2, char operator){
        switch (operator){
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            default:
                return 0;
        }
    }

}