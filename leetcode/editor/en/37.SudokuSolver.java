/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * A sudoku solution must satisfy all of the following rules:
 * <p>
 * <p>
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes
 * of the grid.
 * <p>
 * <p>
 * The '.' character indicates empty cells.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",
 * ".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",
 * ".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".",
 * "6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]]
 * Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4",
 * "8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"]
 * ,["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9
 * ","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4
 * ","5","2","8","6","1","7","9"]]
 * Explanation: The input board is shown above and the only valid solution is
 * shown below:
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit or '.'.
 * It is guaranteed that the input board has only one solution.
 * <p>
 * <p>
 * Related Topics Array Hash Table Backtracking Matrix 👍 9489 👎 260
 */
       
/*
 2024-07-06 12:26:19

 Sudoku Solver
Category	Difficulty	Likes	Dislikes
algorithms	Hard (61.04%)	9489	260
Tags
hash-table | backtracking

Companies
snapchat | uber
*/

class SudokuSolver {
    public static void main(String[] args) {
        Solution solution = new SudokuSolver().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution0 {
        //backtracking : 關鍵思路 2D backtracking
        public void solveSudoku(char[][] board) {
            backtrack(board);
        }

        public boolean backtrack(char[][] board) {
            //遍歷行
            for (int i = 0; i < board.length; i++) {
                //遍歷列
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == '.') {
                        //嘗試填入數字
                        for (char k = '1'; k <= '9'; k++) {
                            if (isValid(k, i, j, board)) {
                                board[i][j] = k;
                                //fill完valid k後，繼續dfs fill下一個數字
                                if (backtrack(board)) {
                                    //1.每一次都能fill valid k,最後return true
                                    return true;
                                }
                                //2.否則回撤
                                board[i][j] = '.';
                            }
                        }
                        //嘗試遍歷完9個數字，都不行，直接返回false
                        return false;
                    }
                }
            }
            //遍歷完沒有返回false，說明找到合適的棋盤位子
            return true;
        }

        //判斷k填入棋盤是否符合規則，從三個緯度判斷 must occur exactly once
        // 同行是否重複，
        // 同列是否重複，
        // 同sub-box是否重複,
        public boolean isValid(char k, int row, int col, char[][] board) {
            //check 同行row
            for (int i = 0; i < board.length; i++) {
                if (board[row][i] == k) {
                    return false;
                }
            }
            //check 同列col
            for (int j = 0; j < board.length; j++) {
                if (board[j][col] == k) {
                    return false;
                }
            }
            //check 同sub-box
            //定位到當前k,在哪一個sub-box
            int starRow = (row/3)*3;
            int startCol =(col/3)*3;
            for (int i = starRow; i< starRow +3; i++){
                for (int j = startCol; j< startCol+3; j++){
                    if(k == board[i][j]){
                        return false;
                    }
                }
            }
            return true;
        }
    }

    /**
     * > 2024/09/01 00:02:25
     * Success:
     * 	Runtime:7 ms, faster than 59.82% of Java online submissions.
     * 	Memory Usage:40.9 MB, less than 69.76% of Java online submissions.
     * */
    class Solution {
        //backtracking : 關鍵思路 2D backtracking
        public void solveSudoku(char[][] board) {
            backtrack(board);
        }

        public boolean backtrack(char[][] board) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == '.') {
                        for (char k = '1'; k <= '9'; k++) {
                            if(isValid(board, i,j,k)){
                                board[i][j] = k;
                                //fill完valid k後，繼續dfs fill下一個數字
                                if (backtrack(board)) {
                                    return true;
                                }
                                //回撤
                                board[i][j] = '.';
                            }
                        }
                        //1-9嘗試完，沒找到valid，return false
                        return false;
                    }
                }
            }
            return true;
        }

        public boolean isValid(char[][] board, int row, int col, char k) {
            //同行
            for(int j = 0; j < board[0].length; j++){
                if(board[row][j] == k){
                    return false;
                }
            }
            //同列
            for(int i = 0; i < board.length; i++){
                if(board[i][col] == k){
                    return false;
                }
            }
            //同subgrid
            int rowstart = row /3 *3;
            int colstart = col /3 *3;
            for(int i = rowstart; i< rowstart +3; i++){
                for(int j = colstart; j< colstart +3; j++){
                    if(board[i][j] == k){
                        return false;
                    }
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}