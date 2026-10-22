class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> hashArr = new HashSet<Integer>();
        for(int n : nums){
            hashArr.add(n);
        }
        int l = 0;
        for(int n : hashArr){
            if(hashArr.contains(n-1)){
                continue;
            }
            else{
                int cnt = 1;
                int num = n;
                
                while(hashArr.contains(num+1)){
                    cnt++;
                    num++;
                }
                l = Math.max(l,cnt);
            }
        }
        return l;
    }
}