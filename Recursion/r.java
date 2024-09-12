/*
 * Print all possible strings of length k that can be formed from a set of n characters
 */
public class r {
    public static void main(String[] args) {
        String[] set = { "a", "b" };
        printStrings(set, "", 3);
    }

    public static void printStrings(String[] set, String s, int k) {
        if (s.length() == k) {
            System.out.println(s);
            return;
        }
        for (int i = 0; i < set.length; i++) {
            printStrings(set, s + set[i], k);
        }
    }
}
