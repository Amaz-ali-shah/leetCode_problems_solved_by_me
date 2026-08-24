import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {

    private PriorityQueue<Integer> low;   // max-heap (stores smaller half)
    private PriorityQueue<Integer> high;  // min-heap (stores larger half)

    public MedianFinder() {
        // Max-heap: use reverse order
        low = new PriorityQueue<>(Collections.reverseOrder());
        // Min-heap: default order
        high = new PriorityQueue<>();
    }

    public void addNum(int num) {
        // Step 1: add to max-heap
        low.offer(num);

        // Step 2: balance values – move largest from low to high
        high.offer(low.poll());

        // Step 3: balance sizes – if high has more elements, move smallest back to low
        if (high.size() > low.size()) {
            low.offer(high.poll());
        }
    }

    public double findMedian() {
        // If odd, low has one extra element
        if (low.size() > high.size()) {
            return low.peek();
        } else {
            // Even: average of two middle values
            return (low.peek() + high.peek()) / 2.0;
        }
    }
}
