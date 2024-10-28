import java.util.Arrays;

/**
 * Given an array of intervals intervals where intervals[i] = [starti, endi],
 * return the minimum number of intervals you need to remove to make the rest of the
 * intervals non-overlapping.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-
 * overlapping.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-
 * overlapping.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're
 * already non-overlapping.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= intervals.length <= 10⁵
 * intervals[i].length == 2
 * -5 * 10⁴ <= starti < endi <= 5 * 10⁴
 * <p>
 * <p>
 * Related Topics Array Dynamic Programming Greedy Sorting 👍 8162 👎 220
 */
       
/*
 2024-07-30 16:51:49
 Non-overlapping Intervals
Category	Difficulty	Likes	Dislikes
algorithms	Medium (53.20%)	8162	220
Tags
greedy

Companies
Unknown
*/

class NonOverlappingIntervals {
    public static void main(String[] args) {
        Solution solution = new NonOverlappingIntervals().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //greedy question：按第一個坐標從小到大，先排序 ，移除重疊區域，局部最優解：移除有边界中较大值，所以取最小值作為新的右邊界，進行迭代實現全局最優
        public int eraseOverlapIntervals(int[][] intervals) {
            //排序
            Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
            //初始化：因為const紀錄重疊區間個數，才++，所以第一個區間初始化為0
            int cost = 0;
            for (int i = 1; i < intervals.length; i++) {
                //不重叠
                if (intervals[i - 1][1] <= intervals[i][0]) {
                    continue;
                } else {
                    //重叠, 需要移除一次，
                    cost++;
                    //更新右边界,贪心算法，局部最優解：移除右边界中较大值，所以取最小值作為新的右邊界，這樣局部cost最小
                    intervals[i][1] = Math.min(intervals[i - 1][1], intervals[i][1]);
                }
            }
            return cost;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}