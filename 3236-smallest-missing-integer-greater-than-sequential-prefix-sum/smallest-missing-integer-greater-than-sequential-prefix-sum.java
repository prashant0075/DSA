class Solution {
    public int missingInteger(int[] nums) {
        int t = nums[0];
        Set<Integer> ls = new HashSet<>();
        for(int i : nums){
            ls.add(i);
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] + 1 == nums[i]) {
                t += nums[i];
            }
            else{
                break;
            }
        }
        while(ls.contains(t)){
            t++;
        }
        return t;
    }
}