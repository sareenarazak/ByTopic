import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Backtracking {

    private static Map<Character, List<Character>> digitToLetterMap;
    static {
        digitToLetterMap = new HashMap<>();
        digitToLetterMap.put('2', Arrays.asList('a','b','c'));
        digitToLetterMap.put('3', Arrays.asList('d','e','f'));
        digitToLetterMap.put('4', Arrays.asList('g','h','i'));
        digitToLetterMap.put('5', Arrays.asList('j','k','l'));
        digitToLetterMap.put('6', Arrays.asList('m','n','o'));
        digitToLetterMap.put('7', Arrays.asList('p','q','r','s'));
        digitToLetterMap.put('8', Arrays.asList('t','u','v'));
        digitToLetterMap.put('9', Arrays.asList('w','x','y','z'));
    }    


    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
        System.out.println(combinationSum(new int[]{2,3,6,7}, 7));
        System.out.println(combinationSumBottomUp(new int[]{2,3,6,7}, 7));

    }

    /**
     * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
        A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
        Example 1:
        Input: digits = "23"
        Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

        Example 2:
        Input: digits = ""
        Output: []
        Example 3:
        Input: digits = "2"
        Output: ["a","b","c"]

        Constraints:
        0 <= digits.length <= 4
        digits[i] is a digit in the range ['2', '9'].
    */

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        
        backtrack(0, digits, result, new ArrayList<>());
        return result;
    }

    public static void backtrack(int start, String digits, List<String> result, List<Character> current) {
        if (current.size() == digits.length()) {
            String currentString = current.stream().map(String::valueOf).collect(Collectors.joining());
            result.add(currentString);
            return;
        }

            List<Character> possibleCharacters = digitToLetterMap.get(digits.charAt(start));

            //for possible states of current index 
            for(char c : possibleCharacters) {
                current.add(c);
                // we dont do a for loop here since we are doing that using recursion
                backtrack(start + 1, digits, result, current);
                current.remove(current.size()-1);
        }

    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        // sorting needed to avoid duplicates, since we only go from least to most, we dont do 3,2,2 when we already have 2,2,3 
        // Passing the start index and using that to backtrack in order also required to avoid duplicates 
        Arrays.sort(candidates);
        backtrackComboSum(0,target, candidates, result, new ArrayList<>());
        return result;
        
    }

    private static void backtrackComboSum(int index, int sum, int[] nums, List<List<Integer>> result, List<Integer> path) {
        if(sum < 0) return;
        if(sum == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i = index; i < nums.length; i++) {     
            path.add(nums[i]);
            backtrackComboSum(i, sum - nums[i] , nums, result, path);
            path.remove(path.size()-1);             
        }
    }


    public static List<List<Integer>> combinationSumBottomUp(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        // Another way to avoid duplicates is to build up the sum from bottom up --> we make sure we never go down a bath where the sum is already been explored. 
        backtrackComboSum(0, 0,target, candidates, result, new ArrayList<>());
        return result;
        
    }

    private static void backtrackComboSum(int index, int currentSum, int sum, int[] nums, List<List<Integer>> result, List<Integer> path) {
        if(currentSum > sum) return;
        if(currentSum == sum) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i = index; i < nums.length; i++) {     
            path.add(nums[i]);
            currentSum += nums[i];        
            backtrackComboSum(i, currentSum, sum , nums, result, path);
            path.remove(path.size()-1);     
            currentSum -= nums[i];        
        }
    }

}
