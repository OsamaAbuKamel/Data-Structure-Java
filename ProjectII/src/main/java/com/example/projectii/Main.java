package com.example.projectii;

public class Main {
    private CStack<String> stack;
    private Converter converter;
    private EquationParser equationParser;

    public Main() {
        this.stack = new CStack<>(350);
        this.converter = new Converter();
        this.equationParser = new EquationParser();
    }

    public boolean isBalanced(String equation) {
        return equationParser.isBalanced(equation, stack);
    }

    public String convertEquation(String equation, int j) {
        StringBuilder builder = new StringBuilder();
        stack.clear();
        for (int i = 0; i < 8; i++) {
            String s = equationParser.extract(equation, j, stack, i);
            if (j == 0) {
                if (i < 4 && s!=null) {
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
                } else if (i >= 4 && i < 8 && s!=null) {
                    String ss = converter.postfixToPrefix(s, stack);
                    double s2 = converter.evaluatePrefix(ss, stack);
                    if (i == 4) {
                        builder.append("\n").append("POSTFIX: ").append("\n");
                    }
                    builder.append(s.trim()).append("===>").append(ss).append("===>").append(s2).append("\n");
                }
            } else if (j == 1 ) {
                if (i == 0 && s!=null) {
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
                }
            } else if (j == 2) {
                if (i == 0&& s!=null) {
                    String ss = converter.postfixToPrefix(s, stack);
                    double s2 = converter.evaluatePrefix(ss, stack);
                    if (i == 0) {
                        builder.append("\n").append("POSTFIX: ").append("\n");
                    }
                    builder.append(s.trim()).append("===>").append(ss).append("===>").append(s2).append("\n");
                }
            } else if (j == 3) {
                if (i == 0)
                    builder.append(s);
            }
        }
        return builder.toString();
    }
}
//public class Main {
//    private CStack<String> stack;
//    private Converter converter;
//    private EquationParser equationParser;
//
//    public Main() {
//        this.stack = new CStack<>(300);
//        this.converter = new Converter();
//        this.equationParser = new EquationParser();
//    }
//
//    public boolean isBalanced(String equation) {
//        return equationParser.isBalanced(equation, stack);
//    }
//
//    public int size() {
//        return equationParser.size(stack);
//    }
//
//    public String convertEquation(String equation, int j) {
//        stack.clear();
//        StringBuilder builder = new StringBuilder();
//        String s = equationParser.extractEquations(equation, j, stack);
//        String[] ex = s.trim().split(",");
//        String exp = " ";
//        double value = 0;
//        int i = 0, k = 0;
//        for (String string : ex) {
//            string = string.replaceAll("^\\s+", "");
//            if (!isPostfix(string)) {
//                if (!equationParser.isBalance(string, stack)) {
//                    throw new IllegalArgumentException("Equation is not balanced");
//                }
//                exp = converter.infixToPostfix(string, stack);
//                value = converter.evaluatePostfix(exp, stack);
//                if (k == 0) {
//                    builder.append("INFIX: ").append("\n");
//                    k++;
//                }
//            } else {
//                exp = converter.postfixToPrefix(string, stack);
//                value = converter.evaluatePrefix(exp, stack);
//                if (i == 0) {
//                    builder.append("POSTFIX: ").append("\n");
//                    i++;
//                }
//            }
//            builder.append(string).append("===>").append(exp).append("===>").append(value).append("\n");
//        }
//        return builder.toString();
//    }
//
//    boolean isPostfix(String equation) {
//        if (equation != null) {
//            if (!equation.contains("(") || !equation.contains(")")) {
//                String[] exp = equation.split(" ");
//                if (exp.length >= 2 && Character.isLetterOrDigit(exp[1].charAt(0)))
//                    return true;
//            }
//        }
//        return false;
//    }
//}