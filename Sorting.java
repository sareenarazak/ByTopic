import java.util.Arrays;

public class Sorting {
    public static void main(String[] args) {
        int[] nums = new int[]{33,2,53,106,73};
        selectionSort(nums);
        System.out.println(Arrays.toString(nums));
    }


    //Selection sort
    // O(N^2) time 
    private static void selectionSort(int[] input){
        if(input == null || input.length == 0) return;
        int length = input.length;

        for(int nextIndexToSort = 0; nextIndexToSort < length ; nextIndexToSort++) {
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
