import java.util.*;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        // 1. Check if the original array is a cyclic increasing permutation
        int zeroPosOrig = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroPosOrig = i;
                break;
            }
        }
        boolean isCyclicInc = true;
        for (int j = 0; j < n; j++) {
            if (nums[(zeroPosOrig + j) % n] != j) {
                isCyclicInc = false;
                break;
            }
        }

        // 2. Build the reversed array and check if it is cyclic increasing
        int[] revNums = new int[n];
        for (int i = 0; i < n; i++) {
            revNums[i] = nums[n - 1 - i];
        }
        
        int zeroPosRev = -1;
        for (int i = 0; i < n; i++) {
            if (revNums[i] == 0) {
                zeroPosRev = i;
                break;
            }
        }
        boolean isCyclicDec = true;
        for (int j = 0; j < n; j++) {
            if (revNums[(zeroPosRev + j) % n] != j) {
                isCyclicDec = false;
                break;
            }
        }

        // If neither pattern matches the targeted sorted structural cycle, it's impossible.
        if (!isCyclicInc && !isCyclicDec) return -1;

        // 3. BFS over the state space
        // State encoding: id = type * n + shift
        // type 0: rotation of original array. Target shift is zeroPosOrig
        // type 1: rotation of reversed array. Target shift is zeroPosRev
        int target0 = isCyclicInc ? zeroPosOrig : -1;
        int target1 = isCyclicDec ? zeroPosRev : -1;

        int[] dist = new int[2 * n];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();

        // Starting state: original array with 0 shifts
        dist[0] = 0;
        queue.offer(0);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int type = curr / n;
            int shift = curr % n;

            // Check if current state matches the targeted sorted alignment
            if (type == 0 && shift == target0) return dist[curr];
            if (type == 1 && shift == target1) return dist[curr];

            // Transition 1: Rotate Left by One
            // (type, shift) -> (type, (shift + 1) % n)
            int nextShift = (shift + 1) % n;
            int rotateNext = type * n + nextShift;
            if (dist[rotateNext] == -1) {
                dist[rotateNext] = dist[curr] + 1;
                queue.offer(rotateNext);
            }

            // Transition 2: Reverse Entire Array
            // Reversing a sequence shifted by 's' yields a sequence shifted by '(n - s) % n' in the opposite family
            int revShift = (n - shift) % n;
            int reverseNext = (1 - type) * n + revShift;
            if (dist[reverseNext] == -1) {
                dist[reverseNext] = dist[curr] + 1;
                queue.offer(reverseNext);
            }
        }

        return -1;
    }
}
