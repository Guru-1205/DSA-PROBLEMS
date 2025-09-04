/**
 * LeetCode 23: Merge k Sorted Lists
 *
 * Problem Description:
 * You are given an array of k linked lists, where each linked list is sorted
 * in ascending order. The task is to merge all these k sorted linked lists
 * into a single sorted linked list and return its head.
 *
 * Example:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 *
 * Constraints:
 * - The number of linked lists (k) can be large.
 * - Each list is already sorted individually.
 * - Goal: Efficiently merge them without flattening into an array.
 *
 * -------------------------------------------------
 * Approach: Divide and Conquer
 * -------------------------------------------------
 * Step 1: Handle Edge Case
 * - If the array of lists is null or empty → return null immediately.
 *
 * Step 2: Divide Lists Recursively
 * - Use a recursive helper function `mergeLists(lists, left, right)`.
 * - Base Case: If left == right → only one list, return it directly.
 * - Recursive Case:
 * → Find the middle index: mid = (left + right) / 2.
 * → Recursively merge the left half: mergeLists(lists, left, mid).
 * → Recursively merge the right half: mergeLists(lists, mid+1, right).
 * → Merge the two halves using `mergeTwoLists`.
 *
 * Step 3: Merging Two Sorted Lists (mergeTwoLists)
 * - Create a dummy node to simplify list construction.
 * - Maintain a pointer `current` starting at dummy.
 * - Compare the heads of both lists:
 * → If l1.val <= l2.val, attach l1 to current.next, move l1 forward.
 * → Else attach l2 to current.next, move l2 forward.
 * → Move current forward.
 * - Continue until one of the lists is fully traversed.
 *
 * Step 4: Attach Remaining Nodes
 * - After the loop, if one list still has nodes left,
 * attach them directly to current.next.
 *
 * Step 5: Return Result
 * - Return dummy.next, which points to the merged sorted list.
 *
 * Intuition:
 * - Works like merge sort: divide into halves, solve recursively,
 * and merge the results.
 * - Reduces k lists → log k levels of merging.
 *
 * Example Walkthrough:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * - Split into [[1,4,5]] and [[1,3,4],[2,6]]
 * - Merge right half: [1,3,4] + [2,6] → [1,2,3,4,6]
 * - Merge left half with result: [1,4,5] + [1,2,3,4,6]
 * - Final merged list = [1,1,2,3,4,4,5,6]
 *
 * -------------------------------------------------
 * Time and Space Complexity
 * -------------------------------------------------
 * Time Complexity: O(N log k)
 * - N = total number of nodes across all lists
 * - log k merge levels (like merge sort)
 * - Each node processed once per merge level
 *
 * Space Complexity: O(1) extra (ignoring recursion stack)
 * - Only uses pointers for merging
 * - Recursion adds O(log k) stack frames
 *
 * Comparison:
 * - More efficient than flatten + sort (O(N log N))
 * - Comparable to heap-based O(N log k), but often faster in practice
 * because merges involve sequential pointer operations.
 */

public class Merge_K_Sorted_List {

    /**
     * Merges k sorted linked lists using divide and conquer.
     *
     * @param lists Array of sorted linked list heads
     * @return Head of the fully merged sorted list
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeLists(lists, 0, lists.length - 1);
    }

    /**
     * Recursively merges lists within a given range using divide and conquer.
     *
     * @param lists Array of list heads
     * @param left  Left index of the range
     * @param right Right index of the range
     * @return Merged sorted list for this range
     */
    private ListNode mergeLists(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        ListNode l1 = mergeLists(lists, left, mid);
        ListNode l2 = mergeLists(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }

    /**
     * Merges two sorted linked lists into one.
     *
     * @param l1 Head of first sorted list
     * @param l2 Head of second sorted list
     * @return Head of the merged sorted list
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

        if (l1 != null) {
            current.next = l1;
        } else {
            current.next = l2;
        }

        return dummy.next;
    }
}
