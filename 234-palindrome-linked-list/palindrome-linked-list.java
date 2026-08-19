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
        public static ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode temp = head;
        while(temp!= null){
            ListNode front = temp.next;
            temp.next = prev;
            prev = temp;
            temp = front;
        }
        return prev;
    }
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;    
        }

        ListNode newNode = reverse(slow.next);

        ListNode first =head;
        ListNode second = newNode;
        while(second != null){
            if(first.val != second.val){
                reverse(newNode);
                return false;
            }
            second  = second.next;
            first = first.next;
        }
        reverse(newNode);
        return true;
    }
}