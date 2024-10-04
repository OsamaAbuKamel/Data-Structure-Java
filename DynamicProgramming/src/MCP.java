// MCP(Minimum Cost Path) problem

import java.util.Arrays;

public class MCP {
    public static void main(String[] args) {

        int cost[][] = { { 1, 2, 3 }, { 4, 8, 2 }, { 1, 5, 3 } };
        System.out.print(minCostSpaceOptimized(cost, 3, 3));

    }

    /*
     * Recursive Solution
     * Time Complexity: O((M * N)^3)
     * Space Complexity: O(m*n)
     */
    public static int minCost(int cost[][], int m, int n) {
        if (n < 0 || m < 0) {
            return Integer.MAX_VALUE;
        } else if (m == 0 && n == 0) {
            return cost[m][n];
        } else {
            return cost[m][n]
                    + Math.min(minCost(cost, m - 1, n - 1), Math.min(minCost(cost, m - 1, n), minCost(cost, m, n - 1)));
        }
    }

    /*
     * Memoization Approach
     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n)
     */
    private static int minCostMemoized(int cost[][], int m, int n, int memo[][]) {
        if (n < 0 || m < 0) {
            return Integer.MAX_VALUE;
        } else if (m == 0 && n == 0) {
            return cost[m][n];
        }
        if (memo[m][n] != -1) {
            return memo[m][n];
        }
        memo[m][n] = cost[m][n]
                + Math.min(minCost(cost, m - 1, n - 1), Math.min(minCost(cost, m - 1, n), minCost(cost, m, n - 1)));
        return memo[m][n];

    }

    public static int minCostHelper(int[][] cost, int m, int n) {
        int R = 3;
        int C = 3;
        // Create a memoization table to store intermediate results
        int[][] memo = new int[R][C];
        for (int[] row : memo)
            Arrays.fill(row, -1); // Initialize memo table with -1

        // Call the memoized function to find the minimum cost
        return minCostMemoized(cost, m, n, memo);
    }

    /*
     * Tabulation DP Approach
     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n)
     */
    public static int minCostTabulation(int cost[][], int m, int n) {
        int i, j;
        int[][] table = new int[m + 1][n + 1];
        table[0][0] = cost[0][0];
        for (i = 1; i <= m; i++) {
            table[i][0] = table[i - 1][0] + cost[i][0];
        }
        for (j = 1; j <= n; j++) {
            table[0][j] = table[0][j - 1] + cost[0][j];
        }
        for (i = 1; i <= m; i++) {
            for (j = 1; j <= n; j++) {
                table[i][j] = Math.min(table[i - 1][j - 1], Math.min(table[i - 1][j], table[i][j - 1])) + cost[i][j];
            }
        }
        return table[m][n];
    }

    /*
     * S#4 DP space Optimized
     * Time Complexity: O(N * M)
     * Auxiliary Space: O(1)
     */
    public static int minCostSpaceOptimized(int cost[][], int r, int c) {
        for (int i = 1; i < r; i++) {
            cost[i][0] += cost[i - 1][0];
        }

        for (int i = 1; i < c; i++) {
            cost[0][i] += cost[0][i - 1];
        }

        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                cost[i][j] += Math.min(cost[i - 1][j - 1], Math.min(cost[i - 1][j], cost[i][j - 1]));
            }
        }
        return cost[r - 1][c - 1];

    }
}
