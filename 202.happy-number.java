/*
 * @lc app=leetcode id=202 lang=java
 *
 * [202] Happy Number
 *
 * https://leetcode.com/problems/happy-number/description/
 *
 * algorithms
 * Easy (56.07%)
 * Likes:    10182
 * Dislikes: 1411
 * Total Accepted:    1.5M
 * Total Submissions: 2.6M
 * Testcase Example:  '19'
 *
 * Write an algorithm to determine if a number n is happy.
 * 
 * A happy number is a number defined by the following process:
 * 
 * 
 * Starting with any positive integer, replace the number by the sum of the
 * squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it
 * loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * 
 * 
 * Return true if n is a happy number, and false if not.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 2^31 - 1
 * 
 * 
 */

 /*
  * 思路：哈希表
         or it loops endlessly in a cycle which does not include 1,
         即表明n is happy时, 無限循環，那麼可以推斷，只要紀錄每次循環的sum，總有sum會發生重複，
         因此可以使用hashset收集每次循環的sum，然後判斷是否存在重複的值
  * 
 */

// @lc code=start

import java.util.HashSet;

class Solution {
    public boolean isHappy(int n) {
        HashSet<Integer> sumsSet = new HashSet<>();

        // ishappy n == 1 ,  is not happy, sum會重複即 sumsSet.contains(n)，跳出
        while (n != 1 && !sumsSet.contains(n)) {
            sumsSet.add(n);
            n = getSum(n);
        }
        return n == 1;
    }

    int getSum(int n){
        int sum = 0;
        while (n > 0) {
          //先獲取最左邊低位數值
         int low = n % 10;
         sum += low *low;
         //去掉最左邊低位數值
         n = n / 10;
        }
        return sum;
    }
}
// @lc code=end

