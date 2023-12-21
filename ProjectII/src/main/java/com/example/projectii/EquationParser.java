package com.example.projectii;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EquationParser {

    public boolean isBalanced(String input, CStack<String> stack) {
        int top = stack.createStack();
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