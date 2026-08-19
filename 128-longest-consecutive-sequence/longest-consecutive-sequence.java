class Solution {
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        int cnt = 0;
        int longest = 0;
        int lastMin = Integer.MIN_VALUE;
        for(int i = 0;i<nums.length;i++){
            if(nums[i]-1==lastMin){
                cnt++;
                lastMin = nums[i];
            }
            else if(nums[i]!=lastMin){
                lastMin = nums[i];
                cnt = 1;
            }
            longest = Math.max(cnt,longest);
        }
        return longest;
    }
}