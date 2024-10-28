import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
You are given the head of a linked list with n nodes. 

 For each node in the list, find the value of the next greater node. That is, 
for each node, find the value of the first node that is next to it and has a 
strictly larger value than it. 

 Return an integer array answer where answer[i] is the value of the next 
greater node of the iᵗʰ node (1-indexed). If the iᵗʰ node does not have a next greater 
node, set answer[i] = 0. 

 
 Example 1: 
 
 
Input: head = [2,1,5]
Output: [5,5,0]
 

 Example 2: 
 
 
Input: head = [2,7,4,3,5]
Output: [7,0,5,5,0]
 

 
 Constraints: 

 
 The number of nodes in the list is n. 
 1 <= n <= 10⁴ 
 1 <= Node.val <= 10⁹ 
 

 Related Topics Array Linked List Stack Monotonic Stack 👍 3321 👎 120

*/
       
/*
 2024-09-24 15:19:02
*/

class NextGreaterNodeInLinkedList {
      public static void main(String[] args) {
           Solution solution = new NextGreaterNodeInLinkedList().new Solution();
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
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int n = list.size();
        int[]res = new int[n];
        Stack<Integer> stack = new Stack<>();
        //從後往前遍歷
        for(int i = n-1; i>=0; i--){
            while (!stack.isEmpty() && stack.peek()<= list.get(i)){
                stack.pop();
            }
            res[i] = stack.isEmpty()
                    ? 0: stack.peek();
            stack.push(list.get(i));
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}