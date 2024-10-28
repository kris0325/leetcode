
/*
 2024-07-15 16:03:53

Ones and Zeroes
Category	Difficulty	Likes	Dislikes
algorithms	Medium (47.56%)	5417	457
Tags
dynamic-programming

Companies
google

You are given an array of binary strings strs and two integers m and n.

Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.

A set x is a subset of a set y if all elements of x are also elements of y.

Example 1:

Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
Output: 4
Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
{"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
Example 2:

Input: strs = ["10","0","1"], m = 1, n = 1
Output: 2
Explanation: The largest subset is {"0", "1"}, so the answer is 2.

Constraints:

1 <= strs.length <= 600
1 <= strs[i].length <= 100
strs[i] consists only of digits '0' and '1'.
1 <= m, n <= 100
*/

class OnesAndZeroes {
    public static void main(String[] args) {
        Solution solution = new OnesAndZeroes().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp 01背包問題： 容量（重量）為二維的01背包問題,  即m,n為容量weight【i】， 字符串個數為價值value[i]的01背包問題
        //定義dp[i][j]：表示i個0，j個1時的最大子集
        //遞推公式： dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1); 當前字符串價值 value[i] 即為個數 1
        public int findMaxForm(String[] strs, int m, int n) {
            //初始化dp[] ，沒有字符串時，價值為0
            int[][] dp = new int[m + 1][n + 1];

            //遍歷物品（字符串）
            for (String str : strs) {
                int curStrZeroNum = 0, curStrOneNum = 0;
                for (char c : str.toCharArray()) {
                    if (c == '0') {
                        curStrZeroNum++;
                    } else if (c == '1') {
                        curStrOneNum++;
                    }
                }
                //從後向前遍歷容量
                for (int i = m; i >= curStrZeroNum; i--) {
                    for (int j = n; j >= curStrOneNum; j--) {
                        //dp Recursive formula
                        //不選 ｜ 選 當前字符串
                        dp[i][j] = Math.max(dp[i][j], dp[i - curStrZeroNum ][j - curStrOneNum] + 1);
                    }
                }
            }
            return dp[m][n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}