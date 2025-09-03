import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode 2487: Remove Nodes From Linked List
 *
 * <p>
 * <b>Problem:</b><br>
 * You are given the head of a linked list.
 * Remove every node which has a node with a strictly greater value anywhere to
 * its right.
 * Return the head of the modified list.
 *
 * <p>
 * <b>Example:</b><br>
 * Input: head = [5,2,13,3,8]<br>
 * Output: [13,8]
 */
class Solution {

    /**
     * -------------------------------------------------
     * Approach 1: Using ArrayList
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1:</b> Traverse the linked list and store all values in an
     *           ArrayList.<br>
     *           <b>Step 2:</b> Iterate from right to left, track the maximum value
     *           seen so far.<br>
     *           - If a value is smaller than the maximum, mark it for removal.<br>
     *           - If it is greater or equal, keep it and update the max.<br>
     *           <br>
     *
     *           <b>Step 3:</b> Build a new linked list only with the kept values
     *           (in correct order).<br>
     *
     *           <b>Example Walkthrough:</b><br>
     *           - Input: [5,2,13,3,8]<br>
     *           - ArrayList = [5,2,13,3,8]<br>
     *           - Traverse backward:
     *           - Start with max=8, keep 8.<br>
     *           - 3 < 8 → remove.<br>
     *           - 13 > 8 → keep, update max=13.<br>
     *           - 2 < 13 → remove.<br>
     *           - 5 < 13 → remove.<br>
     *           - Remaining = [13,8] → build new list.<br>
     *
     * @param head head of the linked list
     * @return modified list with unwanted nodes removed
     *
     *         <p>
     *         <b>Time Complexity:</b> O(n) → One traversal to store + one backward
     *         traversal + rebuild list.<br>
     *         <b>Space Complexity:</b> O(n) → ArrayList used to store values.
     */
    public ListNode removeNodesArrayList(ListNode head) {
        List<Integer> values = new ArrayList<>();
        for (ListNode curr = head; curr != null; curr = curr.next) {
            values.add(curr.val);
        }

        List<Integer> filtered = new ArrayList<>();
        int maxSoFar = Integer.MIN_VALUE;
        for (int i = values.size() - 1; i >= 0; i--) {
            if (values.get(i) >= maxSoFar) {
                filtered.add(values.get(i));
                maxSoFar = values.get(i);
            }
        }

        Collections.reverse(filtered);

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : filtered) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    /**
     * -------------------------------------------------
     * Approach 2: Efficient (Reverse + Track + Reverse Back)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1:</b> Reverse the linked list.
     *           - Now we can traverse from "right to left" in original order.<br>
     *
     *           <b>Step 2:</b> Traverse reversed list while tracking the maximum
     *           value seen so far.<br>
     *           - Keep nodes whose value >= maxSoFar.<br>
     *           - Skip nodes that are smaller.<br>
     *
     *           <b>Step 3:</b> Reverse the list back to restore original order.<br>
     *
     *           <b>Example Walkthrough:</b><br>
     *           - Input: [5,2,13,3,8]<br>
     *           - Reverse → [8,3,13,2,5]<br>
     *           - Traverse:
     *           - max=8 → keep 8.<br>
     *           - 3 < 8 → remove.<br>
     *           - 13 > 8 → keep, update max=13.<br>
     *           - 2 < 13 → remove.<br>
     *           - 5 < 13 → remove.<br>
     *           - Result reversed list = [8,13] → reverse again → [13,8].<br>
     *
     * @param head head of the linked list
     * @return modified list with unwanted nodes removed
     *
     *         <p>
     *         <b>Time Complexity:</b> O(n) → Reverse (O(n)) + Filter (O(n)) +
     *         Reverse back (O(n)).<br>
     *         <b>Space Complexity:</b> O(1) → No extra data structure used, only
     *         pointer manipulation.
     */
    public ListNode removeNodesEfficient(ListNode head) {
        head = reverse(head);

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int maxSoFar = Integer.MIN_VALUE;

        while (head != null) {
            if (head.val >= maxSoFar) {
                curr.next = head;
                curr = head;
                maxSoFar = head.val;
            }
            head = head.next;
        }
        curr.next = null;

        return reverse(dummy.next);
    }

    /** Utility: Reverse a linked list */
    private ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 *
 * Approach 1: ArrayList
 * - Time Complexity: O(n)
 * - Space Complexity: O(n)
 *
 * Approach 2: Efficient (Reverse + Track + Reverse Back)
 * - Time Complexity: O(n)
 * - Space Complexity: O(1)
 */
