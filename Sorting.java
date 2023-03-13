import java.util.Arrays;

public class Sorting {
    public static void main(String[] args) {
        int[] nums = new int[]{33,2,53,106,73};
        selectionSort(nums);
        System.out.println(Arrays.toString(nums));

        int[] nums1 = new int[]{33,2,53,106,73};
        bubbleSort(nums1);
        System.out.println(Arrays.toString(nums1));
        
        int[] nums2 = new int[]{33,2,53,106,73};
        insertionSort(nums2);
        System.out.println(Arrays.toString(nums2));


    }

    // inserts the number into the correct position in the sorted section
    private static void insertionSort(int[] input) {
        if(input == null || input.length == 0) return;
        int sortedIndex = 0;

        for (int unsortedIndex = 1; unsortedIndex < input.length ; unsortedIndex++) {
            int temp = input[unsortedIndex];
            while (sortedIndex >= 0 && temp < input[sortedIndex]) {
                
            }
        }

    }

    //Bubble sort : bubble up the largest number to the end of the array --> by comparing pairs 
    // O(N^2) 
    private static void bubbleSort(int[] input) {
        if(input == null || input.length == 0) return;
        int end = input.length - 1 ;
        while(end > 0 ) {
        for(int i = 0 ; i < input.length -1 ; i++) {
            if(input[i] > input[i+1]) swap(input, i, i+1);
            end--;
        }
        }
    }

    //Selection sort : select the min from the unsorted sub array and add to the next Index of sorted subarray
    // O(N^2) time 
    private static void selectionSort(int[] input){
        if(input == null || input.length == 0) return;
        int length = input.length;

        for(int nextIndexToSort = 0; nextIndexToSort < length - 1 ; nextIndexToSort++) {
            int minIndex = nextIndexToSort;
            for(int unsorted = nextIndexToSort + 1; unsorted < length; unsorted++) {
                if (input[minIndex] > input[unsorted]) {
                    minIndex = unsorted;
                }
            }
            swap(input , minIndex,  nextIndexToSort );

        }
    }

    private static void swap(int[] input, int i , int j) {
        if(i == j) return;
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
