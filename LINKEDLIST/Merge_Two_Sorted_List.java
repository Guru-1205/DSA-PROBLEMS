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

class Solution {

    /**
     * Merges two sorted linked lists into one sorted list.
     *
     * @implNote
     *           Step 1: Create a dummy node (acts as a placeholder) and a tail
     *           pointer to build the result.
     *           Step 2: Compare the heads of both lists:
     *           - If list1's value is smaller/equal, append it to tail, move list1
     *           forward.
     *           - Else, append list2’s value, move list2 forward.
     *           Step 3: Continue until one of the lists becomes null.
     *           Step 4: Append the remaining part of the non-empty list (if any).
     *           Step 5: Return dummy.next, since dummy was only a placeholder.
     *
     *           Intuition:
     *           - Similar to merging two sorted arrays (like in merge sort).
     *           - At every step, we pick the smaller node and move forward.
     *
     *           Example:
     *           Input: list1 = [1,2,4], list2 = [1,3,4]
     *           Output: [1,1,2,3,4,4]
     *
     * @param list1 The head of the first sorted linked list.
     * @param list2 The head of the second sorted linked list.
     * @return The merged sorted linked list.
     *
     *         Time Complexity: O(m + n), where m and n are the lengths of the two
     *         lists.
     *         Space Complexity: O(1), since we only use a few pointers.
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1); // placeholder
        ListNode tail = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        // Append remaining nodes
        if (list1 != null) {
            tail.next = list1;
        } else {
            tail.next = list2;
        }

        return dummy.next; // skip dummy
    }
}
