  /**
Given a 2D matrix matrix, handle multiple queries of the following type: 

 
 Calculate the sum of the elements of matrix inside the rectangle defined by 
its upper left corner (row1, col1) and lower right corner (row2, col2). 
 

 Implement the NumMatrix class: 

 
 NumMatrix(int[][] matrix) Initializes the object with the integer matrix 
matrix. 
 int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the 
elements of matrix inside the rectangle defined by its upper left corner (row1, 
col1) and lower right corner (row2, col2). 
 

 You must design an algorithm where sumRegion works on O(1) time complexity. 

 
 Example 1: 
 
 
Input
["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
[[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3,
 0, 5]]], [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]]
Output
[null, 8, 11, 12]
 

Explanation
NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0,
 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e sum of the red rectangle)
numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e sum of the green rectangle)
numMatrix.sumRegion(1, 2, 2, 4); // return 12 (i.e sum of the blue rectangle)


 
 Constraints: 

 
 m == matrix.length 
 n == matrix[i].length 
 1 <= m, n <= 200 
 -10⁴ <= matrix[i][j] <= 10⁴ 
 0 <= row1 <= row2 < m 
 0 <= col1 <= col2 < n 
 At most 10⁴ calls will be made to sumRegion. 
 

 Related Topics Array Design Matrix Prefix Sum 👍 5007 👎 345

*/
       
/*
 2024-10-05 11:24:24
*/

class RangeSumQuery2dImmutable {
      public static void main(String[] args) {
           Solution solution = new RangeSumQuery2dImmutable().new Solution();
      }
       
      //leetcode submit region begin(Prohibit modification and deletion)
class NumMatrix {
          //prefix sum
          int[][] sum;
    public NumMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        sum = new int[row+1][col+1];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //矩形面積：前綴和， 矩形值指的是數組中的值
                //[0,0] 到【i，j】的當前矩形面積 = 上方矩形面積 + 左邊矩形面積-左上角矩形面積（重和部分）+當前矩形（值）
                sum[i+1][j+1] = sum[i][j+1] + sum[i+1][j] - sum[i][j] + matrix[i][j];
            }
        }
        
    }
    //根據上面當前矩形面積計算可推導
    public int sumRegion(int row1, int col1, int row2, int col2) {
        //基於上面的前綴和得到矩形面積，再用矩形面積的幾何關係，計算得到目標矩形面積
        return sum[row2+1][col2+1] - sum[row1][col2+1]-sum[row2+1][col1] + sum[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
//leetcode submit region end(Prohibit modification and deletion)

}