/**
 * LeetCode 1474: Delete N Nodes After M Nodes of a Linked List
 *
 * <p>
 * <b>Problem:</b><br>
 * Given the head of a linked list, delete N nodes after every M nodes until the
 * end of the list. Return the head of the modified list.
 *
 * <p>
 * <b>Example:</b><br>
 * Input: head = [1,2,3,4,5,6,7,8,9,10], M=2, N=3<br>
 * Output: [1,2,6,7]
 *
 * <p>
 * We solve this using two approaches:
 * <ul>
 * <li><b>Approach 1:</b> Using ArrayList (Rebuild a New Linked List)</li>
 * <li><b>Approach 2:</b> Efficient In-Place Deletion (Single Pass)</li>
 * </ul>
 */
class Solution {

    /**
     * -------------------------------------------------
     * Approach 1: Using ArrayList (Rebuild a New Linked List)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1: Collect values into an ArrayList</b><br>
     *           - Traverse the original list node by node.<br>
     *           - Maintain counters: keep M nodes, skip N nodes.<br>
     *           - Example (M=2, N=3, list=[1,2,3,4,5,6,7]): Collect [1,2], skip
     *           [3,4,5], collect [6,7].<br>
     *           <br>
     *
     *           <b>Step 2: Build a new linked list from ArrayList</b><br>
     *           - Use a dummy node to simplify construction.<br>
     *           - Append all kept values sequentially.<br>
     *           - Example: ArrayList [1,2,6,7] → Linked List 1 -> 2 -> 6 -> 7.<br>
     *           <br>
     *
     *           <b>Step 3: Return dummy.next as new head</b><br>
     *           - Edge Case: If ArrayList is empty (all nodes deleted), return
     *           null.<br>
     *
     * @param head The head of the original linked list
     * @param m    Number of nodes to keep
     * @param n    Number of nodes to delete
     * @return New linked list head after deletions
     *
     *         <p>
     *         <b>Time Complexity:</b> O(n)<br>
     *         <b>Space Complexity:</b> O(n) — due to ArrayList and new list
     *         creation.
     */
    public ListNode deleteNodesArrayList(ListNode head, int m, int n) {
        java.util.ArrayList<Integer> kept = new java.util.ArrayList<>();
        ListNode curr = head;

        while (curr != null) {
            // Collect M nodes
            for (int i = 0; i < m && curr != null; i++) {
                kept.add(curr.val);
                curr = curr.next;
            }
            // Skip N nodes
            for (int i = 0; i < n && curr != null; i++) {
                curr = curr.next;
            }
        }

        // Build new linked list
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        for (int val : kept) {
            temp.next = new ListNode(val);
            temp = temp.next;
        }

        return dummy.next;
    }

    /**
     * -------------------------------------------------
     * Approach 2: Efficient In-Place Deletion (Single Pass)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1: Traverse and keep M nodes</b><br>
     *           - Start with current=head.<br>
     *           - Move forward M-1 times (current already counts as first kept
     *           node).<br>
     *           - Example: head=[1,2,3,4], M=2 → Keep [1,2], stop at 2.<br>
     *           - Edge Case: If list ends before M nodes, stop.<br>
     *           <br>
     *
     *           <b>Step 2: Skip N nodes</b><br>
     *           - From current.next, move N times.<br>
     *           - If list ends early, stop skipping.<br>
     *           - After skipping, link current.next to the remaining list.<br>
     *           - Example: M=2, N=3, list=[1,2,3,4,5,6] → Keep [1,2], Skip [3,4,5],
     *           Link 2 -> 6.<br>
     *           <br>
     *
     *           <b>Step 3: Repeat until end</b><br>
     *           - Move current to current.next (remaining part).<br>
     *           - Repeat Steps 1 & 2 until list ends.<br>
     *           - Edge Case: If fewer than N remain, cut off list at null.<br>
     *
     * @param head The head of the linked list
     * @param m    Number of nodes to keep
     * @param n    Number of nodes to delete
     * @return Modified linked list head after deletions (in-place)
     *
     *         <p>
     *         <b>Time Complexity:</b> O(n)<br>
     *         <b>Space Complexity:</b> O(1) — only pointer manipulation.
     */
    public ListNode deleteNodesEfficient(ListNode head, int m, int n) {
        if (head == null)
            return null;

        ListNode current = head;

        while (current != null) {
            // Keep M-1 nodes
            for (int i = 1; i < m && current != null; i++) {
                current = current.next;
            }

            if (current == null)
                break; // ran out of nodes

            // Skip N nodes
            ListNode temp = current.next;
            for (int i = 0; i < n && temp != null; i++) {
                temp = temp.next;
            }

            // Link current to remaining list
            current.next = temp;
            current = temp;
        }

        return head;
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 *
 * <b>Approach 1: ArrayList</b><br>
 * - Time Complexity: O(n) → Traverse once O(n), rebuild list O(n).<br>
 * - Space Complexity: O(n) → ArrayList stores kept values + new list nodes.<br>
 * <br>
 *
 * <b>Approach 2: Efficient In-Place</b><br>
 * - Time Complexity: O(n) → Each node is visited at most once.<br>
 * - Space Complexity: O(1) → Only a few pointers used.<br>
 * <br>
 *
 * <b>Comparison:</b><br>
 * - Both approaches are O(n) in time.<br>
 * - ArrayList approach uses O(n) space (not memory-efficient).<br>
 * - Efficient approach is optimal with O(1) space.
 */
