import java.util.PriorityQueue;

class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // Max-heap by squared distance from origin.
        // The heap will store the k closest points seen so far.
        // The top of the heap is the farthest among those k.
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(dist(b), dist(a))
        );

        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // remove the farthest point
            }
        }

        // Convert heap to result array
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }
        return result;
    }

    private int dist(int[] p) {
        return p[0] * p[0] + p[1] * p[1];
    }
}
