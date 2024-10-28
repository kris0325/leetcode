import java.util.HashMap;
import java.util.Stack;

/**
 * Given two integer arrays, preorder and postorder where preorder is the preorder
 * traversal of a binary tree of distinct values and postorder is the postorder
 * traversal of the same tree, reconstruct and return the binary tree.
 * <p>
 * If there exist multiple answers, you can return any of them.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
 * Output: [1,2,3,4,5,6,7]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: preorder = [1], postorder = [1]
 * Output: [1]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= preorder.length <= 30
 * 1 <= preorder[i] <= preorder.length
 * All the values of preorder are unique.
 * postorder.length == preorder.length
 * 1 <= postorder[i] <= postorder.length
 * All the values of postorder are unique.
 * It is guaranteed that preorder and postorder are the preorder traversal and
 * postorder traversal of the same binary tree.
 * <p>
 * <p>
 * Related Topics Array Hash Table Divide and Conquer Tree Binary Tree 👍 2746 👎
 * 114
 */
       
/*
 2024-08-23 22:06:31
*/

class ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new ConstructBinaryTreeFromPreorderAndPostorderTraversal().new Solution();
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

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */

    class Solution {
        //根據先序遍歷性質進行遞歸構建

        //注意：在後序遍歷數組中，找出左子樹與右子樹的切割點，必須重前序遍歷數組的第二個節點開始
        //1.計算index是為方便切分後序數組
        //2.計算左子樹的長度，是為了方便切分前序數組

        int N;
        int[] preorder;
        int[] postorder;
        //前序遍歷數組左邊界
        int preStart = 0;
        HashMap<Integer, Integer> indexPostOrderMap;

        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            this.N = preorder.length;
            this.preorder = preorder;
            this.postorder = postorder;
            indexPostOrderMap = new HashMap<>();
            for (int i = 0; i < N; i++) {
                indexPostOrderMap.put(postorder[i], i);
            }
            return buildTreeHelper(0, N - 1);
        }

        private TreeNode buildTreeHelper(int postStart, int postEnd) {
            if (preStart >= N || postStart > postEnd) {
                return null;
            }
            //第二節點開始找
            TreeNode root = new TreeNode(preorder[preStart++]);
            //葉子節點， //因為從前序遍歷的第二節點開始找，才能找到後序遍歷的切分點，切分為左子樹和右子樹
            // ，preStart有+1， 所以要和N比較
            if (preStart >= N || postStart == postEnd) {
                return root;
            }
            //前序遍歷數組中的第二個節點在後序遍歷中的索引作為切分後序遍歷的左子樹和右子樹的切割點
            // postIndex 切分點
            int postIndex = indexPostOrderMap.get(preorder[preStart]);
            root.left = buildTreeHelper(postStart, postIndex);
            root.right = buildTreeHelper(postIndex + 1, postEnd - 1);
            return root;
        }
    }


    class Solution2 {
        //注意：在後序遍歷數組中，找出左子樹與右子樹的切割點，必須重前序遍歷數組的第二個節點開始
        //1.計算index是為方便切分後序數組
        //2.計算左子樹的長度，是為了方便切分前序數組

        int N;
        int[] preorder;
        int[] postorder;
        HashMap<Integer, Integer> indexPostOrderMap;

        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            this.N = preorder.length;
            this.preorder = preorder;
            this.postorder = postorder;
            indexPostOrderMap = new HashMap<>();
            for (int i = 0; i < N; i++) {
                indexPostOrderMap.put(postorder[i], i);
            }
            return buildTreeHelper(0, N - 1, 0, N - 1);
        }

        private TreeNode buildTreeHelper(int preorderIndexStart, int preorderIndexEnd, int postorderIndexStart, int postorderIndexEnd) {
            //終止條件
            //preorderIndexStart + 1從第二個節點開始找，所以需要多加入2個return的條件進行判斷1，2
            // condition1.所以終止條件中需要加入preorderIndexStart > N - 1判斷
            if (preorderIndexStart > preorderIndexEnd || postorderIndexStart > postorderIndexEnd) {
                return null;
            }

            TreeNode root = new TreeNode(preorder[preorderIndexStart]);
            //condition2.如果是葉子節點，直接返回root
            if (postorderIndexStart == postorderIndexEnd) {
                return root;
            }

            //在後序遍歷數組中 查找左子树的根节点(也就是前序遍歷的第二節點：preorderIndexStart + 1)
            //1.計算index是為方便切分後序數組
            int index = indexPostOrderMap.get(preorder[preorderIndexStart + 1]);

            //2.計算左子樹的長度，是為了方便切前序數組
            int leftLength = index - postorderIndexStart;
            //構建左子樹
            root.left = buildTreeHelper(preorderIndexStart + 1, preorderIndexEnd + 1 + leftLength, postorderIndexStart, postorderIndexStart + leftLength);

            //構造右子樹，注意： preorderIndex的開始為preorderIndexStart + 1 + 左子樹的長度
            root.right = buildTreeHelper(preorderIndexStart + 1 + leftLength + 1, preorderIndexEnd, postorderIndexStart + leftLength + 1, postorderIndexEnd - 1);
            return root;
        }
    }
}


//    public Solution426 {
//        //426. Convert Binary Search Tree To Sorted Doubly Linked List
//        //中序遍歷
//        //迭代實現
//        public Node treeToDoublyList (Node root){
//            if (null == root) {
//                return null;
//            }
//            Stack<Node> stack = new Stack<Node>();
//            //保存第一个节点
//            Node head = null;
//            //由於需要構建雙向鍊錶，所有遍歷過程中，需要兩個遍歷指針，一個指向前一個節點pre， 一個指向當前節點root
//            Node pre = null;
//            while (root != null || !stack.isEmpty()) {
//                //1.左
//                while (root != null) {
//                    stack.push(root);
//                    root = root.left;
//                }
//                //2.中
//                root = stack.pop();
//                //此時中序遍歷到左子樹的葉子節點，也是pre為初始值，則找到最小節點，用head保存
//                if (head == null) {
//                    head = root;
//                }
//                if (pre != null) {
//                    //構造雙向鍊錶
//                    //pre -> root
//                    //pre    <- root
//                    pre.right = root;
//                    root.left = pre
//                }
//                //移動pre，繼續下一次遍歷
//                pre = root;
//
//                //右
//                root = root.right;
//            }
//            //while跳出時，root 指向null, pre指向最後一個node，此時連接第一個節點head與最後一個節點
//            head.left = pre;
//            pre.right = head;
//            return head;
//        }
//    }

//    public Solution426 {
//        //426. Convert Binary Search Tree To Sorted Doubly Linked List
//        //中序遍歷
//        //遞歸實現
//        //由於需要構建雙向鍊錶，所有遍歷過程中，需要兩個遍歷指針，一個指向前一個節點pre， 一個指向當前節點root
//        Node head = null;
//        Node pre = null;
//        public Node treeToDoublyList(Node root){
//            if (null == root) {
//                return null;
//            }
//            inorderTravesal(root);
//            //中序遍歷跳出時，root 指向null, pre指向最後一個node，此時連接第一個節點head與最後一個節點
//            head.left = pre;
//            pre.right = head;
//            return head;
//
//
//        private void inorderTravesal(Node root){
//            if (null == root) {
//                return null;
//            }
//            inorderTravesal(root.left);
//            if(head == null){
//                head = root;
//            }
//            if(pre != null ){
//                pre.left = root;
//                root.right = pre;
//            }
//            root.left = pre;
//            pre = root;
//            inorderTravesal(root.right);
//        }
//    }
////leetcode submit region end(Prohibit modification and deletion)
//
//}