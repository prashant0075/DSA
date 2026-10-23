class Solution {
public:
    int minOperations(vector<int>& nums) {
        int n = nums.size();

        int asc_cnt = 0;
        int dsc_cnt = 0;

        for(int i = 1;i < n;i++){
            asc_cnt += (nums[i] == (nums[i-1]+1)%n);
            dsc_cnt += (nums[i-1]==(nums[i]+1)%n);
        }

        if(asc_cnt == n-1 && nums[0]==0){
            return 0;
        }
        if(asc_cnt == n-1){
            int rightRot = 1 + (nums.back() +1) + 1;
            int leftRot = n-nums[0];

            return min(rightRot , leftRot);
        }

        if(dsc_cnt == n-1){
            int leftRot = (nums[0] + 1)%n + 1;
            int rightRot = n-nums.back() + 1;


            return min(rightRot , leftRot);
        }

        return -1;
    }
};