import java.util.Arrays;

public class BinarySearch{
    public static void main(String[] args) {
        System.out.println(findFloor(new int[]{1,2,8,10,11,12,19}, 0)); //-1
        System.out.println(findFloor(new int[]{1,2,8,10,11,12,19}, 5)); //1

        System.out.println(lastOccurence(new int[]{3,4,13,13,13,20,40},13)); //4
        System.out.println(lastOccurence(new int[]{3,4,13,13,13,20,40},60)); // -1
        System.out.println(lastOccurence(new int[]{3,4,13,13,13,20,40},3)); //0
        System.out.println(lastOccurence(new int[]{3,4,13,13,13,20,40,40,80},40)); //7
        System.out.println(lastOccurence(new int[]{3,4,13,13,13,20,40,40,80},80)); //8

        System.out.println(firstOccurence(new int[]{3,4,13,13,13,20,40},13)); //2
        System.out.println(firstOccurence(new int[]{3,4,13,13,13,20,40},60)); // -1
        System.out.println(firstOccurence(new int[]{3,4,13,13,13,20,40},3)); //0
        System.out.println(firstOccurence(new int[]{3,4,13,13,13,20,40,40,80},40)); //6
        System.out.println(firstOccurence(new int[]{3,4,13,13,13,20,40,40,80},80)); //8

       System.out.println(Arrays.toString(findFirstAndLastOccurence( new int[]{5,7,7,8,8,10},8))); // 3,4

       System.out.println(searchInRotatedArray(new int[]{4,5,6,7,0,1,2},0)); //4


    }

    // Problem 1 
    /** 
     * Given a sorted array arr[] of size N without duplicates, and given a value x. 
     * Floor of x is defined as the largest element K in arr[] such that K is smaller than or equal to x.
     * Find the index of K(0-based indexing).
     * N = 7, x = 0  arr[] = {1,2,8,10,11,12,19} Output: -1
     * 
     * N = 7, x = 5 
     * arr[] = {1,2,8,10,11,12,19}
     * Output: 1
     * Solution : check if the first element is greater than x , if so return -1. Else do binary search until 
     * finding an element that is less than or equal to x, so it is a modified binary search with either returning when 
     * element is found. If current number is > search towards left of the current number. if < check towards right of the element 
     * too but while saving the number in result 
     */
    private static int findFloor(int[] nums, int key) {
        if(nums == null || nums.length == 0 || nums[0] > key ) return -1;

        int length = nums.length;
        if(nums[length -1] <= key) return length-1;
        
        
        int left = 0; int right = length -1;
        int index  = -1;
        while(left <= right) {
            int mid = left + (right - left)/2;
            if(nums[mid] == key ) return key;
            else if(nums[mid] > key) right = mid -1;
            else {
                index = mid;
                left = mid + 1;
            }
        }
        return index;
    }

    /**
     * Last occurrence in a sorted array 
     * Given a sorted array of N integers, write a program to find the index of the last occurrence of the target key. If the target is not found then return -1.
     * Note: Consider 0 based indexing
     * Examples:
     * Example 1:
     * Input: N = 7, target=13, array[] = {3,4,13,13,13,20,40}
     * Output: 4
     * Explanation: As the target value is 13 , it appears for the first time at index number 2.
     *  Example 2:
     * Input: N = 7, target=60, array[] = {3,4,13,13,13,20,40}
     * Output: -1
     * Explanation: Target value 60 is not present in the array 
     */

     private static int lastOccurence(int[] nums, int key) {
        if(nums == null || nums.length == 0)  return -1;
        int start = 0;
        int end = nums.length -1;
        while(start <= end) {
            int mid = start  + (end - start )/2;
            if((mid == nums.length - 1  || nums[mid+1] > key) &&  nums[mid] == key) {
                return mid;
            } else if (nums[mid] <= key) {
                start = mid + 1;
            } else {
                end = mid -1;
            }
        }
        return -1;
     }

     private static int firstOccurence(int[] nums, int key) {
        if(nums == null || nums.length == 0)  return -1;
        int start = 0;
        int end = nums.length -1;
        while(start <= end) {
            int mid = start  + (end - start )/2;
            if((mid == 0 || nums[mid - 1] < key) &&  nums[mid] == key) {
                return mid;
            } else if (nums[mid] < key) {
                start = mid + 1;
            } else {
                end = mid -1;
            }
        }
        return -1;
     }
/** 
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * If target is not found in the array, return [-1, -1].
 * You must write an algorithm with O(log n) runtime complexity.
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Example 3:
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 * Constraints:
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums is a non-decreasing array.
 * -109 <= target <= 109
 */


    private static int[] findFirstAndLastOccurence(int[] nums, int key) {
        if(nums == null || nums.length == 0)  return new int[]{-1,-1};
    
        int first = firstOccurence(nums, key);
        int last = lastOccurence(nums, key);
        return new int[]{first, last};
    }

    /**
     * There is an integer array nums sorted in ascending order (with distinct values).
     * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
     * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
     * You must write an algorithm with O(log n) runtime complexity.
     * Example 1:
     * Input: nums = [4,5,6,7,0,1,2], target = 0
     * Output: 4
     * Example 2:
     * Input: nums = [4,5,6,7,0,1,2], target = 3
     * Output: -1
     * Example 3:
     * Input: nums = [1], target = 0
     * Output: -1
     *      Constraints:
     * 1 <= nums.length <= 5000
     * -104 <= nums[i] <= 104
     * All values of nums are unique.
     * nums is an ascending array that is possibly rotated.
     * -104 <= target <= 104
     */

     private static int searchInRotatedArray(int[] nums, int target) {
        if(nums == null || nums.length == 0 ) return -1;
        int start = 0;
        int end = nums.length - 1;
        while(start <= end) {
            int mid = start + (end - start)/2;
            //if target found return index
            if(nums[mid] == target) return mid;
            if(nums[mid] > target) {
                if(nums[mid] > nums[end] && nums[end] > target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                if(nums[mid] < nums[start]) {
                    end = mid -1;
                }    else {
                    start = mid +1;
                }
            }
        }
        return -1;
     }
} 
