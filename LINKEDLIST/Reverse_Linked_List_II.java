/**
 * LeetCode 92: Reverse Linked List II
 *
 * <p>
 * <b>Problem:</b><br>
 * Given the head of a singly linked list and two integers left and right where
 * left <= right,
 * reverse the nodes of the list from position left to right, and return the
 * reversed list.
 *
 * <p>
 * <b>Constraints:</b><br>
 * - Length of list: [1, 500].<br>
 * - Node values: [-500, 500].<br>
 * - 1 <= left <= right <= list length.<br>
 *
 * <p>
 * <b>Example:</b><br>
 * Input: head = [1,2,3,4,5], left = 2, right = 4<br>
 * Output: [1,4,3,2,5]<br>
 */
class Solution {

    /**
     * -------------------------------------------------
     * Approach 1: Using ArrayList (Extra Space)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1:</b> Traverse the linked list and collect all values into
     *           an ArrayList.
     *           Example: [1,2,3,4,5] → [1,2,3,4,5].<br>
     *           <br>
     *
     *           <b>Step 2:</b> Reverse the sublist values from index (left-1) to
     *           (right-1).
     *           Example: [1,2,3,4,5], left=2, right=4 → [1,4,3,2,5].<br>
     *           <br>
     *
     *           <b>Step 3:</b> Rebuild a new linked list from the modified
     *           ArrayList.
     *           Return the new head.<br>
     *
     * @param head  original list head
     * @param left  start position of reversal
     * @param right end position of reversal
     * @return new head of modified list
     */
    public ListNode reverseBetweenArrayList(ListNode head, int left, int right) {
        java.util.ArrayList<Integer> values = new java.util.ArrayList<>();
        ListNode curr = head;

        while (curr != null) {
            values.add(curr.val);
            curr = curr.next;
        }

        int i = left - 1, j = right - 1;
        while (i < j) {
            int temp = values.get(i);
            values.set(i, values.get(j));
            values.set(j, temp);
            i++;
            j--;
        }

        ListNode dummy = new ListNode(0);
        curr = dummy;
        for (int val : values) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }

        return dummy.next;
    }

    /**
     * -------------------------------------------------
     * Approach 2: Efficient In-Place Reversal
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1:</b> Use a dummy node to handle edge cases when left=1.
     *           - Traverse until the node just before `left` (call this `prev`).
     *           - Example: for left=2, `prev` points to node with value 1.<br>
     *           <br>
     *
     *           <b>Step 2:</b> Reverse the sublist from `left` to `right`.
     *           - Keep track of current (`curr`), next, and prev pointers.
     *           - Iteratively reverse `right-left+1` nodes.<br>
     *           - Example: [1,2,3,4,5], left=2, right=4 → reversed sublist
     *           [4,3,2].<br>
     *           <br>
     *
     *           <b>Step 3:</b> Reconnect the reversed sublist with the remaining
     *           nodes.
     *           - `prev.next.next` should point to the node after `right`.
     *           - `prev.next` should point to the head of the reversed sublist.<br>
     *           <br>
     *
     *           <b>Step 4:</b> Return dummy.next as the new head.
     *
     * @param head  original list head
     * @param left  start position of reversal
     * @param right end position of reversal
     * @return new head of modified list
     */
    public ListNode reverseBetweenEfficient(ListNode head, int left, int right) {
        if (head == null || left == right)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        // Step 1: Move prev to the node before left
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        // Step 2: Reverse sublist
        ListNode curr = prev.next;
        ListNode next;
        ListNode sublistPrev = null;

        for (int i = 0; i <= right - left; i++) {
            next = curr.next;
            curr.next = sublistPrev;
            sublistPrev = curr;
            curr = next;
        }

        // Step 3: Connect parts
        ListNode sublistHead = prev.next;
        prev.next = sublistPrev;
        sublistHead.next = curr;

        return dummy.next;
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 *
 * Approach 1: ArrayList
 * - Time: O(n) → Traverse list once + rebuild once.
 * - Space: O(n) → Extra ArrayList + new nodes.
 *
 * Approach 2: Efficient In-Place
 * - Time: O(n) → One pass for locating `left` + one pass reversing sublist.
 * - Space: O(1) → Constant extra pointers.
 *
 * Comparison:
 * - Both O(n) time.
 * - ArrayList wastes O(n) space.
 * - In-place pointer reversal is optimal with O(1) space.
 */
