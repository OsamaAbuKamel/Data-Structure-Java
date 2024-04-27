
public class Factorial {
    public static void main(String[] args) {
        int n = 5;
        System.out.println("Factorial of " + n + " is " + factorial(n));
        
    }
    public static long factorial(int n){
        if (n == 0)
            return 1;
        else
            return n * factorial(n -1);
    }
    
}
