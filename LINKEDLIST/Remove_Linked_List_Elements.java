/**
 * LeetCode 203: Remove Linked List Elements
 *
 * <p>
 * <b>Problem:</b><br>
 * Remove all nodes of a linked list that have a specific value.
 *
 * <p>
 * <b>Example:</b><br>
 * Input: head = [1,2,6,3,4,5,6], val = 6<br>
 * Output: [1,2,3,4,5]
 */
class Solution {

    /**
     * -------------------------------------------------
     * Approach 1: Using ArrayList (Extra Space)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1: Traverse and Collect Values</b><br>
     *           - Traverse the linked list.<br>
     *           - Store all values that are NOT equal to val in an ArrayList.<br>
     *           <br>
     *
     *           <b>Step 2: Rebuild the Linked List</b><br>
     *           - Create a new dummy head.<br>
     *           - Insert nodes from ArrayList into the new list.<br>
     *           <br>
     *
     *           <b>Why this works?</b><br>
     *           - We first filter valid values and then reconstruct the list.<br>
     *
     *           <b>Drawback:</b><br>
     *           - Requires extra O(n) space for ArrayList.<br>
     *
     * @param head The head of the linked list
     * @param val  The value to remove from the list
     * @return The new head after removals
     */
    public ListNode removeElementsArrayList(ListNode head, int val) {
        List<Integer> values = new ArrayList<>();

        // Step 1: Collect valid values
        while (head != null) {
            if (head.val != val) {
                values.add(head.val);
            }
            head = head.next;
        }

        // Step 2: Rebuild linked list
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int v : values) {
            current.next = new ListNode(v);
            current = current.next;
        }

        return dummy.next;
    }

    /**
     * -------------------------------------------------
     * Approach 2: Efficient In-Place Deletion (Dummy Head)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1: Create a Dummy Node</b><br>
     *           - Use a dummy node before the head to simplify edge cases (like
     *           removing head itself).<br>
     *           <br>
     *
     *           <b>Step 2: Traverse and Remove Target Nodes</b><br>
     *           - Use a pointer `current` starting at dummy.<br>
     *           - If `current.next.val == val`, skip it by linking `current.next =
     *           current.next.next`.<br>
     *           - Otherwise, move `current` forward.<br>
     *           <br>
     *
     *           <b>Step 3: Return Result</b><br>
     *           - Return dummy.next (new head).<br>
     *           <br>
     *
     *           <b>Why this works?</b><br>
     *           - By controlling `current.next`, we can safely delete nodes without
     *           losing references.<br>
     *           - Dummy node handles head deletion cleanly.<br>
     *
     * @param head The head of the linked list
     * @param val  The value to remove
     * @return The head after removals
     */
    public ListNode removeElementsEfficient(ListNode head, int val) {
        ListNode dummy = new ListNode(0, head);
        ListNode current = dummy;

        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next; // Skip target node
            } else {
                current = current.next;
            }
        }

        return dummy.next;
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 *
 * <b>Approach 1: ArrayList</b><br>
 * - Time Complexity: O(n) → One traversal to collect + one traversal to
 * rebuild.<br>
 * - Space Complexity: O(n) → Extra ArrayList storage.<br>
 * <br>
 *
 * <b>Approach 2: Efficient In-Place (Dummy Node)</b><br>
 * - Time Complexity: O(n) → One traversal of the list.<br>
 * - Space Complexity: O(1) → Only a few pointers used.<br>
 *
 * ✅ Recommended: Approach 2 (Efficient) for in-place modification.
 */
