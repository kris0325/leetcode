import java.util.Arrays;

/**
 * There are n children standing in a line. Each child is assigned a rating value
 * given in the integer array ratings.
 * <p>
 * You are giving candies to these children subjected to the following
 * requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to distribute the
 * candies to the children.
 * Example 1:
 * Input: ratings = [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2
 * candies respectively.
 * <p>
 * Example 2:
 * Input: ratings = [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1
 * candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 * Constraints:
 * <p>
 * n == ratings.length
 * 1 <= n <= 2 * 10⁴
 * 0 <= ratings[i] <= 2 * 10⁴
 * <p>
 * Related Topics Array Greedy 👍 7831 👎 664
 */
       
/*
 2024-07-22 00:13:41

 Candy
Category	Difficulty	Likes	Dislikes
algorithms	Hard (43.29%)	7831	664
Tags
greedy

Companies
Unknown
*/

class Candy {
    public static void main(String[] args) {
        Solution solution = new Candy().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //greedy question: 局部最優：先初始化為每個孩子得1個糖果；從小到大來比較，大的比小的多一個糖果；全劇最優：兩邊分別進行比較，局部最優推導出全劇最優
        //初始化：要求每個孩子至少一個糖果，那麼先初始化，所有孩子分一個糖果
        //1. 先確定右邊大於左邊的情況
        //局部最優：從小到大來比較，那麼遍歷順序就為從左到右（因為左<右）， 從最左邊孩子開始遍歷，只要右邊比左邊評分高，就多一個糖果；全局最優：相鄰孩子中，評分高的，多得一個糖果

        //2.再確定左邊大於右邊的情況
        //局部最優：從小到大來比較，那麼遍歷順序就為從右到左（因為左>右）， 從最右邊孩子開始遍歷，只有左邊比右邊評分高，取二者糖果中較大值 Math.max(candy[i], candy[i + 1] + 1)，
        //因為case1遍歷完成後，還得滿足case1的情況，即需要滿足：當ratings[i -1]<ratings[i] &&  ratings[i]> ratings[i+1]時，candy[i]取最大值；全劇最優：相鄰孩子中，評分高的，多得一個糖果

        //3. 1，2cases 左右兩邊都遍歷完後 得到全局最優解
        //時間複雜度: o(n)
        //空間複雜度: o(n)
        public int candy(int[] ratings) {
            int needCandy = 0;
            int[] candy = new int[ratings.length];
            Arrays.fill(candy, 1);
            //1. 先確定右邊大於左邊的情況, 從左到右
            for (int i = 1; i < ratings.length; i++) {
                if (ratings[i] > ratings[i - 1]) {
                    candy[i] = candy[i - 1] + 1;
                }
            }
            //2.再確定左邊大於右邊的情況,從右到左遍歷
            for (int i = ratings.length - 2; i >= 0; i--) {
                if (ratings[i] > ratings[i + 1]) {
                    //即需要滿足：當ratings[i -1]<ratings[i] && ratings[i]> ratings[i+1]時，candy[i]取最大值
//                   bug: candy[i] = candy[i + 1] + 1; candy[i]次輪遍歷後被重新賦值後，可能不能滿足case1中 candy[i] > candy[i - 1]了，
                    //所以要取取二者candy[i],candy[i+1]糖果中較大值 Math.max(candy[i], candy[i + 1] + 1)
                    candy[i] = Math.max(candy[i], candy[i + 1] + 1);
                }
            }
            //3. 1，2cases 左右兩邊都遍歷完後 得到全局最優解
            for (int i = 0; i < ratings.length; i++) {
                needCandy += candy[i];
            }
            return needCandy;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}