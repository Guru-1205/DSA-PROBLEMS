import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 2181: Merge Nodes in Between Zeros
 *
 * Problem Description:
 * You are given the head of a linked list that contains integers.
 * - The list always starts and ends with 0.
 * - There are no consecutive zeroes.
 * - Each non-zero segment lies between two zeroes.
 *
 * For each such segment, merge the nodes into a single node whose value is
 * the sum of that segment. Return the head of the modified linked list
 * (excluding the leading and trailing zeros).
 *
 * Example:
 * Input: head = [0,3,1,0,4,5,2,0]
 * Output: [4,11]
 *
 * Constraints:
 * - 2 <= number of nodes in list <= 10^5
 * - The first and last nodes are 0
 * - No two consecutive nodes are 0
 *
 * -------------------------------------------------
 * Approach 1: Using ArrayList (Two Pass Method)
 * -------------------------------------------------
 * Step 1: Initialize an ArrayList<Integer> sums to store segment sums.
 * Step 2: Traverse the linked list from head.next:
 * - Maintain a running sum variable.
 * - Add node values until encountering a zero.
 * - When zero is reached → add the accumulated sum into sums list and reset sum
 * to 0.
 * Step 3: Once traversal ends, create a new linked list using the values stored
 * in sums.
 * Step 4: Return the head of this new constructed linked list.
 *
 * Intuition:
 * - Break the list into segments between zeros.
 * - Store segment sums in an ArrayList for simplicity.
 * - Rebuild the final list from the stored sums.
 *
 * -------------------------------------------------
 * Approach 2: Efficient One-Pass Summation (Optimal)
 * -------------------------------------------------
 * Step 1: Initialize helper nodes:
 * - Create a dummy node to simplify result list building.
 * - Maintain a tail pointer for appending nodes.
 * Step 2: Start traversal from head.next (skip the first zero).
 * Step 3: Maintain a running sum:
 * - Add values while current.val != 0.
 * - On encountering a zero:
 * → Create a new node with accumulated sum.
 * → Append it to tail.next and move tail forward.
 * → Reset sum = 0 for next segment.
 * Step 4: Continue until the last zero is processed.
 * Step 5: Return dummy.next (head of the result list).
 *
 * Intuition:
 * - No extra ArrayList required.
 * - Accumulate and flush sum whenever a zero is encountered.
 * - Processes in one pass, space-efficient.
 *
 * Example Walkthrough (Efficient):
 * Input: [0,3,1,0,4,5,2,0]
 * - Traverse: sum=3+1=4 → hit zero → append [4]
 * - Traverse: sum=4+5+2=11 → hit zero → append [11]
 * - Final Output: [4,11]
 *
 * -------------------------------------------------
 * Time and Space Complexity
 * -------------------------------------------------
 * Approach 1 (ArrayList):
 * - Time: O(n) → traverse once, rebuild list once.
 * - Space: O(m) → ArrayList to store m segment sums.
 *
 * Approach 2 (Efficient One Pass):
 * - Time: O(n) → each node visited once.
 * - Space: O(1) → only pointers + running sum used.
 *
 * Comparison:
 * - Approach 1 is simpler but uses extra space.
 * - Approach 2 is optimal: single pass, no extra storage.
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

public class Merge_Node_In_Between_Zeroes {

    /**
     * Approach 1: Merge nodes between zeros using ArrayList.
     *
     * @param head The head of the linked list starting with 0 and ending with 0
     * @return The head of the modified list after merging
     */
    public ListNode mergeNodesArrayList(ListNode head) {
        List<Integer> sums = new ArrayList<>();
        ListNode current = head.next; // skip first zero
        int sum = 0;

        while (current != null) {
            if (current.val != 0) {
                sum += current.val;
            } else {
                sums.add(sum);
                sum = 0;
            }
            current = current.next;
        }

        // Build new list from sums
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int val : sums) {
            tail.next = new ListNode(val);
            tail = tail.next;
        }

        return dummy.next;
    }

    /**
     * Approach 2: Efficient one-pass summation.
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
                tail.next = new ListNode(sum);
                tail = tail.next;
                sum = 0; // reset for next segment
            }
            current = current.next;
        }

        return dummy.next;
    }
}
