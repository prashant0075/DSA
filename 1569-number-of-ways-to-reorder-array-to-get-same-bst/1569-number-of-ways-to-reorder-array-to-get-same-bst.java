class Solution {
    private static final int MOD = 1000000007;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        // Arrays of length 1 or 2 can only be formed in 1 way, so 0 *other* ways
        if (n <= 2) return 0; 

        // 1. Array-based BST representation (Extremely fast, 0 object allocation overhead)
        int[] left = new int[n + 1];
        int[] right = new int[n + 1];
        int[] size = new int[n + 1];

        int root = nums[0];
        size[root] = 1;

        // Build the BST and track subtree sizes
        for (int i = 1; i < n; i++) {
            int val = nums[i];
            int curr = root;
            
            while (true) {
                size[curr]++; // val will be a child of curr, so increase curr's size
                
                if (val < curr) {
                    if (left[curr] == 0) {
                        left[curr] = val;
                        size[val] = 1;
                        break;
                    } else {
                        curr = left[curr];
                    }
                } else {
                    if (right[curr] == 0) {
                        right[curr] = val;
                        size[val] = 1;
                        break;
                    } else {
                        curr = right[curr];
                    }
                }
            }
        }

        // 2. Precompute Modular Inverses in strictly O(N) time
        long[] inv = new long[n + 1];
        inv[1] = 1;
        for (int i = 2; i <= n; i++) {
            inv[i] = MOD - (MOD / i) * inv[(int)(MOD % i)] % MOD;
        }

        long ans = 1;
        
        // 3. Compute formula: N! / Product(size[i])
        for (int i = 1; i <= n; i++) {
            // Multiply by i to build N!
            ans = (ans * i) % MOD;
            
            // Divide by size[i] using the precomputed modular inverse
            if (size[i] > 1) { // Skip size 1 to save micro-operations
                ans = (ans * inv[size[i]]) % MOD;
            }
        }

        // Subtract 1 because the problem asks for the number of *other* ways
        return (int) ((ans - 1 + MOD) % MOD);
    }
}