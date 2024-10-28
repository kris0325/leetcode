import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard
 * such that no two queens attack each other.
 * <p>
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You
 * may return the answer in any order.
 * <p>
 * Each solution contains a distinct board configuration of the n-queens'
 * placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown
 * above
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: n = 1
 * Output: [["Q"]]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= n <= 9
 * <p>
 * <p>
 * Related Topics Array Backtracking 👍 12315 👎 289
 */
       
/*
 2024-07-02 15:30:41
*/

class NQueens {
    public static void main(String[] args) {
        Solution solution = new NQueens().new Solution();
        System.out.println(solution.solveNQueens(5));

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution0 {
        //按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
        //backtracking
         List<List<String>> result = new ArrayList<>();
        public List<List<String>> solveNQueens(int n) {
            char[][] chessboard = new char[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(chessboard[i], '.');
            }
            backtracking(chessboard, 0, n);
            return result;

        }

        public void backtracking(char[][] chessboard, int row, int n) {
            //终止条件：到达棋盘底部
            if (row == n) {
                // collect
                result.add(Array2List(chessboard));
                return;
            }
            //遍历列
            for (int i = 0; i < n; i++) {
                if (isValid(chessboard, row, i, n)) {
                    chessboard[row][i] = 'Q';
                    backtracking(chessboard, row + 1, n);
                    //回溯,回到另一中放法开始，所以需要撤销放置Q
                    chessboard[row][i] = '.';
                }
            }
        }

        public List<String> Array2List(char[][] chessboard) {
            List<String> list = new ArrayList<>();
            for (char[] c : chessboard) {
                list.add(String.copyValueOf(c));
            }
            return list;
        }

        //根据国际象棋规则：验证棋盘是否合法
        //按照如下标准去重：
        //不能同行
        //不能同列
        //不能同斜线 （45度和135度角）
        public boolean isValid(char[][] chessboard, int row, int col, int n) {
            //每一层遍历只处理当层一个元素，所以不会出现同一行多个Q的情况，所以无需检查是否同行的case

            //检查同列是否存在queue
            for (int i = 0; i < row; i++) {
                if (chessboard[i][col] == 'Q') {
                    return false;
                }
            }
            //检查45度角(左上)
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (chessboard[i][j] == 'Q') {
                    return false;
                }
            }
            //检查135度角（右上）
            for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
                if (chessboard[i][j] == 'Q') {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * > 2024/09/01 11:35:36
     * Success:
     * 	Runtime:2 ms, faster than 89.83% of Java online submissions.
     * 	Memory Usage:44.8 MB, less than 63.92% of Java online submissions.
     * **/
    class Solution {
        //按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
        //backtracking
        List<List<String>> result = new ArrayList<>();
        public List<List<String>> solveNQueens(int n) {
            char[][] chessboard = new char[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(chessboard[i], '.');
            }
            backtracking(chessboard, 0, n);
            return result;
        }

        public void backtracking(char[][] chessboard, int row, int n) {
            //到達旗盤底部
            if (row == n) {
                result.add(arry2List(chessboard));
                return;
            }
            //橫向遍歷列
            for (int j = 0; j < n; j++) {
                if(isValid(chessboard, row, j,n)){
                    chessboard[row][j] = 'Q';
                    backtracking(chessboard, row + 1, n);
                    chessboard[row][j] = '.';
                }

            }
        }

        public boolean isValid(char[][] chessboard, int row, int col, int n){
            //從向遍歷行 同一行是否存在queue
            for (int i = 0; i < n; i++) {
                if('Q' == chessboard[i][col]){
                    return false;
                }
            }

            //45度角（左上）是否存在queue
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if('Q' == chessboard[i][j]){
                    return false;
                }
            }

            //135度角（右上）是否存在queue
            for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
                if('Q' == chessboard[i][j]){
                    return false;
                }
            }
            return true;
        }

        public List<String> arry2List(char[][] chessboard) {
            List<String> list = new ArrayList<>();
            for (char[] c : chessboard) {
                list.add(String.copyValueOf(c));
            }
            return list;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}