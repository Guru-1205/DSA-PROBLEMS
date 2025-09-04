import java.util.HashMap;
import java.util.Map;

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
 * <b>Example:</b><br>
 * Input: head = [1,2,3,3,4,4,5]<br>
 * Output: [1,2,5]
 */
public class Remove_Duplicates_From_Sorted_List_I {

    /**
     * -------------------------------------------------
     * Approach 1: HashSet (Two-Pass with Extra Space)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1: Identify Duplicate Values</b><br>
     *           - Traverse the linked list once.<br>
     *           - Use a HashMap (or HashSet with counts) to record occurrences of
     *           each value.<br>
     *           - Any value with count > 1 is considered a duplicate.<br>
     *           <br>
     *
     *           <b>Step 2: Build a New List Without Duplicates</b><br>
     *           - Traverse the list again.<br>
     *           - Include only values with frequency == 1 in the new list.<br>
     *           <br>
     *
     *           <b>Why this works?</b><br>
     *           - We separate the detection of duplicates from the construction of
     *           the result.<br>
     *
     *           <b>Drawback:</b><br>
     *           - Requires O(n) extra space for storing counts.<br>
     *
     * @param head The head of the linked list
     * @return The new head after removing duplicates
     */
    public ListNode deleteDuplicatesHashSet(ListNode head) {
        Map<Integer, Integer> countMap = new HashMap<>();

        // Step 1: Count occurrences
        ListNode temp = head;
        while (temp != null) {
            countMap.put(temp.val, countMap.getOrDefault(temp.val, 0) + 1);
            temp = temp.next;
        }

        // Step 2: Build new list with only unique values
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        temp = head;

        while (temp != null) {
            if (countMap.get(temp.val) == 1) {
                current.next = new ListNode(temp.val);
                current = current.next;
            }
            temp = temp.next;
        }

        return dummy.next;
    }

    /**
     * -------------------------------------------------
     * Approach 2: Efficient In-Place Deletion (Dummy Node)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1: Create a Dummy Node</b><br>
     *           - Use a dummy node before the head.<br>
     *           - This helps handle cases where the head itself is duplicated.<br>
     *           <br>
     *
     *           <b>Step 2: Traverse with Two Pointers</b><br>
     *           - Use `prev` (last confirmed unique node) and `current` (traversal
     *           pointer).<br>
     *           - When a duplicate sequence is detected (`current.val ==
     *           current.next.val`):<br>
     *           - Skip all nodes with this value.<br>
     *           - Link `prev.next` to the node after the duplicates.<br>
     *           - Otherwise, move `prev` forward normally.<br>
     *           <br>
     *
     *           <b>Step 3: Return Result</b><br>
     *           - Return dummy.next (new head).<br>
     *           <br>
     *
     *           <b>Why this works?</b><br>
     *           - Since the list is sorted, duplicates appear consecutively.<br>
     *           - We can efficiently skip all occurrences of duplicates in one
     *           pass.<br>
     *
     * @param head The head of the linked list
     * @return The head after removing all duplicates
     */
    public ListNode deleteDuplicatesEfficient(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        while (head != null) {
            // Skip duplicate sequence
            if (head.next != null && head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val) {
                    head = head.next; // Skip all duplicates
                }
                prev.next = head.next; // Remove entire duplicate block
            } else {
                prev = prev.next; // Confirmed unique, move forward
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
 * <b>Approach 1: HashSet (Two-Pass)</b><br>
 * - Time Complexity: O(n) → One traversal to count + one traversal to
 * rebuild.<br>
 * - Space Complexity: O(n) → Extra HashMap to store frequencies.<br>
 * <br>
 *
 * <b>Approach 2: Efficient In-Place (Dummy Node)</b><br>
 * - Time Complexity: O(n) → Single traversal of the list.<br>
 * - Space Complexity: O(1) → Only pointers used.<br>
 *
 * ✅ Recommended: Approach 2 (Efficient) since it modifies the list in-place.
 */
