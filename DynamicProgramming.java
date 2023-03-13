import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicProgramming {
    public static void main(String[] args) {
        /*System.out.println(gridTravelerIterative(1,1)); //1
        System.out.println(gridTravelerIterative(2,3)); //3
        System.out.println(gridTravelerIterative(3,2)); //3
        System.out.println(gridTravelerIterative(3,3)); //6
        System.out.println(gridTravelerIterative(10,10)); //48620

        System.out.println(gridTraverlerRecursive(1,1)); //1
        System.out.println(gridTraverlerRecursive(2,3)); //3
        System.out.println(gridTraverlerRecursive(3,2)); //3
        System.out.println(gridTraverlerRecursive(3,3)); //6
        System.out.println(gridTraverlerRecursive(10,10)); //48620

        System.out.println(canSum(7,new int[]{2,3})); // true
        System.out.println(canSum(7,new int[]{5,3,4,7})); //true
        System.out.println(canSum(7,new int[]{2,4})); //false 
        System.out.println(canSum(7,new int[]{2,3}));//true
        System.out.println(canSum(300,new int[]{7,14})); //false

       System.out.println(howSum(7,new int[]{2,3})); 
        System.out.println(howSum(7,new int[]{5,3,4,7})); 
        System.out.println(howSum(7,new int[]{2,4})); 
        System.out.println(howSum(7,new int[]{2,3}));
        System.out.println(howSum(300,new int[]{7,14,5})); */

        //System.out.println(bestSum(7,new int[]{5,3,4,7})); 
       // System.out.println(bestSum(8,new int[]{2,3,5})); 
       // System.out.println(bestSum(9,new int[]{1,4,5})); 
        System.out.println(canConstructWord("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"}));
        System.out.println(canConstructWord("skateboard",
                                                new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
        System.out.println(canConstructWord("enterapotentpot", new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}));
        System.out.println(canConstructWord("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee"}));


    }


    private static boolean canConstructWord(String word, String[] wordbank) {
        Map<String, Boolean> memo = new HashMap<>();
        return canConstructWordMemo(word, wordbank, memo);
    }
    
    private static boolean canConstructWordMemo(String word, String[] wordbank, Map<String, Boolean> memo) {
        if(word == null || word.length() == 0) return true;
        if(memo.containsKey(word)) return memo.get(word);
        for(String w : wordbank) {
            if(word.startsWith(w)) {
                String suffix = word.substring(w.length());
                if(canConstructWordMemo(suffix, wordbank, memo)) {
                    memo.put(word, true);
                    return true;
            }
        }
        }
        memo.put(word, false);

        return false;
    }
    // Solution 1 :  Dynamic programming solution 
    //  Solution 2 : Attempt later 
        /*This is actually brute force because we are not memoising 
        //damn why did i think other wise 
        // m is the target sum
        // n is the length of array 
        // If I look at the decision tree, run time is O(n^m * n) &
        // O(m) space complexity 

        if(target == 0) {
            List<Integer> result = new ArrayList<>();
            return result;
        }
        if(target < 0) return null;

        for(int n : nums) {
            int remainder = target - n;
            List<Integer> path = howSum(remainder, nums);

            if(path != null) {

                path.add(n);
                return path;
            }
        }
        return null;*/

        // space O(m^2)
        // time O(n*m^2)




    private static List<Integer> bestSum(int target, int[] nums) {
        Map<Integer, List<Integer>> bestSumMemo  = new HashMap<>();
        bestSumMemo.put(0, new ArrayList<>());
        return bestSumMemoized(target, nums, bestSumMemo);

    }

    private static List<Integer> bestSumMemoized(int target, int[] nums, Map<Integer, List<Integer>> memo) {
        if(target < 0) return null;
        if(target == 0) return new ArrayList<>();
        if(memo.containsKey(target)) return new ArrayList<>(memo.get(target));
        for(int n : nums) {
            int rem = target - n; 
            List<Integer> path = bestSumMemoized(rem, nums, memo);
            if(path != null) {
                path.add(n);
                if(memo.get(target) == null ||  path.size() < memo.get(target).size()) {
                    memo.put(target, new ArrayList<>(path));

                }
            }
        }
        return memo.get(target);
    }
    //run time is O(n*m^2) &
    // O(m*m) space complexity 
    private static List<Integer> howSum(int target, int[] nums) {
        Map<Integer, List<Integer>> memo = new HashMap<>();
        memo.put(0, new ArrayList<>());
        return howSumMemo(target, nums, memo);

    }

    // memo is indexed from 0 -> n for our ca   se 
    private static List<Integer> howSumMemo(int target, int[] nums,Map<Integer, List<Integer>> memo) {
        if(target < 0 ) return null;
         // Since java is pass by reference we want to return a new 
         // list object - otherwise it corrupts the old data
        if(memo.containsKey(target)) return new ArrayList<>(memo.get(target));
        for(int n : nums) {
            int rem = target - n;
            List<Integer> path = howSumMemo(rem, nums, memo);
            if(path != null) {
                path.add(n);
                memo.put(target, path);
               //System.out.println("Target is " + target);
                //System.out.println(memo);
                // Since java is pass by reference we want to return a new 
                // list object - otherwise it corrupts the old data
                return new ArrayList<>(path);
            }
        }
        return null;
    }




    private static boolean canSum(int target, int[] nums) {
        Boolean[] memo = new Boolean[target+1]; // 0-> target
        memo[0] = true;
        return canSumMemo(target, nums, memo);
    }


    private static Boolean canSumMemo(int target, int[] nums, Boolean[] memo) {
       // System.out.println("Target is " + target);
      // System.out.println(Arrays.deepToString(memo));
        if(target == 0) return true;
        if(target < 0) return false;

        if(memo[target] != null) return memo[target];
        for(int n : nums) {
            if(canSumMemo(target -n , nums, memo)) {
                memo[target] = true;
                // Returning here means it wont calculate all the possible branches 
                // when we get a result. In case of bestSum we will not return 
                return true; 
            } 
        }
        memo[target] = false;
        return false;

    }
    // O(m*n) time complexity O(m*n) space 
    private static Integer gridTraverlerRecursive(int m , int n) {
        // another optimization is if memo[m][n] is same as memo[n][m]
        if(m == 1 && n ==1 ) return 1;
        if(m == 0 || n == 0) return 0;
        Integer[][] memo = new Integer[m + 1][n + 1 ];
        Arrays.fill(memo[0], 0);
        for(int i = 0 ; i <= m ; i++) {
            memo[i][0] = 0;
        }
        memo[1][1] = 1;
       return gridTraverlerRecursiveMemo(m, n , memo);
    }

    private static Integer gridTraverlerRecursiveMemo(int m, int n, Integer[][] memo) {

        if(memo[m][n] != null) return memo[m][n] ;
        memo[m][n] = gridTraverlerRecursiveMemo( m -1, n, memo) + gridTraverlerRecursiveMemo(m, n-1, memo);
        return memo[m][n];

    }
    // if you could only move from a cell down or to right, how many ways can you move from top left corner to the bottom right corner
    private static Integer gridTravelerIterative(int m, int n) {
        //iterative
       if(m == 1 && n ==1 ) return 1;
        if(m == 0 || n == 0) return 0;
        Integer[][] memo = new Integer[m + 1][n + 1 ];
        
        Arrays.fill(memo[0], 0);
        for(int i = 0 ; i <= m ; i++) {
            memo[i][0] = 0;
        }

        for(int i = 1; i <= m ; i++) {
            for(int j = 1; j <= n ; j++) {
                if(i == 1 && j == 1) memo[i][j] = 1;
                else {
                memo[i][j] = memo[i-1][j] + memo[i][j-1];
                }
            }
        } 
        return memo[m][n];
    }


}
