/**
 * LeetCode 1265: Print Immutable Linked List in Reverse
 *
 * <p><b>Problem:</b><br>
 * You are given the head of an immutable singly linked list.
 * You must print the values in reverse order.
 *
 * <p><b>Constraints:</b><br>
 * - The list length is between [1, 1000].<br>
 * - Node values are within the integer range.<br>
 * - The list is immutable → you cannot modify next pointers.<br>
 *
 * <p><b>Example:</b><br>
 * Input: head = [1,2,3,4]<br>
 * Output: 4,3,2,1<br>
 */

/**
 * This is the ImmutableListNode’s API interface.
 * You should not implement it or speculate about its implementation.
 */
interface ImmutableListNode {
    public void printValue(); // print the value of this node

    public ImmutableListNode getNext(); // return the next node
}

class Solution {

    /**
     * -------------------------------------------------
     * Approach: Using ArrayList
     * -------------------------------------------------
     *
     * @implNote
     *           <b>Step 1:</b> Traverse the immutable linked list and collect all
     *           values
     *           into an ArrayList.<br>
     *           Example: [1,2,3,4] → ArrayList [1,2,3,4].<br>
     *           <br>
     *
     *           <b>Step 2:</b> Iterate over the ArrayList in reverse order and
     *           print values.<br>
     *           Example: ArrayList [1,2,3,4] → print 4,3,2,1.<br>
     *           <br>
     *
     *           <b>Why it works:</b> We cannot modify the linked list, but we can
     *           store values
     *           externally and then access them in reverse.<br>
     *
     * @param head immutable list head
     */
    public void printLinkedListInReverse(ImmutableListNode head) {
        java.util.ArrayList<ImmutableListNode> list = new java.util.ArrayList<>();

        // Step 1: Collect nodes
        ImmutableListNode curr = head;
        while (curr != null) {
            list.add(curr);
            curr = curr.getNext();
        }

        // Step 2: Print in reverse
        for (int i = list.size() - 1; i >= 0; i--) {
            list.get(i).printValue();
        }
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 *
 * Approach: ArrayList
 * - Time: O(n)
 * → One pass to collect nodes, one pass to print in reverse.
 * - Space: O(n)
 * → ArrayList stores all nodes.
 *
 * Comparison:
 * - Works under immutability constraint.
 * - Uses extra memory O(n), but simplest and safe.
 */
