class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        Arrays.sort(nums);
        int cnt = 1;
        int max = 0;
        for(int i = 1;i<nums.length;i++){
            if(nums[i-1] == nums[i]-1){
                cnt++;
            }
            else if(nums[i-1]==nums[i]){
                continue;
            }
            else{
                max = Math.max(cnt , max);
                cnt = 1;
            }
        }
        max = Math.max(cnt,max);
        return max;
    }
}