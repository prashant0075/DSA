import java.util.Arrays;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        
        // arr[i][0] = digit sum
        // arr[i][1] = value
        // arr[i][2] = original index
        int[][] arr = new int[n][3];
        
        for (int i = 0; i < n; i++) {
            arr[i][0] = getDigitSum(nums[i]);
            arr[i][1] = nums[i];
            arr[i][2] = i;
        }
        
        // Sort first by digit sum, then by value
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        
        boolean[] visited = new boolean[n];
        int swaps = 0;
        
        // Detect cycles in the target permutation
        for (int i = 0; i < n; i++) {
            // If already visited or element is already at the correct position
            if (visited[i] || arr[i][2] == i) {
                continue;
            }
            
            int cycleLen = 0;
            int j = i;
            
            // Follow the cycle
            while (!visited[j]) {
                visited[j] = true;
                j = arr[j][2];
                cycleLen++;
            }
            
            // For a cycle of length L, we need (L - 1) swaps
            if (cycleLen > 1) {
                swaps += cycleLen - 1;
            }
        }
        
        return swaps;
    }
    
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}