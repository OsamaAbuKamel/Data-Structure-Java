package Problem;

import java.util.HashMap;

public class Anagram {
    public static void main(String[] args) {
        String a = "car";
        String b = "tar";
        System.out.println(checkAnagram(a, b));
    }

    static boolean checkAnagram(String a, String b) {
        char arr1[] = a.toCharArray();
        char arr2[] = b.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> map1 = new HashMap<>();
        for (int i = 0; i < arr1.length; i++) {
            if (map.get(arr1[i]) == null) {
                map.put(arr1[i], 1);
            } else {
                Integer count = (int) map.get(arr1[i]);
                map.put(arr1[i], ++count);
            }
        }
        for (int i = 0; i < arr2.length; i++) {
            if (map1.get(arr2[i]) == null) {
                map1.put(arr2[i], 1);
            } else {
                Integer count = (int) map1.get(arr2[i]);
                map1.put(arr2[i], ++count);
            }
        }
        if (map.equals(map1))
            return true;
        else
            return false;
    }
}
