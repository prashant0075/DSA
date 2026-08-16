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
    public ListNode middleNode(ListNode head) {
        int size = 0;
        ListNode t = head;
        while(t != null){
            size++;
            t = t.next;
        }
        ListNode t2 = head;
        int i = 0;
        while(i < size){
            if(i==size/2){
                head = t2;
                return head;
            }
            t2 = t2.next;
            i++;
        }
        return head;
    }
}