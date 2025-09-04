/**
 * LeetCode 369: Plus One Linked List
 *
 * Problem:
 * You are given the head of a non-empty singly linked list representing a
 * non-negative integer. Each node contains a single digit. The most significant
 * digit comes first. Increment the number by one and return the resulting
 * linked list.
 *
 * Constraints:
 * - The number contains no leading zeros except the number 0 itself.
 * - Length of list: [1, 100].
 * - Node values: [0, 9].
 *
 * Example:
 * Input: head = [1,2,3]
 * Output: [1,2,4]
 * Explanation: 123 + 1 = 124
 *
 * Input: head = [9,9,9]
 * Output: [1,0,0,0]
 * Explanation: 999 + 1 = 1000
 *
 * -------------------------------------------------
 * Approach 1: Using ArrayList (Two Pass Simulation)
 * -------------------------------------------------
 * Step 1: Traverse the linked list, store digits into an ArrayList.
 * Example: [1,2,3] → [1,2,3]
 *
 * Step 2: Simulate addition of 1 starting from the end of ArrayList.
 * - Add 1 to last digit, handle carry.
 * - Propagate carry backwards if needed.
 * Example: [1,2,9] → [1,3,0]
 * Example: [9,9,9] → [1,0,0,0]
 *
 * Step 3: Construct a new linked list from the updated digits.
 *
 * Intuition:
 * - Works exactly like adding one to an integer represented as an array.
 * - Simple but requires extra O(n) storage.
 *
 * -------------------------------------------------
 * Approach 2: Efficient Reverse + Add + Reverse Back (Optimal)
 * -------------------------------------------------
 * Step 1: Reverse the linked list so the least significant digit comes first.
 * Example: [1,2,3] → [3,2,1]
 *
 * Step 2: Add 1 to the head (least significant digit).
 * - Traverse while carry > 0.
 * - Update node.val = (val + carry) % 10
 * - Update carry = (val + carry) / 10
 * - If carry remains at the end, append a new node.
 * Example: [9,9,9] reversed → [9,9,9]
 * after addition → [0,0,0,1]
 *
 * Step 3: Reverse the list again to restore original digit order.
 * Example: [0,0,0,1] → [1,0,0,0]
 *
 * Intuition:
 * - Reversing lets us process from least significant digit first (like normal
 * addition).
 * - No extra list storage needed.
 *
 * -------------------------------------------------
 * Time and Space Complexity
 * -------------------------------------------------
 * Approach 1 (ArrayList):
 * - Time: O(n) → traverse + simulate addition + rebuild.
 * - Space: O(n) → ArrayList stores digits.
 *
 * Approach 2 (Efficient Reverse):
 * - Time: O(n) → reverse + add + reverse back.
 * - Space: O(1) → only pointers and carry used.
 *
 * Comparison:
 * - Both are O(n) time.
 * - ArrayList uses extra O(n) space.
 * - Reverse approach is optimal in space.
 */

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Plus_One_Linked_List {

    /**
     * Approach 1: Using ArrayList to simulate addition.
     */
    public ListNode plusOneArrayList(ListNode head) {
        java.util.ArrayList<Integer> digits = new java.util.ArrayList<>();
        ListNode curr = head;

        // Collect digits
        while (curr != null) {
            digits.add(curr.val);
            curr = curr.next;
        }

        // Simulate +1 from the end
        int carry = 1;
        for (int i = digits.size() - 1; i >= 0; i--) {
            int sum = digits.get(i) + carry;
            digits.set(i, sum % 10);
            carry = sum / 10;
        }

        // Build new list
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
     * Approach 2: Reverse → Add → Reverse back (Efficient).
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
