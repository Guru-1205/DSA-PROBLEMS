/**
 * LeetCode 2181: Merge Nodes in Between Zeros
 *
 * Problem:
 * You are given the head of a linked list that contains integers. The list
 * starts and ends with 0,
 * and there are no consecutive zeroes. Each non-zero segment is between two
 * zeroes.
 * For each such segment, merge the nodes into a single node whose value is the
 * sum of that segment.
 * Return the head of the modified list (excluding the leading and trailing
 * zeros).
 *
 * Example:
 * Input: head = [0,3,1,0,4,5,2,0]
 * Output: [4,11]
 *
 * -------------------------------------------------
 * Efficient Approach (One Pass Summation)
 * -------------------------------------------------
 * Step 1: Initialize pointers
 * - current = head.next (skip the first zero)
 * - dummy node for building result
 * - tail pointer for appending merged nodes
 *
 * Step 2: Traverse the list
 * - Maintain a running sum.
 * - Add node values until a zero is encountered.
 *
 * Step 3: On encountering zero
 * - Create a new node with the accumulated sum.
 * - Append it to the result list.
 * - Reset sum to 0 for the next segment.
 *
 * Step 4: Continue until the end
 * - The final zero will trigger the last merged node.
 * - Return dummy.next as the new head.
 *
 * Intuition:
 * - Each segment between zeros is independent.
 * - We just need to accumulate values until we hit a zero and then flush the
 * sum.
 *
 * Time Complexity: O(n), each node visited once
 * Space Complexity: O(1), only pointers used
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

class Solution {

    /**
     * Efficient approach to merge nodes between zeros.
     *
     * @param head The head of the linked list starting with 0 and ending with 0
     * @return The head of the modified list after merging
     */
    public ListNode mergeNodes(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        ListNode current = head.next; // skip the first zero
        int sum = 0;

        while (current != null) {
            if (current.val != 0) {
                sum += current.val;
            } else {
                // hit a zero → flush the sum into a new node
                tail.next = new ListNode(sum);
                tail = tail.next;
                sum = 0; // reset for next segment
            }
            current = current.next;
        }

        return dummy.next;
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 * Efficient Approach:
 * - Time: O(n), where n = number of nodes
 * Each node is visited exactly once.
 * - Space: O(1), no extra storage except pointers.
 *
 * Comparison:
 * - Optimal since it processes in a single pass without auxiliary data
 * structures.
 */
