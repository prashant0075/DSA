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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || n == 0){
            return head;
        }
        int size = 0;
        ListNode temp = head;

        while(temp != null){
            size++;
            temp = temp.next;
        }

        if(size == n){
            head = head.next;
            return head;
        }
        int i = 0;
        temp = head;
        ListNode prev= null;
        while(temp != null){
            if(size - i == n){
                prev.next = temp.next;
                temp.next = null;
                return head;
            }
            prev = temp;
            temp = temp.next;
            i++;
        }
        return head;
    }
}