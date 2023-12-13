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
        System.out.println(isBalance("(){]}"));
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

    public static boolean isBalance(String exp) {
        // Create a new stack to store the parentheses
        LStack<Character> stack = new LStack<>();
        char[] chars = exp.toCharArray();
        for (char c : chars) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty())
                    return false;
                char open = stack.pop();
                if (!isPair(open, c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean isPair(char open, char c) {
        return (open == '(' && c == ')') || (open == '{' && c == '}'
                || open == '[' && c == ']');
    }
}