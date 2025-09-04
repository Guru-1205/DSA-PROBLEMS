/**
 * LeetCode 1669: Merge In Between Linked Lists
 *
 * <p>
 * <b>Problem:</b><br>
 * You are given two linked lists: list1 and list2, and two integers a and b.
 * Remove the nodes from index a to index b in list1, and insert list2 in their
 * place.
 * Return the modified list1.
 *
 * <p>
 * <b>Example:</b><br>
 * Input: list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 =
 * [1000000,1000001,1000002]<br>
 * Output: [0,1,2,1000000,1000001,1000002,5]<br>
 */
public class Merge_In_Between_Linked_Lists {

    /**
     * -------------------------------------------------
     * Approach: Efficient In-Place Merge (Pointer Manipulation)
     * -------------------------------------------------
     *
     * 
     * <b>Step 1: Traverse list1 to locate two critical nodes</b><br>
     * - aBefore → the node just before index a.<br>
     * - bAfter → the node just after index b.<br>
     * These nodes help us cut out the segment [a...b].<br>
     * <br>
     *
     * <b>Step 2: Connect aBefore to list2</b><br>
     * - If aBefore exists, link aBefore.next = list2.<br>
     * - If a == 0, then aBefore is null, so replace the head of list1
     * with list2.<br>
     * <br>
     *
     * <b>Step 3: Traverse list2 to find its tail</b><br>
     * - Move through list2 until the last node.<br>
     * - Example: list2 = [1000000,1000001,1000002] → tail = 1000002.<br>
     * <br>
     *
     * <b>Step 4: Connect list2’s tail to bAfter</b><br>
     * - Attach list2Tail.next = bAfter.<br>
     * - This ensures that the portion of list1 after b remains
     * connected.<br>
     * <br>
     *
     * <b>Edge Cases:</b><br>
     * - If a = 0 → list2 replaces the beginning of list1.<br>
     * - If list2 has only one node → still works, since its "tail" is
     * itself.<br>
     *
     * @param list1 The first linked list
     * @param a     The starting index of the segment to remove
     * @param b     The ending index of the segment to remove
     * @param list2 The second linked list to insert
     * @return Modified list1 with list2 inserted in place of [a...b]
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode t1 = list1;
        ListNode aBefore = null, bAfter = null;
        ListNode prev = null;
        int counter = 0;

        // Step 1: Find aBefore and bAfter in list1
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

        // Step 2: Connect aBefore to list2 (or replace head if a == 0)
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
 *
 * <b>Time Complexity:</b> O(n + m)<br>
 * - O(n) → Traverse list1 until bAfter is found.<br>
 * - O(m) → Traverse list2 to find its tail.<br>
 * - Overall = O(n + m).<br>
 * <br>
 *
 * <b>Space Complexity:</b> O(1)<br>
 * - Only pointers are used (aBefore, bAfter, list2Tail).<br>
 * <br>
 *
 * <b>Comparison:</b><br>
 * - Optimal solution since it modifies list1 in-place.<br>
 * - No extra arrays or rebuilding needed.<br>
 */
