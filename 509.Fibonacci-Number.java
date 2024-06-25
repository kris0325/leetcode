/*
 * @lc app=leetcode.cn id=509 lang=java
 *
 * [509] 斐波那契数
 *
 * https://leetcode.cn/problems/fibonacci-number/description/
 *
 * algorithms
 * Easy (65.98%)
 * Likes:    745
 * Dislikes: 0
 * Total Accepted:    674.9K
 * Total Submissions: 1M
 * Testcase Example:  '2'
 *
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * 
 * 
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 
 * 
 * 给定 n ，请计算 F(n) 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：n = 4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 <= n <= 30
 * 
 *  * 思路：动态规划，
 * 1.定义dp数组
 * 2.初始化dp数组的初始值，
 * 3.写出递推公式
 * 4.选择递推顺序
 * 5.打印递推数组
 * 
 * 注意：n<2的边界条件需要额外处理
 */

// @lc code=start
class Solution {
    public int fib(int n) {
        //注意处理边界条件
        if(n<2) return n;
        int[] fib = new int[n+1];
        fib[0]= 0;
        fib[1]= 1;
        for(int i =2; i <=n ;i++){
            fib[i] = fib[i-1]+fib[i-2];
            // System.out.println("fib[n]:"+fib[n]);
        }
        return fib[n];
    }
}
// @lc code=end

