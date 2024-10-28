/**
 * Given an integer n, return the number of structurally unique BST's (binary
 * search trees) which has exactly n nodes of unique values from 1 to n.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 3
 * Output: 5
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: n = 1
 * Output: 1
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= n <= 19
 * <p>
 * <p>
 * Related Topics Math Dynamic Programming Tree Binary Search Tree Binary Tree 👍
 * 10311 👎 403
 */
       
/*
 2024-07-03 23:45:03

 Unique Binary Search Trees
Category	Difficulty	Likes	Dislikes
algorithms	Medium (61.00%)	10311	403
Tags
dynamic-programming | tree

Companies
snapchat

Given an integer n, return the number of structurally unique BST's (binary search trees)
 which has exactly n nodes of unique values from 1 to n.
*/

class UniqueBinarySearchTrees {
    public static void main(String[] args) {
        Solution solution = new UniqueBinarySearchTrees().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // dp :

        //1.确定dp数组（dp table）以及下标的含义
        //dp[i] ： 1到i为节点组成的二叉搜索树的个数为dp[i]。

        //画图分析以每个j为根节点root，可以画出多少种BST
        // 画图 n = 1, 2, 3的情况，找规律，分析递推关系,

        //2.确定递推公式
        //在上面的分析中，其实已经看出其递推关系， dp[i] += dp[以j为头节点左子树节点的搜素树数量] * dp[以j为头节点右子树节点的搜素树数量]
       //整个树 是 左右子树的不同组合，所以是左子树的组合数量 * 右子树的组合数量
        //j 相当于头节点的元素，j是从1遍历到i为止
        //i 是从前到后遍历到n, 所以求dp[n] 需要累加dp[i]
        //所以递推公式：dp[i] += dp[j-1] * dp[i-j]
        //3.初始化
          //dp[0]=1 (假设空树case), dp[1] =1
        public int numTrees(int n) {
            int[] dp = new int[n + 1];
            dp[0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    //对于第i个节点，需要考虑1作为根节点直到i作为根节点的情况，所以需要累加
                    dp[i] += dp[j-1] * dp[i - j];
                }
            }
            return dp[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}