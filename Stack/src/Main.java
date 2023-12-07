public class Main {
    public static void main(String[] args) {
        AStack<Integer> stack = new AStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        for (Integer integer : stack) {
            System.out.println(integer);
        }
        // LStack<Integer> stack = new LStack<>();
        // stack.push(1);
        // stack.push(2);
        // stack.push(3);
        // stack.push(4);
        // stack.push(5);
        // System.out.println(stack);
        // System.out.println("-----------------------------");
        // stack.pop();
        // stack.pop();
        // System.out.println(stack);
        // System.out.println("-----------------------------");
        // System.out.println(stack.peek());
    }
}