/*
 * Given a number n, find all binary sequences of length 2n such that sum of first n bits is same as sum of last n bits.
 */

public class BinarySequences {
    static int n = 2;

    public static void main(String[] args) {
        // n = sc.nextInt();
        binarySequences("", 0, 0);

    }

    public static void binarySequences(String s, int sum, int sum2) {
        if (s.length() == 2 * n) {
            if (sum == sum2) {
                System.out.println(s);
            }
            return;
        }
        binarySequences(s + "0", sum, sum2);
        if (s.length() < n) {
            binarySequences(s + "1", sum + 1, sum2);
        } else
            binarySequences(s + "1", sum, sum2 + 1);

    }

}
