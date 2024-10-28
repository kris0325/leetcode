import java.util.Arrays;

/**
 * There are some spherical balloons taped onto a flat wall that represents the XY-
 * plane. The balloons are represented as a 2D integer array points where points[i]
 * = [xstart, xend] denotes a balloon whose horizontal diameter stretches between
 * xstart and xend. You do not know the exact y-coordinates of the balloons.
 * <p>
 * Arrows can be shot up directly vertically (in the positive y-direction) from
 * different points along the x-axis. A balloon with xstart and xend is burst by an
 * arrow shot at x if xstart <= x <= xend. There is no limit to the number of
 * arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any
 * balloons in its path.
 * <p>
 * Given the array points, return the minimum number of arrows that must be shot
 * to burst all balloons.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: points = [[10,16],[2,8],[1,6],[7,12]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 * - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
 * - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: points = [[1,2],[3,4],[5,6],[7,8]]
 * Output: 4
 * Explanation: One arrow needs to be shot for each balloon for a total of 4
 * arrows.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: points = [[1,2],[2,3],[3,4],[4,5]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 * - Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
 * - Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= points.length <= 10⁵
 * points[i].length == 2
 * -2³¹ <= xstart < xend <= 2³¹ - 1
 * <p>
 * <p>
 * Related Topics Array Greedy Sorting 👍 7459 👎 240
 */
       
/*
 2024-07-30 11:30:00
 Minimum Number of Arrows to Burst Balloons
Category	Difficulty	Likes	Dislikes
algorithms	Medium (58.92%)	7452	238
Tags
greedy

Companies
microsoft
*/

class MinimumNumberOfArrowsToBurstBalloons {
    public static void main(String[] args) {
        Solution solution = new MinimumNumberOfArrowsToBurstBalloons().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //greedy question： 讓arrow盡可能射中x坐標區間重疊的balloon，因此需要對氣球排序
        //可以按start 排序，從小到大，（也可以按end排序）
        //case1:如果前一個balloon的end(右邊界)與後一個balloon的start（左邊界）不重疊，則需要加一隻arrow，即 if(points[i-1][1]<points[i][0]) arrows++;
        //case2:前後balloon重疊，則取重疊balloon的最小右邊界作為當前區域的右邊界end，繼續下一次比較，points[i][1] = Math.min(points[i-1][1],points[i][1]);
        public int findMinArrowShots(int[][] points) {
            if (points.length == 0) return 0;
            //長度不為0時，至少需要一隻arrow，用來射points[0]
            int arrows = 1;
            //排序  按star坐標從小到大排序
            Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));
//            特别是当值非常大或非常小时。Java 中的整数运算可能会导致溢出，当 a[0] 和 b[0] 的值非常大或非常小时，a[0] - b[0] 可能会超出整数范围，从而得到错误的结果。
//            bug: Arrays.sort(points, (a, b) -> a[0] - b[0]);
            for (int i = 1; i < points.length; i++) {
                if (points[i - 1][1] < points[i][0]) {
                    //不重疊，則需要一隻arrow,射points[i]
                    arrows++;
                } else {
                    //有重疊區域，則更新當前重疊區域的右邊界，取二者較小值，進行下一次比較
                    points[i][1] = Math.min(points[i - 1][1], points[i][1]);
                }
            }
            return arrows;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}