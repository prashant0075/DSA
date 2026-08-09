class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int high = 0;
        int low = bloomDay[0];
        //calculating min max
        for(int i =0;i<bloomDay.length;i++){
                high = Math.max(bloomDay[i],high);
                low = Math.min(bloomDay[i],low);
        }

        if(bloomDay.length < m*k) return -1;
        int ans = -1;
        //binary search
        while(low <= high){
            int mid = (low + high )/2;
            int count = 0;
            int b = 0;
            for(int i = 0;i<bloomDay.length;i++){
                if(bloomDay[i]<=mid){
                    count++;
                    if(count == k){
                        b++;
                        count = 0;
                    }
                }
                else{
                    count = 0;
                }
            }
            if(b>=m){
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