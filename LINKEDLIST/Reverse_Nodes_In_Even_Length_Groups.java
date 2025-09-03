/**
 * LeetCode 2074: Reverse Nodes in Even Length Groups
 *
 * <p>
 * <b>Problem:</b><br>
 * You are given the head of a linked list. The nodes are arranged into groups
 * of increasing size:
 * - The 1st node is in the 1st group.
 * - The next 2 nodes are in the 2nd group.
 * - The next 3 nodes are in the 3rd group, and so on.
 *
 * You must reverse the nodes in each group with an even length.
 *
 * <p>
 * <b>Constraints:</b><br>
 * - The number of nodes in the list is in the range [1, 10^5].<br>
 * - Node values: [0, 10^5].<br>
 *
 * <p>
 * <b>Example:</b><br>
 * Input: head = [1,2,3,4,5,6], Output = [1,3,2,4,5,6]<br>
 * - Groups: [1], [2,3], [4,5,6].<br>
 * - Group [2,3] has even length → reversed.<br>
 */
class Solution {

    /**
     * -------------------------------------------------
     * Approach 1: ArrayList (Extra Space)
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1:</b> Traverse the list and collect values into an
     *           ArrayList.<br>
     *           Example: [1,2,3,4,5,6] → [1,2,3,4,5,6].<br>
     *           <br>
     *
     *           <b>Step 2:</b> Process values group by group:<br>
     *           - Group sizes: 1,2,3,... until list ends.<br>
     *           - For each group, if group size is even → reverse that segment in
     *           the ArrayList.<br>
     *           - Otherwise keep it as-is.<br>
     *           Example: groups [1], [2,3], [4,5,6]. Reverse [2,3].<br>
     *           <br>
     *
     *           <b>Step 3:</b> Rebuild linked list from modified ArrayList.<br>
     *
     * @param head head of original list
     * @return modified head
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
     * -------------------------------------------------
     * Approach 2: Efficient In-Place
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1:</b> Traverse the list in increasing group sizes.<br>
     *           - Group sizes are 1, 2, 3, ...<br>
     *           - For each group, count actual number of nodes (because last group
     *           may be shorter).<br>
     *           <br>
     *
     *           <b>Step 2:</b> If actual group size is even:<br>
     *           - Reverse nodes within that group using standard linked list
     *           reversal technique.<br>
     *           - Reconnect group start and end correctly.<br>
     *           - Example: [2,3] → reversed to [3,2].<br>
     *           <br>
     *
     *           <b>Step 3:</b> If actual group size is odd:<br>
     *           - Do nothing, just move pointer forward.<br>
     *           <br>
     *
     *           <b>Step 4:</b> Continue until list ends.<br>
     *
     * @param head head of original list
     * @return modified head
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
                ListNode newGroupStart = prev;
                ListNode newGroupEnd = curr;

                prevGroupEnd.next = newGroupStart;
                prevGroupEnd = newGroupEnd;
                curr = temp;
            } else {
                // Move prevGroupEnd and curr forward by count
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
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 *
 * Approach 1: ArrayList
 * - Time: O(n) → Traverse once, reverse groups, rebuild list.
 * - Space: O(n) → Stores all values in ArrayList + new linked list.
 *
 * Approach 2: Efficient In-Place
 * - Time: O(n) → Each node is visited and possibly reversed once.
 * - Space: O(1) → Only pointer manipulation, no extra structures.
 *
 * Comparison:
 * - Both run in linear time.
 * - ArrayList is simpler but wastes O(n) memory.
 * - Efficient in-place is optimal.
 */
