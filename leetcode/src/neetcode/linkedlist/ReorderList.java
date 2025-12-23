package neetcode.linkedlist;

import java.util.Stack;

public class ReorderList {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public void reorderList(ListNode head) {
        // find middle point of list - using two pointers
        ListNode slow = head;
        ListNode fast = head;

        if (slow.next == null)
            return; // edge case if head is a single node

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse last half of the list
        ListNode nextPtr = slow.next;
        ListNode currPtr = slow;
        slow.next = null;

        while (nextPtr != null) {
            ListNode temp = nextPtr.next;
            nextPtr.next = currPtr;

            currPtr = nextPtr;
            nextPtr = temp;
        }

        // while (currPtr != null) {
        // System.out.println(currPtr.val);
        // currPtr = currPtr.next;
        // }

        // handle the reordering
        ListNode firstHalf = head; // 1->2
        ListNode nextHalf = currPtr; // 5->4->3

        while (nextHalf != null && nextHalf.next != null) {
            ListNode temp1 = firstHalf.next;
            ListNode temp2 = nextHalf.next;

            firstHalf.next = nextHalf;
            nextHalf.next = temp1;

            firstHalf = temp1;
            nextHalf = temp2;
        }
    }
}
