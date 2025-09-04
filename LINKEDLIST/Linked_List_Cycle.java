/**
 * LeetCode 141: Linked List Cycle
 *
 * <p>
 * <b>Problem:</b><br>
 * Given the head of a linked list, determine if the linked list contains a
 * cycle.
 * A cycle exists if there is some node in the list that can be reached again
 * by continuously following the next pointer.
 *
 * <p>
 * <b>Key Notes:</b><br>
 * - The cycle is created by connecting the tail node to some previous node.<br>
 * - If no cycle exists, return false.<br>
 * - If a cycle exists, return true.<br>
 *
 * <p>
 * <b>Example:</b><br>
 * Input: head = [3,2,0,-4], pos = 1 (tail connects to node at index 1)<br>
 * Output: true<br>
 * Explanation: The linked list has a cycle starting at node with value 2.<br>
 */
public class Linked_List_Cycle {

    /**
     * -------------------------------------------------
     * Approach: Floyd’s Cycle Detection (Tortoise & Hare)
     * -------------------------------------------------
     *
     * 
     * <b>Step 1: Handle base cases</b><br>
     * - If head is null → empty list, return false.<br>
     * - If head.next is null → single node, cannot form a cycle, return
     * false.<br>
     * <br>
     *
     * <b>Step 2: Initialize pointers</b><br>
     * - slow → starts at head, moves 1 step at a time.<br>
     * - fast → starts at head, moves 2 steps at a time.<br>
     * <br>
     *
     * <b>Step 3: Traverse the list</b><br>
     * - While fast != null and fast.next != null:<br>
     * • Move slow = slow.next (1 step).<br>
     * • Move fast = fast.next.next (2 steps).<br>
     * - If slow == fast at any point → cycle detected, return true.<br>
     * <br>
     *
     * <b>Step 4: If traversal ends</b><br>
     * - If fast or fast.next becomes null, we reached the end of
     * list.<br>
     * - No cycle exists → return false.<br>
     * <br>
     *
     * <b>Intuition:</b><br>
     * - If the list has no cycle → fast pointer will exit by hitting
     * null.<br>
     * - If the list has a cycle → fast pointer will "lap" slow pointer,
     * causing them to meet.<br>
     *
     * @param head The head of the linked list
     * @return true if a cycle exists, false otherwise
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slow = head;
        ListNode fast = head;

        // Step 3: Traverse with two pointers
        while (fast != null && fast.next != null) {
            slow = slow.next; // move by 1 step
            fast = fast.next.next; // move by 2 steps

            if (slow == fast) { // cycle detected
                return true;
            }
        }

        return false; // no cycle found
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 *
 * <b>Time Complexity:</b> O(n)<br>
 * - Each pointer visits nodes at most O(n) times.<br>
 * - Fast moves twice as quickly but still bounded by 2n steps.<br>
 * - Overall → O(n).<br>
 * <br>
 *
 * <b>Space Complexity:</b> O(1)<br>
 * - Only two pointers (slow, fast) are used regardless of input size.<br>
 * <br>
 *
 * <b>Comparison with HashSet method:</b><br>
 * - HashSet approach → O(n) space to store visited nodes.<br>
 * - Floyd’s Algorithm → O(1) space, optimal.<br>
 */
