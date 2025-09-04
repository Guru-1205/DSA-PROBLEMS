/**
 * LeetCode 82: Remove Duplicates from Sorted List II
 *
 * <p>
 * <b>Problem:</b><br>
 * Given the head of a sorted linked list, delete all nodes that have duplicate
 * numbers,
 * leaving only distinct numbers from the original list.
 *
 * <p>
 * <b>Constraints:</b><br>
 * - The number of nodes in the list is in the range [0, 300].<br>
 * - Node values are in the range [-100, 100].<br>
 * - The list is guaranteed to be sorted in non-decreasing order.<br>
 *
 * <p>
 * <b>Example 1:</b><br>
 * Input: head = [1,2,3,3,4,4,5]<br>
 * Output: [1,2,5]<br>
 *
 * <p>
 * <b>Example 2:</b><br>
 * Input: head = [1,1,1,2,3]<br>
 * Output: [2,3]<br>
 *
 * -------------------------------------------------
 * Approach 1: HashMap (Two-Pass with Extra Space)
 * -------------------------------------------------
 *
 * Step 1: Traverse the list and count frequency of each value.
 * - Use a HashMap<Integer, Integer> to record counts.
 * - Example: [1,2,3,3,4,4,5] → {1:1, 2:1, 3:2, 4:2, 5:1}.
 *
 * Step 2: Traverse the list again and build a new list.
 * - Only include values where frequency == 1.
 * - Example: {1,2,5} remain.
 *
 * Why it works?
 * - First pass detects duplicates.
 * - Second pass reconstructs the list without them.
 *
 * Drawback:
 * - Requires O(n) extra space.
 *
 * -------------------------------------------------
 * Approach 2: Efficient In-Place Deletion (Dummy Node)
 * -------------------------------------------------
 *
 * Step 1: Create a dummy node before the head.
 * - Handles edge cases where head itself is a duplicate.
 *
 * Step 2: Use two pointers:
 * - prev → last confirmed unique node.
 * - head → current traversal node.
 *
 * Step 3: Detect duplicate sequences.
 * - If head.val == head.next.val, skip all nodes with that value.
 * - Link prev.next to node after duplicates.
 *
 * Step 4: Otherwise, move prev forward normally.
 *
 * Step 5: Continue until traversal ends, return dummy.next.
 *
 * Why it works?
 * - Since list is sorted, duplicates appear consecutively.
 * - We can efficiently skip them in one pass.
 *
 * Example Walkthrough:
 * Input: [1,2,3,3,4,4,5]
 * Duplicate groups: [3,3], [4,4]
 * Result: [1,2,5]
 */
public class Remove_Duplicates_From_Sorted_List_II {

    // Approach 1: HashMap (Two-Pass)
    public ListNode deleteDuplicatesHashSet(ListNode head) {
        java.util.Map<Integer, Integer> countMap = new java.util.HashMap<>();

        // Step 1: Count frequencies
        ListNode temp = head;
        while (temp != null) {
            countMap.put(temp.val, countMap.getOrDefault(temp.val, 0) + 1);
            temp = temp.next;
        }

        // Step 2: Build new list
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        temp = head;

        while (temp != null) {
            if (countMap.get(temp.val) == 1) {
                curr.next = new ListNode(temp.val);
                curr = curr.next;
            }
            temp = temp.next;
        }

        return dummy.next;
    }

    // Approach 2: Efficient In-Place
    public ListNode deleteDuplicatesEfficient(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        while (head != null) {
            // Skip duplicate block
            if (head.next != null && head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                prev.next = head.next; // remove duplicate block
            } else {
                prev = prev.next; // unique node
            }
            head = head.next;
        }

        return dummy.next;
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 *
 * Approach 1: HashMap
 * - Time: O(n) → Traverse once to count, once to rebuild.
 * - Space: O(n) → Extra space for HashMap.
 *
 * Approach 2: Efficient In-Place
 * - Time: O(n) → Single traversal.
 * - Space: O(1) → Only pointers used.
 *
 * ✅ Recommended: Approach 2 (Efficient) → modifies list in-place with O(1)
 * space.
 */
