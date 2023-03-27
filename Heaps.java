import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Heaps {
    private static Map<Character,Integer> symbolValueMap =  new HashMap<>() {
        symbolValueMap.put('I', 1);
        symbolValueMap.put('V', 5);
        symbolValueMap.put('X', 10);
        symbolValueMap.put('L', 50);
        symbolValueMap.put('C', 100);
        symbolValueMap.put('D', 500);
        symbolValueMap.put('M', 1000);

    }

    public static void main(String[] args) {
        List<Integer> result = findKLargestNumbers(new int[] { 3, 1, 5, 12, 2, 11 }, 3);
        System.out.println("Here are the top K numbers: " + result);

        result = findKLargestNumbers(new int[] { 5, 12, 11, -1, 12 }, 3);
        System.out.println("Here are the top K numbers: " + result);
        System.out.println("Kth smallest number is: " + findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 3));
        System.out.println("Kth smallest number is: " + findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 4));
        System.out.println("Kth smallest number is: " + findKthSmallestNumber(new int[] { 5, 12, 11, -1, 12 }, 3));

        System.out.println(Arrays.deepToString(kClosest(new int[][] { { 1, 3 }, { 2, -2 } }, 1)));
    }

    // Here we create a min heap of k elements, So once the heap is built, we
    // iterate through rest of the array and remove any numbers smaller than the
    // rest n-k elements
    // we are then left with k max numbers
    // ex 1,3,5 -- > Comparing and removing any elements smaller from pq , compared
    // to rest of the array
    // loop 1 -- > 3,5,12 loop 2 --> 3,5,12 Loop 3 --> 5,11,12
    public static List<Integer> findKLargestNumbers(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();

        if (nums == null || nums.length == 0 || nums.length < k)
            return result;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return new ArrayList<>(minHeap);
    }

    // Find Kth smallest number is n-kth largest number
    // Solving by building max heap of first k , then we can compare and remove
    // numbers if there is a smaller number in rest of the array
    // first element of the queue after will be kth small number

    public static int findKthSmallestNumber(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k)
            return Integer.MAX_VALUE;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < k; i++) {
            maxHeap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(nums[i]);
            }
        }
        return maxHeap.poll();
    }

    public static int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length == 0)
            return null;
        Comparator<int[]> byEuclideanDistance = (a, b) -> distance(b) - distance(a);
        PriorityQueue<int[]> pq = new PriorityQueue<>(byEuclideanDistance);
        for (int i = 0; i < k; i++) {
            pq.offer(points[i]);
        }

        for (int i = k; i < points.length; i++) {
            if (distance(points[i]) < distance(pq.peek())) {
                pq.poll();
                pq.offer(points[i]);
            }
        }
        return pq.toArray(new int[k][2]);

    }

    public static int distance(int[] a) {
        return a[0] * a[0] + a[1] * a[1];
    }

}
