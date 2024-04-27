public class CountA {
    public static void main(String[] args) {
        System.out.println(countA("osama kamel"));
    }
    public static int countA(String s) {
        return countA(s,0);
    }
    
    private static int countA(String s, int i) {
        if (s==null||s.isEmpty() || i>=s.length()){
            return 0;
        }
        if (s.charAt(i) == 'a'){
            return 1+countA(s,i+1);
        }
        return countA(s,i+1);
    }
}
