
/**
 * LeetCode 2: Add Two Numbers
 *
 * <p>
 * <b>Problem Description:</b><br>
 * You are given two non-empty linked lists representing two non-negative
 * integers.
 * The digits are stored in reverse order, and each node contains a single
 * digit.
 * Add the two numbers and return the sum as a linked list in reverse order.
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
public class Add_Two_Numbers {

    /**
     * -------------------------------------------------
     * Efficient Approach: Digit-by-Digit Simulation
     * -------------------------------------------------
     *
     * <p>
     * <b>Step 1:</b> Initialize helper variables.<br>
     * - Create a dummy node to simplify result list construction.<br>
     * - Maintain a pointer `curr` initially at dummy.<br>
     * - Maintain an integer `carry` initialized to 0 (to handle digit sum
     * overflow).<br>
     *
     * <p>
     * <b>Step 2:</b> Traverse both linked lists until both are fully processed.<br>
     * - At each step:
     * - Extract digit from l1 (x = l1.val if not null, else 0).<br>
     * - Extract digit from l2 (y = l2.val if not null, else 0).<br>
     * - Compute sum = x + y + carry.<br>
     * - Compute new digit = sum % 10.<br>
     * - Update carry = sum / 10.<br>
     * - Create a new node with digit and link it to result list.<br>
     * - Move l1 and l2 pointers forward if they are not null.<br>
     *
     * <p>
     * <b>Step 3:</b> Handle leftover carry after traversal.<br>
     * - If carry > 0, create a new node with value carry and link it to the
     * list.<br>
     *
     * <p>
     * <b>Step 4:</b> Return the result list.<br>
     * - Since dummy node was a placeholder, return dummy.next as the head.<br>
     *
     * <p>
     * <b>Why Efficient?</b><br>
     * - Processes both lists in a single traversal.<br>
     * - Handles lists of different lengths naturally by treating missing digits as
     * 0.<br>
     * - No integer overflow risk since we never convert the whole number.<br>
     *
     * @param l1 head of first linked list
     * @param l2 head of second linked list
     * @return head of new linked list representing the sum
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;

        // Step 2: Traverse both lists
        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;

            int sum = x + y + carry;
            carry = sum / 10;

            curr.next = new ListNode(sum % 10); // add new digit
            curr = curr.next;

            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }

        // Step 3: handle leftover carry
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
 * → The result list contains at most max(m, n) + 1 nodes (in case of final
 * carry).
 * → No extra data structures used apart from output.
 *
 * Comparison:
 * - This is the optimal approach since we cannot avoid building the output
 * list.
 */
