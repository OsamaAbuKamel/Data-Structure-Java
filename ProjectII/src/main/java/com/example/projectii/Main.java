package com.example.projectii;

public class Main {
    private CStack<String> stack;
    private Converter converter;
    private EquationParser equationParser;

    public Main() {
        this.stack = new CStack<>(300);
        this.converter = new Converter();
        this.equationParser = new EquationParser();
    }

    public boolean isBalanced(String equation) {
        return equationParser.isBalanced(equation, stack);
    }

    public String convertEquation(String equation, int j) {
        StringBuilder builder = new StringBuilder();
        stack.clear();
        for (int i = 0; i < 7; i++) {
            if (j == 0) {
                String s = equationParser.extract(equation, 0, stack, i);
                if (i < 3) {
                    if (!equationParser.isBalance(equation, stack, j, i)) {
                        throw new IllegalArgumentException("Equation is not balanced");
                    } else {
                        String ss = converter.infixToPostfix(s, stack);
                        double s2 = converter.evaluatePostfix(ss, stack);
                        if (i == 0) {
                            builder.append("\n").append("INFIX: ").append("\n");
                        }
                        builder.append(s.trim()).append("===>").append(ss).append("===>").append(s2).append("\n");
                    }
                } else if (i >= 3) {
                    String ss = converter.postfixToPrefix(s, stack);
                    double s2 = converter.evaluatePrefix(ss, stack);
                    if (i == 3) {
                        builder.append("\n").append("POSTFIX: ").append("\n");
                    }
                    builder.append(s.trim()).append("===>").append(ss).append("===>").append(s2).append("\n");
                }
            } else if (j == 1) {
                String s = equationParser.extract(equation, 1, stack, i);
                if (i < 2) {
                    if (!equationParser.isBalance(equation, stack, j, i)) {
                        throw new IllegalArgumentException("Equation is not balanced");
                    } else {
                        String ss = converter.infixToPostfix(s, stack);
                        double s2 = converter.evaluatePostfix(ss, stack);
                        if (i == 0) {
                            builder.append("\n").append("INFIX: ").append("\n");
                            if (!equationParser.isBalance(equation, stack, j, i)) {
                                throw new IllegalArgumentException("Equation is not balanced");
                            }
                        }
                        builder.append(s.trim()).append("===>").append(ss).append("===>").append(s2).append("\n");
                    }
                } else if (i >= 2 && i <= 3) {
                    String ss = converter.postfixToPrefix(s, stack);
                    double s2 = converter.evaluatePrefix(ss, stack);
                    if (i == 2) {
                        builder.append("\n").append("POSTFIX: ").append("\n");
                    }
                    builder.append(s.trim()).append("===>").append(ss).append("===>").append(s2).append("\n");
                }
            }
        }
        return builder.toString();
    }
}