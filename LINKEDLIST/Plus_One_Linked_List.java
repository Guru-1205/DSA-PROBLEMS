/**
 * LeetCode 369: Plus One Linked List
 *
 * <p>
 * <b>Problem:</b><br>
 * Given the head of a non-empty singly linked list representing a non-negative
 * integer,
 * increment the number by one and return the resulting linked list.
 *
 * <p>
 * <b>Constraints:</b><br>
 * - The number contains no leading zeros except the number 0 itself.<br>
 * - Length of list: [1, 100].<br>
 * - Node values: [0, 9].<br>
 *
 * <p>
 * <b>Example:</b><br>
 * Input: head = [1,2,3]<br>
 * Output: [1,2,4]<br>
 * Explanation: 123 + 1 = 124<br>
 * <br>
 *
 * Input: head = [9,9,9]<br>
 * Output: [1,0,0,0]<br>
 * Explanation: 999 + 1 = 1000
 */
class Solution {

    /**
     * -------------------------------------------------
     * Approach 1: Using ArrayList (or Stack)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1:</b> Collect digits into ArrayList.
     *           - Traverse linked list and push values into ArrayList.<br>
     *           - Example: [1,2,3] → list = [1,2,3].<br>
     *
     *           <b>Step 2:</b> Simulate addition from last index.
     *           - Start from the last element (least significant digit).<br>
     *           - Add 1 to it, handle carry.<br>
     *           - Propagate carry backwards as needed.<br>
     *           - Example: [1,2,9] → [1,3,0].<br>
     *           - Example: [9,9,9] → [1,0,0,0].<br>
     *
     *           <b>Step 3:</b> Build new linked list from updated ArrayList.
     *
     * @param head original linked list head
     * @return new head after adding one
     */
    public ListNode plusOneArrayList(ListNode head) {
        java.util.ArrayList<Integer> digits = new java.util.ArrayList<>();
        ListNode curr = head;

        while (curr != null) {
            digits.add(curr.val);
            curr = curr.next;
        }

        int carry = 1; // we are adding one
        for (int i = digits.size() - 1; i >= 0; i--) {
            int sum = digits.get(i) + carry;
            digits.set(i, sum % 10);
            carry = sum / 10;
        }

        ListNode dummy = new ListNode(0);
        curr = dummy;

        if (carry > 0) {
            curr.next = new ListNode(carry);
            curr = curr.next;
        }

        for (int val : digits) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }

        return dummy.next;
    }

    /**
     * -------------------------------------------------
     * Approach 2: Efficient Reverse + Add + Reverse Back
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1:</b> Reverse the linked list.
     *           - This makes least significant digit come first.<br>
     *           - Example: [1,2,3] → [3,2,1].<br>
     *
     *           <b>Step 2:</b> Add one to the head.
     *           - Traverse list while carry exists.<br>
     *           - sum = node.val + carry → node.val = sum % 10, carry = sum /
     *           10.<br>
     *           - If carry remains after processing last node, append new node.<br>
     *           - Example: [9,9,9] reversed → [9,9,9], after add → [0,0,0,1].<br>
     *
     *           <b>Step 3:</b> Reverse the list back to original order.
     *           - Example: [0,0,0,1] → [1,0,0,0].<br>
     *
     * @param head original linked list head
     * @return head of incremented linked list
     */
    public ListNode plusOneEfficient(ListNode head) {
        head = reverse(head);

        ListNode curr = head;
        int carry = 1; // we are adding one

        while (curr != null) {
            int sum = curr.val + carry;
            curr.val = sum % 10;
            carry = sum / 10;

            if (carry == 0)
                break;

            if (curr.next == null && carry > 0) {
                curr.next = new ListNode(carry);
                carry = 0;
            }

            curr = curr.next;
        }

        return reverse(head);
    }

    // Helper method to reverse linked list
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

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
 * - Time: O(n) → Traverse once to collect digits, once to rebuild list.
 * - Space: O(n) → ArrayList stores all digits.
 *
 * Approach 2: Efficient Reverse + Add + Reverse
 * - Time: O(n) → Reverse list, add digits, reverse back.
 * - Space: O(1) → Only pointers used (ignoring output list).
 *
 * Comparison:
 * - Both O(n) time.
 * - ArrayList wastes extra O(n) space.
 * - Reverse approach is optimal with O(1) space.
 */
