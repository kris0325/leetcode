  /**
Given the head of a linked list, return the node where the cycle begins. If 
there is no cycle, return null. 

 There is a cycle in a linked list if there is some node in the list that can 
be reached again by continuously following the next pointer. Internally, pos is 
used to denote the index of the node that tail's next pointer is connected to (0-
indexed). It is -1 if there is no cycle. Note that pos is not passed as a 
parameter. 

 Do not modify the linked list. 

 
 Example 1: 
 
 
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the 
second node.
 

 Example 2: 
 
 
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the 
first node.
 

 Example 3: 
 
 
Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
 

 
 Constraints: 

 
 The number of the nodes in the list is in the range [0, 10⁴]. 
 -10⁵ <= Node.val <= 10⁵ 
 pos is -1 or a valid index in the linked-list. 
 

 
 Follow up: Can you solve it using O(1) (i.e. constant) memory? 

 Related Topics Hash Table Linked List Two Pointers 👍 13718 👎 975

*/
       
/*
 2024-09-14 00:02:16
*/
  // 定义单链表节点类
  class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }

class LinkedListCycleIi {


      public static void main(String[] args) {
           Solution solution = new LinkedListCycleIi().new Solution();
          // 创建带环链表 [1, 2]，其中 2 指向 1
          ListNode head = new ListNode(1);
          ListNode second = new ListNode(2);
          head.next = second;
          second.next = head; // 这里制造了一个环
          ListNode cycleNode = solution.detectCycle(head);
          if (cycleNode != null) {
              System.out.println("Cycle detected at node with value: " + cycleNode.val);
          } else {
              System.out.println("No cycle detected.");
          }

      }


      
      public class TreeNode {
        int val; TreeNode left; TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
       
      //leetcode submit region begin(Prohibit modification and deletion)
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
 */

public class Solution1 {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {// 有环
                ListNode index1 = fast;
                ListNode index2 = head;
                // 两个指针，从头结点和相遇结点，各走一步，直到相遇，相遇点即为环入口
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }
}

public class Solution2 {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null; // 如果链表为空或只有一个节点，无环
        }

        // 初始化慢指针和快指针
        ListNode slow = head;
        ListNode fast = head;

        // 第一阶段：寻找是否存在环
        while (fast != null && fast.next != null && fast.next.next != null ) {
            slow = slow.next;        // 慢指针每次走两步
            fast = fast.next.next.next;   // 快指针每次走五步

            if (slow == fast) {     // 找到相遇点，表示有环
                // 第二阶段：寻找环的入口
                slow = head;        // 将慢指针移回起点
                while (slow != fast) {
                    slow = slow.next;  // 两个指针每次都走一步
                    fast = fast.next;
                }
                return slow;  // 相遇点就是环的入口
            }
        }

        return null; // 如果没有环，返回 null
    }
}
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            if (head == null || head.next == null) {
                return null;
            }

            // 第一步：检测是否存在环
            ListNode slow = head;
            ListNode fast = head;
            boolean hasCycle = false;

            while (fast != null && fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next.next;  // 快指针每次走3步
                if (slow == fast) {
                    hasCycle = true;
                    break;
                }
            }

            if (!hasCycle) {
                return null;
            }

            // 第二步：找到环的长度
            int cycleLength = 1;
            fast = slow.next;
            while (fast != slow) {
                fast = fast.next;
                cycleLength++;
            }

            // 第三步：找到环的入口
            slow = head;
            fast = head;
            for (int i = 0; i < cycleLength; i++) {
                fast = fast.next;
            }

            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }

            return slow;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}