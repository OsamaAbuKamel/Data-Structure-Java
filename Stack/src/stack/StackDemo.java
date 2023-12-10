package stack;
public class StackDemo {
    public static void main(String[] args) {
        DStack<Integer> stack = new DStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(3);
        stack.push(6);
        // System.out.println(stack.length());
        // for (Integer integer : stack) {
        //     System.out.println(integer);
        // }
        // System.out.println(stack);

        AStack<Integer> stack1 = new AStack<>();
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        stack1.push(4);
        stack1.push(2);

        // System.out.println(stack1.length());
        // for (Integer integer : stack1) {
        //     System.out.println(integer);
        // }
        // System.out.println("------------------");
        // System.out.println(stack1);
        LStack<Integer> stack2 = new LStack<>();
        stack2.push(1);
        stack2.push(2);
        stack2.push(3);
        stack2.push(4);
        stack2.push(5);
        System.out.println(stack2.contains(2));
        
        // for (Integer integer : stack2) {
        //     System.out.println(integer);
        // }
        // System.out.println(stack2);
        // System.out.println("-----------------------------");
        // stack2.pop();
        // stack2.pop();
        // System.out.println(stack2);
        // System.out.println("-----------------------------");
        // System.out.println(stack2.peek());
    }
}