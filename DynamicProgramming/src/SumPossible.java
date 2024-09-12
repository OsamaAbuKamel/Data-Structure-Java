import java.util.HashMap;
import java.util.List;

public class SumPossible {
    public static void main(String[] args) {
        long start = System.nanoTime();
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        int amount = 10;
        System.out.println(sumPossible(amount, numbers));
        long end = System.nanoTime();
        System.out.println("Time taken: " + (end - start) + " nanoseconds");
    }

    public static boolean sumPossible(int amount, List<Integer> numbers) {
        return sumPossible(amount, numbers, new HashMap<>());
    }

    private static boolean sumPossible(int amount, List<Integer> numbers, HashMap<Integer, Boolean> memo) {
        if (amount == 0) {
            return true;
        }
        if (amount < 0) {
            return false;
        }
        if (memo.containsKey(amount)) {
            return memo.get(amount);
        }
        for (Integer nums : numbers) {
            int subAmount = amount - nums;
            if (sumPossible(subAmount, numbers, memo)) {
                memo.put(amount, true);
                return true;
            }
        }
        memo.put(amount, false);
        return false;
    }

}
