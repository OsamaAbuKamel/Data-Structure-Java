//  Hacker rank Power Sum
// import java.util.Scanner;

public class PowerSum {
    static int cnt = 0;
    static long x = 100, n = 2;

    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // x = sc.nextLong();
        // n = sc.nextLong();
        powerSum(1, 0);
        System.out.println(cnt);
    }

    static void powerSum(long i, long sum) { // Change sum to long
        if (i == 50) {
            if (sum == x) {
                cnt++;
            }
            return;
        }
        if (sum + Math.pow(i, n) <= x) {
            powerSum(i + 1, (long) (sum + Math.pow(i, n))); // Cast to long

        }
        powerSum(i + 1, sum);
    }
}