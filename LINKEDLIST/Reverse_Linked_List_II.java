/**
 * LeetCode 92: Reverse Linked List II
 *
 * <p>
 * <b>Problem Description:</b><br>
 * Given the {@code head} of a singly linked list and two integers {@code left}
 * and {@code right} where {@code left <= right}, reverse the nodes of the list
 * from position {@code left} to position {@code right}, and return the reversed
 * list.
 *
 * <p>
 * <b>Sample Input / Output:</b>
 *
 * <pre>{@code
 * Example 1:
 * Input:  head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 *
 * Example 2:
 * Input:  head = [5], left = 1, right = 1
 * Output: [5]
 * }</pre>
 *
 * <p>
 * <b>Constraints:</b>
 * <ul>
 * <li>The number of nodes in the list is {@code n}.</li>
 * <li>1 <= n <= 500</li>
 * <li>-500 <= Node.val <= 500</li>
 * <li>1 <= left <= right <= n</li>
 * </ul>
 *
 * -----------------------------------------------------------------------
 */
public class Reverse_Linked_List_II {

    /**
     * Reverses the sublist between left and right using an ArrayList to store
     * references.
     *
     * <p>
     * <b>Approach 1: ArrayList-Based (Simple but Less Efficient)</b>
     *
     * <p>
     * <b>Detailed Steps:</b>
     * <ol>
     * <li><b>Store all nodes in a list:</b>
     * <ul>
     * <li>Create {@code List<ListNode>} and traverse the linked list.</li>
     * <li>Add each node to the ArrayList (preserving order).</li>
     * </ul>
     * </li>
     *
     * <li><b>Reverse values between left and right:</b>
     * <ul>
     * <li>Indices are {@code left-1} to {@code right-1} (0-based).</li>
     * <li>Use two-pointer technique on the ArrayList → swap values in place.</li>
     * </ul>
     * </li>
     *
     * <li><b>Reconstruct list:</b>
     * <ul>
     * <li>Since only values are swapped, no structural re-linking needed.</li>
     * <li>Return original head.</li>
     * </ul>
     * </li>
     * </ol>
     *
     * <p>
     * <b>Complexity:</b>
     * <ul>
     * <li>Time: O(n) — traversal + reversal inside ArrayList.</li>
     * <li>Space: O(n) — stores all nodes in ArrayList.</li>
     * </ul>
     */
    public ListNode reverseBetweenArrayList(ListNode head, int left, int right) {
        if (head == null || left == right)
            return head;

        java.util.List<ListNode> list = new java.util.ArrayList<>();
        ListNode curr = head;

        // Store nodes in ArrayList
        while (curr != null) {
            list.add(curr);
            curr = curr.next;
        }

        // Swap values between left and right
        int i = left - 1, j = right - 1;
        while (i < j) {
            int temp = list.get(i).val;
            list.get(i).val = list.get(j).val;
            list.get(j).val = temp;
            i++;
            j--;
        }

        return head;
    }

    /**
     * Efficient in-place reversal of a sublist between left and right.
     *
     * <p>
     * <b>Approach 2: In-Place Reversal (Efficient)</b>
     *
     * <p>
     * <b>Detailed Steps:</b>
     * <ol>
     * <li><b>Initialize dummy node:</b>
     * <ul>
     * <li>Create {@code dummy = new ListNode(0, head)}.</li>
     * <li>Use pointer {@code prev} to reach the node before {@code left}.</li>
     * </ul>
     * </li>
     *
     * <li><b>Identify sublist:</b>
     * <ul>
     * <li>{@code curr = prev.next} → first node in sublist.</li>
     * </ul>
     * </li>
     *
     * <li><b>Reverse sublist by shifting nodes:</b>
     * <ul>
     * <li>Repeat {@code right - left} times:</li>
     * <li>Take out node after {@code curr} → {@code next = curr.next}.</li>
     * <li>Detach {@code next} and insert it right after {@code prev}.</li>
     * <li>Reconnect pointers accordingly.</li>
     * </ul>
     * </li>
     *
     * <li><b>Reconnect list:</b>
     * <ul>
     * <li>Dummy ensures even if left=1, logic works seamlessly.</li>
     * </ul>
     * </li>
     * </ol>
     *
     * <p>
     * <b>Complexity:</b>
     * <ul>
     * <li>Time: O(n) — single traversal, localized reversal.</li>
     * <li>Space: O(1) — in-place operations only.</li>
     * </ul>
     */
    public ListNode reverseBetweenEfficient(ListNode head, int left, int right) {
        if (head == null || left == right)
            return head;

        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        // Step 1: move prev to node before left
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        // Step 2: start reversing
        ListNode curr = prev.next;
        for (int i = 0; i < right - left; i++) {
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }

        return dummy.next;
    }

    /**
     * -----------------------------------------------------------------------
     * <b>Recommendation:</b>
     * Approach 1 (ArrayList) is simple to implement but uses extra memory.
     * Approach 2 (In-place) is the optimal interview-ready solution.
     * -----------------------------------------------------------------------
     */
}
