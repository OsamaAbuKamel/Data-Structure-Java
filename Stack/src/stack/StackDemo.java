package stack;
public class StackDemo {
    public static void main(String[] args) {
        CStack<Integer> stack = new CStack<>(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        
        System.out.println(stack.peek());
    }
}