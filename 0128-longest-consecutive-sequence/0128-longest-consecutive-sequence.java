import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestConsecutive(int[] nums) {
        // Safe check for empty input, though 0 is naturally handled by 'longest' initialization
        if (nums == null || nums.length == 0) return 0; 
        
        // Step 1: Populate the hash set to get O(1) lookups
        Set<Integer> hashArr = new HashSet<>();
        for (int n : nums) {
            hashArr.add(n);
        }
        
        int longestStreak = 0;
        
        // Step 2: Iterate through the set to find the start of sequences
        for (int n : hashArr) {
            // Check if 'n' is the absolute beginning of a consecutive sequence
            if (!hashArr.contains(n - 1)) {
                int currentNum = n;
                int currentStreak = 1;
                
                // Track and increment the sequence length upward
                while (hashArr.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }
                
                // Record the maximum streak found so far
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        
        return longestStreak;
    }
}
