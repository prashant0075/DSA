/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        Map<ListNode,Integer> mpp = new HashMap<>();
        int count = 0;
        ListNode temp = head;

        while(temp != null){
            if(mpp.containsKey(temp)){
                return temp;
            }
            else{
                mpp.put(temp,count);
            }
            temp = temp.next;
            count++;
        }
        return temp;
    }
}