import java.util.ArrayList;

/**
 * LeetCode 1474: Delete N Nodes After M Nodes of a Linked List
 *
 * <p>
 * <b>Problem Description:</b><br>
 * Given the head of a linked list, delete N nodes after every M nodes until the
 * end of the list. Return the head of the modified list.
 *
 * <p>
 * Example:<br>
 * Input: head = [1,2,3,4,5,6,7,8,9,10], M = 2, N = 3<br>
 * Output: [1,2,6,7]<br>
 */
public class Delete_N_Nodes_After_M_Nodes_Of_Linked_List {

    /**
     * -------------------------------------------------
     * Approach 1: Using ArrayList (Rebuild a New Linked List)
     * -------------------------------------------------
     *
     * <b>Step 1: Collect kept nodes into an ArrayList</b><br>
     * - Start traversal from the head.<br>
     * - For each cycle, collect M nodes into an ArrayList.<br>
     * - Then skip N nodes by moving the pointer forward.<br>
     * - Example: M=2, N=3 on [1,2,3,4,5,6,7] → Keep [1,2], Skip [3,4,5], Keep
     * [6,7].<br>
     * <br>
     *
     * <b>Step 2: Build a new linked list from kept values</b><br>
     * - Create a dummy node to simplify construction.<br>
     * - Append all collected values sequentially.<br>
     * - Example: ArrayList [1,2,6,7] → Linked List 1 -> 2 -> 6 -> 7.<br>
     * <br>
     *
     * <b>Step 3: Return dummy.next</b><br>
     * - If ArrayList is empty (all nodes skipped), return null.<br>
     *
     * @param head Head of the original linked list
     * @param m    Number of nodes to keep
     * @param n    Number of nodes to delete
     * @return New linked list head after deletions
     */
    public ListNode deleteNodesArrayList(ListNode head, int m, int n) {
        ArrayList<Integer> kept = new ArrayList<>();
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
     * <b>Step 1: Traverse and keep M nodes</b><br>
     * - Start from head, move forward M-1 times (since current counts as first kept
     * node).<br>
     * - Example: list=[1,2,3,4], M=2 → Keep [1,2], stop at 2.<br>
     * - If list ends before M nodes, stop.<br>
     * <br>
     *
     * <b>Step 2: Skip N nodes</b><br>
     * - From current.next, move N steps ahead.<br>
     * - If list ends early, stop skipping.<br>
     * - Link current.next to the remaining part after skipping.<br>
     * - Example: M=2, N=3, list=[1,2,3,4,5,6] → Keep [1,2], Skip [3,4,5], Link 2 →
     * 6.<br>
     * <br>
     *
     * <b>Step 3: Repeat until end</b><br>
     * - Move current pointer to current.next.<br>
     * - Repeat Steps 1 & 2 until end of list.<br>
     * - Edge case: If fewer than N remain at the end, set current.next = null.<br>
     *
     * @param head Head of the linked list
     * @param m    Number of nodes to keep
     * @param n    Number of nodes to delete
     * @return Modified linked list head (in-place)
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
                break;

            // Skip N nodes
            ListNode temp = current.next;
            for (int i = 0; i < n && temp != null; i++) {
                temp = temp.next;
            }

            // Link kept part to remaining part
            current.next = temp;
            current = temp;
        }

        return head;
    }
}

/**
 * -------------------------------------------------
 * Complexity Analysis
 * -------------------------------------------------
 *
 * Approach 1 (ArrayList):
 * - Time Complexity: O(n) → Traverse list once, rebuild list once.
 * - Space Complexity: O(n) → Extra ArrayList + new linked list.
 *
 * Approach 2 (Efficient In-Place):
 * - Time Complexity: O(n) → Each node visited at most once.
 * - Space Complexity: O(1) → Constant extra space, only pointers used.
 *
 * Comparison:
 * - Both are O(n) in time.
 * - ArrayList uses extra space; efficient approach is optimal.
 */
