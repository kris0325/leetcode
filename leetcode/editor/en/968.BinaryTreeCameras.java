/**
 * You are given the root of a binary tree. We install cameras on the tree nodes
 * where each camera at a node can monitor its parent, itself, and its immediate
 * children.
 * <p>
 * Return the minimum number of cameras needed to monitor all nodes of the tree.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [0,0,null,0,0]
 * Output: 1
 * Explanation: One camera is enough to monitor all nodes if placed as shown.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: root = [0,0,null,0,null,0,null,null,0]
 * Output: 2
 * Explanation: At least two cameras are needed to monitor all nodes of the tree.
 * The above image shows one of the valid configurations of camera placement.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * // The number of nodes in the tree is in the range [1, 1000].
 * Node.val == 0
 * <p>
 * <p>
 * Related Topics Dynamic Programming Tree Depth-First Search Binary Tree 👍 5336
 * 👎 77
 */
       
/*
 2024-07-31 11:53:50
 Binary Tree Cameras
Category	Difficulty	Likes	Dislikes
algorithms	Hard (46.61%)	5336	77
Tags
divide-and-conquer

Companies
Unknown
*/

class BinaryTreeCameras {
    public static void main(String[] args) {
        Solution solution = new BinaryTreeCameras().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
//        TC: O(n)
//        SC: O(n)

        //greedy question: 從題目示例中可以得到啟發，所有攝像頭都沒有放在葉子節點，
        // 攝像頭可以覆蓋上中下三層，所有把攝像頭放在葉子節點的父節點才可以實現局部最優
        //從頭節點還是從葉子節點開始看（遍歷順序）？因為頭節點只有一個，葉子節點有指數個，
        // 所有選擇從葉子節點開始從下往上看，可以實現局部最優：葉子節點安裝攝像頭，所用攝像頭最少，全局最優：全部攝像頭使用最少

        //那麼問題可以拆分為：
        //1.二叉樹遍歷 從下往上
        //2.如何每個兩個節點放一個攝像頭

        // 確定遍歷順：從下往上，可以使用後序遍歷：左右中，這樣可以在回溯的過程中實現從下往上推導

        //如何每個兩個節點放一個攝像頭
        //此時需要狀態轉移公式，（注意：不是dp中的狀態轉移公式），因為本題狀態轉移沒有擇優的過程，只是單純的狀態轉移
        //每個節點的狀態情況 （空節點算是有覆蓋的case）
        //0:該節點無覆蓋
        //1:該節點有攝像頭
        //2:該節點有覆蓋

        //攝像頭個數
        int result = 0;

        public int minCameraCover(TreeNode root) {
            //case4:頭節點沒有覆蓋（遞歸結束後 可能頭節點還有一個無覆蓋的情況）
            if (cameraCoverState(root) == 0) {
                result++;
            }
            return result;
        }

        public int cameraCoverState(TreeNode root) {
            //終止條件：葉子節點為空節點，空節點算是有覆蓋的case
            if (null == root) {
                return 2;
            }
            //後序遍歷
            int left = cameraCoverState(root.left);
            int right = cameraCoverState(root.right);
            //case1:左右子節點都有覆蓋，
            /**
             *  那麼此時當前的父節點就是無覆蓋的狀態
             * */
            if (left == 2 && right == 2) {
                return 0;
            }

            //case2:左右子節點至少有一個是無覆蓋
           /*如果是以下情况，则中间节点（父节点）应该放摄像头：
left == 0 && right == 0 左右节点无覆盖
left == 1 && right == 0 左节点有摄像头，右节点无覆盖
left == 0 && right == 1 左节点有无覆盖，右节点摄像头
left == 0 && right == 2 左节点无覆盖，右节点覆盖
left == 2 && right == 0 左节点覆盖，右节点无覆盖
           * */
            else if (left == 0 || right == 0) {
                //當前節點需要放攝像頭（回溯遍歷過程，在次case場景下，安裝攝像頭，統計result數量）
                result++;
                return 1;
            }

            //case3: 左右子節點至少有一個是有攝像頭
            /**
             * 那麼當前父節點就應該是2覆蓋的狀態：
             * left == 1 && right == 2 左节点有摄像头，右节点有覆盖
             * left == 2 && right == 1 左节点有覆盖，右节点有摄像头
             * left == 1 && right == 1 左右节点都有摄像头
             * */
            else {
                //即 if (left == 1 || right == 1)
                return 2;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}