
public class Fibonacci {
    public static void main(String[] args) {
        int num = 50;
        System.out.println("Fibonacci of " + num + " is " + fib(num));
    }
    public static long fib(long index){
        if (index == 0)
            return 0;
        if (index == 1)
            return 1;
        return fib(index - 1) + fib(index - 2);
    }
}
