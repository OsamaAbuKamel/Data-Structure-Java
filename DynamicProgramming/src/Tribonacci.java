import java.util.HashMap;

public class Tribonacci {
    public static void main(String[] args) {
        System.out.println(trib(4));
        System.out.println(trib(70));

    }

    public static int trib(int n) {
        return trib(n, new HashMap<>());
    }

    private static int trib(int n, HashMap<Integer, Integer> memo) {
        if (n == 0 || n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        int result = trib(n - 1, memo) + trib(n - 2, memo) + trib(n - 3, memo);
        memo.put(n, result);
        return result;
    }
}
