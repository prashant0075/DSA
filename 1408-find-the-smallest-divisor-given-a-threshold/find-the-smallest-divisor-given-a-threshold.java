class Solution {
    public int sum (int[] arr, int n){
        int ans = 0;
        for(int e: arr){
            ans += Math.ceil((double)e/(double)n);
        }
        return ans;
    }
    public int smallestDivisor(int[] nums, int threshold) {
        int low = 1;
        int high = nums[0];
        int ans = 0;
        for(int e : nums){
            high = Math.max(e,high);
        }

        while(low <= high){
            int mid = (low + high)/2;
            if(sum(nums,mid)<=threshold){
                ans = mid;
                high = mid-1;
            }
            else{
                low = mid+1;
            }
        }
        return ans;
    }
}