class Solution {
    // Standard days per month for a non-leap year
    private final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        // Transform the "MM-DD" text dates into standalone days of the year (1 - 365)
        int startA = dateToDayOfYear(arriveAlice);
        int endA = dateToDayOfYear(leaveAlice);
        int startB = dateToDayOfYear(arriveBob);
        int endB = dateToDayOfYear(leaveBob);

        // Intersect the two intervals
        int overlapStart = Math.max(startA, startB);
        int overlapEnd = Math.min(endA, endB);

        // Compute the duration; if they don't cross paths, return 0
        return Math.max(0, overlapEnd - overlapStart + 1);
    }

    private int dateToDayOfYear(String date) {
        // Extract numerical parts using basic substring parsing
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int dayOfYear = 0;
        // Tally up total days from preceding months
        for (int i = 0; i < month - 1; i++) {
            dayOfYear += daysInMonth[i];
        }
        
        // Include the target month's offset
        return dayOfYear + day;
    }
}
