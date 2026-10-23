class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int lo = 1;
        int hi = 0;
        for (int p : piles) hi = Math.max(hi, p);

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canFinish(piles, h, mid)) {
                hi = mid;         // mid works → try smaller
            } else {
                lo = mid + 1;     // mid too slow → try bigger
            }
        }
        return lo;
    }

    private boolean canFinish(int[] piles, int h, int k) {
        long hours = 0;
        for (int p : piles) {
            hours += (p + k - 1) / k;   // ceil(p / k)
            if (hours > h) return false; // early exit
        }
        return hours <= h;
    }
}
