/**
 * LeetCode 3217: Delete Nodes From Linked List Present in Array
 *
 * <p>
 * <b>Problem:</b><br>
 * You are given the head of a linked list and an array of integers nums.
 * Delete all nodes from the linked list that have a value present in nums,
 * and return the modified linked list.
 *
 * <p>
 * <b>Example:</b><br>
 * Input: head = [1,2,3,4,5], nums = [1,2,5]<br>
 * Output: [3,4]
 *
 * <p>
 * <b>Constraints:</b><br>
 * - The number of nodes in the list is in the range [1, 10^5].<br>
 * - 1 <= Node.val <= 10^5<br>
 * - 1 <= nums.length <= 10^5<br>
 * - 1 <= nums[i] <= 10^5
 */
class Solution {

    /**
     * -------------------------------------------------
     * Approach 1: Using ArrayList (Rebuild New Linked List)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1: Collect nodes not in nums</b><br>
     *           - Traverse the original list node by node.<br>
     *           - If the node's value is NOT in nums, add it to an ArrayList.<br>
     *           - Otherwise, skip that node.<br>
     *           - Example: head=[1,2,3,4,5], nums=[1,2,5] → kept=[3,4].<br>
     *           <br>
     *
     *           <b>Step 2: Build a new linked list from ArrayList</b><br>
     *           - Start with a dummy node.<br>
     *           - Append all collected values sequentially.<br>
     *           - Example: kept=[3,4] → Linked List 3 -> 4.<br>
     *           <br>
     *
     *           <b>Step 3: Return new head</b><br>
     *           - If no values are kept, return null.<br>
     *
     * @param head The head of the linked list
     * @param nums Array of integers representing values to delete
     * @return New head of the modified linked list
     *
     *         <p>
     *         <b>Time Complexity:</b> O(n + m)<br>
     *         (n = number of nodes, m = nums.length; lookup in HashSet O(1)).<br>
     *         <b>Space Complexity:</b> O(n) → ArrayList stores kept values + new
     *         list storage.<br>
     */
    public ListNode deleteNodesArrayList(ListNode head, int[] nums) {
        java.util.HashSet<Integer> toDelete = new java.util.HashSet<>();
        for (int num : nums) {
            toDelete.add(num);
        }

        java.util.ArrayList<Integer> kept = new java.util.ArrayList<>();
        ListNode curr = head;

        while (curr != null) {
            if (!toDelete.contains(curr.val)) {
                kept.add(curr.val);
            }
            curr = curr.next;
        }

        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        for (int val : kept) {
            temp.next = new ListNode(val);
            temp = temp.next;
        }

        return dummy.next;
    }

    /**
     * -------------------------------------------------
     * Approach 2: Efficient In-Place Deletion (HashSet + One Pass)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1: Convert nums into a HashSet</b><br>
     *           - Store all values from nums in a HashSet for O(1) lookup.<br>
     *           - Example: nums=[1,2,5] → set={1,2,5}.<br>
     *           <br>
     *
     *           <b>Step 2: Traverse the linked list</b><br>
     *           - Use two pointers: prev and curr.<br>
     *           - If curr.val is in set, delete it by linking
     *           prev.next=curr.next.<br>
     *           - Otherwise, move prev forward.<br>
     *           - Example: head=[1,2,3,4,5], nums={1,2,5} → result=[3,4].<br>
     *           <br>
     *
     *           <b>Step 3: Return new head</b><br>
     *           - Handle case where head itself is deleted (dummy node helps
     *           simplify).<br>
     *
     * @param head The head of the linked list
     * @param nums Array of integers representing values to delete
     * @return Modified linked list head (in-place deletion)
     *
     *         <p>
     *         <b>Time Complexity:</b> O(n + m)<br>
     *         (n = nodes, m = nums.length; set lookup O(1)).<br>
     *         <b>Space Complexity:</b> O(m) → HashSet for nums.<br>
     */
    public ListNode deleteNodesEfficient(ListNode head, int[] nums) {
        java.util.HashSet<Integer> toDelete = new java.util.HashSet<>();
        for (int num : nums) {
            toDelete.add(num);
        }

        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null) {
            if (toDelete.contains(curr.val)) {
                prev.next = curr.next; // delete curr
            } else {
                prev = curr; // keep curr
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
 * <b>Approach 1: ArrayList</b><br>
 * - Time Complexity: O(n + m)<br>
 * - Build HashSet of nums → O(m).<br>
 * - Traverse list and collect values → O(n).<br>
 * - Build new list → O(n).<br>
 * - Space Complexity: O(n) → ArrayList of kept values + new list.<br>
 * <br>
 *
 * <b>Approach 2: Efficient In-Place</b><br>
 * - Time Complexity: O(n + m)<br>
 * - Build HashSet of nums → O(m).<br>
 * - Single pass traversal of list → O(n).<br>
 * - Space Complexity: O(m) → HashSet only.<br>
 * <br>
 *
 * <b>Comparison:</b><br>
 * - Both are O(n + m) in time.<br>
 * - ArrayList approach wastes O(n) extra space by rebuilding the list.<br>
 * - Efficient approach is optimal, modifies in place, and only needs O(m).
 */
