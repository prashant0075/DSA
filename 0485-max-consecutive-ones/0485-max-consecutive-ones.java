class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int m = 0;
        int i = 0;
        while(i < nums.length){
            if(nums[i]==1){
                int count = 0;
                while(i<nums.length && nums[i]==1){
                    count++;
                    i++;
                }
                m = Math.max(m,count);
            }
            i++;
        }
        return m;
    }
}