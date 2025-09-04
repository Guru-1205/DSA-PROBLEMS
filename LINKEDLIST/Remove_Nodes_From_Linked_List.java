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
 * 
 * -----------------------------------------------------------------------
 * 
 */
public class Remove_Nodes_From_Linked_List {

    /**
     * Removes all nodes equal to {@code val} by collecting kept values into an
     * ArrayList,
     * then rebuilding a new linked list from those values.
     * 
     * @param head the head of the input list (may be {@code null})
     * @param val  the value to remove
     * @return the head of the rebuilt list containing only nodes with value !=
     *         {@code val}
     *         <p>
     *         <b>Method details:</b>
     *         <p>
     *         <b>Approach 1: Using ArrayList (Filter → Rebuild)</b>
     *         <ul>
     *         <li>Preserves original relative order of kept nodes.</li>
     *         <li>Allocates new ListNode objects for the result.</li>
     *         </ul>
     *         <p>
     *         <b>Detailed Steps:</b>
     *         <ol>
     *         <li><b>Traverse & Collect:</b>
     *         <ul>
     *         <li>Initialize an
     *         {@code ArrayList<Integer> kept = new ArrayList<>();}.</li>
     *         <li>Walk the linked list from {@code head} to {@code null}.</li>
     *         <li>For each node, if {@code node.val != val} add {@code node.val} to
     *         {@code kept}.</li>
     *         </ul>
     *         <b>Invariant:</b> after this pass {@code kept} contains exactly the
     *         values to
     *         preserve,
     *         in the same order they appeared in the original list.</li>
     *
     *         <li><b>Rebuild the linked list:</b>
     *         <ul>
     *         <li>Create a dummy node {@code ListNode dummy = new ListNode(0);} and
     *         a tail
     *         pointer {@code tail = dummy}.</li>
     *         <li>Iterate over {@code kept} and append new {@code ListNode} nodes
     *         in
     *         sequence.</li>
     *         <li>Return {@code dummy.next} as the new head.</li>
     *         </ul>
     *         <b>Invariant:</b> rebuilt list contains only values from {@code kept}
     *         in
     *         correct order.</li>
     *
     *         <li><b>Edge cases:</b>
     *         <ul>
     *         <li>If {@code head == null} → {@code kept} remains empty → returns
     *         {@code null}.</li>
     *         <li>If every node matches {@code val} → {@code kept} empty → returns
     *         {@code null}.</li>
     *         </ul>
     *         </li>
     *         </ol>
     *         <p>
     *         <b>Approach 1 — Complexity:</b>
     *         <ul>
     *         <li>Time: O(n) — one pass to collect values plus one pass to rebuild
     *         (linear
     *         overall).</li>
     *         <li>Space (aux): O(n) — the ArrayList holds up to n values in
     *         worst-case.</li>
     *         </ul>
     * 
     *
     * 
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

        // Rebuild list from kept values
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : kept) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    /**
     * Removes all nodes equal to {@code val} in-place using a dummy sentinel and a
     * single pass.
     * 
     * @param head the head of the input list (may be {@code null})
     * @param val  the value to remove
     * @return the head of the modified list (may be {@code null})
     *         <p>
     *         <b>Method details:</b>
     *         <ul>
     *         <li>Does not allocate additional list nodes other than the
     *         dummy.</li>
     *         <li>Handles deletions at the head cleanly because of the dummy
     *         sentinel.</li>
     *         <li>Careful not to advance the traversal pointer after a deletion so
     *         consecutive matches are handled.</li>
     *         </ul>
     *         <p>
     *         <b>Approach 2: Efficient In-Place Deletion (Dummy + One Pass)</b>
     *
     *         <p>
     *         <b>Detailed Steps (very explicit):</b>
     *         <ol>
     *         <li><b>Create dummy sentinel:</b>
     *         <ul>
     *         <li>{@code ListNode dummy = new ListNode(0, head);} — dummy.next
     *         points to
     *         the real head.</li>
     *         <li>This unifies handling of deletions at the original head without
     *         special
     *         cases.</li>
     *         </ul>
     *         </li>
     *
     *         <li><b>Initialize traversal pointer:</b>
     *         <ul>
     *         <li>Set {@code ListNode current = dummy;}.</li>
     *         <li>We will examine {@code current.next} each iteration (look-ahead
     *         pattern).</li>
     *         <li><b>Invariant:</b> at the start of each loop, all nodes before
     *         {@code current} are already
     *         confirmed to be kept (none of them has value {@code val}).</li>
     *         </ul>
     *         </li>
     *
     *         <li><b>Loop: while (current.next != null):</b>
     *         <ol type="a">
     *         <li><b>If {@code current.next.val == val}:</b>
     *         <ul>
     *         <li>We need to delete {@code current.next}.</li>
     *         <li>Perform {@code current.next = current.next.next;} which bypasses
     *         (unlinks) the node.</li>
     *         <li><b>Important:</b> Do NOT move {@code current} forward after
     *         deletion —
     *         the new {@code current.next}
     *         must be examined (handles consecutive nodes equal to
     *         {@code val}).</li>
     *         </ul>
     *         </li>
     *
     *         <li><b>Else (current.next.val != val):</b>
     *         <ul>
     *         <li>No deletion needed for {@code current.next}, so advance:
     *         {@code current = current.next;}.</li>
     *         <li>This preserves the invariant for the next iteration.</li>
     *         </ul>
     *         </li>
     *         </ol>
     *         </li>
     *
     *         <li><b>Termination:</b>
     *         <ul>
     *         <li>Loop ends when {@code current.next == null} — no more nodes to
     *         check.</li>
     *         <li>Return {@code dummy.next} which points to the possibly-updated
     *         head.</li>
     *         </ul>
     *         </li>
     *         </ol>
     *
     *         <p>
     *         <b>Why the "do not advance after delete" rule is required:</b>
     *         <ul>
     *         <li>If you advanced {@code current} after unlinking, you might skip
     *         checking
     *         the next node which could also
     *         be equal to {@code val}. That would cause incorrect behavior on runs
     *         like
     *         {@code [6,6,6,...]}.</li>
     *         </ul>
     *
     *         <p>
     *         <b>Approach 2 — Complexity:</b>
     *         <ul>
     *         <li>Time: O(n) — each node is visited at most once; deletions are
     *         O(1).</li>
     *         <li>Space: O(1) auxiliary — only a small fixed number of pointers are
     *         used
     *         (dummy, current).</li>
     *         </ul>
     */
    public ListNode removeElementsEfficient(ListNode head, int val) {
        ListNode dummy = new ListNode(0, head);
        ListNode current = dummy;

        while (current.next != null) {
            if (current.next.val == val) {
                // unlink the matching node
                current.next = current.next.next;
                // do NOT advance current here - need to re-examine new current.next
            } else {
                current = current.next;
            }
        }

        return dummy.next;
    }
    /**
     * -----------------------------------------------------------------------
     * <p>
     * <b>Recommendation:</b> Use Approach 2 (Efficient In-Place) for
     * interviews and
     * production code
     * unless you specifically need a rebuilt copy or cannot modify links
     * (immutable
     * lists).
     * </p>
     */
}