class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long left = 0;
        long right = (long)1e18;

        while (left < right) {
            long mid = left + (right - left) / 2;

            if (canReduce(mid, mountainHeight, workerTimes)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canReduce(long time, int mountainHeight, int[] workerTimes) {
        long totalHeight = 0;

        for (int t : workerTimes) {
            long x = (long)((Math.sqrt(1 + (8.0 * time) / t) - 1) / 2);
            totalHeight += x;

            if (totalHeight >= mountainHeight) return true;
        }

        return false;
    }
}
