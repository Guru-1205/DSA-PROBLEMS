/**
 * LeetCode 142: Linked List Cycle II
 *
 * <p>
 * <b>Problem:</b><br>
 * Given the head of a linked list, return the node where the cycle begins.
 * If there is no cycle, return null.
 *
 * <p>
 * <b>Key Notes:</b><br>
 * - A cycle exists if a node’s next pointer points to an earlier node.<br>
 * - We need to detect if a cycle exists, and if yes, find the node
 * where the cycle begins.<br>
 * - If no cycle exists, return null.<br>
 *
 * <p>
 * <b>Example:</b><br>
 * Input: head = [3,2,0,-4], pos = 1 (tail connects to index 1)<br>
 * Output: Node with value = 2<br>
 * Explanation: The cycle starts at node index 1 (value = 2).
 */
public class Linked_List_Cycle_II {

    /**
     * -------------------------------------------------
     * Approach: Floyd’s Cycle Detection (Tortoise & Hare)
     * -------------------------------------------------
     *
     * 
     * <b>Step 1: Handle base cases</b><br>
     * - If head is null or head.next is null, no cycle possible → return
     * null.<br>
     * <br>
     *
     * <b>Step 2: Detect if a cycle exists</b><br>
     * - Use two pointers:
     * - slow → moves 1 step at a time
     * - fast → moves 2 steps at a time
     * - Traverse the list:
     * - If fast or fast.next becomes null → no cycle, return null.<br>
     * - If slow == fast → cycle detected, break out of loop.<br>
     * <br>
     *
     * <b>Step 3: Find the cycle start</b><br>
     * - Reset slow to head, keep fast at the meeting point.<br>
     * - Move both slow and fast one step at a time.<br>
     * - The point where they meet again is the start of the cycle.<br>
     * <br>
     *
     * <b>Step 4: Return result</b><br>
     * - Return the node where slow and fast meet again (cycle start).<br>
     * - If no cycle found, return null.<br>
     * <br>
     *
     * <b>Why this works?</b><br>
     * - When slow and fast meet inside the cycle:
     * distance(head → cycleStart) = distance(meetingPoint →
     * cycleStart).<br>
     * - This ensures that resetting slow to head and moving both
     * one step at a time guarantees meeting at cycle start.<br>
     *
     * @param head Head of the linked list
     * @return The node where the cycle begins, or null if no cycle
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head;
        ListNode fast = head;

        // Step 2: Detect cycle using fast & slow pointers
        while (fast != null && fast.next != null) {
            slow = slow.next; // move 1 step
            fast = fast.next.next; // move 2 steps

            if (slow == fast) { // cycle detected
                // Step 3: Find cycle start
                slow = head; // reset slow to head
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow; // start of cycle
            }
        }

        return null; // no cycle found
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 *
 * <b>Time Complexity:</b> O(n)<br>
 * - In worst case, fast and slow traverse the list twice.<br>
 * - Detect cycle: O(n).<br>
 * - Find cycle start: O(n).<br>
 * - Overall: O(n).<br>
 * <br>
 *
 * <b>Space Complexity:</b> O(1)<br>
 * - Only two pointers (slow, fast) used.<br>
 *
 * <b>Comparison with HashSet method:</b><br>
 * - HashSet method requires O(n) extra space.<br>
 * - Floyd’s Algorithm is optimal → O(1) space.<br>
 */
