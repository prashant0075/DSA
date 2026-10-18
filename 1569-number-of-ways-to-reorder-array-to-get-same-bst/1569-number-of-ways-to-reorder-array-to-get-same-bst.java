class Solution {
    private static final long MOD = 1000000007;
    private long[] fact;
    private long[] invFact;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        
        // 1. Precompute factorials and inverse factorials for O(1) combinations
        fact = new long[n + 1];
        invFact = new long[n + 1];
        fact[0] = 1;
        invFact[0] = 1;
        
        for (int i = 1; i <= n; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
            invFact[i] = power(fact[i], MOD - 2); // Fermat's Little Theorem
        }
        
        return (int) ((dfs(nums) - 1 + MOD) % MOD);
    }
    
    // Fast exponentiation to calculate modular inverse
    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }

    // O(1) combination formula using precomputed arrays: nCr = n! / (r! * (n-r)!)
    private long getCombination(int n, int r) {
        if (r < 0 || r > n) return 0;
        long res = fact[n];
        res = (res * invFact[r]) % MOD;
        res = (res * invFact[n - r]) % MOD;
        return res;
    }

    private long dfs(int[] arr) {
        int n = arr.length;
        // Base case
        if (n <= 2) return 1;
        
        int root = arr[0];
        
        // 2. Count left elements to allocate exact primitive arrays (avoids ArrayList overhead)
        int leftCount = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] < root) leftCount++;
        }
        
        int[] left = new int[leftCount];
        int[] right = new int[n - 1 - leftCount];
        int l = 0, r = 0;
        
        // Distribute elements
        for (int i = 1; i < n; i++) {
            if (arr[i] < root) {
                left[l++] = arr[i];
            } else {
                right[r++] = arr[i];
            }
        }
        
        // Recursion
        long leftWays = dfs(left);
        long rightWays = dfs(right);
        
        // Calculate permutations for the current subtree
        long totalWays = getCombination(n - 1, leftCount);
        totalWays = (totalWays * leftWays) % MOD;
        totalWays = (totalWays * rightWays) % MOD;
        
        return totalWays;
    }
}