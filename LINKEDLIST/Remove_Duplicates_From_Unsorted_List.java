import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1836: Remove Duplicates From an Unsorted Linked List
 *
 * <p>
 * <b>Problem:</b><br>
 * Given the head of an unsorted linked list, delete all nodes that have
 * duplicate numbers,
 * leaving only distinct numbers from the original list.
 *
 * <p>
 * <b>Example:</b><br>
 * Input: head = [1,2,3,2]<br>
 * Output: [1,3]
 */
class Solution {

    /**
     * -------------------------------------------------
     * Approach 1: HashMap (Two-Pass Rebuild)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1: Count Frequencies</b><br>
     *           - Traverse the linked list once.<br>
     *           - Use a HashMap<Integer, Integer> to store frequency of each
     *           value.<br>
     *           - Example: [1,2,3,2] → freq = {1=1, 2=2, 3=1}.<br>
     *           <br>
     *
     *           <b>Step 2: Build New List With Unique Values</b><br>
     *           - Traverse the list again.<br>
     *           - Only keep nodes whose frequency == 1.<br>
     *           - Example: Keep {1,3} → new list = 1 -> 3.<br>
     *           <br>
     *
     *           <b>Why this works?</b><br>
     *           - Separates detection of duplicates (pass 1) from construction of
     *           result (pass 2).<br>
     *
     *           <b>Drawback:</b><br>
     *           - Needs O(n) extra space for the frequency map.<br>
     *
     * @param head The head of the unsorted linked list
     * @return The head of the list after removing duplicates
     */
    public ListNode deleteDuplicatesUnsortedHashMap(ListNode head) {
        if (head == null)
            return null;

        // Step 1: Count frequencies
        Map<Integer, Integer> freq = new HashMap<>();
        ListNode curr = head;
        while (curr != null) {
            freq.put(curr.val, freq.getOrDefault(curr.val, 0) + 1);
            curr = curr.next;
        }

        // Step 2: Build new list with unique values
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        curr = head;
        while (curr != null) {
            if (freq.get(curr.val) == 1) {
                temp.next = new ListNode(curr.val);
                temp = temp.next;
            }
            curr = curr.next;
        }

        return dummy.next;
    }

    /**
     * -------------------------------------------------
     * Approach 2: Efficient In-Place With HashMap
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1: Count Frequencies</b><br>
     *           - As in Approach 1, use a HashMap to count occurrences.<br>
     *           <br>
     *
     *           <b>Step 2: Modify List In-Place</b><br>
     *           - Use a dummy node before head (to handle deletions at head).<br>
     *           - Traverse the list using two pointers: prev and curr.<br>
     *           - If curr.val has frequency > 1, skip it by linking prev.next =
     *           curr.next.<br>
     *           - Otherwise, move prev forward normally.<br>
     *           <br>
     *
     *           <b>Why this works?</b><br>
     *           - The HashMap gives duplicate detection.<br>
     *           - In-place removal avoids building a new list.<br>
     *           <br>
     *
     * @param head The head of the unsorted linked list
     * @return Modified head with duplicates removed
     */
    public ListNode deleteDuplicatesUnsortedEfficient(ListNode head) {
        if (head == null)
            return null;

        // Step 1: Count frequencies
        Map<Integer, Integer> freq = new HashMap<>();
        ListNode curr = head;
        while (curr != null) {
            freq.put(curr.val, freq.getOrDefault(curr.val, 0) + 1);
            curr = curr.next;
        }

        // Step 2: In-place removal
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        curr = head;

        while (curr != null) {
            if (freq.get(curr.val) > 1) {
                prev.next = curr.next; // skip duplicate
            } else {
                prev = curr; // keep unique
            }
            curr = curr.next;
        }

        return dummy.next;
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 *
 * <b>Approach 1: HashMap (Two-Pass Rebuild)</b><br>
 * - Time Complexity: O(n) → One pass for counting + one pass for
 * rebuilding.<br>
 * - Space Complexity: O(n) → Extra HashMap + new list storage.<br>
 * <br>
 *
 * <b>Approach 2: In-Place With HashMap</b><br>
 * - Time Complexity: O(n) → One pass for counting + one pass for removal.<br>
 * - Space Complexity: O(n) → Extra HashMap for frequencies, but no new list
 * built.<br>
 * <br>
 *
 * ✅ Both approaches need O(n) extra space because the list is unsorted.<br>
 * ✅ Approach 2 is usually preferred since it modifies the list directly.
 */
