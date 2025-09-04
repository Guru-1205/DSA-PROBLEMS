/**
 * LeetCode 206: Reverse Linked List
 *
 * <p>
 * <b>Problem Description:</b><br>
 * Given the {@code head} of a singly linked list, reverse the list and return
 * the new head.
 *
 * <p>
 * <b>Sample Input / Output:</b>
 *
 * <pre>{@code
 * Example 1:
 * Input:  head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 *
 * Example 2:
 * Input:  head = []
 * Output: []
 * }</pre>
 *
 * <p>
 * <b>Constraints:</b>
 * <ul>
 * <li>The number of nodes in the list is in the range [0, 5000].</li>
 * <li>-5000 <= Node.val <= 5000</li>
 * </ul>
 *
 * -----------------------------------------------------------------------
 */
public class Reverse_Linked_List {

    /**
     * Reverses a linked list using an ArrayList to store values and rebuild a
     * new reversed list.
     *
     * <p>
     * <b>Approach 1: ArrayList-Based (Extra Space)</b>
     *
     * <p>
     * <b>Detailed Steps:</b>
     * <ol>
     * <li><b>Traverse and collect values:</b>
     * <ul>
     * <li>Create an {@code ArrayList<Integer>}.</li>
     * <li>Traverse the linked list and add each node value into the list.</li>
     * <li>Example: [1,2,3,4,5] → ArrayList = [1,2,3,4,5].</li>
     * </ul>
     * </li>
     *
     * <li><b>Rebuild in reverse order:</b>
     * <ul>
     * <li>Iterate ArrayList from last index to first.</li>
     * <li>Create new nodes with these values and link them.</li>
     * <li>Example: [1,2,3,4,5] → new list [5,4,3,2,1].</li>
     * </ul>
     * </li>
     *
     * <li><b>Return new head:</b>
     * <ul>
     * <li>If input is empty, return null.</li>
     * </ul>
     * </li>
     * </ol>
     *
     * <p>
     * <b>Complexity:</b>
     * <ul>
     * <li>Time: O(n) — traverse list + rebuild list.</li>
     * <li>Space: O(n) — extra ArrayList and new nodes.</li>
     * </ul>
     */
    public ListNode reverseListArrayList(ListNode head) {
        java.util.ArrayList<Integer> values = new java.util.ArrayList<>();
        ListNode curr = head;

        // Collect values
        while (curr != null) {
            values.add(curr.val);
            curr = curr.next;
        }

        // Rebuild reversed list
        ListNode dummy = new ListNode(0);
        curr = dummy;

        for (int i = values.size() - 1; i >= 0; i--) {
            curr.next = new ListNode(values.get(i));
            curr = curr.next;
        }

        return dummy.next;
    }

    /**
     * Reverses a linked list in-place using three pointers.
     *
     * <p>
     * <b>Approach 2: Efficient Three-Pointer In-Place</b>
     *
     * <p>
     * <b>Detailed Steps:</b>
     * <ol>
     * <li><b>Initialize pointers:</b>
     * <ul>
     * <li>{@code prev = null} → will become new tail.</li>
     * <li>{@code curr = head} → used to traverse list.</li>
     * </ul>
     * </li>
     *
     * <li><b>Iterate and reverse links:</b>
     * <ul>
     * <li>Save next node: {@code next = curr.next}.</li>
     * <li>Reverse link: {@code curr.next = prev}.</li>
     * <li>Advance pointers: {@code prev = curr}, {@code curr = next}.</li>
     * <li>Example with [1 → 2 → 3]:</li>
     * <ul>
     * <li>After first iteration: prev = 1 → null, curr = 2.</li>
     * <li>After second iteration: prev = 2 → 1 → null, curr = 3.</li>
     * </ul>
     * </ul>
     * </li>
     *
     * <li><b>Finish:</b>
     * <ul>
     * <li>When curr becomes null, prev is the new head.</li>
     * </ul>
     * </li>
     * </ol>
     *
     * <p>
     * <b>Complexity:</b>
     * <ul>
     * <li>Time: O(n) — single traversal.</li>
     * <li>Space: O(1) — in-place, no extra memory.</li>
     * </ul>
     */
    public ListNode reverseListThreePointers(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextNode = curr.next; // save next
            curr.next = prev; // reverse link
            prev = curr; // move prev
            curr = nextNode; // move curr
        }

        return prev;
    }

    /**
     * -----------------------------------------------------------------------
     * <b>Recommendation:</b>
     * Approach 1 (ArrayList) is intuitive but inefficient — O(n) space.
     * Approach 2 (Three Pointers) is the optimal solution with O(1) space.
     * -----------------------------------------------------------------------
     */
}
