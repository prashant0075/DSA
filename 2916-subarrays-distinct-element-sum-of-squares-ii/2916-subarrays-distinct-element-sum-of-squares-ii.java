import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final long MOD = 1_000_000_007L;

    private long[] sum;
    private long[] sumSquares;
    private int[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new long[4 * n];
        sumSquares = new long[4 * n];
        lazy = new int[4 * n];

        Map<Integer, Integer> lastSeen = new HashMap<>();
        long answer = 0;

        for (int right = 0; right < n; right++) {
            int left = lastSeen.getOrDefault(nums[right], -1) + 1;
            add(1, 0, n - 1, left, right);

            answer = (answer + sumSquares[1]) % MOD;
            lastSeen.put(nums[right], right);
        }

        return (int) answer;
    }

    private void apply(int node, int left, int right, int increment) {
        long length = right - left + 1L;
        long oldSum = sum[node];

        sumSquares[node] = (
            sumSquares[node]
            + 2L * increment * oldSum
            + (long) increment * increment * length
        ) % MOD;
        sum[node] = (oldSum + (long) increment * length) % MOD;
        lazy[node] += increment;
    }

    private void push(int node, int left, int right) {
        if (lazy[node] == 0 || left == right) {
            return;
        }

        int mid = (left + right) / 2;
        apply(node * 2, left, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, right, lazy[node]);
        lazy[node] = 0;
    }

    private void add(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryLeft <= left && right <= queryRight) {
            apply(node, left, right, 1);
            return;
        }

        push(node, left, right);
        int mid = (left + right) / 2;

        if (queryLeft <= mid) {
            add(node * 2, left, mid, queryLeft, queryRight);
        }
        if (queryRight > mid) {
            add(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
        }

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sumSquares[node] = (sumSquares[node * 2] + sumSquares[node * 2 + 1]) % MOD;
    }
}