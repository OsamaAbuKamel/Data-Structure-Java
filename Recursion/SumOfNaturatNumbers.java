public class SumOfNaturatNumbers {
    public static void main(String[] args) {
        int num = 10 ;
        System.out.println("The sum of first 10 natural numbers is : " + recursiveSummation(num));

    }
    public static int recursiveSummation(int num){
        if (num <= 1) {
            return num;
        }
        return num + recursiveSummation(num - 1);
    }

}
