/**
 * LeetCode 1669: Merge In Between Linked Lists
 *
 * Problem:
 * You are given two linked lists: list1 and list2, and two integers a and b.
 * Remove the nodes from index a to b in list1, and insert list2 in their place.
 * Return the modified list1.
 *
 * Example:
 * Input: list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
 * Output: [0,1,2,1000000,1000001,1000002,5]
 *
 * -------------------------------------------------
 * Efficient Approach (Pointer Manipulation)
 * -------------------------------------------------
 * Step 1: Traverse list1 to identify:
 * - aBefore → the node just before index a
 * - bAfter → the node just after index b
 * These nodes help us cut out the [a...b] segment.
 *
 * Step 2: Connect aBefore.next to list2
 * - This attaches list2 at the position of the removed segment.
 *
 * Step 3: Traverse list2 until its tail
 * - This ensures we can connect the last node of list2 to bAfter.
 *
 * Step 4: Connect list2's tail to bAfter
 * - Completes the merge process.
 *
 * Edge Cases:
 * - If a = 0, then aBefore is null, meaning list2 replaces the start of list1.
 *
 * Time Complexity: O(n + m), where n = length of list1, m = length of list2
 * Space Complexity: O(1), only pointers used
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

class Solution {

    /**
     * Merges list2 into list1 by removing nodes from index a to b in list1.
     *
     * @param list1 The first linked list
     * @param a     The starting index of the segment to remove
     * @param b     The ending index of the segment to remove
     * @param list2 The second linked list to insert
     * @return The modified list1 with list2 inserted in place of [a...b]
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode t1 = list1;
        ListNode aBefore = null, bAfter = null;
        ListNode prev = null;
        int counter = 0;

        // Step 1: Find aBefore and bAfter
        while (t1 != null) {
            if (counter == a) {
                aBefore = prev; // Node before index 'a'
            }
            if (counter == b + 1) {
                bAfter = t1; // Node after index 'b'
                break;
            }
            counter++;
            prev = t1;
            t1 = t1.next;
        }

        // Step 2: Connect aBefore to list2
        if (aBefore != null) {
            aBefore.next = list2;
        } else {
            list1 = list2; // if a == 0, replace head
        }

        // Step 3: Traverse list2 to its tail
        ListNode list2Tail = list2;
        while (list2Tail.next != null) {
            list2Tail = list2Tail.next;
        }

        // Step 4: Connect list2's tail to bAfter
        list2Tail.next = bAfter;

        return list1;
    }
}

/**
 * -------------------------------------------------
 * Time and Space Complexity Analysis
 * -------------------------------------------------
 * Efficient Approach:
 * - Time: O(n + m)
 * → O(n) to find aBefore and bAfter in list1
 * → O(m) to find the tail of list2
 * - Space: O(1), only pointers are used
 *
 * Comparison:
 * - Optimal since it modifies in-place without auxiliary data structures.
 */
