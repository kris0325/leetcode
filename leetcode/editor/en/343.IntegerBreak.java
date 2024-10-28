/**
 * Given an integer n, break it into the sum of k positive integers, where k >= 2,
 * and maximize the product of those integers.
 * <p>
 * Return the maximum product you can get.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 2
 * Output: 1
 * Explanation: 2 = 1 + 1, 1 × 1 = 1.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: n = 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 2 <= n <= 58
 * <p>
 * <p>
 * Related Topics Math Dynamic Programming 👍 5093 👎 447
 */
       
/*
 2024-07-03 22:19:36

 Integer Break
Category	Difficulty	Likes	Dislikes
algorithms	Medium (60.32%)	5093	447
Tags
math | dynamic-programming

Companies
Unknown

Given an integer n, break it into the sum of k positive integers, where k >= 2,
 and maximize the product of those integers.

Return the maximum product you can get.
*/

class IntegerBreak {
    public static void main(String[] args) {
        Solution solution = new IntegerBreak().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        //dp : 时间复杂度 0(n2) , 空间复杂度o(n)
        // 数学结论是：一个数n,拆成m个接近相同的数，可以得到最大乘积值
        // 定义dp[i]数组为 整数i可拆为的最大的乘积
        //用最小数j去试着遍历i, 那么i拆成 j * (i-j),即可以理解为拆成2个数j 和 i-j,
        // 同时也可以用dp数组表示为 j*dp[i-j]， 即最小尝试单元数j(无需再拆分)  和对 i-j的拆分 dp[i-j]
        // 那么可得到dp数组的的递推公式 dp[i] = max(dp[i], max(j * (i-j), j*dp[i-j]));
        // 注意：需对 用j遍历i的内层for循环得到的多个dp[i] 也进行max比较，从而求最大值
        public int integerBreak(int n) {
            int[] dp = new int[n + 1];
            dp[2] = 1;
            for (int i = 3; i <= n; i++) {
                //由题目条件可推出，dp[0],dp[1]无法拆分，没有实际意义
                //dp[2] =1, 那么j = 3 - 2,所以j从1开始，由前面拆分最大乘积的数学结论，那么j的边界条件可以优化为j <= i/2
//                for (int j = 1; j < i; j++) {}
                //对于i的最大值，内层for循环会得出多个dp[i],所以dp[i]也需要参与和Math.max(j, dp[i - j])比较来求最值
                for (int j = 1; j <= i / 2; j++) {
                    dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
                }
            }
            return dp[n];
        }
    }

    class Solution {
        // 数学结论是：一个数n,拆成m个接近相同的数，可以得到最大乘积值
        // greedy algorithm: 时间复杂度 0(n) , 空间复杂度o(1)
        // 根据上面数学结论 局部以10为例子，334为最大，
        // 那么局部最优推导整体最优， 对整数n 则按照每次拆成m个3，如果剩下的为4，则保留4，然后相乘
        public int integerBreak(int n) {
            if (n == 2) {
                return 1;
            }
            if (n == 3) {
                return 2;
            }
            int maxProduct = 1;
            while (n > 4) {
                maxProduct *= 3;
                n -= 3;
            }
            maxProduct *= n;
            return maxProduct;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}