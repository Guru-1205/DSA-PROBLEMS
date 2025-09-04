/**
 * LeetCode 3217: Delete Nodes From Linked List Present in Array
 *
 * <p>
 * <b>Problem Description:</b><br>
 * You are given the head of a linked list and an array of integers nums.
 * Delete all nodes from the linked list that have a value present in nums,
 * and return the modified linked list.
 *
 * <p>
 * Example:<br>
 * Input: head = [1,2,3,4,5], nums = [1,2,5]<br>
 * Output: [3,4]<br>
 *
 * <p>
 * <b>Constraints:</b><br>
 * - The number of nodes in the list is in the range [1, 10^5].<br>
 * - 1 <= Node.val <= 10^5<br>
 * - 1 <= nums.length <= 10^5<br>
 * - 1 <= nums[i] <= 10^5<br>
 */
public class Delete_Nodes_From_Linked_List_Present_In_Array {

    /**
     * -------------------------------------------------
     * Approach 1: Using ArrayList (Rebuild New Linked List)
     * -------------------------------------------------
     *
     * <b>Step 1: Preprocess nums</b><br>
     * - Convert nums into a HashSet for O(1) lookup.<br>
     * - Example: nums=[1,2,5] → set={1,2,5}.<br>
     * <br>
     *
     * <b>Step 2: Traverse the linked list</b><br>
     * - For each node, check if its value is in the set.<br>
     * - If NOT in set → keep it (add value to ArrayList).<br>
     * - If in set → skip it.<br>
     * - Example: head=[1,2,3,4,5], set={1,2,5} → kept=[3,4].<br>
     * <br>
     *
     * <b>Step 3: Build a new linked list</b><br>
     * - Start with a dummy node.<br>
     * - Append all kept values from the ArrayList sequentially.<br>
     * - Example: kept=[3,4] → Linked List: 3 -> 4.<br>
     * <br>
     *
     * <b>Step 4: Return new head</b><br>
     * - If ArrayList is empty → return null.<br>
     *
     * @param head The head of the linked list
     * @param nums Array of integers representing values to delete
     * @return New head of the modified linked list
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
                kept.add(curr.val); // collect nodes not in nums
            }
            curr = curr.next;
        }

        // Rebuild new list
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
     * <b>Step 1: Preprocess nums</b><br>
     * - Store nums into a HashSet for quick O(1) lookup.<br>
     * - Example: nums=[1,2,5] → set={1,2,5}.<br>
     * <br>
     *
     * <b>Step 2: Initialize dummy and pointers</b><br>
     * - Use a dummy node pointing to head.<br>
     * - Maintain two pointers: prev and curr.<br>
     * - prev starts at dummy, curr at head.<br>
     * <br>
     *
     * <b>Step 3: Traverse and delete nodes</b><br>
     * - For each curr node:<br>
     * - If curr.val ∈ set → skip it by linking prev.next = curr.next.<br>
     * - Else → keep it, move prev forward.<br>
     * - Example:<br>
     * head=[1,2,3,4,5], set={1,2,5}.<br>
     * - Delete 1 → prev.next=2.next=3.<br>
     * - Delete 2 → prev.next=3.next=4.<br>
     * - Keep 3, keep 4.<br>
     * - Delete 5 → prev.next=null.<br>
     * Result=[3,4].<br>
     * <br>
     *
     * <b>Step 4: Return new head</b><br>
     * - Return dummy.next (handles case where head was deleted).<br>
     *
     * @param head The head of the linked list
     * @param nums Array of integers representing values to delete
     * @return Modified linked list head (in-place deletion)
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
 * Complexity Analysis
 * -------------------------------------------------
 *
 * <b>Approach 1: ArrayList</b><br>
 * - Time Complexity: O(n + m)<br>
 * - Build HashSet from nums → O(m).<br>
 * - Traverse linked list → O(n).<br>
 * - Build new list → O(n).<br>
 * - Space Complexity: O(n) → ArrayList + new list.<br>
 * <br>
 *
 * <b>Approach 2: Efficient In-Place</b><br>
 * - Time Complexity: O(n + m)<br>
 * - Build HashSet from nums → O(m).<br>
 * - Single traversal → O(n).<br>
 * - Space Complexity: O(m) → HashSet only.<br>
 * <br>
 *
 * <b>Comparison:</b><br>
 * - Both run in O(n + m).<br>
 * - ArrayList approach uses extra O(n) space and rebuilds list.<br>
 * - Efficient approach is optimal: modifies in place, only O(m) space.<br>
 */
