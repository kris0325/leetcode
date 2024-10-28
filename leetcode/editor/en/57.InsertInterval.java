import java.util.ArrayList;
import java.util.List;

/**
 * You are given an array of non-overlapping intervals intervals where intervals[i]
 * = [starti, endi] represent the start and the end of the iᵗʰ interval and
 * intervals is sorted in ascending order by starti. You are also given an interval
 * newInterval = [start, end] that represents the start and end of another interval.
 * <p>
 * Insert newInterval into intervals such that intervals is still sorted in
 * ascending order by starti and intervals still does not have any overlapping intervals
 * (merge overlapping intervals if necessary).
 * <p>
 * Return intervals after the insertion.
 * <p>
 * Note that you don't need to modify intervals in-place. You can make a new
 * array and return it.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 0 <= intervals.length <= 10⁴
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10⁵
 * intervals is sorted by starti in ascending order.
 * newInterval.length == 2
 * 0 <= start <= end <= 10⁵
 * <p>
 * <p>
 * Related Topics Array 👍 10436 👎 820
 */
       
/*
 2024-08-13 22:50:07
 Insert Interval
Category	Difficulty	Likes	Dislikes
algorithms	Medium (41.47%)	10436	820
Tags
array | sort

Companies
facebook | google | linkedin
*/

class InsertInterval {
    public static void main(String[] args) {
        Solution solution = new InsertInterval().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //scan line
        public int[][] insert(int[][] intervals, int[] newInterval) {
            List<int[]> list = new ArrayList<>();
            //newInterval与当前interval的位置只有3种关系
            for (int i = 0; i < intervals.length; i++) {
                //1.newInterval与当前interval没有交集，newInterval在其右边或为null, 只需收集当前元素
                if (newInterval == null || intervals[i][1] < newInterval[0]) {
                    list.add(intervals[i]);
                } else if (newInterval[1] < intervals[i][0]) {
                    //2.newInterval与当前interval没有交集，newInterval在其左边，收集newInterval，与当前intervals[i]
                    list.addAll(List.of(newInterval, intervals[i]));
                    newInterval = null;
                } else {
                    //3.存在交集 overlap，所以merge
                    newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
                    newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
                }
            }
            //for loop执行完，newInterval != null，表示与intervals的末尾间隔也没有交集，在其右边，所以需单独处理newInterval
            if (newInterval != null) {
                list.add(newInterval);
            }
            return list.toArray(new int[list.size()][]);
        }
    }

    public class Solution1272 {
        //scan line
        //cunrentinterval 与newInterval的相对位置分三种情况
        //case1.cunrentinterval在其左边
        //case2.cunrentinterval在其右边
        //case3.cunrentinterval与newInterval有交集
        //case3.1 仅与newInterval左边有交集
        //case3.2 cunrentinterval被完全覆盖
        //case3.3 仅与newInterval右边有交集
        public int[][] removeInterval(int[][] intervals, int[] newInterval) {
            List<int[]> list = new ArrayList<>();
            for (int i = 0; i < intervals.length; i++) {
                //case1. cunrentinterval在其左边
                // Case 1: Current interval is to the left of newInterval
                if (intervals[i][1] < newInterval[0]) {
                    list.add(intervals[i]);
                }
                //case2.cunrentinterval在其右边
                // Case 2: Current interval is to the right of newInterval
                else if (newInterval[1] < intervals[i][0]) {
                    list.add(intervals[i]);
                }
                // Case 3: Current interval intersects with newInterval
                //case3.cunrentinterval与newInterval有交集
                else {
                    //case3.1 仅与newInterval左边有交集
                    // Case 3.1: Overlaps on the left
                    if (intervals[i][0] < newInterval[0]) {
                        list.add(new int[]{intervals[i][0], newInterval[0]});
                    }
                    //case3.2 cunrentinterval被完全覆盖 continue;
                    // Case 3.3: Overlaps on the right
                    if (intervals[i][1] > newInterval[1]) {
                        list.add(new int[]{newInterval[1], intervals[i][1]});
                    }

//                        if(intervals[i][0]< newInterval[0] &&  newInterval[0]<intervals[i][1]){
//                            int[]tmp = intervals[i];
//                            tmp[1] = newInterval[0];
//                            list.add(tmp);
//                        } else if(newInterval[0] <= intervals[i][0] && intervals[i][1]<= newInterval[1]){
//                            //case3.2 cunrentinterval被完全覆盖
//                            continue;
//                        } else if(newInterval[1] > intervals[i][0] && intervals[i][1] > newInterval[1]) {
//                            //case3.3 仅与newInterval右边有交集
//                            int[]tmp = intervals[i];
//                            tmp[1] = newInterval[1];
//                            list.add(tmp);
//                        }
                }
            }
            return list.toArray(new int[list.size()][]);
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}