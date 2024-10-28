/**
 * An integer has monotone increasing digits if and only if each pair of adjacent
 * digits x and y satisfy x <= y.
 * <p>
 * Given an integer n, return the largest number that is less than or equal to n
 * with monotone increasing digits.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 10
 * Output: 9
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: n = 1234
 * Output: 1234
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: n = 332
 * Output: 299
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 0 <= n <= 10⁹
 * <p>
 * <p>
 * Related Topics Math Greedy 👍 1321 👎 105
 */
       
/*
 2024-07-31 00:08:32
 Monotone Increasing Digits
Category	Difficulty	Likes	Dislikes
algorithms	Medium (48.03%)	1321	105
Tags
greedy

Companies
Unknown


*/

class MonotoneIncreasingDigits {
    public static void main(String[] args) {
        Solution solution = new MonotoneIncreasingDigits().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //greedy question: 一旦出现strNum[i - 1] > strNum[i]的情况（非单调递增），根據 十進制，前一位減1時，需要將後面所有位子的數字變為9，可以實現局部最優
        //求單調遞增，那麼從後往前遍歷，實現全局最優
        //想到了贪心，还要考虑遍历顺序，只有从后向前遍历才能重复利用上次比较的结果。

        //题目要求小于等于N的最大单调递增的整数，那么拿一个两位的数字来举例。
        //例如：98，一旦出现strNum[i - 1] > strNum[i]的情况（非单调递增），首先想让strNum[i - 1]--，然后strNum[i]给为9(局部貪心)，这样这个整数就是89，即小于98的最大的单调递增整数。
        //这一点如果想清楚了，这道题就好办了。
        //此时是从前向后遍历还是从后向前遍历呢？
        //从前向后遍历的话，遇到strNum[i - 1] > strNum[i]的情况，让strNum[i - 1]减一，但此时如果strNum[i - 1]减一了，可能又小于strNum[i - 2]。
        //这么说有点抽象，举个例子，数字：332，从前向后遍历的话，那么就把变成了329，此时2又小于了第一位的3了，真正的结果应该是299。
        //那么从后向前遍历，就可以重复利用上次比较得出的结果了，从后向前遍历332的数值变化为：332 -> 329 -> 299
        //确定了遍历顺序之后，那么此时局部最优就可以推出全局，找不出反例，试试贪心。
        public int monotoneIncreasingDigits(int n) {
            char[] chars = String.valueOf(n).toCharArray();
            //标记start索引后的所有元素都需要重置为'9'
            int start = chars.length;
            //從後往前遍歷
            for (int i = chars.length - 1; i > 0; i--) {
                //不滿足遞增性質時，chars[i]--，需要標記chars[i+1]設置為‘9’
                if (chars[i - 1] > chars[i]) {
                    start = i;
                    chars[i - 1]--;
                }
            }
            for (int i = start; i < chars.length; i++) {
                chars[i] = '9';
            }
            return Integer.parseInt(String.valueOf(chars));
        }
    }

    class Solution1 {
        //暴力解法：會超時 Time Limit Exceeded
        public int monotoneIncreasingDigits(int n) {
            for (int i = n; i >= 0; i--) {
                if (isMonotoneIncreasing(i)) {
                    return i;
                }
            }
            return 0;
        }

        //判斷一個數字的各個位上的數是否遞增
        public boolean isMonotoneIncreasing(int num) {
            int max = 10;
            while (num > 0) {
                //每次對10取模，即求個位數（最左邊位子的數）
                int t = num % 10;
                if (t <= max) {
                    max = t;
                } else {
                    return false;
                }
                //除去最最左邊的一位數，繼續迭代
                num /= 10;
            }
            return true;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}