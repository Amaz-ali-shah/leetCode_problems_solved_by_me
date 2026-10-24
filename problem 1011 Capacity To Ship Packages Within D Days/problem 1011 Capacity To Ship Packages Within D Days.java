class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int lo = 0, hi = 0;
        for (int w : weights) {
            lo = Math.max(lo, w);
            hi += w;
        }

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canShip(weights, days, mid)) {
                hi = mid;          // feasible → try smaller
            } else {
                lo = mid + 1;      // not feasible → need bigger
            }
        }
        return lo;
    }

    private boolean canShip(int[] weights, int days, int capacity) {
        int usedDays = 1;
        int load = 0;
        for (int w : weights) {
            if (load + w > capacity) {
                usedDays++;
                load = 0;
            }
            load += w;
            if (usedDays > days) return false;
        }
        return true;
    }
}
