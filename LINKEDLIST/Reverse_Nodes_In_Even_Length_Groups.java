/**
 * LeetCode 2074: Reverse Nodes in Even Length Groups
 *
 * Problem Description:
 * ---------------------
 * You are given the head of a linked list. The nodes are arranged into groups
 * of increasing size:
 * - The 1st node is in the 1st group.
 * - The next 2 nodes are in the 2nd group.
 * - The next 3 nodes are in the 3rd group, and so on.
 *
 * You must reverse the nodes in each group with an even length.
 *
 * Constraints:
 * - The number of nodes in the list is in the range [1, 10^5].
 * - Node values: [0, 10^5].
 *
 * Example:
 * Input: head = [1,2,3,4,5,6]
 * Output: [1,3,2,4,5,6]
 * Explanation:
 * - Groups: [1], [2,3], [4,5,6]
 * - Group [2,3] has even length → reversed.
 */
public class Reverse_Nodes_In_Even_Length_Groups {

    /**
     * Approach 1: ArrayList (Extra Space)
     * ------------------------------------
     * Steps:
     * 1. Traverse the linked list and store all node values in an ArrayList.
     * Example: head = [1,2,3,4,5,6] → values = [1,2,3,4,5,6].
     *
     * 2. Process values group by group:
     * - Group sizes: 1, 2, 3, ...
     * - For each group, determine actual size (since the last group may be
     * shorter).
     * - If group size is even → reverse that sublist inside the ArrayList.
     * - Otherwise keep it as-is.
     * Example: groups [1], [2,3], [4,5,6] → reverse [2,3] → [1,3,2,4,5,6].
     *
     * 3. Rebuild a new linked list from the modified ArrayList and return it.
     */
    public ListNode reverseEvenLengthGroupsArrayList(ListNode head) {
        java.util.ArrayList<Integer> values = new java.util.ArrayList<>();
        ListNode curr = head;

        // Step 1: Collect values
        while (curr != null) {
            values.add(curr.val);
            curr = curr.next;
        }

        // Step 2: Process groups
        int index = 0;
        int groupSize = 1;

        while (index < values.size()) {
            int remaining = values.size() - index;
            int actualSize = Math.min(groupSize, remaining);

            if (actualSize % 2 == 0) {
                int left = index, right = index + actualSize - 1;
                while (left < right) {
                    int temp = values.get(left);
                    values.set(left, values.get(right));
                    values.set(right, temp);
                    left++;
                    right--;
                }
            }

            index += actualSize;
            groupSize++;
        }

        // Step 3: Build new linked list
        ListNode dummy = new ListNode(0);
        curr = dummy;
        for (int val : values) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }

        return dummy.next;
    }

    /**
     * Approach 2: Efficient In-Place
     * -------------------------------
     * Steps:
     * 1. Traverse the list group by group with increasing sizes (1,2,3,...).
     * - For each group, count how many nodes are actually present.
     *
     * 2. If the group length is even:
     * - Reverse that group using the standard linked list reversal technique.
     * - Reconnect the group properly with previous and next nodes.
     * Example: [2,3] → reversed to [3,2].
     *
     * 3. If the group length is odd:
     * - Leave the nodes as-is.
     * - Simply move the pointer to the end of this group.
     *
     * 4. Continue this process until the entire list is processed.
     */
    public ListNode reverseEvenLengthGroupsEfficient(ListNode head) {
        if (head == null)
            return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;
        ListNode curr = head;

        int groupSize = 1;

        while (curr != null) {
            // Step 1: Count actual nodes in this group
            ListNode temp = curr;
            int count = 0;
            while (temp != null && count < groupSize) {
                temp = temp.next;
                count++;
            }

            // Step 2: If group size is even → reverse
            if (count % 2 == 0) {
                ListNode prev = temp;
                ListNode current = curr;

                // Reverse exactly count nodes
                for (int i = 0; i < count; i++) {
                    ListNode nextNode = current.next;
                    current.next = prev;
                    prev = current;
                    current = nextNode;
                }

                // Connect reversed group
                ListNode newGroupStart = prev; // after reversal
                ListNode newGroupEnd = curr; // old start, now end

                prevGroupEnd.next = newGroupStart;
                prevGroupEnd = newGroupEnd;
                curr = temp;
            } else {
                // Step 3: Skip odd-length group
                for (int i = 0; i < count; i++) {
                    prevGroupEnd = curr;
                    curr = curr.next;
                }
            }

            groupSize++;
        }

        return dummy.next;
    }
}

/**
 * Time and Space Complexity Analysis:
 * ------------------------------------
 * Approach 1: ArrayList
 * - Time: O(n) → Traverse list, reverse groups, rebuild list.
 * - Space: O(n) → Uses ArrayList + builds new linked list.
 *
 * Approach 2: Efficient In-Place
 * - Time: O(n) → Each node is visited and possibly reversed once.
 * - Space: O(1) → No extra data structures, only pointers.
 *
 * Comparison:
 * - Both approaches are linear in time.
 * - ArrayList is simpler but not memory-efficient.
 * - Efficient in-place is optimal.
 */
