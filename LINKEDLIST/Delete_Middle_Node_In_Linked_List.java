/**
 * LeetCode 2095: Delete the Middle Node of a Linked List
 *
 * <p>
 * <b>Problem Description:</b><br>
 * You are given the head of a singly linked list. Delete the middle node, and
 * return the head of the modified list.
 *
 * <p>
 * <b>Definition of middle:</b><br>
 * - If the list has odd length → middle = exact center.<br>
 * - If the list has even length → middle = ⌊length/2⌋th node (0-indexed).<br>
 *
 * <p>
 * <b>Example:</b><br>
 * Input: head = [1,3,4,7,1,2,6]<br>
 * Output: [1,3,4,1,2,6]<br>
 *
 * <p>
 * <b>Constraints:</b><br>
 * - The number of nodes is in the range [1, 10^5].<br>
 * - 1 <= Node.val <= 10^5
 */
public class Delete_Middle_Node_In_Linked_List {

    /**
     * -------------------------------------------------
     * Approach 1: Length-Based (Two-Pass)
     * -------------------------------------------------
     *
     * <p>
     * <b>Step 1: Count the length of the list.</b><br>
     * - Traverse the list once and count total nodes.<br>
     * - Example: [1,3,4,7,1,2,6] → length = 7.<br>
     *
     * <p>
     * <b>Step 2: Find the index of the middle node.</b><br>
     * - middle = length / 2 (integer division).<br>
     * - Example: length=7 → middle=3 (0-indexed, node with value 7).<br>
     * - Example: length=6 → middle=3 (delete the 4th node).<br>
     *
     * <p>
     * <b>Step 3: Traverse again to delete the middle node.</b><br>
     * - Move (middle-1) steps to reach the node before middle.<br>
     * - Set prev.next = prev.next.next (unlink the middle).<br>
     * - Edge Case: If list has only 1 node, return null.<br>
     *
     * @param head The head of the linked list
     * @return Modified linked list after deleting the middle node
     */
    public ListNode deleteMiddleLength(ListNode head) {
        if (head == null || head.next == null)
            return null; // Single node → deleted → empty list

        // Step 1: Count length
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        // Step 2: Compute middle index
        int middle = length / 2;

        // Step 3: Traverse again to just before the middle
        curr = head;
        for (int i = 0; i < middle - 1; i++) {
            curr = curr.next;
        }

        // Delete middle node
        curr.next = curr.next.next;

        return head;
    }

    /**
     * -------------------------------------------------
     * Approach 2: Fast and Slow Pointer (Efficient, Single Pass)
     * -------------------------------------------------
     *
     * <p>
     * <b>Step 1: Initialize pointers.</b><br>
     * - Use two pointers: slow (moves 1 step) and fast (moves 2 steps).<br>
     * - Also track `prev` (node before slow).<br>
     *
     * <p>
     * <b>Step 2: Move fast and slow together.</b><br>
     * - For each step: slow moves 1 node, fast moves 2 nodes.<br>
     * - When fast reaches the end, slow will be at the middle.<br>
     * - Example: [1,3,4,7,1,2,6] → slow at 7, fast at null.<br>
     *
     * <p>
     * <b>Step 3: Delete the middle node.</b><br>
     * - Set prev.next = slow.next (unlink slow).<br>
     * - Edge Case: If the list has only 1 node, return null.<br>
     *
     * @param head The head of the linked list
     * @return Modified linked list after deleting the middle node
     */
    public ListNode deleteMiddleFastSlow(ListNode head) {
        if (head == null || head.next == null)
            return null; // Single node → deleted → empty list

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        // Step 2: Move fast twice as quickly as slow
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next; // 1 step
            fast = fast.next.next; // 2 steps
        }

        // Step 3: Delete the middle node
        prev.next = slow.next;

        return head;
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 *
 * Approach 1: Length-Based (Two-Pass)
 * - Time Complexity: O(n) → One traversal to count + one traversal to delete.
 * - Space Complexity: O(1) → Only counters and pointers used.
 *
 * Approach 2: Fast & Slow Pointer (Single-Pass)
 * - Time Complexity: O(n) → Single traversal with fast and slow pointers.
 * - Space Complexity: O(1) → Only slow, fast, and prev pointers.
 *
 * Comparison:
 * - Both are O(n) time and O(1) space.
 * - Approach 2 is preferred → finds and deletes middle in a single pass.
 */
