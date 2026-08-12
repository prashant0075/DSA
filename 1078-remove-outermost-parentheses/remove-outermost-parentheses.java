class Solution {
    public String removeOuterParentheses(String s) {
        char[] ch = s.toCharArray();
        StringBuilder st = new StringBuilder();
        int b = 0;
        for(char c : ch){
            if(c == '('){
                if(b>0){
                    st.append(c);
                }
                b++;
            }
            else{
                b--;
                if(b>0){
                    st.append(c);
                }
            }
        }
        return st.toString();
    }
}