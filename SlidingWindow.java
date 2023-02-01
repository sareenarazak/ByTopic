import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SlidingWindow {

    public static void main(String[] args) {
        System.out.println(longestSubstringKDistinctSW("araaci",2)); //4
        System.out.println(longestSubstringKDistinctSW("araaci",1)); //2
        System.out.println(longestSubstringKDistinctSW("cbbebi",3)); //5
        System.out.println(longestSubstringKDistinctSW("hq",2)); //2

    }
    
    /**
     * Longest Substring with K Distinct Characters (medium)
     * 
     * Problem Statement
     * 
     * Given a string, find the length of the longest substring in it with no more
     * than K distinct characters.
     * 
     * You can assume that K is less than or equal to the length of the given
     * string.
     * 
     * Example 1:
     * 
     * Input: String="araaci", K=2
     * Output: 4
     * Explanation: The longest substring with no more than '2' distinct characters
     * is "araa".
     * Example 2:
     * 
     * Input: String="araaci", K=1
     * Output: 2
     * Explanation: The longest substring with no more than '1' distinct characters
     * is "aa".
     * Example 3:
     * 
     * Input: String="cbbebi", K=3
     * Output: 5
     * Explanation: The longest substrings with no more than '3' distinct characters
     * are "cbbeb" & "bbebi".
     */

     // There is some bug here - I think one for loop is misisng 
     // debug later 
     private static int longestSubstringKDistinctBF(String pattern, int k) {
        if(pattern == null || pattern.length() == 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int start = 0 ; start < pattern.length(); start++) {
            Set<Character> charSet = new HashSet<>();
            for (int end = start; end < pattern.length(); end++) {
                    char endChar = pattern.charAt(end);
                    if(charSet.size() < k) {
                        charSet.add(endChar);

                    } else {

                        if(!charSet.contains(endChar)){
                            break;
                        }
                    }
                    max = Math.max(max, end - start + 1 );

            } 

        }
        return max;
     }

     // O(N) time 
     // O(K) space
     private static int longestSubstringKDistinctSW(String pattern, int k) {
        if(pattern == null || pattern.length() == 0 || pattern.length() < k ) return 0;
        int max = Integer.MIN_VALUE;
        int start = 0;
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int end = 0; end < pattern.length(); end++) {
            char endChar = pattern.charAt(end);
            freqMap.put(endChar, freqMap.getOrDefault(endChar, 0) + 1);

            while(freqMap.size() > k) {
                char startChar = pattern.charAt(start);
                int startCharFreq  = freqMap.getOrDefault(startChar, 0) -1 ;
                if(startCharFreq == 0)  { 
                    freqMap.remove(startChar);
                } 
                else {
                    freqMap.put(startChar, startCharFreq);
                }
                start++;
            }

            max = Math.max(max, end - start + 1 );

        }

        return max;
    }


    /**
     * Given a string and a pattern, find out if the string contains any permutation
     * of the pattern.
     * 
     * Permutation is defined as the re-arranging of the characters of the string.
     * For example, “abc” has the following six permutations:
     * 
     * abc
     * acb
     * bac
     * bca
     * cab
     * cba
     * If a string has ‘n’ distinct characters, it will have
     * n! permutations.
     * 
     * Example 1:
     * 
     * Input: String="oidbcaf", Pattern="abc"
     * Output: true
     * Explanation: The string contains "bca" which is a permutation of the given
     * pattern.
     * Example 2:+
     * 
     * Input: String="odicf", Pattern="dc"
     * Output: false
     * Explanation: No permutation of the pattern is present in the given string as
     * a substring.
     * Example 3:
     * 
     * Input: String="bcdxabcdy", Pattern="bcdyabcdx"
     * Output: true
     * Explanation: Both the string and the pattern are a permutation of each other.
     * Example 4:
     * 
     * Input: String="aaacb", Pattern="abc"
     * Output: true
     * Explanation: The string contains "acb" which is a permutation of the given
     * pattern.
     */

     // Algorithm 
     // 1. Make a Hashmap of pattern : key characters in the pattern, Value is frequency
     // 2. clone the map 
     // 3. start at index 0 of the word 
     // 4. keep going till we hit one letter that is in the pattern
     // 5. go from start ->  start + patternlength - 1 --> keep decrementing frequency if the letter matches --> remove letters from map if the frequency hits 0 
     // 6. If frequency < 0 then break , if new character appears then break. set the start = start + 1; reset the hasmap repeat  
     //7. if any time in the loop our hashmap is empty then we return true
     // 8. else return false after checking 
    
    private static boolean foundPatternPerm(String word, String pattern) {
        if (word == null || word.length() == 0 || word.length() < pattern.length()) return false; 
        if(pattern == null || pattern.length() == 0 || word.equals(pattern)) return true;
        int patternLength = pattern.length();
        int wordLength = word.length();
        // Step 1 
        Map<Character, Integer> freqMap = new HashMap<>();
        for(char p : pattern.toCharArray()) {
            freqMap.put(p, freqMap.getOrDefault(p, 0) + 1);

        }

        //Step 2
        // step 3  
        int start = 0;
        Map<Character, Integer> cloneFreqMap = freqMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        while(start < wordLength) {
            char startChar = word.charAt(start);
            if(cloneFreqMap.containsKey(word.charAt(start))) {
                int end = start + patternLength - 1;
                for(int i = start; i < end && i < wordLength; i++) {
                    int startCharFreq = cloneFreqMap.get(startChar) - 1;
                    if(startCharFreq == 0) cloneFreqMap.remove(startChar);
                    else {
                        cloneFreqMap.put(startChar, startCharFreq);
                    }
    

                }
            }
        }


    }
}
