/**
 * LeetCode 19: Remove Nth Node From End of List
 *
 * <p>
 * <b>Problem Description:</b><br>
 * Given the {@code head} of a linked list, remove the {@code nth} node from the
 * end of the list and return its head.
 *
 * <p>
 * <b>Sample Input / Output:</b>
 *
 * <pre>{@code
 * Example 1:
 * Input:  head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 *
 * Example 2:
 * Input:  head = [1], n = 1
 * Output: []
 *
 * Example 3:
 * Input:  head = [1,2], n = 1
 * Output: [1]
 * }</pre>
 *
 * <p>
 * <b>Constraints:</b>
 * <ul>
 * <li>The number of nodes in the list is {@code sz}.</li>
 * <li>1 <= sz <= 10^5</li>
 * <li>0 <= Node.val <= 100</li>
 * <li>1 <= n <= sz</li>
 * </ul>
 *
 * -----------------------------------------------------------------------
 */
public class Remove_Nth_Node_Back_From_List {

    /**
     * Removes the nth node from the end by first counting the total length, then
     * deleting the (length - n)th node from the front.
     *
     * <p>
     * <b>Approach 1: Counting Method (Two Passes)</b>
     *
     * <p>
     * <b>Detailed Steps:</b>
     * <ol>
     * <li><b>Count the length of the list:</b>
     * <ul>
     * <li>Initialize {@code length = 0} and a pointer {@code curr = head}.</li>
     * <li>Traverse the entire list while incrementing {@code length}.</li>
     * <li><b>Invariant:</b> After traversal, {@code length} stores total
     * nodes.</li>
     * </ul>
     * </li>
     *
     * <li><b>Find the node before the target:</b>
     * <ul>
     * <li>Target index from the front = {@code length - n} (0-based).</li>
     * <li>Create dummy node pointing to {@code head} to simplify head
     * deletion.</li>
     * <li>Set {@code curr = dummy}.</li>
     * <li>Move forward {@code length - n} steps → {@code curr} now points to the
     * node before the one to remove.</li>
     * </ul>
     * </li>
     *
     * <li><b>Delete the target node:</b>
     * <ul>
     * <li>Unlink it by {@code curr.next = curr.next.next}.</li>
     * </ul>
     * </li>
     *
     * <li><b>Return result:</b>
     * <ul>
     * <li>Return {@code dummy.next} as the new head.</li>
     * </ul>
     * </li>
     *
     * <li><b>Edge Cases:</b>
     * <ul>
     * <li>If {@code n == length}, it means remove head → handled by dummy
     * node.</li>
     * <li>If list has only one node and {@code n == 1}, result is
     * {@code null}.</li>
     * </ul>
     * </li>
     * </ol>
     *
     * <p>
     * <b>Complexity:</b>
     * <ul>
     * <li>Time: O(n) — one pass to count, one pass to remove.</li>
     * <li>Space: O(1) — only pointers and counters used.</li>
     * </ul>
     */
    public ListNode removeNthFromEndCounting(ListNode head, int n) {
        int length = 0;
        ListNode curr = head;

        // First pass: count length
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        // Dummy node for safe deletion
        ListNode dummy = new ListNode(0, head);
        curr = dummy;

        // Move to node before target
        for (int i = 0; i < length - n; i++) {
            curr = curr.next;
        }

        // Delete target node
        curr.next = curr.next.next;

        return dummy.next;
    }

    /**
     * Removes the nth node from the end using two pointers with a gap of n between
     * them in one pass.
     *
     * <p>
     * <b>Approach 2: Two Pointers (Fast & Slow)</b>
     *
     * <p>
     * <b>Detailed Steps:</b>
     * <ol>
     * <li><b>Create dummy node:</b>
     * <ul>
     * <li>{@code dummy = new ListNode(0, head)} to simplify edge cases.</li>
     * <li>Initialize two pointers: {@code fast = dummy}, {@code slow = dummy}.</li>
     * </ul>
     * </li>
     *
     * <li><b>Advance fast pointer by n+1 steps:</b>
     * <ul>
     * <li>Move {@code fast} ahead {@code n+1} times.</li>
     * <li><b>Reason:</b> Creates a gap of n nodes between {@code fast} and {@code
     * slow}.</li>
     * <li>After this, when {@code fast} reaches the end, {@code slow} will be just
     * before the node to remove.</li>
     * </ul>
     * </li>
     *
     * <li><b>Move both pointers together:</b>
     * <ul>
     * <li>While {@code fast != null}, move both {@code fast} and {@code slow}
     * forward one step at a time.</li>
     * <li><b>Invariant:</b> Distance between {@code fast} and {@code slow} remains
     * n.</li>
     * </ul>
     * </li>
     *
     * <li><b>Delete the target node:</b>
     * <ul>
     * <li>Now {@code slow.next} is the node to remove.</li>
     * <li>Perform {@code slow.next = slow.next.next}.</li>
     * </ul>
     * </li>
     *
     * <li><b>Return result:</b>
     * <ul>
     * <li>Return {@code dummy.next} as the updated head.</li>
     * </ul>
     * </li>
     *
     * <li><b>Edge Cases:</b>
     * <ul>
     * <li>If {@code n == length}, fast moves beyond head and slow stays at dummy,
     * deleting head.</li>
     * <li>If only one node, deletion works since dummy bypasses it → returns
     * null.</li>
     * </ul>
     * </li>
     * </ol>
     *
     * <p>
     * <b>Complexity:</b>
     * <ul>
     * <li>Time: O(n) — each node visited once.</li>
     * <li>Space: O(1) — constant extra pointers used.</li>
     * </ul>
     */
    public ListNode removeNthFromEndTwoPointers(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        // Move fast n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both until fast reaches end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Delete target node
        slow.next = slow.next.next;

        return dummy.next;
    }

    /**
     * -----------------------------------------------------------------------
     * <b>Recommendation:</b>
     * Approach 2 (Two Pointers) is preferable in interviews and production,
     * since it solves the problem in a single pass and is memory-efficient.
     * Approach 1 (Counting) is simpler but requires two passes.
     * -----------------------------------------------------------------------
     */
}
