package com.example.projectii;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EquationParser {

    public boolean isBalanced(String exp,CStack<String> stack){
       
        int top = stack.createStack();
        char[] chars = exp.toCharArray();
        for (char c : chars ) {
            if (c=='<') {
                stack.push(String.valueOf(c),top);
            }else if (c=='>') {
                if (stack.isEmpty(top)) {
                    return false;
                }
                char open = stack.pop(top).charAt(0);
                if (!(open=='<'&& c=='>')) {
                    return false;
                }
            }
        }
        return stack.isEmpty(top);
    }

    
    public String extractInfixPostFix(String exp,int i) {
        String sections = extractSections(exp, i);
        StringBuilder result = new StringBuilder();
        for (String section : sections.split("\n\n")) {
            ArrayList<String> equations = extractInfixPostfixFromSection(section);
            result.append("Infix Equations: ").append(equations.get(0)).append("\n");
            result.append("Postfix Equations: ").append(equations.get(1)).append("\n\n");
        }
        return result.toString();
    }

    private ArrayList<String> extractInfixPostfixFromSection(String section) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> infixEquations = extractEquations(section, "<infix>", "</infix>");
        ArrayList<String> postfixEquations = extractEquations(section, "<postfix>", "</postfix>");
        result.add(infixEquations.get(0)); // Add the first (and possibly only) infix equation
        result.add(postfixEquations.get(0)); // Add the first (and possibly only) postfix equation
        return result;
    }

    private ArrayList<String> extractEquations(String section, String startTag, String endTag) {
        ArrayList<String> equations = new ArrayList<>();
        String escapedStartTag = startTag.replaceAll("([.*+?^=!:${}()|[\\]/\\\\])", "\\\\$1");
        String escapedEndTag = endTag.replaceAll("([.*+?^=!:${}()|[\\]/\\\\])", "\\\\$1");
        
        // Use the escaped tags in the regular expression for splitting
        String[] splitExp = section.split(escapedStartTag + "|" + escapedEndTag);
        for (String string : splitExp) {
            // String equation
        }
                // int startIndex = section.indexOf(startTag);
        // int endIndex = section.indexOf(endTag);
        // while (startIndex != -1 && endIndex != -1) {
        //     String equation = section.substring(startIndex + startTag.length(), endIndex);
        //     equation = equation.replace("<equation>", "").replace("</equation>", ","); // Remove the equation tags
        //     equations.add(equation);
        //     startIndex = section.indexOf(startTag, endIndex + endTag.length());
        //     endIndex = section.indexOf(endTag, startIndex + startTag.length());
        // }
        return equations;
    }
    
    private String extractSections(String exp,int i) {
        ArrayList<String> sections = new ArrayList<>();
        String[] splitExp = exp.split("<section>|</section>");
        for (int index = 1; index < splitExp.length; index++) {
            sections.add(splitExp[index]);
        }
        return sections.get(i);
    }
    // public String extractSections(String exp,int i){
    //     ArrayList<String> sections = new ArrayList<>();
    //     String startTag = "<section>";
    //     String endTag = "</section>";
    //     int startIndex = String.valueOf(exp).indexOf(startTag);
    //     int endIndex = String.valueOf(exp).indexOf(endTag);
    //     while (startIndex != -1 && endIndex != -1) {
    //         sections.add(exp.substring(startIndex + startTag.length(), endIndex));
    //         startIndex = String.valueOf(exp).indexOf(startTag, endIndex + endTag.length());
    //         endIndex = String.valueOf(exp).indexOf(endTag, startIndex + startTag.length());
    //     }
        
    //     return sections.get(i);
        // String startTag = "<section>";
        // String endTag = "</section>";
        // int startIndex = String.valueOf(exp).indexOf(startTag);
        // int endIndex = String.valueOf(exp).indexOf(endTag);
        //     if (startIndex != -1 && endIndex != -1) {
        //     // return exp.substring(startIndex + startTag.length(), endIndex);
        // }
        // String startTag1 = "<infix>";
        // String endTag1 = "</infix>";
        // startIndex = String.valueOf(exp).indexOf(startTag1);
        // endIndex = String.valueOf(exp).indexOf(endTag1);
        //     if (startIndex != -1 && endIndex != -1) {
        //     // return exp.substring(startIndex + startTag1.length(), endIndex);
        // }
        // String startTag2 = "<postfix>";
        // String endTag2 = "</postfix>";
        // startIndex = String.valueOf(exp).indexOf(startTag2);
        // endIndex = String.valueOf(exp).indexOf(endTag2);
        //     if (startIndex != -1 && endIndex != -1) {
        //     // return exp.substring(startIndex + startTag2.length(), endIndex);
        // }
        // String startTag3 = "<equation>";
        // String endTag3 = "</equation>";
        // startIndex = String.valueOf(exp).indexOf(startTag3);
        // endIndex = String.valueOf(exp).indexOf(endTag3);
        //     if (startIndex != -1 && endIndex != -1) {
        //     // return exp.substring(startIndex + startTag3.length(), endIndex);
        // }

        
        // return null;
    // }

    public String readFile(String filePath) {
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

    public String extractTag(String input) {
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

}