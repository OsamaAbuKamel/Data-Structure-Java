package com.example.projectii;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EquationParser {
    public boolean isBalanced(String file, CStack<String> stack) {
        // Create a stack
        int l = stack.createStack();
        // Read the file
        String exp = readFile(file);
        StringBuilder sb = new StringBuilder();
        boolean insideTag = false;
        boolean isClosing = false;
        char[] chars = exp.toCharArray();
        for (char c : chars) {
            // Check if the char is '<'
            if (c == '<') {
                insideTag = true;
                isClosing = false;
                // Check if the char is '>'
            } else if (c == '>') {
                insideTag = false;
                // Check if the tag is not closing
                if (!isClosing) {
                    // Push the tag onto the stack
                    stack.push(sb.toString(), l);
                } else {
                    // Check if the stack is empty or if the top of the stack is not equal to the
                    // tag
                    if (stack.isEmpty(l) || !stack.pop(l).equals(sb.toString())) {
                        return false;
                    }
                }
                sb.setLength(0);
                // Check if we are inside a tag
            } else if (insideTag) {
                // Check if the char is '/'
                if (c == '/') {
                    isClosing = true;
                } else {
                    sb.append(c);
                }
            }
        }
        return stack.isEmpty(l);
    }

    public boolean isBalance(String file, CStack<String> stack, int i, int j) {
        int l = stack.createStack();
        String exp = extract(file, i, stack, j);
        char chars[] = exp.toCharArray();
        for (char c : chars) {
            if (c == '(') {
                stack.push(String.valueOf(c), l);
            } else if (c == ')') {
                if (stack.isEmpty(l) || !stack.pop(l).equals("(")) {
                    return false;
                }
            }
        }
        return stack.isEmpty(l);
    }

    // Method the extracts the equations
    public String extract(String exp, int i, CStack<String> stack, int j) {
        int l = stack.createStack();
        // call method to extract equations
        String s = extractEquations(exp, i, stack);
        // Split the string by comma
        String[] ex = s.split(",");
        // Iterate through the string in reverse order
        for (int k = ex.length - 1; k >= 0; k--) {
            // Check if the string is not empty
            if (ex[k].trim().length() > 0) {
                stack.push(ex[k], l);
            }
        }
        // Get the equation at index j from the stack
        return stack.get(l, j);
    }

    // Method to replace the tags
    String extractEquations(String exp, int i, CStack<String> stack) {
        StringBuilder result = new StringBuilder();
        // call method to extract section
        String s = extractSection(exp, i, stack);
        if (s != null) {
            // replace all tags
            result.append(s.replaceAll("<infix>", " ").replaceAll("</infix>", " ").replaceAll("<postfix>", " ")
                    .replaceAll("</postfix> ", " ").replaceAll("<equation>", " ").replaceAll("</equation>", " ,"));
        }
        return result.toString();
    }

    // Method th extracts the section from the file
    String extractSection(String exp, int i, CStack<String> stack) {
        // Read the file
        exp = readFile(exp);
        int l = stack.createStack();
        // Split the file into sections
        String[] ex = exp.split("<section>|</section>");
        // Iterate through the sections in reverse order
        for (int j = ex.length - 1; j >= 0; j--) {
            // If the string not empty and not equal to <242> and not equal to </242>
            if (!ex[j].trim().isEmpty() && !ex[j].trim().equals("<242>") && !ex[j].trim().equals("</242>")) {
                // push the section onto the stack
                stack.push(ex[j], l);
            }
        }
        // Get the equation at index j from the stack
        return stack.get(l, i);
    }

    // Method to extract the file content from a file
    private String readFile(String filePath) {
        StringBuilder result = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line.trim()).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}