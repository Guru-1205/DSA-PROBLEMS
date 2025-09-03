import java.util.LinkedHashSet;
import java.util.Set;

/**
 * LeetCode 83: Remove Duplicates from Sorted List
 *
 * <p>
 * <b>Problem:</b><br>
 * Given the head of a sorted linked list, delete all duplicates such that each
 * element appears only once.
 *
 * <p>
 * <b>Example:</b><br>
 * Input: head = [1,1,2,3,3]<br>
 * Output: [1,2,3]
 */
class Solution {

    /**
     * -------------------------------------------------
     * Approach 1: HashSet (Extra Space)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1: Traverse and Collect Unique Values</b><br>
     *           - Use a HashSet to store all unique values from the list.<br>
     *           - This ensures duplicates are ignored.<br>
     *           - Example: [1,1,2,3,3] → Set = {1,2,3}.<br>
     *           <br>
     *
     *           <b>Step 2: Build a New Linked List</b><br>
     *           - Iterate over the stored values in order of appearance.<br>
     *           - Append them into a new linked list.<br>
     *           - Example: {1,2,3} → 1 -> 2 -> 3.<br>
     *           <br>
     *
     *           <b>Why it works?</b><br>
     *           - A HashSet ensures no duplicates.<br>
     *           - Two-pass approach: collect, then rebuild.<br>
     *           <br>
     *
     *           <b>Drawback:</b><br>
     *           - Requires O(n) extra space for storing values.<br>
     *
     * @param head The head of the sorted linked list
     * @return New head with duplicates removed
     */
    public ListNode deleteDuplicatesHashSet(ListNode head) {
        if (head == null)
            return null;

        Set<Integer> set = new LinkedHashSet<>(); // maintains insertion order
        ListNode curr = head;

        // Step 1: Collect unique values
        while (curr != null) {
            set.add(curr.val);
            curr = curr.next;
        }

        // Step 2: Build new list
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        for (int val : set) {
            temp.next = new ListNode(val);
            temp = temp.next;
        }

        return dummy.next;
    }

    /**
     * -------------------------------------------------
     * Approach 2: Efficient In-Place Deletion
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1: Traverse the List</b><br>
     *           - Start from head and check consecutive nodes.<br>
     *           - Since the list is sorted, duplicates must appear next to each
     *           other.<br>
     *           <br>
     *
     *           <b>Step 2: Remove Consecutive Duplicates</b><br>
     *           - If `current.val == current.next.val`, skip the duplicate
     *           node.<br>
     *           - Otherwise, move `current` forward normally.<br>
     *           <br>
     *
     *           <b>Step 3: Continue Until End</b><br>
     *           - Repeat until all nodes are processed.<br>
     *           - Example: [1,1,2,3,3]<br>
     *           - Compare 1 and 1 → skip duplicate.<br>
     *           - Compare 1 and 2 → keep.<br>
     *           - Compare 2 and 3 → keep.<br>
     *           - Compare 3 and 3 → skip duplicate.<br>
     *           - Result = [1,2,3].<br>
     *           <br>
     *
     * @param head The head of the sorted linked list
     * @return Modified head with duplicates removed
     */
    public ListNode deleteDuplicatesEfficient(ListNode head) {
        ListNode current = head;

        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next; // skip duplicate
            } else {
                current = current.next; // move to next unique
            }
        }

        return head;
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 *
 * <b>Approach 1: HashSet</b><br>
 * - Time Complexity: O(n) → Traverse once to collect, once to rebuild.<br>
 * - Space Complexity: O(n) → Extra HashSet + new linked list.<br>
 * <br>
 *
 * <b>Approach 2: Efficient In-Place</b><br>
 * - Time Complexity: O(n) → Traverse each node once.<br>
 * - Space Complexity: O(1) → No extra storage, modifies list in-place.<br>
 * <br>
 *
 * ✅ Recommended: Approach 2, as it is both time and space optimal.
 */
