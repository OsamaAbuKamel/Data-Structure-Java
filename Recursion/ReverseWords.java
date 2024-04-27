public class ReverseWords {
    public static void main(String[] args) {
        String n = "cat is running"; ;
        System.out.println(reverseWords(n));
    }
    public static String  reverseWords(String s) {
        //Check if the string is null or empty or does not contain any spaces
        if (s == null || s.isEmpty() ||!s.trim().contains(" "))
            return s;
        //Trim the string to remove any leading or trailing spaces
        s=s.trim();
        //Find the last space in the string
        int lastSpace = s.lastIndexOf(' ');
        //Get the last word from the string
        String lastWord = s.substring(lastSpace+1);
        //Return the last word reversed plus the reversed remaining part of the string
        return lastWord +" "+  reverseWords(s.substring(0, lastSpace));
        }
    
}
