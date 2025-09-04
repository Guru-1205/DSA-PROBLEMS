import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 203: Remove Linked List Elements
 *
 * <p>
 * <b>Problem Description:</b><br>
 * Given the {@code head} of a singly linked list and an integer {@code val},
 * remove all nodes of the linked list that have {@code node.val == val}, and
 * return the head of the modified list.
 *
 * <p>
 * <b>Sample Input / Output:</b>
 * 
 * <pre>{@code
 * Example 1:
 * Input:  head = [1,2,6,3,4,5,6], val = 6
 * Output: [1,2,3,4,5]
 *
 * Example 2:
 * Input:  head = [], val = 1
 * Output: []
 *
 * Example 3:
 * Input:  head = [7,7,7,7], val = 7
 * Output: []
 * }</pre>
 *
 * <p>
 * <b>Constraints:</b>
 * <ul>
 * <li>0 <= n <= 10^5 (n = number of nodes)</li>
 * <li>Node values are within 32-bit signed integer range</li>
 * </ul>
 *
 * -----------------------------------------------------------------------
 */
public class Remove_Linked_List_Elements {

    /**
     * Removes all nodes equal to {@code val} by collecting values into an
     * ArrayList and then rebuilding a new linked list.
     * 
     * <p>
     * <b>Approach 1: Using ArrayList (Filter → Rebuild)</b>
     *
     * <p>
     * <b>Detailed Steps:</b>
     * <ol>
     * <li><b>Initialize an empty list:</b>
     * <ul>
     * <li>Create {@code ArrayList<Integer> kept = new ArrayList<>();}.</li>
     * <li>This will temporarily hold only the values we want to keep.</li>
     * </ul>
     * </li>
     *
     * <li><b>Traverse the original linked list:</b>
     * <ul>
     * <li>Start from {@code head} and move until {@code null}.</li>
     * <li>For each node:
     * <ul>
     * <li>If {@code node.val != val}, add it to {@code kept}.</li>
     * <li>If {@code node.val == val}, skip it (do not add).</li>
     * </ul>
     * </li>
     * <li><b>Invariant:</b> At the end, {@code kept} contains exactly the values
     * that should remain in the final list, in the same order.</li>
     * </ul>
     * </li>
     *
     * <li><b>Rebuild the linked list:</b>
     * <ul>
     * <li>Create a dummy node: {@code ListNode dummy = new ListNode(0);}.</li>
     * <li>Maintain a tail pointer {@code tail = dummy;}.</li>
     * <li>Iterate through {@code kept}:
     * <ul>
     * <li>For each value {@code v}, create a new node {@code new ListNode(v)}.</li>
     * <li>Attach it to {@code tail.next} and move {@code tail} forward.</li>
     * </ul>
     * </li>
     * <li>At the end, return {@code dummy.next} as the head of the new list.</li>
     * </ul>
     * </li>
     *
     * <li><b>Edge Cases:</b>
     * <ul>
     * <li>If {@code head == null}, the traversal does nothing → return
     * {@code null}.</li>
     * <li>If every node equals {@code val}, then {@code kept} is empty → return
     * {@code null}.</li>
     * <li>If no node equals {@code val}, then {@code kept} has all values → rebuild
     * gives the same list.</li>
     * </ul>
     * </li>
     * </ol>
     *
     * <p>
     * <b>Complexity:</b>
     * <ul>
     * <li>Time: O(n) — one full traversal to collect, one to rebuild.</li>
     * <li>Space: O(n) — extra ArrayList stores up to n values.</li>
     * </ul>
     */
    public ListNode removeElementsArrayList(ListNode head, int val) {
        List<Integer> kept = new ArrayList<>();
        ListNode curr = head;

        // Collect values to keep
        while (curr != null) {
            if (curr.val != val) {
                kept.add(curr.val);
            }
            curr = curr.next;
        }

        // Rebuild list
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : kept) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    /**
     * Removes all nodes equal to {@code val} directly by relinking nodes in place.
     *
     * <p>
     * <b>Approach 2: Efficient In-Place Deletion (Dummy + One Pass)</b>
     *
     * <p>
     * <b>Detailed Steps:</b>
     * <ol>
     * <li><b>Create a dummy sentinel node:</b>
     * <ul>
     * <li>{@code ListNode dummy = new ListNode(0, head);} — dummy points to the
     * real head.</li>
     * <li>Using dummy avoids special handling when the head itself must be
     * deleted.</li>
     * </ul>
     * </li>
     *
     * <li><b>Initialize a traversal pointer:</b>
     * <ul>
     * <li>Set {@code current = dummy}.</li>
     * <li>We will always check {@code current.next}, not {@code current}, so that
     * we can modify links if needed.</li>
     * <li><b>Invariant:</b> all nodes before {@code current} are guaranteed to be
     * valid (do not equal {@code val}).</li>
     * </ul>
     * </li>
     *
     * <li><b>Traverse the list:</b>
     * <ul>
     * <li>While {@code current.next != null}:</li>
     * <li>If {@code current.next.val == val}:
     * <ul>
     * <li>Unlink that node using {@code current.next = current.next.next}.</li>
     * <li>Do not move {@code current} forward — because the new
     * {@code current.next}
     * might also have value {@code val}.</li>
     * </ul>
     * </li>
     * <li>Else (value is fine):
     * <ul>
     * <li>Advance {@code current = current.next}.</li>
     * </ul>
     * </li>
     * </ul>
     * </li>
     *
     * <li><b>End condition:</b>
     * <ul>
     * <li>Loop finishes when {@code current.next == null} → no more nodes
     * left.</li>
     * <li>Return {@code dummy.next} as the possibly-updated head.</li>
     * </ul>
     * </li>
     * </ol>
     *
     * <p>
     * <b>Why "do not advance after deletion" is necessary:</b>
     * <ul>
     * <li>Example: list = [6,6,6,3], val = 6.</li>
     * <li>If we advanced after the first deletion, we would skip checking the
     * second 6.</li>
     * <li>By staying in place, we ensure consecutive matches are also removed.</li>
     * </ul>
     *
     * <p>
     * <b>Complexity:</b>
     * <ul>
     * <li>Time: O(n) — each node checked once, deletions O(1).</li>
     * <li>Space: O(1) — only dummy and current pointers used.</li>
     * </ul>
     */
    public ListNode removeElementsEfficient(ListNode head, int val) {
        ListNode dummy = new ListNode(0, head);
        ListNode current = dummy;

        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next; // unlink node
            } else {
                current = current.next;
            }
        }
        return dummy.next;
    }

    /**
     * -----------------------------------------------------------------------
     * <b>Recommendation:</b>
     * Use Approach 2 (Efficient In-Place) in interviews and production code
     * because it is memory-efficient, avoids rebuilding, and handles all cases
     * cleanly.
     * -----------------------------------------------------------------------
     */
}
