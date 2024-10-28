  /**
Given a m x n grid filled with non-negative numbers, find a path from top left 
to bottom right, which minimizes the sum of all numbers along its path. 

 Note: You can only move either down or right at any point in time. 

 
 Example 1: 
 
 
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 

 Example 2: 

 
Input: grid = [[1,2,3],[4,5,6]]
Output: 12
 

 
 Constraints: 

 
 m == grid.length 
 n == grid[i].length 
 1 <= m, n <= 200 
 0 <= grid[i][j] <= 200 
 

 Related Topics Array Dynamic Programming Matrix 👍 12471 👎 170

*/
       
/*
 2024-08-01 18:00:56
 Minimum Path Sum
Category	Difficulty	Likes	Dislikes
algorithms	Medium (63.83%)	12471	170
Tags
array | dynamic-programming

Companies
Unknown
*/

class MinimumPathSum {
      public static void main(String[] args) {
           Solution solution = new MinimumPathSum().new Solution();
      }
        
      //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minPathSum(int[][] grid) {
        //注意：本題和 62. Unique Paths 62.不同路徑的區別
        //定义dp數組：dp[i][j]表示到達grid[i][j]路徑總和最小
        int [][] dp = new int[grid.length][grid[0].length];
        //初始化  dp[0][0] ，dp[i][0]，dp[0][j]
        //因為根據遞推公式，當前狀態依賴上一個狀態（上方，左方），dp[i][-1],dp[-1][j]這樣的case無法表達，
        // 即j，i從1開始遞推的，所以j = 0 ||i = 0 的情況 必須初始化
        dp[0][0] = grid[0][0];
        //
        //j = 0 第一列初始化
        for (int i=1; i<grid.length; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        //i = 0 第一行初始化
        for (int j=1; j<grid[0].length; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

        //遍历顺序从上到下，从做到右
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                //递推公式：到達grid[i][j]路徑總和最小依賴上方，左方取最小值，+ grid[i][j]
                dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]) + grid[i][j];
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}