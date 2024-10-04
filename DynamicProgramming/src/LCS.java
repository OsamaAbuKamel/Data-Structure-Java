import java.util.Arrays;

public class LCS {
    public static void main(String[] args) {
        String S1 = "AGGTAB";
        String S2 = "GXTXAYB";

        int m = S1.length();
        int n = S2.length();

        int[][] memo = new int[m + 1][n + 1];
        // Initialize the memo table with -1
        for (int i = 0; i <= m; i++) {
            Arrays.fill(memo[i], -1);
        }

        System.out.println("Length of LCS is "
                + lcsDP(S1, S2, m, n, memo));
        System.out.println("Length of LCS is "
                + lcsTab(S1, S2));

    }

    public static int lcsTab(String S1, String S2) {
        int m = S1.length();
        int n = S2.length();

        // Initializing a matrix of size (m+1)*(n+1)
        int[][] dp = new int[m + 1][n + 1];

        // Building dp[m+1][n+1] in bottom-up fashion
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j],
                            dp[i][j - 1]);
                }
            }
        }

        // dp[m][n] contains length of LCS for S1[0..m-1]
        // and S2[0..n-1]
        return dp[m][n];

    }

    public static int lcsDP(String s1, String s2, int m, int n, int[][] memo) {
        // Base Case
        if (m == 0 || n == 0) {
            return 0;
        }
        if (memo[m][n] != -1) {
            return memo[m][n];
        }
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return memo[m][n] = 1 + lcsDP(s1, s2, m - 1, n - 1, memo);
        }
        return memo[m][n] = Math.max(
                lcsDP(s1, s2, m, n - 1, memo),
                lcsDP(s1, s2, m - 1, n, memo));
    }

    public static int lcs(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return 1 + lcs(s1, s2, m - 1, n - 1);
        } else
            return Math.max(lcs(s1, s2, m, n - 1), lcs(s1, s2, m - 1, n));

    }

}
