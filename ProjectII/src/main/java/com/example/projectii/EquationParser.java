package com.example.projectii;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EquationParser {
    private CStack<String> stack;
    public EquationParser() {
        this.stack = new CStack<>(15);
    }
    public boolean isBalanced(String input) {
        int top = stack.getList().createList();

        return stack.isEmpty(top);
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
        String file = ("C:\\Users\\osama\\DataStructure\\Data-Structure-\\ProjectII\\src\\main\\resources\\com\\example\\projectii\\DS-Proj2 (1).242");
        String s = ep.readFile(file);
        String ss = ep.extractTag(s);
        System.out.println(s);
        System.out.println("=== === === === === === === === === === === === === === === === === === === === ===");
        System.out.println(ss);
        System.out.println("=== === === === === === === === === === === === === === === === === === === === ===");
    }
}