/**
 * LeetCode 2095: Delete the Middle Node of a Linked List
 *
 * <p>
 * <b>Problem:</b><br>
 * You are given the head of a singly linked list. Delete the middle node, and
 * return the head of the modified list.
 *
 * <p>
 * <b>Definition of middle:</b><br>
 * - If the list has odd length, middle = exact center.<br>
 * - If the list has even length, middle = ⌊length/2⌋th node (0-indexed).<br>
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
class Solution {

    /**
     * -------------------------------------------------
     * Approach 1: Length-Based (Two-Pass)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1: Count the length of the list</b><br>
     *           - Traverse once and count total nodes.<br>
     *           - Example: list=[1,2,3,4,5] → length=5.<br>
     *           <br>
     *
     *           <b>Step 2: Find index of middle</b><br>
     *           - Middle = length / 2 (integer division).<br>
     *           - Example: length=5 → middle=2 (0-indexed).<br>
     *           - Example: length=6 → middle=3 (delete 4th node).<br>
     *           <br>
     *
     *           <b>Step 3: Traverse again and delete middle</b><br>
     *           - Move (middle-1) steps to reach node before middle.<br>
     *           - Set prev.next = prev.next.next.<br>
     *           - Edge Case: If list has only 1 node, return null.<br>
     *
     * @param head The head of the linked list
     * @return Modified linked list after deleting the middle node
     *
     *         <p>
     *         <b>Time Complexity:</b> O(n) → Two passes (count + delete).<br>
     *         <b>Space Complexity:</b> O(1) → No extra data structures.
     */
    public ListNode deleteMiddleLength(ListNode head) {
        if (head == null || head.next == null)
            return null; // single node case

        // Step 1: Count length
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        // Step 2: Middle index
        int middle = length / 2;

        // Step 3: Traverse again to delete middle
        curr = head;
        for (int i = 0; i < middle - 1; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;

        return head;
    }

    /**
     * -------------------------------------------------
     * Approach 2: Fast and Slow Pointer (Efficient, Single Pass)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1: Initialize pointers</b><br>
     *           - Use two pointers: slow and fast.<br>
     *           - Also track prev (node before slow).<br>
     *           <br>
     *
     *           <b>Step 2: Move fast twice as quickly</b><br>
     *           - For every 1 move of slow, fast moves 2 steps.<br>
     *           - When fast reaches end, slow will be at middle.<br>
     *           - Example: list=[1,2,3,4,5] → slow at 3, fast at null.<br>
     *           <br>
     *
     *           <b>Step 3: Delete middle node</b><br>
     *           - prev.next = slow.next.<br>
     *           - Edge Case: If list has only 1 node, return null.<br>
     *
     * @param head The head of the linked list
     * @return Modified linked list after deleting the middle node
     *
     *         <p>
     *         <b>Time Complexity:</b> O(n) → Single traversal.<br>
     *         <b>Space Complexity:</b> O(1) → Only pointers used.
     */
    public ListNode deleteMiddleFastSlow(ListNode head) {
        if (head == null || head.next == null)
            return null; // single node case

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next; // move 1 step
            fast = fast.next.next; // move 2 steps
        }

        // Delete middle node
        prev.next = slow.next;

        return head;
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 *
 * <b>Approach 1: Length-Based (Two-Pass)</b><br>
 * - Time Complexity: O(n) → Two full traversals of the list.<br>
 * - Space Complexity: O(1) → Only counters and pointers used.<br>
 * <br>
 *
 * <b>Approach 2: Fast & Slow Pointer (Single-Pass)</b><br>
 * - Time Complexity: O(n) → Single traversal until fast reaches end.<br>
 * - Space Complexity: O(1) → Only slow, fast, and prev pointers.<br>
 * <br>
 *
 * <b>Comparison:</b><br>
 * - Both are O(n) time and O(1) space.<br>
 * - Approach 2 is more efficient since it finds and deletes in one pass.
 */
