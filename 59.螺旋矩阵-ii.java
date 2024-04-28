/*
 * @lc app=leetcode.cn id=59 lang=java
 *
 * [59] 螺旋矩阵 II
 *
 * https://leetcode.cn/problems/spiral-matrix-ii/description/
 *
 * algorithms
 * Medium (71.30%)
 * Likes:    1271
 * Dislikes: 0
 * Total Accepted:    407.8K
 * Total Submissions: 573.1K
 * Testcase Example:  '3'
 *
 * 给你一个正整数 n ，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 1
 * 输出：[[1]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 
 * 
 * 思路：初始化一个n*n的矩阵matrix，然后模拟整个由内向外螺旋填入数字的过程：
 * 1.定义当前左右上下边界l,r,t,b。 
 *   计数赋值的初始值num=1,迭代终止值为 target = n*n;
 * 2.当num < target时，始终按照从左到右，从上到下，从右到左，从下到上填入顺序循环，
 *   每次填入后执行：
 *    i.执行num +=1 ,即得到下一个待填入数值
 *    ii.更新边界：例如从左到右填入后，上边界需要 t+=1，相当于上边界向内缩1
 * 3.使用 num < target, 而不是l<r||t<b作为迭代条件，是为解决当n为奇数时，
 *   矩阵中心数字无法在迭代过程中被填充的问题。
 * 4.最终返回matrix即可。
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[][] generateMatrix(int n) {
        int [][] matrix = new int[n][n];
        int left =0, right = n-1, top = 0, bottom = n-1;
        int target = n * n, num = 1;

        while (num <= target ) {
            //从左到右遍历，行不变为t
            for(int l = left; l <=right; l++){
                matrix[top][l] = num++;
            } 

            //准备进入下一次循环，从上到下遍历，所以t加一，
            top++;
            //列不变为r
            for(int t = top; t<=bottom; t++){
                matrix[t][right] = num++;
            } 

            //准备下次循环，所以从右到左遍历，所以r减一，
            right--;
            //行不变为b
            for(int r = right; r>=left;r--){
                matrix[bottom][r] = num++;
            } 

            //准备进入下一次循环，从下到上遍历，所以b减一
            bottom--;
            //列不变为l
            for(int b = bottom; b>=top; b--){
                matrix[b][left] = num++;
            } 

            //准备进入下一次循环，从左到右遍历，所以l加一
            left++;
        }
        return matrix;
    }
}
// @lc code=end

