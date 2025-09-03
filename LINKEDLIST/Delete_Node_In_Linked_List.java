/**
 * LeetCode 237: Delete Node in a Linked List
 *
 * <p>
 * <b>Problem:</b><br>
 * Write a function to delete a node (except the tail) in a singly linked list,
 * given only access to that node.
 *
 * <p>
 * <b>Key Restriction:</b><br>
 * - We are not given access to the head of the list.<br>
 * - We only receive the node to delete.<br>
 * - It is guaranteed that the node to be deleted is not the last node.<br>
 *
 * <p>
 * <b>Example:</b><br>
 * Input: head = [4,5,1,9], node = 5<br>
 * Output: [4,1,9]
 */
class Solution {

    /**
     * -------------------------------------------------
     * Approach: Copy Next Node's Data and Skip It
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1: Copy value from the next node</b><br>
     *           - Take node.next.val and assign it to node.val.<br>
     *           - Example: node=5, node.next=1 → overwrite node.val=1.<br>
     *           <br>
     *
     *           <b>Step 2: Skip the next node</b><br>
     *           - Set node.next = node.next.next.<br>
     *           - This effectively removes the next node (duplicate value).<br>
     *           - Example: list before: [4,5,1,9], after modification: [4,1,9].<br>
     *           <br>
     *
     *           <b>Why this works?</b><br>
     *           - We cannot access the previous node (to adjust its pointer).<br>
     *           - Instead, we overwrite the target node with the next node's data,
     *           then delete the next node.<br>
     *
     *           <b>Edge Case:</b><br>
     *           - Guaranteed that the node to delete is not the tail, so node.next
     *           will always exist.<br>
     *
     * @param node The node to delete (not the tail node)
     * @return Nothing (in-place modification)
     *
     *         <p>
     *         <b>Time Complexity:</b> O(1) → Only two operations (copy + skip).<br>
     *         <b>Space Complexity:</b> O(1) → No extra space used.
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val; // Copy next node's value
        node.next = node.next.next; // Skip the next node
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 *
 * <b>Approach: Copy and Skip</b><br>
 * - Time Complexity: O(1) → Constant-time operations (copy value + change
 * pointer).<br>
 * - Space Complexity: O(1) → No extra space required.<br>
 *
 * <b>Note:</b><br>
 * - This is the only possible solution because we do not have access to the
 * head
 * or the previous node of the target.
 */
