public class Anagrams {
    public static void main(String[] args) {
        String word = "cat";
        anagram(word, 0);
    }
    
    private static void anagram(String word, int index) {
        if (index == word.length() - 1) {
            System.out.println(word);
            return;
        }
        
        for (int i = index; i < word.length(); i++) {
            word = swap(word, index, i);
            anagram(word, index + 1);
            word = swap(word, index, i);
        }
    }
    
    private static String swap(String word, int i, int j) {
        char[] charArray = word.toCharArray();
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return new String(charArray);
    }
}
