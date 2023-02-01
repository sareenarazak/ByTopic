import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringProblems {
    public static void main(String[] s) {
        System.out.println(isAnagram("cat", "tac")); // true
        System.out.println(isAnagram("car", "rat")); // false

        System.out.println(groupAnagrams(List.of("eat","tea","tan","ate","nat","bat")));  //["bat"],["nat","tan"],["ate","eat","tea"]]
        System.out.println(groupAnagrams2(List.of("eat","tea","tan","ate","nat","bat")));  //["bat"],["nat","tan"],["ate","eat","tea"]]
    }
    public static boolean isAnagram(String s, String t) {
        if(s== null || t == null || s.length() != t.length()) return false;
        int[] frequency = new int[26];
        for(int i = 0 ; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            frequency[sc- 'a']++;
            frequency[tc-'a']--;
        }

        for(int f :frequency) {
            if(f != 0) return false;
        }
        return true;    
    }

    // Method 1 - sort the words and use as key
    // Method 2 - Create a frequency map and convert to string to use as key 
    // While creating string, dont forget to use String.valueOf(chars) or Array.toString(array )
    // Time O(Nlong N * K ) N length of string, K size of words list
    // Space (O N*K)
     public static List<List<String>> groupAnagrams(List<String> words) {
        if(words == null || words.size() == 0) return new ArrayList<>();
        Map<String, List<String>> repWordsMap = new HashMap<>();

        for(String word : words) {
            char[] letters = word.toCharArray();
            Arrays.sort(letters);
            String key = String.valueOf(letters);
            List<String> group = repWordsMap.getOrDefault(key, new ArrayList<>());
            group.add(word);
            repWordsMap.put(key, group);
        }
        return repWordsMap.values().stream().collect(Collectors.toList());
    }

    // time space (O N*K)

    public static List<List<String>> groupAnagrams2(List<String> words) {
        if(words == null || words.size() == 0) return new ArrayList<>();
        Map<String, List<String>> repWordsMap = new HashMap<>();

        for(String word : words) {
            int[] frequency = new int[26];
            for(int i = 0 ; i < word.length(); i++) {
                char c = word.charAt(i);
                frequency[c-'a']++;
            }

            String key = Arrays.toString(frequency);
            List<String> group = repWordsMap.getOrDefault(key, new ArrayList<>());
            group.add(word);
            repWordsMap.put(key, group);
        }
        return repWordsMap.values().stream().collect(Collectors.toList());
    }

}
