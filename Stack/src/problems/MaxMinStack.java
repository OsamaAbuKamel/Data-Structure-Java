package problems;

import stack.List.LStack;

public class MaxMinStack extends LStack<Integer> {
    LStack<Integer> MinStack;
    LStack<Integer> MaxStack;

    public MaxMinStack() {
        MinStack = new LStack<>();
        MaxStack = new LStack<>();
    }

    @Override
    public void push(Integer value) {
        if (value <= min()) {
            MinStack.push(value);
        }
        if (value >= max()) {
            MaxStack.push(value);
        }
        super.push(value);
    }

    @Override
    public Integer pop() {
        int value = super.pop();
        if (value == max()) {
            MaxStack.pop();
        }
        if (value == min()) {
            MinStack.pop();
        }
        return value;
    }

    public Integer max() {
        return MaxStack.isEmpty() ? Integer.MIN_VALUE : MaxStack.peek();
    }

    public Integer min() {
        return MinStack.isEmpty() ? Integer.MAX_VALUE : MinStack.peek();
    }

    public static void main(String[] args) {
        MaxMinStack stack = new MaxMinStack();
        stack.push(2);
        stack.push(3);
        System.out.println(stack.max()); // 输出 1
        stack.pop();
        System.out.println(stack.max()); // 输出 2
    }
}