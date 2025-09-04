/**
 * LeetCode 1836: Remove Duplicates From an Unsorted Linked List
 *
 * Problem Description:
 * --------------------
 * Given the head of an unsorted singly linked list, delete all nodes that have
 * duplicate numbers, leaving only distinct numbers from the original list.
 * Return the head of the modified list.
 *
 * Example:
 * Input: head = [1,2,3,2]
 * Output: [1,3]
 *
 * Constraints:
 * - The number of nodes is in the range [0, 10^5].
 * - Node values are integers (can repeat).
 *
 * stdgc (standard) format: problem → detailed steps for each approach →
 * implementation → complexity analysis.
 */
public class Remove_Duplicates_From_Unsorted_List {

    /**
     * -------------------------------------------------
     * Approach 1: HashMap (Two-Pass Rebuild)
     * -------------------------------------------------
     *
     * Steps (detailed):
     * 1. Edge-case: If head is null → return null immediately.
     * 2. First pass — count frequencies:
     * - Traverse the list once and record counts in a HashMap<Integer, Integer>.
     * - Example: [1,2,3,2] → freq = {1=1, 2=2, 3=1}.
     * 3. Second pass — rebuild the result list using only values with freq == 1:
     * - Create a dummy node and a tail pointer.
     * - Traverse the original list again; for each node whose value has freq == 1,
     * append a new node with that value to the rebuilt list.
     * - This preserves original order of distinct values.
     * 4. Return dummy.next (the rebuilt list head).
     *
     * Why use this approach:
     * - Simple and clear separation: detect duplicates first, construct result
     * second.
     * - Good when you prefer building a fresh list instead of mutating the input.
     *
     * Drawbacks:
     * - Uses O(n) extra space for frequency map and also allocates new nodes for
     * the result.
     *
     * @param head Head of the unsorted linked list
     * @return New head with only distinct values (values that appeared exactly
     *         once)
     */
    public ListNode deleteDuplicatesUnsortedHashMap(ListNode head) {
        if (head == null)
            return null;

        // Step 1: Count frequencies
        java.util.Map<Integer, Integer> freq = new java.util.HashMap<>();
        ListNode node = head;
        while (node != null) {
            freq.put(node.val, freq.getOrDefault(node.val, 0) + 1);
            node = node.next;
        }

        // Step 2: Rebuild list with values whose frequency == 1
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        node = head;
        while (node != null) {
            if (freq.get(node.val) == 1) {
                tail.next = new ListNode(node.val);
                tail = tail.next;
            }
            node = node.next;
        }

        return dummy.next;
    }

    /**
     * -------------------------------------------------
     * Approach 2: Efficient In-Place With HashMap (Two-Pass, No New Node
     * Allocation)
     * -------------------------------------------------
     *
     * Steps (detailed):
     * 1. Edge-case: If head is null → return null immediately.
     * 2. First pass — count frequencies:
     * - Traverse the list and store counts in a HashMap<Integer, Integer>.
     * - Example: [1,2,3,2] → freq = {1=1, 2=2, 3=1}.
     * 3. Second pass — remove nodes in-place:
     * - Use a dummy node pointing to head to simplify deletions (especially at
     * head).
     * - Maintain two pointers: prev (initially dummy) and curr (initially head).
     * - For each curr:
     * • If freq.get(curr.val) > 1 → skip curr: prev.next = curr.next (do NOT move
     * prev).
     * • Else → keep curr: prev = curr.
     * • Move curr forward in all cases: curr = curr.next.
     * - This removes duplicates while preserving order of kept nodes.
     * 4. Return dummy.next (modified original list head).
     *
     * Why use this approach:
     * - Avoids allocating a full new list (we reuse nodes where possible).
     * - Keeps memory usage lower than rebuilding (still needs O(n) for the
     * frequency map).
     *
     * Notes / Edge cases:
     * - If all values are duplicates, result is an empty list (dummy.next == null).
     * - If head itself must be deleted, dummy ensures correct reconnection.
     *
     * @param head Head of the unsorted linked list
     * @return Modified head after removing all nodes whose values appeared more
     *         than once
     */
    public ListNode deleteDuplicatesUnsortedEfficient(ListNode head) {
        if (head == null)
            return null;

        // Step 1: Count frequencies
        java.util.Map<Integer, Integer> freq = new java.util.HashMap<>();
        ListNode node = head;
        while (node != null) {
            freq.put(node.val, freq.getOrDefault(node.val, 0) + 1);
            node = node.next;
        }

        // Step 2: In-place removal using dummy + prev/curr
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null) {
            if (freq.get(curr.val) > 1) {
                // skip current node (delete it)
                prev.next = curr.next;
            } else {
                // keep current node
                prev = curr;
            }
            curr = curr.next;
        }

        return dummy.next;
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis (stdgc)
 * -------------------------------------------------
 *
 * Approach 1: HashMap (Two-Pass Rebuild)
 * - Time: O(n)
 * • One pass to build frequency map, one pass to rebuild resulting list.
 * - Space: O(n)
 * • Frequency map uses O(n) in worst case.
 * • Rebuilt list allocates up to O(n) new nodes.
 *
 * Approach 2: Efficient In-Place With HashMap
 * - Time: O(n)
 * • One pass to build frequency map, one pass to remove nodes in-place.
 * - Space: O(n)
 * • Frequency map uses O(n) in worst case.
 * • No additional list is allocated (we reuse existing nodes).
 *
 * Comparison & Recommendation:
 * - Because the input list is unsorted, tracking frequencies requires O(n)
 * extra space;
 * this is unavoidable without additional constraints.
 * - Prefer Approach 2 in practice: it avoids allocating a new list and performs
 * deletions in-place,
 * while retaining the same time complexity and only O(n) extra space for the
 * frequency map.
 */
