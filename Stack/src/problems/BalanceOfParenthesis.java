package problems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import stack.LStack;

public class BalanceOfParenthesis {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\osama\\DataStructure\\Data-Structure-\\Stack\\src\\stack\\Node.java";
        String fileContent = readFile(filePath);
        
        System.out.println(fileContent);
        System.out.println("---------------------------------");
        balanceOfParenthesis(fileContent);
    }
    
    public static String readFile(String filePath) {
        StringBuilder result = new StringBuilder();
        
        try (Scanner s = new Scanner(new File(filePath))) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                char c = ' ';
                for (int i = 0; i < line.length(); i++) {
                    c = line.charAt(i);
                
                     c = line.charAt(i);
                    if (c == '{' || c == '(' || c == '[') {
                        result.append(c);
                    }
                    if (c == '}' || c == ')' || c == ']') {
                        result.append(c);
                    }
            }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result.toString();
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