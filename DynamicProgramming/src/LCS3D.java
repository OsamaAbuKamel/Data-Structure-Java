public class LCS3D {
    public static void main(String[] args) {
        // ! Solution #1 using recursion
        // char[] X = "geeks".toCharmemoay();
        // char[] Y = "geeksfor".toCharmemoay();
        // char[] Z = "geeksforge".toCharmemoay();
        // int m = X.length;
        // int n = Y.length;
        // int o = Z.length;
        // System.out.println("Length of LCS is " + lcs(X, Y, Z, m, n, o));
        // ! Solution #2 using memoization
        // int[][][] memo = new int[100][100][100];
        // for (int i = 0; i < 100; i++) {
        // for (int j = 0; j < 100; j++) {
        // for (int k = 0; k < 100; k++) {
        // memo[i][j][k] = -1;
        // }
        // }
        // }

        // String X = "geeks";
        // String Y = "geeksfor";
        // String Z = "geeksforgeeks";
        // int m = X.length();
        // int n = Y.length();
        // int o = Z.length();
        // System.out.print("Length of LCS is " + lcsMemo(X, Y, Z, m, n, o, memo));

        // ! Solution #3 using tabulation
        String X = "geeks";
        String Y = "geeksfor";
        String Z = "geeksforgeeks";
        System.out.println("Length of LCS is " + lcsTab(X, Y, Z));

    }

    static int lcsTab(String X, String Y, String Z) {
        int m = X.length();
        int n = Y.length();
        int o = Z.length();
        int[][][] dp = new int[m + 1][n + 1][o + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= o; k++) {
                    if (X.charAt(i - 1) == Y.charAt(j - 1) && X.charAt(i - 1) == Z.charAt(k - 1)) {
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    } else {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
                    }
                }
            }
        }
        return dp[m][n][o];
    }

    static int lcsMemo(String X, String Y, String Z, int m, int n, int o, int[][][] memo) {
        // base case
        if (m == 0 || n == 0 || o == 0)
            return 0;
    
        // if the same state has already been
        // computed
        if (memo[m - 1][n - 1][o - 1] != -1)
            return memo[m - 1][n - 1][o - 1];

        // if equal, then we store the value of the
        // function call
        if (X.charAt(m - 1) == Y.charAt(n - 1) &&
                Y.charAt(n - 1) == Z.charAt(o - 1)) {

            // store it in memo to avoid further repetitive work
            // in future function calls
            memo[m - 1][n - 1][o - 1] = 1 + lcsMemo(X, Y, Z, m - 1,
                    n - 1, o - 1, memo);
            return memo[m - 1][n - 1][o - 1];
        } else {

            // store it in memo to avoid further repetitive work
            // in future function calls
            memo[m - 1][n - 1][o - 1] = Math
                    .max(
                            lcsMemo(X, Y, Z, m, n - 1, o, memo),
                            Math.max(lcsMemo(X, Y, Z, m - 1, n, o,
                                    memo),
                                    lcsMemo(X, Y, Z, m, n, o - 1, memo)));
            return memo[m - 1][n - 1][o - 1];
        }
    }

    static int lcs(char[] X, char[] Y, char[] Z,
            int m, int n, int o) {

        // base case
        if (m == 0 || n == 0 || o == 0)
            return 0;

        // if equal, then check for next combination
        if (X[m - 1] == Y[n - 1] && Y[n - 1] == Z[o - 1]) {

            // recursive call
            return 1 + lcs(X, Y, Z, m - 1, n - 1, o - 1);
        } else {

            // return the maximum of the three other
            // possible states in recursion
            return Math.max(lcs(X, Y, Z, m, n - 1, o),
                    Math.max(lcs(X, Y, Z, m - 1, n, o),
                            lcs(X, Y, Z, m, n, o - 1)));
        }

    }

}
