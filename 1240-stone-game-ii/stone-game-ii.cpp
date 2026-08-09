
class Solution {
public:
    int n;
    int dp[101][101];
    int suffixSum[101];

    int solve(int i, int M, vector<int>& piles) {
        if (i >= n) {
            return 0;
        }

        // Already calculated
        if (dp[i][M] != -1) {
            return dp[i][M];
        }

        // If we can take all remaining piles
        if (2 * M >= n - i) {
            return dp[i][M] = suffixSum[i];
        }

        int best = 0;

        // Try every possible X
        for (int X = 1; X <= 2 * M; X++) {

            int newM = max(M, X);

            // Opponent's maximum score
            int opponent = solve(i + X, newM, piles);

            // Total stones currently available
            int total = suffixSum[i];

            // Whatever opponent gets, we get the rest
            int currentPlayer = total - opponent;

            best = max(best, currentPlayer);
        }

        return dp[i][M] = best;
    }

    int stoneGameII(vector<int>& piles) {

        n = piles.size();

        memset(dp, -1, sizeof(dp));

        // suffixSum[i] = sum of piles[i ... n-1]
        suffixSum[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        // Initially:
        // i = 0
        // M = 1
        return solve(0, 1, piles);
    }
};
