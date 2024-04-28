/*
 * @lc app=leetcode.cn id=746 lang=java
 *
 * [746] 使用最小花费爬楼梯
 *
 * https://leetcode.cn/problems/min-cost-climbing-stairs/description/
 *
 * algorithms
 * Easy (66.29%)
 * Likes:    1455
 * Dislikes: 0
 * Total Accepted:    416K
 * Total Submissions: 626.5K
 * Testcase Example:  '[10,15,20]'
 *
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * 
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * 
 * 请你计算并返回达到楼梯顶部的最低花费。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：cost = [10,15,20]
 * 输出：15
 * 解释：你将从下标为 1 的台阶开始。
 * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
 * 总花费为 15 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：cost = [1,100,1,1,1,100,1,1,100,1]
 * 输出：6
 * 解释：你将从下标为 0 的台阶开始。
 * - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达楼梯顶部。
 * 总花费为 6 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 * 
 * 
 * 
 * 思路：动态规划，
 * 先1.定义dp数组
 * 2.初始化dp数组的初始值，
 * 3.写出递推公式
 * 4.选择递推顺序
 * 5.打印递推数组
 */

// @lc code=start
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        //定义dp数组
        int[] dp = new int [cost.length +1];
        //初始化dp初始值，可以下标为0或1的台阶开始，因此初始值均为0
        dp[0]= 0;
        dp[1]= 0;
        //1.写出递推公式dp[i] = Math.min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2]);
        //2.选择递推顺序，进行循环递推求解
        for(int i = 2; i<= cost.length; i++){
            dp[i] = Math.min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2]);
         //打印递推值
        //  System.out.println(dp[i]);
        }
       
        return dp[cost.length];
    }
}
// @lc code=end

