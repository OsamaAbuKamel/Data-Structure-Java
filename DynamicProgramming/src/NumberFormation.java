import java.util.Arrays;

public class NumberFormation {
    static int[] dp;

    public static int solve(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        int ways = solve(n - 1) + solve(n - 3) + solve(n - 5);
        dp[n] = ways;
        return ways;
    }

    public static void main(String[] args) {
        int n = 6;
        dp = new int[n + 1];
        Arrays.fill(dp, -1);

        int result = solve(n);
        System.out.println("Total number of ways to form " + n + " is: " + result);
    }
}