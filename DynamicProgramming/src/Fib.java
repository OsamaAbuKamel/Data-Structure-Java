import java.util.HashMap;

public class Fib {
    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println(fib(40));
        long end = System.nanoTime();
        System.out.println("Time taken by recursive method: " + (end - start) + " ms");
        System.out.println("=====================");
        start = System.nanoTime();
        System.out.println(fib2(40));
        end = System.nanoTime();
        System.out.println("Time taken by dynamic programming method: " + (end - start) + " ms");

    }

    public static int fib(int n) {
        return fib(n, new HashMap<>());
    }

    private static int fib(int n, HashMap<Integer, Integer> memo) {
        if (n == 0 || n == 1)
            return n;
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        int result = fib(n - 1, memo) + fib(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    public static int fib2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[dp.length - 1];
    }

}
