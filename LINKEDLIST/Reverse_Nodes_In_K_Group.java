/**
 * LeetCode 25: Reverse Nodes in k-Group
 *
 * <p>
 * <b>Problem:</b><br>
 * Given the head of a linked list, reverse the nodes of the list k at a time,
 * and return the modified list. If the number of nodes is not a multiple of k,
 * the last remaining nodes should remain as they are.
 *
 * <p>
 * <b>Constraints:</b><br>
 * - The number of nodes in the list is in the range [1, 5000].<br>
 * - 0 <= Node.val <= 1000.<br>
 * - 1 <= k <= 5000.<br>
 *
 * <p>
 * <b>Example:</b><br>
 * Input: head = [1,2,3,4,5], k = 2<br>
 * Output: [2,1,4,3,5]<br>
 * <br>
 *
 * Input: head = [1,2,3,4,5], k = 3<br>
 * Output: [3,2,1,4,5]<br>
 */

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Reverse_Nodes_In_K_Group {

    /**
     * -------------------------------------------------
     * Approach 1: ArrayList (Extra Space)
     * -------------------------------------------------
     *
     * 
     * <b>Step 1:</b> Traverse the linked list and collect values in an
     * ArrayList.<br>
     * Example: [1,2,3,4,5], k=2 → [1,2,3,4,5].<br>
     * <br>
     *
     * <b>Step 2:</b> Process ArrayList in chunks of size k.<br>
     * - For every k-group, reverse that sublist.<br>
     * - Leave the last group as-is if its size < k.<br>
     * Example: [1,2,3,4,5], k=2 → [2,1,4,3,5].<br>
     * <br>
     *
     * <b>Step 3:</b> Build a new linked list from the modified
     * ArrayList.<br>
     * Example: [2,1,4,3,5] → 2 → 1 → 4 → 3 → 5.<br>
     * <br>
     *
     * <b>Step 4:</b> Return the new head.<br>
     *
     * @param head head of original list
     * @param k    group size
     * @return new head after reversing k-group chunks
     */
    public ListNode reverseKGroupArrayList(ListNode head, int k) {
        java.util.ArrayList<Integer> values = new java.util.ArrayList<>();
        ListNode curr = head;

        // Step 1: Collect values
        while (curr != null) {
            values.add(curr.val);
            curr = curr.next;
        }

        // Step 2: Reverse chunks of size k
        for (int i = 0; i + k <= values.size(); i += k) {
            int left = i, right = i + k - 1;
            while (left < right) {
                int temp = values.get(left);
                values.set(left, values.get(right));
                values.set(right, temp);
                left++;
                right--;
            }
        }

        // Step 3: Rebuild linked list
        ListNode dummy = new ListNode(0);
        curr = dummy;
        for (int val : values) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }

        return dummy.next;
    }

    /**
     * -------------------------------------------------
     * Approach 2: Efficient In-Place (Three-Pointer + Sliding Window)
     * -------------------------------------------------
     *
     *
     * <b>Step 1:</b> Count total nodes to ensure we have enough to form
     * groups of k.<br>
     * Example: [1,2,3,4,5], k=3 → length=5 → can only reverse first
     * 3.<br>
     * <br>
     *
     * <b>Step 2:</b> Use three pointers to reverse in chunks:<br>
     * - prev (marks the node before current k-group)<br>
     * - curr (traverses nodes in current group)<br>
     * - next (stores curr.next temporarily)<br>
     * <br>
     *
     * <b>Step 3:</b> For each group of size k:<br>
     * - Reverse pointers within that chunk.<br>
     * - Link prev.next to the new head of the reversed group.<br>
     * - Move prev to the end of this group.<br>
     * <br>
     *
     * <b>Step 4:</b> Continue until fewer than k nodes remain.<br>
     *
     * @param head head of original list
     * @param k    group size
     * @return new head after reversing k-group chunks
     */
    public ListNode reverseKGroupEfficient(ListNode head, int k) {
        if (head == null || k == 1)
            return head;

        // Step 1: Count nodes
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroup = dummy;

        // Step 2: Process groups
        while (length >= k) {
            curr = prevGroup.next;
            ListNode next = curr.next;

            // Step 3: Reverse k-1 links within this group
            for (int i = 1; i < k; i++) {
                curr.next = next.next;
                next.next = prevGroup.next;
                prevGroup.next = next;
                next = curr.next;
            }

            prevGroup = curr;
            length -= k;
        }

        return dummy.next;
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 *
 * Approach 1: ArrayList
 * - Time: O(n) → Traverse list once, reverse in chunks, rebuild list.
 * - Space: O(n) → Extra ArrayList + new linked list nodes.
 *
 * Approach 2: Efficient In-Place
 * - Time: O(n) → Each node visited and rearranged once.
 * - Space: O(1) → Only pointers used, no extra data structures.
 *
 * Comparison:
 * - Both are O(n) in time.
 * - ArrayList wastes O(n) memory.
 * - Efficient in-place solution is optimal in both time and space.
 */
