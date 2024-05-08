/*
 * @lc app=leetcode id=24 lang=java
 *
 * [24] Swap Nodes in Pairs
 *
 * https://leetcode.com/problems/swap-nodes-in-pairs/description/
 *
 * algorithms
 * Medium (64.32%)
 * Likes:    11816
 * Dislikes: 436
 * Total Accepted:    1.4M
 * Total Submissions: 2.1M
 * Testcase Example:  '[1,2,3,4]'
 *
 * Given a linked list, swap every two adjacent nodes and return its head. You
 * must solve the problem without modifying the values in the list's nodes
 * (i.e., only nodes themselves may be changed.)
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: head = []
 * Output: []
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: head = [1]
 * Output: [1]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the list is in the range [0, 100].
 * 0 <= Node.val <= 100
 * 
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
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        //设置一个虚拟头节点
        ListNode dumyHead = new ListNode(0);
        //将虚拟头结点指向head，这样方便后面操作
        dumyHead.next = head;
        ListNode cur = dumyHead;
        //临时节点，保存两个节点之中的第一个节点
        ListNode firstNode ;
        // 临时节点，保存两个节点之中的第二个节点
        ListNode secondeNode ;
        while (cur.next != null && cur.next.next != null) {
            // tmpNode = cur.next.next.next;
            firstNode = cur.next;
            secondeNode = cur.next.next;
            //1 cur指针next指向第2个节点
            cur.next = secondeNode;
            //2 第一个节点指针next指向第三个节点
            firstNode.next = secondeNode.next;
            //3 第二个节点指针next指向第一个节点
            secondeNode.next = firstNode;
            //移动cur指针，准备下一轮交换
            cur = cur.next.next;
        }
        return dumyHead.next; 
        
    }
}
// @lc code=end

