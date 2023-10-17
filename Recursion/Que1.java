public class Que1 {
    public static void main(String[] args) {
//        print(10);
        printNRec(10);
    }
    
    
    public static void  print(int n) {
        if (n >= 0) {
            System.out.println(n);
            print(n - 1);
        }
    }
    public static void  printNRec(int n) {
        if (n >= 0) {
            print(n - 1);
            System.out.println(n);
        }
    }
}
