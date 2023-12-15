package com.example.projectii;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EquationParser {
    private CStack<String> stack;

    public EquationParser() {
        this.stack = new CStack<>(100);
    }

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

    public String infixToPostfix(String infix) {
        int h = stack.getList().createList();
        StringBuilder postfix = new StringBuilder();
        for (char c : infix.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
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

    public boolean isBalanced(String input) {
        int top = stack.getList().createList();
        String[] words = input.split("");
        for (String s : words) {
            if (isOpenTag(s)) {
                stack.push(s, top);
            }
            if (isCloseTag(s)) {
                if (!isPair(stack.peek(top), s)) {
                    return false;
                }
                stack.pop(top);
            }
        }

        return stack.isEmpty(top);
    }

    private boolean isOpenTag(String tag) {
        return tag.equals("<242>") || tag.equals("<section>") || tag.equals("<infix>")
                || tag.equals("<postfix>") || tag.equals("<equation>");
    }

    private boolean isCloseTag(String tag) {
        return tag.equals("</242>") || tag.equals("</section>") || tag.equals("</infix>")
                || tag.equals("</postfix>") || tag.equals("</equation>");
    }

    private boolean isPair(String openTag, String closeTag) {
        return openTag != null && openTag.equals(closeTag);
    }

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

    private String extractTag(String input) {
        StringBuilder sb = new StringBuilder();
        String[] words = input.split(" ");
        for (String s : words) {
            if (s.contains("<242>")) {
                sb.append("<242>" + " ");
            } else if (s.contains("</242>")) {
                sb.append("</242>" + " ");
            } else if (s.contains("<section>")) {
                sb.append("<section>" + " ");
            } else if (s.contains("</section>")) {
                sb.append("</section>" + " ");
            } else if (s.contains("<infix>")) {
                sb.append("<infix>" + " ");
            } else if (s.contains("</infix>")) {
                sb.append("</infix>" + " ");
            } else if (s.contains("<equation>")) {
                sb.append("<equation>" + " ");
            } else if (s.contains("</equation>")) {
                sb.append("</equation>" + " ");
            } else if (s.contains("<postfix>")) {
                sb.append("<postfix>" + " ");
            } else if (s.contains("</postfix>")) {
                sb.append("</postfix>" + " ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        EquationParser ep = new EquationParser();
        String infixExpression = "2-3+4-5*6";
        String postfixExpression = ep.infixToPostfix(infixExpression);
        System.out.println("Infix expression: " + infixExpression);
        System.out.println("Postfix expression: " + postfixExpression);
        // String file =
        // ("C:\\Users\\osama\\DataStructure\\Data-Structure-\\ProjectII\\src\\main\\resources\\com\\example\\projectii\\DS-Proj2
        // (1).242");
        // String s = ep.readFile(file);
        // String ss = ep.extractTag(s);
        // System.out.println(s);
        // System.out.println("=== === === === === === === === === === === === === ===
        // === === === === === === ===");
        // System.out.println(ss);
        // System.out.println("=== === === === === === === === === === === === === ===
        // === === === === === === ===");
        // System.out.println(ep.isBalanced(ss));
    }
}