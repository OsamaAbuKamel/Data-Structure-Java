import java.util.Scanner;

public class PrincessFarida {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // Number of test cases

        for (int i = 1; i <= t; i++) {
            int n = scanner.nextInt(); // Number of monsters
            int[] coins = new int[n];
            for (int j = 0; j < n; j++) {
                coins[j] = scanner.nextInt();
            }
            long result = solve(coins);
            System.out.println("Case " + i + ": " + result);
        }
    }

    public static long solve(int[] coins) {
        int n = coins.length;
        if (n == 0) {
            return 0;
        }

        // dp[i] stores the maximum coins Farida can collect up to monster i
        long[] dp = new long[n];
        dp[0] = coins[0];
        if (n > 1) {
            dp[1] = Math.max(coins[0], coins[1]);
        }

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + coins[i]);
        }

        return dp[n - 1];
    }
}