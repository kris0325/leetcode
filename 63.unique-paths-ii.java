/*
 * @lc app=leetcode id=63 lang=java
 *
 * [63] Unique Paths II
 *
 * https://leetcode.com/problems/unique-paths-ii/description/
 *
 * algorithms
 * Medium (41.53%)
 * Likes:    8687
 * Dislikes: 508
 * Total Accepted:    953.9K
 * Total Submissions: 2.3M
 * Testcase Example:  '[[0,0,0],[0,1,0],[0,0,0]]'
 *
 * You are given an m x n integer array grid. There is a robot initially
 * located at the top-left corner (i.e., grid[0][0]). The robot tries to move
 * to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only
 * move either down or right at any point in time.
 * 
 * An obstacle and space are marked as 1 or 0 respectively in grid. A path that
 * the robot takes cannot include any square that is an obstacle.
 * 
 * Return the number of possible unique paths that the robot can take to reach
 * the bottom-right corner.
 * 
 * The testcases are generated so that the answer will be less than or equal to
 * 2 * 10^9.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: obstacleGrid = [[0,1],[0,0]]
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] is 0 or 1.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //1.dp[i][j] 达到db[i][j]坐标的路径数
        //2. recursive math dp[i][j]  = dp[i-1][j]  +dp[i][j-1] 
        //3. itnitial if(obstacleGrid[] != 1) dp[0][j] = 1 dp[i][0] = 1
        //4. print
        //5. return 结果

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if(obstacleGrid[0][0] ==1 && obstacleGrid[m-1][n-1] ==1){
            return 0;
        }
        int[][] dp = new int[m][n];
        for(int i=0; i< m && obstacleGrid[i][0] == 0;i++ ){
            dp[i][0]=1;
        }
        for(int j=0; j< n && obstacleGrid[0][j] == 0;j++ ){
            dp[0][j]=1;
        }
        for(int i= 1; i< m;i++ ){
            for(int j=1; j< n;j++ ){
                //如果当前坐标obstacleGrid[i][j]上有障碍物，则所有路径都无法达到单前坐标，所以dp[i][j] =0
                dp[i][j] = obstacleGrid[i][j] == 1?
                 0 : dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
        
    }
}
// @lc code=end

