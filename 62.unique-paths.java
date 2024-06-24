/*
 * @lc app=leetcode id=62 lang=java
 *
 * [62] Unique Paths
 *
 * https://leetcode.com/problems/unique-paths/description/
 *
 * algorithms
 * Medium (64.26%)
 * Likes:    16533
 * Dislikes: 440
 * Total Accepted:    1.9M
 * Total Submissions: 2.9M
 * Testcase Example:  '3\n7'
 *
 * There is a robot on an m x n grid. The robot is initially located at the
 * top-left corner (i.e., grid[0][0]). The robot tries to move to the
 * bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move
 * either down or right at any point in time.
 * 
 * Given the two integers m and n, return the number of possible unique paths
 * that the robot can take to reach the bottom-right corner.
 * 
 * The test cases are generated so that the answer will be less than or equal
 * to 2 * 10^9.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: m = 3, n = 7
 * Output: 28
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach
 * the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= m, n <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    //1.define dp[i][j]数组 为到每个坐标的可能的路径
    //2.推导 recursion formula, dp[m][n] = dp左边+dp上边
    //3.initial dp[i][j]，第一行，第一列初始化：dp[i][0] = 1;dp[0][j] = 1;
    //4.traverse order遍历顺序
    //5.推导结果result 
    public int uniquePaths(int m, int n) {
        int[][]dp = new int[m][n];
        //第一列，首先dp[i][0]一定都是1，因为从(0, 0)的位置到(i, 0)的路径只有一条，
        //同理，第一行，dp[0][j]一定都是1，因为从(0, 0)的位置到(0, j)的路径只有一条
        for(int i= 0; i<m; i++){dp[i][0] = 1;}
        for(int j= 0; j<n;j++){dp[0][j] = 1;}
        
        for(int i= 1; i<m; i++){
            for(int j= 1; j<n;j++){
               
               dp[i][j] = dp[i-1][j] +dp[i][j-1];
            }
        }
       
        return dp[m-1][n-1];
    }
}
// @lc code=end

