/**
 * The thief has found himself a new place for his thievery again. There is only
 * one entrance to this area, called root.
 * <p>
 * Besides the root, each house has one and only one parent house. After a tour,
 * the smart thief realized that all houses in this place form a binary tree. It
 * will automatically contact the police if two directly-linked houses were broken
 * into on the same night.
 * <p>
 * Given the root of the binary tree, return the maximum amount of money the
 * thief can rob without alerting the police.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,2,3,null,3,null,1]
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: root = [3,4,5,1,3,null,1]
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * The number of nodes in the tree is in the range [1, 10⁴].
 * 0 <= Node.val <= 10⁴
 * <p>
 * <p>
 * Related Topics Dynamic Programming Tree Depth-First Search Binary Tree 👍 8512
 * 👎 143
 */
       
/*
 2024-07-13 01:58:25

 House Robber III
Category	Difficulty	Likes	Dislikes
algorithms	Medium (54.24%)	8513	143
Tags
tree | depth-first-search

Companies
uber
*/

class HouseRobberIii {
    public static void main(String[] args) {
        Solution solution = new HouseRobberIii().new Solution();
    }
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left,TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //DP + 递归 后续遍历：
        //用一个长度为2的数组，记录当前节点的偷与不偷
        //1 递归函数的返回值与参数
        // 求一个节点 偷｜不偷的2个状态所得金钱，那么返回值就是一个长度为2的数组
//        int[] robTree(TreeNode root)
        // 返回数组也是dp数组
        //2.确定终止条件, 遇到空节点，偷｜不偷 都是0
        // if(cur==null) return {0,0};
        //3.确定遍历顺序

        //首先明确的是使用后序遍历，因为要通过递归函数的返回值来做下一步计算

        // 递归左节点，得到左节点的偷｜不偷的金钱
        // 递归右节点，得到右节点的偷｜不偷的金钱
//        int [] left = ronTree(cur.left);
//        int [] right = ronTree(cur.right);
        //4.确定当层递归
        //偷当前节点，那么左右孩子不能偷，val1 = cur.val + left[0] + right[0];
        //不偷当前节点，那么左右孩子可以考虑偷｜不偷， val2 = Math.max(left[0],left[1]) + Math.max(right[0],right[1])
        // return {val1,val2};

        //TC:O(n) 每個節點只遍歷了一次
        //SC:O(log n) 算上遞推系統棧的空間
        public int rob(TreeNode root) {
            int[] result = robTree(root);
            return Math.max(result[0], result[1]);
        }

        public int[] robTree(TreeNode cur) {
            //初始化
            int[] result = new int[2];
            if (null == cur) return result;
            //后续遍历
            //左子树
            int[] left = robTree(cur.left);
            //右子树
            int[] right = robTree(cur.right);
            //根节点 1.不偷，  那么可考虑 偷 ｜ 或不偷左节点，右节点，并且取较大值
            result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            //根节点 1.偷,          那么不能偷左节点，右节点
            result[1] = cur.val + left[0] + right[0];

            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}