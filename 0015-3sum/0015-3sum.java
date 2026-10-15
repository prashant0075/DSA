class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ls = new ArrayList<>();
        Set<List<Integer>> st = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> hash = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                int k = -(nums[i] + nums[j]);
                if (hash.contains(k)) {
                    List<Integer> l = new ArrayList<>();
                    Collections.addAll(l, nums[i], nums[j], k);
                    Collections.sort(l);
                    st.add(l);
                }
                hash.add(nums[j]);
            }
        }
        for (List<Integer> e : st) {
            ls.add(e);
        }
        return ls;
    }
}