  /**
You have n binary tree nodes numbered from 0 to n - 1 where node i has two 
children leftChild[i] and rightChild[i], return true if and only if all the given 
nodes form exactly one valid binary tree. 

 If node i has no left child then leftChild[i] will equal -1, similarly for the 
right child. 

 Note that the nodes have no values and that we only use the node numbers in 
this problem. 

 
 Example 1: 
 
 
Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
Output: true
 

 Example 2: 
 
 
Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
Output: false
 

 Example 3: 
 
 
Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
Output: false
 

 
 Constraints: 

 
 n == leftChild.length == rightChild.length 
 1 <= n <= 10⁴ 
 -1 <= leftChild[i], rightChild[i] <= n - 1 
 

 Related Topics Tree Depth-First Search Breadth-First Search Union Find Graph 
Binary Tree 👍 2138 👎 510

*/
       
/*
 2024-09-10 20:06:59
*/

class ValidateBinaryTreeNodes {
      public static void main(String[] args) {
           Solution solution = new ValidateBinaryTreeNodes().new Solution();
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
class Solution {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}