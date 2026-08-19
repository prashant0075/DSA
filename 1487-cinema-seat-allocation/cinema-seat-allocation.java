import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        // Store only rows that have reserved seats
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.computeIfAbsent(row, k -> new HashSet<>()).add(col);
        }

        // Initially, every row can accommodate 2 groups
        // (left: 2-5 and right: 6-9)
        int ans = (n - map.size()) * 2;

        // Check rows having reservations
        for (Set<Integer> seats : map.values()) {

            boolean left = true;    // 2,3,4,5
            boolean middle = true;  // 4,5,6,7
            boolean right = true;   // 6,7,8,9

            for (int seat : seats) {

                if (seat >= 2 && seat <= 5) {
                    left = false;
                }

                if (seat >= 4 && seat <= 7) {
                    middle = false;
                }

                if (seat >= 6 && seat <= 9) {
                    right = false;
                }
            }

            if (left && right) {
                ans += 2;
            } else if (left || middle || right) {
                ans += 1;
            }
        }

        return ans;
    }
}