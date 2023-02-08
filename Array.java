import java.util.Arrays;
import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
     // prefix sum/ prefix operations
     System.out.println(Arrays.toString(productExceptSelf(new int[]{1,2,3,4}))); //[24, 12, 8, 6]
     System.out.println(Arrays.toString(productExceptSelf(new int[]{-1,1,0,-3,3}))); //[0,0,9,0,0]

     System.out.println(minSubArraySumK(new int[]{2,3,1,2,4,3}, 7)); //2
     System.out.println(minSubArraySumK(new int[]{1,4,4}, 4)); //1
     System.out.println(minSubArraySumK(new int[]{1,1,1,1,1,1,1,1,1,1}, 11)); //0
     System.out.println(minSubArraySumK(new int[]{1,1,1,1,1,1,1,1,1,1,12}, 11)); //1
     System.out.println(minSubArraySumK(new int[]{1,1,1,1,1,1,1,1,1,1,1,1}, 11)); //11
    }

    private static int[] productExceptSelf(int[] input) {
        if(input == null || input.length == 0) return null;
        int size = input.length;
        int[] result = new int[size];
        int[] left = new int[size];
        int[] right = new int[size];

        left[0] = input[0];
        right[size - 1] = input[size - 1];
        for(int i = 1; i < size ; i++) {
            left[i] = left[i-1] * input[i];
        }
        for(int i = size - 2; i >= 0 ; i--) {
            right[i] = right[i + 1] * input[i];
        }
        result[0] = right[1];
        result[size -1 ] = left[size -2];
        for(int i = 1 ; i < size-1 ; i ++) {
            result[i] = left[i-1] * right[i+1]; 
        }
        return result;

    }
    /* Cleaner solution from Leetcode  */
    private static int[] productExceptSelfClean(int[] input) {
        if(input == null || input.length == 0) return null;
        int size = input.length;
        int[] result = new int[size];
        int[] left = new int[size];
        int[] right = new int[size];

        left[0] = 1;
        right[size - 1] = 1;
        for(int i = 1; i < size ; i++) {
            left[i] = left[i-1] * input[i -1 ];
        }
        for(int i = size - 2; i >= 0 ; i--) {
            right[i] = right[i + 1] * input[i + 1 ];
        }
        for(int i = 0 ; i < size ; i ++) {
            result[i] = left[i] * right[i]; 
        }
        return result;

    }

    private static int minSubArraySumK(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int windowSum = 0;
        int start = 0;
        for(int end = 0 ; end < nums.length; end++) {
                        windowSum += nums[end];

            while(windowSum >= k) {
                
                min = Math.min(min, end - start + 1 );
                windowSum -= nums[start];

                start++;
            }

        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
