/*
 * @lc app=leetcode id=19 lang=java
 *
 * [19] Remove Nth Node From End of List
 *
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
 *
 * algorithms
 * Medium (45.25%)
 * Likes:    18583
 * Dislikes: 785
 * Total Accepted:    2.7M
 * Total Submissions: 6M
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * Given the head of a linked list, remove the n^th node from the end of the
 * list and return its head.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: head = [1], n = 1
 * Output: []
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: head = [1,2], n = 1
 * Output: [1]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * 
 * 
 * 
 * Follow up: Could you do this in one pass?
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 * 
 * 思路：快慢指针
 *      快指针先走步长n，然后慢指针后走，当快指针走到末尾，
 *      慢指针即指向倒数第n个节点the n^th node from the end of the.
 *      注意：邊界條件
 *        
 *      solution優化：使用虛擬头節點dumyNode更方便，可以覆蓋邊界條件，不用特殊考慮
 * 
 *   
 * 
 * 
 * 
 *       
 * list，remove即可
 */
class Solution1 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        //specil case: only one node
        if(slow.next == null){
            return null;
        }
        for(int i = 0; i <n; i++){
            fast = fast.next;
        }
        //specil case: when n == size of the list, the fast pointer is null, it means we need remove the head node
        if(fast == null){
            return head.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        //At this point, the slow pointer is n nodes behind the fast pointer
        //, which means it's pointing to the node just before the node we want to remove.
       
        //delete the n^th node from the end of the list
        slow.next = slow.next.next;
        return head;
    }
}

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //新建一个虚拟头节点指向head
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        //快慢指针指向虚拟头节点
        ListNode fastIndex = dummyNode;
        ListNode slowIndex = dummyNode;

        // 只要快慢指针相差 n 个结点即可
        for (int i = 0; i <= n; i++) {
            fastIndex = fastIndex.next;
        }

        while (fastIndex != null) {
            fastIndex = fastIndex.next;
            slowIndex = slowIndex.next;
        }

        // 此时 slowIndex 的位置就是待删除元素的前一个位置。
        // 具体情况可自己画一个链表长度为 3 的图来模拟代码来理解
        // 检查 slowIndex.next 是否为 null，以避免空指针异常
        if (slowIndex.next != null) {
            slowIndex.next = slowIndex.next.next;
        }
        return dummyNode.next;
    }
}
// @lc code=end

