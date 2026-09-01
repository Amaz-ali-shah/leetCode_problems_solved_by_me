class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] ans = new double[n - k + 1];

        // max-heap for the lower half
        PriorityQueue<Integer> lo = new PriorityQueue<>(Collections.reverseOrder());
        // min-heap for the upper half
        PriorityQueue<Integer> hi = new PriorityQueue<>();

        // lazy deletion map: value -> count of stale occurrences
        Map<Integer, Integer> delayed = new HashMap<>();

        // balance = valid size of lo - valid size of hi
        // After rebalancing, balance is 0 (even k) or 1 (odd k)
        int balance = 0;

        for (int i = 0; i < n; i++) {
            // Remove stale elements that are already at the top
            clean(lo, hi, delayed);

            // Add new number
            if (lo.isEmpty() || nums[i] <= lo.peek()) {
                lo.offer(nums[i]);
                balance++;
            } else {
                hi.offer(nums[i]);
                balance--;
            }

            // Remove the element that leaves the window
            if (i >= k) {
                int out = nums[i - k];
                delayed.put(out, delayed.getOrDefault(out, 0) + 1);

                // Find out which heap contained 'out' and update balance
                if (lo.isEmpty() || out <= lo.peek()) {
                    balance--;
                } else {
                    balance++;
                }
            }

            // Rebalance heaps, cleaning stale elements on the way
            balance = rebalance(lo, hi, delayed, balance);

            // Final cleaning after rebalancing
            clean(lo, hi, delayed);

            // Record median when the window is full
            if (i >= k - 1) {
                if (k % 2 == 1) {
                    ans[i - k + 1] = (double) lo.peek();
                } else {
                    ans[i - k + 1] = ((double) lo.peek() + hi.peek()) / 2.0;
                }
            }
        }

        return ans;
    }

    // Remove stale elements that are at the top of either heap
    private void clean(PriorityQueue<Integer> lo, PriorityQueue<Integer> hi,
                       Map<Integer, Integer> delayed) {
        while (!lo.isEmpty() && delayed.getOrDefault(lo.peek(), 0) > 0) {
            int val = lo.poll();
            delayed.put(val, delayed.get(val) - 1);
            if (delayed.get(val) == 0) delayed.remove(val);
        }

        while (!hi.isEmpty() && delayed.getOrDefault(hi.peek(), 0) > 0) {
            int val = hi.poll();
            delayed.put(val, delayed.get(val) - 1);
            if (delayed.get(val) == 0) delayed.remove(val);
        }
    }

    // Rebalance using the 'balance' variable.
    // If a stale element is at the top, remove it without changing balance.
    private int rebalance(PriorityQueue<Integer> lo, PriorityQueue<Integer> hi,
                          Map<Integer, Integer> delayed, int balance) {
        while (balance > 1) {
            // Remove stale top elements from lo
            while (!lo.isEmpty() && delayed.getOrDefault(lo.peek(), 0) > 0) {
                int val = lo.poll();
                delayed.put(val, delayed.get(val) - 1);
                if (delayed.get(val) == 0) delayed.remove(val);
            }

            if (lo.isEmpty()) break;

            // Move one valid element from lo to hi
            hi.offer(lo.poll());
            balance -= 2;
        }

        while (balance < 0) {
            // Remove stale top elements from hi
            while (!hi.isEmpty() && delayed.getOrDefault(hi.peek(), 0) > 0) {
                int val = hi.poll();
                delayed.put(val, delayed.get(val) - 1);
                if (delayed.get(val) == 0) delayed.remove(val);
            }

            if (hi.isEmpty()) break;

            // Move one valid element from hi to lo
            lo.offer(hi.poll());
            balance += 2;
        }

        return balance;
    }
}
