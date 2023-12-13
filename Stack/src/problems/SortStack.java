package problems;

import java.util.Scanner;

import stack.LStack;

public class SortStack {
    public static void main(String[] args) {
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
    //This method sorts an LStack of Integers
public static void sort(LStack<Integer> stack){
        //Create a new LStack to store the sorted elements
        LStack<Integer> temp = new LStack<>();
        //Loop until the stack is empty
        while (!stack.isEmpty()) {
            //Remove the top element from the stack
            int top = stack.pop();
            //Loop until the temp stack is empty or the top element is less than the top element of the temp stack
            while (!temp.isEmpty()&& top < temp.peek()) {
                //Push the top element of the temp stack onto the stack
                stack.push(temp.pop());
            }
            //Push the top element onto the temp stack
            temp.push(top);            
        }
        //Loop until the temp stack is empty
        while (!temp.isEmpty()) {
            //Push the top element of the temp stack onto the stack
            stack.push(temp.pop());
        }
    }
}