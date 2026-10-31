class Solution {
    void combination(int ind , int target, int[] arr,List<Integer> ls,List<List<Integer>> ans){
        if(target == 0){
            ans.add(new ArrayList<>(ls));
            return;
        }
        if(ind == arr.length){
            if(target == 0){
                ans.add(new ArrayList<>(ls));
            }
            return;
        }
        if(arr[ind]<=target){
            ls.add(arr[ind]);
            combination(ind,target-arr[ind],arr,ls,ans);
            ls.remove(ls.size()-1);
        }
        combination(ind+1,target,arr,ls,ans);
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        combination(0,target,candidates,new ArrayList<>(),ans);
        return ans;
    }
}