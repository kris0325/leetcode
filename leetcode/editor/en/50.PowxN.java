/**
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xⁿ).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2⁻² = 1/2² = 1/4 = 0.25
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * -100.0 < x < 100.0
 * -2³¹ <= n <= 2³¹-1
 * n is an integer.
 * Either x is not zero or n > 0.
 * -10⁴ <= xⁿ <= 10⁴
 * <p>
 * <p>
 * Related Topics Math Recursion 👍 9947 👎 9665
 */
       
/*
 2024-09-21 22:46:36
*/

class PowxN {
    public static void main(String[] args) {
        Solution solution = new PowxN().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double myPow(double x, int n) {
            //先處理edge case
            if (x == 0 || x == 1) {
                return x;
            }
            if (n < 0) {
                return 1 / pow(x, -n);
            }
            return pow(x, n);

        }

        public double pow(double x, int n) {
            if (n == 0) {
                return 1;
            }
            //先求一半 n/2
            double half = pow(x, n / 2);
            if (n % 2 == 0) {
                //n為偶數
                return half * half;
            } else {
                //n為奇數，則先取一個x, 剩下為偶數
                return x * half * half;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}