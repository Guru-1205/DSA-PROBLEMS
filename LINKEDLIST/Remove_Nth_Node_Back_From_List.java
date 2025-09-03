/**
 * LeetCode 19: Remove Nth Node From End of List
 *
 * <p>
 * <b>Problem:</b><br>
 * Given the head of a linked list, remove the nth node from the end of the list
 * and return its head.
 *
 * <p>
 * <b>Example:</b><br>
 * Input: head = [1,2,3,4,5], n = 2<br>
 * Output: [1,2,3,5]
 */
class Solution {

    /**
     * -------------------------------------------------
     * Approach 1: Counting (Two-Pass)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1: Find Length of List</b><br>
     *           - Traverse the entire list once and count the number of nodes.<br>
     *           - Example: [1,2,3,4,5] → length = 5.<br>
     *           <br>
     *
     *           <b>Step 2: Find Node to Remove</b><br>
     *           - The node to remove is at index (length - n) from the start.<br>
     *           - Example: n=2, length=5 → index = 3 (0-based) → remove node with
     *           value 4.<br>
     *           <br>
     *
     *           <b>Step 3: Traverse Again and Remove Node</b><br>
     *           - Use a dummy node before head to simplify edge cases.<br>
     *           - Traverse until just before the target node.<br>
     *           - Adjust pointers to skip the target node.<br>
     *           <br>
     *
     * @param head The head of the linked list
     * @param n    The position from the end (1-indexed)
     * @return The modified linked list after removing the nth node
     */
    public ListNode removeNthFromEndCount(ListNode head, int n) {
        // Step 1: Find length
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        // Step 2: Find index to remove
        int targetIndex = length - n;

        // Step 3: Remove target node
        ListNode dummy = new ListNode(0, head);
        curr = dummy;
        for (int i = 0; i < targetIndex; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next; // skip target node

        return dummy.next;
    }

    /**
     * -------------------------------------------------
     * Approach 2: Two Pointers (Fast & Slow, One-Pass)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1: Initialize Two Pointers</b><br>
     *           - Use `fast` and `slow` pointers starting at dummy node.<br>
     *           - Dummy node simplifies removal of head.<br>
     *           <br>
     *
     *           <b>Step 2: Advance Fast Pointer</b><br>
     *           - Move `fast` forward by n+1 steps.<br>
     *           - Why n+1? So that `slow` stops just before the node to remove.<br>
     *           - Example: n=2, list=[1,2,3,4,5] → fast moves to node after 2 steps
     *           → gap maintained.<br>
     *           <br>
     *
     *           <b>Step 3: Move Both Pointers</b><br>
     *           - Move both `fast` and `slow` until `fast` reaches null.<br>
     *           - At this point, `slow` is just before the node to delete.<br>
     *           <br>
     *
     *           <b>Step 4: Remove Node</b><br>
     *           - Set `slow.next = slow.next.next`.<br>
     *           - Example: [1,2,3,4,5], n=2<br>
     *           - fast ahead by 3 steps.<br>
     *           - slow stops at node 3.<br>
     *           - skip 4 → result [1,2,3,5].<br>
     *           <br>
     *
     * @param head The head of the linked list
     * @param n    The position from the end (1-indexed)
     * @return The modified linked list after removing the nth node
     */
    public ListNode removeNthFromEndTwoPointers(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        // Step 2: Move fast ahead by n+1
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Step 3: Move both until fast reaches end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Step 4: Remove node
        slow.next = slow.next.next;

        return dummy.next;
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 *
 * <b>Approach 1: Counting (Two-Pass)</b><br>
 * - Time Complexity: O(n) → One traversal to find length + one traversal to
 * remove.<br>
 * - Space Complexity: O(1) → Only a few pointers used.<br>
 * <br>
 *
 * <b>Approach 2: Two Pointers (One-Pass)</b><br>
 * - Time Complexity: O(n) → Single traversal with fast & slow.<br>
 * - Space Complexity: O(1) → No extra storage used.<br>
 * <br>
 *
 * ✅ Both are O(n), but Approach 2 is preferred since it finishes in a single
 * pass.
 */
