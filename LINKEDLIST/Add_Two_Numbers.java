/**
 * LeetCode 2: Add Two Numbers
 *
 * <p>
 * <b>Problem:</b><br>
 * You are given two non-empty linked lists representing two non-negative
 * integers.
 * The digits are stored in reverse order, and each node contains a single
 * digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * <p>
 * <b>Constraints:</b><br>
 * - Each linked list represents a number without leading zeros.<br>
 * - Length of lists: [1, 100].<br>
 * - Node values: [0, 9].<br>
 *
 * <p>
 * <b>Example:</b><br>
 * Input: l1 = [2,4,3], l2 = [5,6,4]<br>
 * Output: [7,0,8]<br>
 * Explanation: 342 + 465 = 807
 */
class Solution {

    /**
     * -------------------------------------------------
     * Efficient Approach: Digit-by-Digit Simulation
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1:</b> Initialize a dummy node and a pointer `curr`.
     *           - This will simplify construction of the result list.<br>
     *           - Also maintain an integer `carry` initialized to 0.<br>
     *           <br>
     *
     *           <b>Step 2:</b> Traverse both linked lists until both are null.
     *           - At each step, extract values: x = (l1 != null ? l1.val : 0), y =
     *           (l2 != null ? l2.val : 0).<br>
     *           - Compute sum = x + y + carry.<br>
     *           - New digit = sum % 10.<br>
     *           - Update carry = sum / 10.<br>
     *           - Append new node with digit to the result list.<br>
     *
     *           <b>Step 3:</b> If carry > 0 after traversal, add a new node with
     *           carry value.<br>
     *
     *           <b>Why Efficient?</b><br>
     *           - Works in a single pass through both lists.<br>
     *           - Handles lists of different lengths gracefully (treats missing
     *           digits as 0).<br>
     *           - Avoids converting lists to numbers (which could cause
     *           overflow).<br>
     *
     * @param l1 head of first linked list
     * @param l2 head of second linked list
     * @return head of new linked list representing the sum
     *
     *         <p>
     *         <b>Time Complexity:</b> O(max(m, n)) → Traverse both lists once.<br>
     *         <b>Space Complexity:</b> O(max(m, n)) → For result list (output only,
     *         no extra DS).<br>
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;

            int sum = x + y + carry;
            carry = sum / 10;

            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }

        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return dummy.next;
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 *
 * Efficient Approach:
 * - Time Complexity: O(max(m, n))
 * → Traverse both lists once, where m and n are their lengths.
 *
 * - Space Complexity: O(max(m, n))
 * → Result linked list contains at most max(m, n) + 1 nodes (for final carry).
 * → No extra auxiliary space used.
 */
