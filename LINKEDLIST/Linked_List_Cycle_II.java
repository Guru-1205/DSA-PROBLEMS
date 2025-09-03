/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */

class Solution {

    /**
     * Finds the node where the cycle begins in a linked list, if one exists.
     *
     * @implNote
     *           Step 1: Initialize two pointers (slow and fast) at the head.
     *           Step 2: Move slow one step at a time, fast two steps at a time.
     *           Step 3: If fast reaches null, no cycle exists → return null.
     *           Step 4: If slow == fast, a cycle is detected. Break out of the
     *           loop.
     *           Step 5: Reset slow to head. Keep fast at the meeting point.
     *           Step 6: Move slow and fast one step at a time. The node where they
     *           meet again is the start of the cycle.
     *
     *           Intuition:
     *           - Floyd’s Cycle Detection works because once slow and fast meet
     *           inside the cycle,
     *           the distance from head to cycle start = distance from meeting point
     *           to cycle start.
     *
     *           Example:
     *           Input: head = [3,2,0,-4], pos = 1 (tail connects to index 1)
     *           Output: Node with value = 2
     *
     * @param head The head of the linked list.
     * @return The node where the cycle begins, or null if no cycle exists.
     *
     *         Time Complexity: O(n), each pointer traverses the list at most twice.
     *         Space Complexity: O(1), only two pointers are used.
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head;
        ListNode fast = head;

        // Step 1: Detect cycle using fast & slow pointers
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) { // cycle detected
                // Step 2: Find cycle start
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow; // start of cycle
            }
        }

        return null; // no cycle
    }
}
