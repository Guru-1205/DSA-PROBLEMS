/**
 * LeetCode 445: Add Two Numbers II
 *
 * Problem Description:
 * ---------------------
 * You are given two non-empty linked lists representing two non-negative
 * integers.
 * - The most significant digit comes first.
 * - Each node contains a single digit.
 * - Add the two numbers and return the sum as a linked list.
 *
 * Constraints:
 * - Each linked list represents a number without leading zeros.
 * - Length of lists: [1, 100].
 * - Node values: [0, 9].
 *
 * Example:
 * Input: l1 = [7,2,4,3], l2 = [5,6,4]
 * Output: [7,8,0,7]
 * Explanation: 7243 + 564 = 7807
 */
public class Add_Two_Numbers_II {

    /**
     * Approach 1: Using Stacks
     * -------------------------
     * Idea:
     * - Since digits are given in forward order, we need to process from the end.
     * - Stacks help us access digits from least significant to most significant.
     *
     * Steps:
     * 1. Push all digits of l1 into stack s1, and all digits of l2 into stack s2.
     * Example: l1 = [7,2,4,3] → s1 = [7,2,4,3]
     * l2 = [5,6,4] → s2 = [5,6,4]
     *
     * 2. Pop values from stacks and add them with carry:
     * - sum = x + y + carry
     * - digit = sum % 10
     * - carry = sum / 10
     * - Create a new node for digit and prepend it to the result.
     *
     * 3. If carry > 0 after processing, add a new node at the front.
     *
     * Example Trace:
     * s1=[7,2,4,3], s2=[5,6,4], carry=0
     * → Pop 3+4=7 → node=7
     * → Pop 4+6=10 → digit=0, carry=1 → node=0, list=[0,7]
     * → Pop 2+5+1=8 → node=8, list=[8,0,7]
     * → Pop 7+0=7 → node=7, list=[7,8,0,7]
     */
    public ListNode addTwoNumbersStack(ListNode l1, ListNode l2) {
        java.util.Stack<Integer> s1 = new java.util.Stack<>();
        java.util.Stack<Integer> s2 = new java.util.Stack<>();

        // Step 1: Push values into stacks
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        // Step 2: Build result backwards
        ListNode head = null;
        int carry = 0;

        while (!s1.isEmpty() || !s2.isEmpty() || carry > 0) {
            int x = (!s1.isEmpty()) ? s1.pop() : 0;
            int y = (!s2.isEmpty()) ? s2.pop() : 0;

            int sum = x + y + carry;
            carry = sum / 10;

            // Prepend new node
            ListNode node = new ListNode(sum % 10);
            node.next = head;
            head = node;
        }

        return head;
    }

    /**
     * Approach 2: Reverse + Add + Reverse Back
     * -----------------------------------------
     * Idea:
     * - If we reverse the input lists, the digits become least-significant-first,
     * which reduces the problem to LeetCode 2 ("Add Two Numbers").
     * - After addition, reverse the result back.
     *
     * Steps:
     * 1. Reverse both input lists.
     * Example: l1 = [7,2,4,3] → [3,4,2,7]
     * l2 = [5,6,4] → [4,6,5]
     *
     * 2. Perform digit-by-digit addition:
     * - sum = x + y + carry
     * - digit = sum % 10
     * - carry = sum / 10
     * - Append new nodes at the end of result.
     *
     * 3. If carry > 0 at the end, add a new node.
     *
     * 4. Reverse the result back to forward order.
     *
     * Example Trace:
     * l1=[3,4,2,7], l2=[4,6,5], carry=0
     * → 3+4=7 → [7]
     * → 4+6=10 → digit=0, carry=1 → [7,0]
     * → 2+5+1=8 → [7,0,8]
     * → 7+0=7 → [7,0,8,7]
     * Reverse → [7,8,0,7]
     */
    public ListNode addTwoNumbersEfficient(ListNode l1, ListNode l2) {
        // Step 1: Reverse both lists
        l1 = reverse(l1);
        l2 = reverse(l2);

        // Step 2: Perform addition
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

        // Step 3: Reverse result back
        return reverse(dummy.next);
    }

    // Helper: Reverse a linked list
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
 * Time and Space Complexity Analysis:
 * ------------------------------------
 * Approach 1: Using Stacks
 * - Time: O(m + n) → Traverse both lists + process stacks.
 * - Space: O(m + n) → Stacks store all digits.
 *
 * Approach 2: Reverse + Add + Reverse
 * - Time: O(m + n) → Reverse lists + add digits + reverse result.
 * - Space: O(1) → Only uses pointers (ignoring result list).
 *
 * Comparison:
 * - Both run in linear time O(m+n).
 * - Stack approach is simpler but uses O(m+n) extra memory.
 * - Reverse approach is more space-efficient (O(1)).
 */
