/**
 * LeetCode 445: Add Two Numbers II
 *
 * <p>
 * <b>Problem:</b><br>
 * You are given two non-empty linked lists representing two non-negative
 * integers.
 * The most significant digit comes first, and each node contains a single
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
 * Input: l1 = [7,2,4,3], l2 = [5,6,4]<br>
 * Output: [7,8,0,7]<br>
 * Explanation: 7243 + 564 = 7807
 */
class Solution {

    /**
     * -------------------------------------------------
     * Approach 1: Using Stacks (or ArrayLists)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1:</b> Push all values of l1 and l2 into stacks.
     *           - This allows us to process digits from least significant to most
     *           significant.
     *           - Example: l1=[7,2,4,3] → stack1=[7,2,4,3].<br>
     *
     *           <b>Step 2:</b> Pop values from both stacks and add with carry.
     *           - sum = x + y + carry.<br>
     *           - digit = sum % 10, carry = sum / 10.<br>
     *           - Insert digit at the front of result (or build in reverse).<br>
     *
     *           <b>Step 3:</b> If carry > 0 after processing, prepend new node.
     *
     * @param l1 head of first list
     * @param l2 head of second list
     * @return head of new list representing the sum
     */
    public ListNode addTwoNumbersStack(ListNode l1, ListNode l2) {
        java.util.Stack<Integer> s1 = new java.util.Stack<>();
        java.util.Stack<Integer> s2 = new java.util.Stack<>();

        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        ListNode head = null;
        int carry = 0;

        while (!s1.isEmpty() || !s2.isEmpty() || carry > 0) {
            int x = (!s1.isEmpty()) ? s1.pop() : 0;
            int y = (!s2.isEmpty()) ? s2.pop() : 0;

            int sum = x + y + carry;
            carry = sum / 10;

            ListNode node = new ListNode(sum % 10);
            node.next = head; // prepend
            head = node;
        }

        return head;
    }

    /**
     * -------------------------------------------------
     * Approach 2: Reverse + Add + Reverse Back
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1:</b> Reverse both lists.
     *           - This makes digits stored in reverse order, like LeetCode 2.<br>
     *           - Example: l1=[7,2,4,3] → [3,4,2,7].<br>
     *
     *           <b>Step 2:</b> Perform normal digit-by-digit addition with carry
     *           (like LeetCode 2).
     *           - Traverse both lists simultaneously.
     *           - Compute digit = (x+y+carry)%10, update carry.
     *           - Append nodes to result.
     *
     *           <b>Step 3:</b> Reverse result list back to forward order.
     *           - Example: [7,0,8,7] reversed to [7,8,0,7].<br>
     *
     * @param l1 head of first list
     * @param l2 head of second list
     * @return head of new list representing the sum
     */
    public ListNode addTwoNumbersEfficient(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry > 0) {
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

        return reverse(dummy.next);
    }

    // Helper method to reverse a linked list
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
 * Approach 1: Using Stacks
 * - Time: O(m + n) → Traverse both lists and process stacks.
 * - Space: O(m + n) → Stacks store all digits.
 *
 * Approach 2: Reverse + Add + Reverse
 * - Time: O(m + n) → Reverse lists, add digits, reverse result.
 * - Space: O(1) → Only pointers used (ignoring output list).
 *
 * Comparison:
 * - Both are O(m + n) in time.
 * - Approach 1 uses extra space O(m + n).
 * - Approach 2 is optimal in space O(1).
 */
