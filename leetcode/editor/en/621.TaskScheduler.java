  /**
You are given an array of CPU tasks, each represented by letters A to Z, and a 
cooling time, n. Each cycle or interval allows the completion of one task. Tasks 
can be completed in any order, but there's a constraint: identical tasks must 
be separated by at least n intervals due to cooling time. 

 Return the minimum number of intervals required to complete all tasks. 

 
 Example 1: 

 
 Input: tasks = ["A","A","A","B","B","B"], n = 2 
 

 Output: 8 

 Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> 
B. 

 After completing task A, you must wait two cycles before doing A again. The 
same applies to task B. In the 3ʳᵈ interval, neither A nor B can be done, so you 
idle. By the 4ᵗʰ cycle, you can do A again as 2 intervals have passed. 

 Example 2: 

 
 Input: tasks = ["A","C","A","B","D","B"], n = 1 
 

 Output: 6 

 Explanation: A possible sequence is: A -> B -> C -> D -> A -> B. 

 With a cooling interval of 1, you can repeat a task after just one other task. 


 Example 3: 

 
 Input: tasks = ["A","A","A", "B","B","B"], n = 3 
 

 Output: 10 

 Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -
> idle -> A -> B. 

 There are only two types of tasks, A and B, which need to be separated by 3 
intervals. This leads to idling twice between repetitions of these tasks. 

 
 Constraints: 

 
 1 <= tasks.length <= 10⁴ 
 tasks[i] is an uppercase English letter. 
 0 <= n <= 100 
 

 Related Topics Array Hash Table Greedy Sorting Heap (Priority Queue) Counting ?
? 10600 👎 2088

*/
       
/*
 2024-09-18 22:43:00
*/

class TaskScheduler {
      public static void main(String[] args) {
           Solution solution = new TaskScheduler().new Solution();
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
    public int leastInterval(char[] tasks, int n) {
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}