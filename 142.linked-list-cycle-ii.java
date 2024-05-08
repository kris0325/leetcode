/*
 * @lc app=leetcode id=142 lang=java
 *
 * [142] Linked List Cycle II
 *
 * https://leetcode.com/problems/linked-list-cycle-ii/description/
 *
 * algorithms
 * Medium (51.25%)
 * Likes:    13392
 * Dislikes: 937
 * Total Accepted:    1.3M
 * Total Submissions: 2.5M
 * Testcase Example:  '[3,2,0,-4]\n1'
 *
 * Given the head of a linked list, return the node where the cycle begins. If
 * there is no cycle, return null.
 * 
 * There is a cycle in a linked list if there is some node in the list that can
 * be reached again by continuously following the next pointer. Internally, pos
 * is used to denote the index of the node that tail's next pointer is
 * connected to (0-indexed). It is -1 if there is no cycle. Note that pos is
 * not passed as a parameter.
 * 
 * Do not modify the linked list.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the
 * second node.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: head = [1,2], pos = 0
 * Output: tail connects to node index 0
 * Explanation: There is a cycle in the linked list, where tail connects to the
 * first node.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: head = [1], pos = -1
 * Output: no cycle
 * Explanation: There is no cycle in the linked list.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of the nodes in the list is in the range [0, 10^4].
 * -10^5 <= Node.val <= 10^5
 * pos is -1 or a valid index in the linked-list.
 * 
 * 
 * 
 * Follow up: Can you solve it using O(1) (i.e. constant) memory?
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 * 
 * 思路：使用弗洛伊德判圈算法，即快慢指針，
 *      畫圖輔助，
 *     1若快慢指針能相遇，則存在環，
 *     2然後設置2個新指針，一個從頭節點開始，另一個指針從相遇節點開始，當兩指針相遇處即為環的入口節點 
 * 再
 * 
 * 使用快慢指针检测链表中循环的原理是一种常见且高效的算法，也被称为Floyd's Tortoise and Hare Algorithm（弗洛伊德的乌龟和兔子算法）。这种算法通过使用两个指针，一个快指针和一个慢指针，来检测链表中是否存在循环，并找到循环的起始节点。

快慢指针的原理是，快指针每次移动两步，慢指针每次移动一步。如果链表中存在循环，快指针最终会追上慢指针。这是因为快指针的速度是慢指针的两倍，所以在循环中，快指针会绕圈追赶慢指针，最终相遇。

当快慢指针相遇时，我们可以得出以下结论：

链表中存在循环。
通过数学推导，可以确定链表中循环的起始节点。
具体来说，假设链表头到循环起始节点的距离为A，循环起始节点到快慢指针相遇点的距离为B。根据快慢指针的移动规律，可以得出以下等式：快指针走过的距离 = 2 * 慢指针走过的距离。根据这个等式，可以推导出A = C，即链表头到循环起始节点的距离等于相遇点到循环起始节点的距离。

因此，当快慢指针相遇后，我们可以设置一个新的指针从链表头开始，与相遇点的指针同时移动，它们相遇的节点就是循环的起始节点。

通过这种方法，我们可以高效地检测链表中的循环，并找到循环的起始节点，而且算法的时间复杂度为O(N)，空间复杂度为O(1)。
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        //需要确保快指针與快指針的下一个节点不为空，以避免空指针异常
        while (fast != null && fast.next !=null) {
            fast = fast.next.next;
            slow = slow.next;
            //相遇則存在環
            if(slow == fast){
                ListNode index1 = fast;
                //在检测到快慢指针相遇时，需要重新设置一个新的指针从头节点开始，以找到循环的入口点
                ListNode index2 = head;
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                //index1與index2在環的入口處相遇
                return index1;
            }
        }
        return null;    
    }
}
// @lc code=end

