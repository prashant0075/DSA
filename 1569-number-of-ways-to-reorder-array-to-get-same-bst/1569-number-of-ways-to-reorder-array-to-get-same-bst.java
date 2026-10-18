import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final long MOD = 1000000007;
    private long[][] combinations;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        
        // Precompute combinations (nCr) using Pascal's Triangle
        combinations = new long[n][n];
        for (int i = 0; i < n; i++) {
            combinations[i][0] = 1;
            combinations[i][i] = 1;
            for (int j = 1; j < i; j++) {
                combinations[i][j] = (combinations[i - 1][j - 1] + combinations[i - 1][j]) % MOD;
            }
        }
        
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        
        // Subtract 1 because we want the number of *other* ways
        return (int) ((dfs(list) - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> list) {
        // Base case: arrays of length 0, 1, or 2 only have 1 valid arrangement
        if (list.size() <= 2) {
            return 1;
        }
        
        int root = list.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        
        // Divide remaining elements into left and right subtrees
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < root) {
                left.add(list.get(i));
            } else {
                right.add(list.get(i));
            }
        }
        
        // Recursively find the number of ways for left and right subtrees
        long leftWays = dfs(left);
        long rightWays = dfs(right);
        
        int lLen = left.size();
        int rLen = right.size();
        
        // Total ways = C(lLen + rLen, lLen) * leftWays * rightWays
        long totalWays = combinations[lLen + rLen][lLen];
        totalWays = (totalWays * leftWays) % MOD;
        totalWays = (totalWays * rightWays) % MOD;
        
        return totalWays;
    }
}