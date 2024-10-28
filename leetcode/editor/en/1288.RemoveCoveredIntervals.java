import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Given an array intervals where intervals[i] = [li, ri] represent the interval [
 * li, ri), remove all intervals that are covered by another interval in the list.
 * <p>
 * The interval [a, b) is covered by the interval [c, d) if and only if c <= a
 * and b <= d.
 * <p>
 * Return the number of remaining intervals.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: intervals = [[1,4],[3,6],[2,8]]
 * Output: 2
 * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: intervals = [[1,4],[2,3]]
 * Output: 1
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= intervals.length <= 1000
 * intervals[i].length == 2
 * 0 <= li < ri <= 10⁵
 * All the given intervals are unique.
 * <p>
 * <p>
 * Related Topics Array Sorting 👍 2229 👎 60
 */
       
/*
 2024-08-14 21:37:37
 Remove Covered Intervals
Category	Difficulty	Likes	Dislikes
algorithms	Medium (56.41%)	2229	60
*/

class RemoveCoveredIntervals {
    public static void main(String[] args) {
        Solution solution = new RemoveCoveredIntervals().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //scan line
        //起点升序，终点降序排序后，判断前一个节点是否能覆盖后一个节点，只需比较终点值,
        // 如果前一个节点終點值大，則能前一個間隔能覆蓋當前間隔
        public int removeCoveredIntervals(int[][] intervals) {
            int count = 0;
            //起点升序，终点降序排序
            Arrays.sort(intervals, (a, b) -> {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                } else {
                    return Integer.compare(b[1], a[1]);
                }
            });
            //初始化前面intervals的最大終點值，
            int preIntervalsMaxEnd = 0;
            int overlapCount = 0;
            for (int i = 0; i < intervals.length; i++) {
                //如果前面intervals比當前終點值大，則能overlap當前interval，overlapCount++
                if (preIntervalsMaxEnd >= intervals[i][1]) {
                    overlapCount++;
                } else {
                    //沒重合，則更新preIntervalsMaxEnd
                    preIntervalsMaxEnd = intervals[i][1];
                }
            }
            return intervals.length - overlapCount;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}