import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashTable {
    
    public static void main(String[] args) {
        List<int[]> result = twoSumAllPairs(new int[]{1, 5, 7, -1, 5}, 6);
        for(int[] r : result) {
            System.out.println(Arrays.toString(r));
        }
        System.out.println(threeSum(new int[]{1, 5, 7, -1, 5, 10,5,-1, -4, 4, 10,-4, -2, -2}, 6));

        System.out.println(twoSumAllUniquePairs(new int[]{1, 5, 7, -1, 5, 10,5,-1, -4, 4, 10,-4}, 6));

    }


    private static List<List<Integer>> threeSum(int[] nums, int target) {
        Set<List<Integer>> result = new HashSet<>();

        if(nums == null || nums.length == 0) return new ArrayList<>(result);
        Arrays.sort(nums);

        for(int i = 0 ; i < nums.length ; i++) {
            int remSum = target - nums[i];
            int start = i + 1;
            int end = nums.length - 1;
            while(start < end ) {
                int currSum = nums[start] + nums[end];
                if(currSum == remSum) {
                    result.add(List.of(nums[start], nums[end], nums[i]));
                    start++;
                    end--;
                   // while(start < end && nums[start] == nums[start - 1]) start++;
                    //while(start < end && nums[end] == nums[end + 1]) end--;

                } else if (currSum < remSum) {
                    start++;
                } else {
                    end--;
                }
            }
            while(i + 1 < nums.length && nums[i + 1] == nums[i]) {
                i++;
            }
    }
         return new ArrayList<>(result);

    }

    private static List<List<Integer>> twoSumAllUniquePairs(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        if(nums == null || nums.length == 0) return result;
        Arrays.sort(nums);
        int start = 0 ; 
        int end = nums.length - 1;

        while(start < end ) {
            int currSum = nums[start] + nums[end];
            if(currSum == target) {
                result.add(List.of(nums[start], nums[end]));
                start++;
                end--;
                while(start < end && nums[start] == nums[start - 1]) start++;
                while(start < end && nums[end] == nums[end + 1]) end--;

            } else if (currSum < target) {
                start++;
            } else {
                end--;
            }
        }
        return result;

    }


    private static List<int[]> twoSumAllPairs(int[] nums, int target) {
        List<int[]> result = new ArrayList<>();

        if(nums == null || nums.length == 0) return result;
        Map<Integer, List<Integer>> numIndexMap = new HashMap<>();
        
        for(int i = 0; i < nums.length ; i++) {
            int difference = target - nums[i];
            if(numIndexMap.containsKey(difference)) {
                List<Integer> indicesDifference = numIndexMap.get(difference);
                for(int index : indicesDifference) {
                    result.add(new int[]{index,i});
                }
            }
            List<Integer> indices = numIndexMap.getOrDefault(nums[i], new ArrayList<>());
            indices.add(i);
            numIndexMap.put(nums[i], indices);
        
        }
        return result;

    }


    //two sum brute force - check every pair 
    private int[] twoSumBruteForce(int[] nums, int target) {
        if(nums == null || nums.length == 0) return new int[]{};

        for(int i = 0; i < nums.length ; i++) {
            for(int j = i + 1; j < nums.length ; j++) {
                if (nums[i] + nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{};
    }

    // nums = [2,7,11,15], target = 9

    private int[] twoSumHashMap(int[] nums, int target) {
        if(nums == null || nums.length == 0) return new int[]{};
        Map<Integer, Integer> numIndexMap = new HashMap<>();
        
        for(int i = 0; i < nums.length ; i++) {
            int difference = target - nums[i];
            if(numIndexMap.containsKey(difference)) {
                return new int[]{numIndexMap.get(difference), i};
            }
            numIndexMap.put(nums[i], i);
        
        }
        return new int[]{};
    }
}
