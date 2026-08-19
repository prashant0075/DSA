/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode tail = head;
        Stack<Integer> st = new Stack<>();
        st.push(tail.val);
        while(tail.next != null){
            tail = tail.next;
            st.push(tail.val);
        }

        while(st.peek() == head.val){
            if(head.next == null && st.peek()==head.val){
                return true;
            }
            head = head.next;
            st.pop();
        }
        return false;
    }
}