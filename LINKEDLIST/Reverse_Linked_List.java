/**
 * LeetCode 206: Reverse Linked List
 *
 * <p>
 * <b>Problem:</b><br>
 * Given the head of a singly linked list, reverse the list and return the new
 * head.
 *
 * <p>
 * <b>Constraints:</b><br>
 * - Length of list: [0, 5000].<br>
 * - Node values: [-5000, 5000].<br>
 *
 * <p>
 * <b>Example:</b><br>
 * Input: head = [1,2,3,4,5]<br>
 * Output: [5,4,3,2,1]<br>
 * <br>
 *
 * Input: head = []<br>
 * Output: []<br>
 */
class Solution {

    /**
     * -------------------------------------------------
     * Approach 1: Using ArrayList (Extra Space)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1:</b> Traverse the list and collect all values into an
     *           ArrayList.
     *           - Example: [1,2,3,4,5] → [1,2,3,4,5].<br>
     *
     *           <b>Step 2:</b> Build a new linked list in reverse order.
     *           - Iterate ArrayList from last to first.<br>
     *           - Example: [1,2,3,4,5] → new list [5,4,3,2,1].<br>
     *
     *           <b>Step 3:</b> Return the new head.
     *           - If input list is empty, return null.<br>
     *
     * @param head head of original list
     * @return head of reversed list
     */
    public ListNode reverseListArrayList(ListNode head) {
        java.util.ArrayList<Integer> values = new java.util.ArrayList<>();
        ListNode curr = head;

        while (curr != null) {
            values.add(curr.val);
            curr = curr.next;
        }

        ListNode dummy = new ListNode(0);
        curr = dummy;

        for (int i = values.size() - 1; i >= 0; i--) {
            curr.next = new ListNode(values.get(i));
            curr = curr.next;
        }

        return dummy.next;
    }

    /**
     * -------------------------------------------------
     * Approach 2: Three-Pointer Efficient In-Place
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1:</b> Initialize three pointers:
     *           - prev = null (to become new tail).
     *           - curr = head (to traverse the list).
     *           - next = null (to temporarily hold next node).<br>
     *           <br>
     *
     *           <b>Step 2:</b> Traverse the list and reverse pointers one by one.
     *           - Save curr.next into next.<br>
     *           - Redirect curr.next to prev.<br>
     *           - Move prev to curr.<br>
     *           - Move curr to next.<br>
     *           - Example iteration: [1 → 2 → 3]
     *           - After first iteration: prev=1 → null, curr=2.<br>
     *           - After second iteration: prev=2 → 1 → null, curr=3.<br>
     *
     *           <b>Step 3:</b> When curr reaches null, prev points to the new head.
     *           - Return prev as the new head of reversed list.<br>
     *
     * @param head head of original list
     * @return head of reversed list
     */
    public ListNode reverseListThreePointers(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextNode = curr.next; // save next
            curr.next = prev; // reverse link
            prev = curr; // move prev forward
            curr = nextNode; // move curr forward
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
 * - Time: O(n) → Traverse list once to collect, once to rebuild.
 * - Space: O(n) → Extra ArrayList + new list nodes.
 *
 * Approach 2: Three Pointers
 * - Time: O(n) → Traverse list once.
 * - Space: O(1) → In-place reversal using only pointers.
 *
 * Comparison:
 * - Both O(n) time.
 * - ArrayList wastes O(n) extra space.
 * - Three-pointer method is optimal with O(1) space.
 */
