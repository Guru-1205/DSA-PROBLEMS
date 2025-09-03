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
     * Detects if a cycle exists in the linked list using the Fast and Slow Pointers
     * technique.
     *
     * @implNote
     *           Step 1: Initialize two pointers: slow and fast, both pointing to
     *           the head.
     *           Step 2: Move slow pointer one step at a time and fast pointer two
     *           steps at a time.
     *           Step 3: If the fast pointer or fast.next becomes null, it means no
     *           cycle exists (we reached the end).
     *           Step 4: If slow and fast meet at some point, a cycle exists in the
     *           list.
     *
     *           Intuition:
     *           - If there's no cycle, the fast pointer will eventually reach null.
     *           - If there's a cycle, the fast pointer will "lap" the slow pointer,
     *           causing them to meet.
     *
     *           Example:
     *           Input: head = [3,2,0,-4], pos = 1
     *           Linked list has a cycle (tail connects to node index 1).
     *           Output: true
     *
     * @param head The head of the linked list.
     * @return true if a cycle exists, false otherwise.
     *
     *         Time Complexity: O(n), since each node is visited at most twice.
     *         Space Complexity: O(1), as only two pointers are used.
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next; // move by 1
            fast = fast.next.next; // move by 2

            if (slow == fast) {
                return true; // cycle detected
            }
        }

        return false; // no cycle
    }
}
