public class Pow {
    public static void main(String[] args) {
        System.out.println(powIteration(2, 2));
        System.out.println("=================");
        System.out.println(powRe(2, 2));
        System.out.println("=================");
        System.out.println(pow(2, 2));
    }

    /**
     * Iterative approach (powIteration):
     * Straightforward but least efficient (O(n) time complexity).
     */
    public static int powIteration(int x, int n) {
        int res = 1;
        for (int i = 0; i < n; i++) {
            res *= x;
        }
        return res;
    }

    /**
     * Recursive Approach with Optimization
     * Uses divide-and-conquer but calculates pow(x, n/2) twice, leading to
     * redundant calls (O(n) time complexity).
     */
    public static int powRe(int x, int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return x;
        }
        if (n % 2 == 0) {
            return pow(x, n / 2) *
                    pow(x, n / 2);
        } else
            return pow(x, n / 2) *
                    pow(x, n / 2) * x;
    }

    /**
     * Recursive approach with optimization (pow):
     * Most efficient due to reduced redundant calculations (O(log n) time
     * complexity).
     */
    public static int pow(int x, int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return x;
        }
        int res = pow(x, n / 2);
        if (n % 2 == 0) {
            return res * res;
        } else
            return res * res * x;
    }
}
