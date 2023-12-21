package com.example.projectii;

import java.io.IOException;

public class Main {
    static CStack<String> stack = new CStack<>(100);

    public static void main(String[] args) throws IOException {
        Converter c = new Converter();
        String postfix = "2.57*";
        String prefix = c.postfixToPrefix(postfix, stack);
        System.out.println("Prefix expression: " + prefix);
        // evaluate prefix
        System.out.println("Result: " + c.evaluatePrefix(prefix, stack));


        System.out.println("=== === === === === === === === === === === === === === === === === === === === ===");

        String infixExpression = "5 + 3 * 4.5";
        String postfixExpression = c.infixToPostfix(infixExpression, stack);
        System.out.println("Infix expression: " + infixExpression);
        System.out.println("Postfix expression: " + postfixExpression);
        System.out.println(c.evaluatePostfix(postfixExpression, stack));
        EquationParser ep = new EquationParser();
        String file =("C:\\Users\\osama\\DataStructure\\Data-Structure-\\ProjectII\\src\\main\\resources\\com\\example\\projectii\\DS-Proj2 (1).242");
        String s = ep.readFile(file);
        String ss = ep.extractTag(s);
        // System.out.println(s);
        // System.out.println("=== === === === === === === === === === === === === === === === === === === === ===");
        // System.out.println(ss);
        // System.out.println("=== === === === === === === === === === === === === === === === === === === === ===");
        // System.out.println(ep.isBalanced(ss, stack));
    }
}