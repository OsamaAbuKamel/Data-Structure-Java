package problems;

import java.util.Scanner;

import stack.LStack;

public class SortStack {
    public static void main(String[] args) {
        // LStack<Integer> lStack = new LStack<>();
        // lStack.push(44);
        // lStack.push(1);
        // lStack.push(41);
        // lStack.push(43);
        // sortRecursion(lStack);
        // System.out.println(lStack);
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int N = scanner.nextInt();
            LStack<Integer> lStack = new LStack<>();
            for (int j = 0; j < N; j++) {
                lStack.push(scanner.nextInt());
            }
            sort(lStack);
                System.out.print(lStack);
            
        }
        
    }
    public static void sort(LStack<Integer> stack){
        LStack<Integer> temp = new LStack<>();
        while (!stack.isEmpty()) {
            int top = stack.pop();
            while (!temp.isEmpty()&& top < temp.peek()) {
                stack.push(temp.pop());
            }
            temp.push(top);            
        }
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }
    public static void sortRecursion(LStack<Integer> stack){
        if (stack.isEmpty()) {
            return;
        }
        int top = stack.pop();
        sort(stack);
        insert(stack,top);
    }
    private static void insert(LStack<Integer> stack, int element) {
        if (stack.isEmpty()) {
            stack.push(element);
            return;
        }
        int top = stack.pop();
        insert(stack, element);
        stack.push(top);
    }


}