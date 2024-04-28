/*
 * @lc app=leetcode id=160 lang=java
 *
 * [160] Intersection of Two Linked Lists
 *
 * https://leetcode.com/problems/intersection-of-two-linked-lists/description/
 *
 * algorithms
 * Easy (57.40%)
 * Likes:    14737
 * Dislikes: 1327
 * Total Accepted:    1.5M
 * Total Submissions: 2.7M
 * Testcase Example:  '8\n[4,1,8,4,5]\n[5,6,1,8,4,5]\n2\n3'
 *
 * Given the heads of two singly linked-lists headA and headB, return the node
 * at which the two lists intersect. If the two linked lists have no
 * intersection at all, return null.
 * 
 * For example, the following two linked lists begin to intersect at node c1:
 * 
 * The test cases are generated such that there are no cycles anywhere in the
 * entire linked structure.
 * 
 * Note that the linked lists must retain their original structure after the
 * function returns.
 * 
 * Custom Judge:
 * 
 * The inputs to the judge are given as follows (your program is not given
 * these inputs):
 * 
 * 
 * intersectVal - The value of the node where the intersection occurs. This is
 * 0 if there is no intersected node.
 * listA - The first linked list.
 * listB - The second linked list.
 * skipA - The number of nodes to skip ahead in listA (starting from the head)
 * to get to the intersected node.
 * skipB - The number of nodes to skip ahead in listB (starting from the head)
 * to get to the intersected node.
 * 
 * 
 * The judge will then create the linked structure based on these inputs and
 * pass the two heads, headA and headB to your program. If you correctly return
 * the intersected node, then your solution will be accepted.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA =
 * 2, skipB = 3
 * Output: Intersected at '8'
 * Explanation: The intersected node's value is 8 (note that this must not be 0
 * if the two lists intersect).
 * From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as
 * [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are
 * 3 nodes before the intersected node in B.
 * - Note that the intersected node's value is not 1 because the nodes with
 * value 1 in A and B (2^nd node in A and 3^rd node in B) are different node
 * references. In other words, they point to two different locations in memory,
 * while the nodes with value 8 in A and B (3^rd node in A and 4^th node in B)
 * point to the same location in memory.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3,
 * skipB = 1
 * Output: Intersected at '2'
 * Explanation: The intersected node's value is 2 (note that this must not be 0
 * if the two lists intersect).
 * From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as
 * [3,2,4]. There are 3 nodes before the intersected node in A; There are 1
 * node before the intersected node in B.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB =
 * 2
 * Output: No intersection
 * Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it
 * reads as [1,5]. Since the two lists do not intersect, intersectVal must be
 * 0, while skipA and skipB can be arbitrary values.
 * Explanation: The two lists do not intersect, so return null.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes of listA is in the m.
 * The number of nodes of listB is in the n.
 * 1 <= m, n <= 3 * 10^4
 * 1 <= Node.val <= 10^5
 * 0 <= skipA < m
 * 0 <= skipB < n
 * intersectVal is 0 if listA and listB do not intersect.
 * intersectVal == listA[skipA] == listB[skipB] if listA and listB
 * intersect.
 * 
 * 
 * 
 * Follow up: Could you write a solution that runs in O(m + n) time and use
 * only O(1) memory?
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 * 
 * 思路1：两个链表若有交点，从链表末尾向前看，必有一段公共节点，因此，
 *      先求出2个链表长度差gap, 长链表指针先走gap步长，直到长短两个链表位置对齐，
 *      同时移动长短链表的指针，直到两个指针相等，return，
 *      否则return null
 * 
 */
// public class Solution {
//     public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//         int lenA =0;
//         int lenB =0;
//         ListNode nodeA = headA;
//         ListNode nodeB = headB;
//         while (nodeA != null) {
//             nodeA = nodeA.next;
//             lenA +=1;
//         }
//         while (nodeB != null) {
//             nodeB = nodeB.next;
//             lenB +=1;
//         }
//         nodeA = headA;
//         nodeB = headB;
//         if(lenB > lenA){
//             int tmplen = lenA;
//             lenA = lenB;
//             lenB = tmplen;
//             ListNode nodetmp = nodeA;
//             nodeA = nodeB;
//             nodeB = nodetmp;
//         }
//         int gap = lenA -lenB;

//         while (gap-- > 0) {
//             nodeA = nodeA.next;
//         }
//         while (nodeA != null) {
//             if(nodeA == nodeB){
//                 return nodeA;
//             }
//             nodeA = nodeA.next;
//             nodeB = nodeB.next;    
//         }
//         return null;
//     }
// }

/* 思路2：合并链表，同步移动指针寻找公共节点
        本质上和思路1是一样的，就是指向短链表的快指针先走，
        直到走完（两个链表长度之差）gap步长后， 这时快慢指针同步移动，再寻找公共节点
 *      
 * 
 */ 

 public class Solution {
 public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode pA = headA;
    ListNode pB = headB;
    while(pA != pB){
        if(pA == null){
            //指针pA走一步，直到走到a链表末尾，则转到b链表
            pA = headB;
        } else {
            pA = pA.next;
        }

        //指针pB走一步，直到走到B链表末尾，则转到A链表
        if(pB == null){
            pB = headA;
        } else{
            pB = pB.next;
        }
    }
    return pA;   
} 
}   

// @lc code=end

