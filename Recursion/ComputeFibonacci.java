import java.util.Scanner;

public class ComputeFibonacci {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an index for a Fibonacci number: ");
        int index = input.nextInt();
        System.out.println("Factorial of " + index + " is " + fib(index));
    }
    public static long fib(long index){
        if (index == 0)
            return 0;
        if (index == 1)
            return 1;
        return fib(index - 1) + fib(index - 2);
    }
}
