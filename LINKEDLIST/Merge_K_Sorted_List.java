/**
 * LeetCode 23: Merge k Sorted Lists
 *
 * Problem:
 * You are given an array of k linked-lists, each list is sorted in ascending
 * order.
 * Merge all the linked lists into one sorted linked list and return it.
 *
 * Example:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 *
 * -------------------------------------------------
 * Approach: Divide and Conquer
 * -------------------------------------------------
 * Step 1: Treat the array of k lists like merge sort.
 * - Repeatedly merge lists in pairs until only one list remains.
 * Step 2: At each level, we merge two lists using the standard "merge two
 * sorted lists" approach.
 * Step 3: Continue dividing and merging until the final single sorted list is
 * formed.
 *
 * Why Efficient?
 * - Each merge operation handles fewer nodes per step.
 * - Reduces complexity compared to simple O(N log N) sorting or heap-based
 * approach.
 *
 * Time Complexity: O(N log k), where N = total number of nodes, k = number of
 * lists
 * Space Complexity: O(1), ignoring recursion overhead
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
     * Main function: Merge k sorted linked lists using divide & conquer.
     *
     * @param lists Array of k sorted linked list heads
     * @return Head of the merged sorted linked list
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        return mergeLists(lists, 0, lists.length - 1);
    }

    /**
     * Recursively divides the lists array and merges them.
     *
     * @param lists Array of k sorted linked list heads
     * @param left  Starting index of current subarray
     * @param right Ending index of current subarray
     * @return Head of the merged list for this subarray
     */
    private ListNode mergeLists(ListNode[] lists, int left, int right) {
        if (left == right)
            return lists[left];
        int mid = left + (right - left) / 2;
        ListNode l1 = mergeLists(lists, left, mid);
        ListNode l2 = mergeLists(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }

    /**
     * Helper function: Merges two sorted linked lists.
     *
     * @param l1 Head of first sorted linked list
     * @param l2 Head of second sorted linked list
     * @return Head of merged sorted linked list
     */
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        if (l1 != null)
            current.next = l1;
        else
            current.next = l2;

        return dummy.next;
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 * Divide & Conquer Approach:
 * - Time: O(N log k), where N = total number of nodes, k = number of lists
 * (Each node is processed log k times across recursive merges)
 * - Space: O(1) extra (ignoring recursion stack)
 *
 * Comparison:
 * - Much better than simple O(N log N) sorting approach.
 * - More efficient than heap-based O(N log k) in practice, due to fewer heap
 * operations.
 */
