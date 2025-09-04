/**
 * LeetCode 237: Delete Node in a Linked List
 *
 * <p>
 * <b>Problem Description:</b><br>
 * Write a function to delete a node (except the tail) in a singly linked list,
 * given only access to that node.
 *
 * <p>
 * <b>Key Restriction:</b><br>
 * - We are not given access to the head of the list.<br>
 * - Only the node to delete is provided.<br>
 * - Guaranteed: the node to be deleted is not the last node.<br>
 *
 * <p>
 * Example:<br>
 * Input: head = [4,5,1,9], node = 5<br>
 * Output: [4,1,9]<br>
 */
public class Delete_Node_In_Linked_List {

    /**
     * -------------------------------------------------
     * Approach: Copy Next Node's Data and Skip It
     * -------------------------------------------------
     *
     * <b>Step 1: Identify the limitation</b><br>
     * - In normal deletion, we need the previous node to change its "next"
     * pointer.<br>
     * - Here, we don’t have access to the head or previous node, so we cannot
     * perform the standard deletion.<br>
     * <br>
     *
     * <b>Step 2: Overwrite current node’s value</b><br>
     * - Copy the value from the next node into the current node.<br>
     * - Example: node=5, node.next=1 → set node.val = 1.<br>
     * - Now, the current node looks like the next node.<br>
     * <br>
     *
     * <b>Step 3: Bypass the next node</b><br>
     * - Set current node’s next pointer to skip over the next node:<br>
     * node.next = node.next.next.<br>
     * - This removes the duplicate value (the original next node).<br>
     * - Example: list [4,5,1,9] becomes [4,1,9].<br>
     * <br>
     *
     * <b>Step 4: Edge Case Handling</b><br>
     * - Guaranteed: node is not the tail → node.next always exists.<br>
     * - Hence, no null pointer issues.<br>
     *
     * <b>Why this works?</b><br>
     * - By overwriting and skipping, the target node effectively disappears from
     * the list.<br>
     * - We simulate deletion without needing the previous node.<br>
     *
     * @param node The node to delete (not the tail node)
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val; // Copy next node's value
        node.next = node.next.next; // Skip the next node
    }
}

/**
 * -------------------------------------------------
 * Complexity Analysis
 * -------------------------------------------------
 *
 * Approach (Copy and Skip):
 * - Time Complexity: O(1) → Only two operations (copy + pointer update).
 * - Space Complexity: O(1) → No extra memory used.
 *
 * Note:
 * - This is the only valid approach because the head and previous node
 * are not accessible.
 */
