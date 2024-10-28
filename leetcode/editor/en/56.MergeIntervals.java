import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all
 * overlapping intervals, and return an array of the non-overlapping intervals that
 * cover all the intervals in the input.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= intervals.length <= 10⁴
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10⁴
 * <p>
 * <p>
 * Related Topics Array Sorting 👍 22233 👎 780
 */
       
/*
 2024-07-30 23:05:48
 Merge Intervals
Category	Difficulty	Likes	Dislikes
algorithms	Medium (47.29%)	22233	780
Tags
array | sort

Companies
bloomberg | facebook | google | linkedin | microsoft | twitter | yelp
*/

class MergeIntervals {
    public static void main(String[] args) {
        Solution solution = new MergeIntervals().new Solution();

        // 示例 1
        int[][] bookings1 = {{1, 2}, {4, 5}, {7, 7}};
        System.out.println(solution.maxProfit(bookings1)); // 输出 5

        // 示例 2
        int[][] bookings2 = {{4, 5}, {7, 9}, {1, 100}};
        System.out.println(solution.maxProfit(bookings2)); // 输出 100
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution0 {
        //scan line 扫描线 算法 question
        //greedy question: 按左邊界升序排序，合併重疊區間，非重疊區間跳過，繼續笑一次迭代
        public int[][] merge(int[][] intervals) {
            //按左邊界升序排序
            Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
            //當前區間指向前一個區間，迭代和下一個區間進行比較看是否重疊
            int[] current = intervals[0];
            List<int[]> newIntervals = new ArrayList<>();
            for (int i = 1; i < intervals.length; i++) {
                //case1. 前後區間不重疊
                if (current[1] < intervals[i][0]) {
                    newIntervals.add(current);
                    //更新current 左，右邊界
                    current = intervals[i];
                } else {
                    //case2. 前後區間重疊,更新current 右邊界為重疊區間的最大值
                    current[1] = Math.max(intervals[i][1], current[1]);
                }
            }
            //添加最後一個區間,注意current的定義，for loop執行完後，
            // 不管是否重合都沒有將最後一個區間加入，所以需要單獨處理加入最後一個區間
            newIntervals.add(current);
            return newIntervals.toArray(new int[newIntervals.size()][]);
        }
    }


    class Solution1 {
        //greedy question: 按左邊界升序排序，合併重疊區間，非重疊區間跳過，繼續笑一次迭代
        public int[][] merge(int[][] intervals) {
            //按左邊界升序排序
            Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
            //當前區間指向前一個區間，迭代和下一個區間進行比較看是否重疊
            int[] current = intervals[0];
            List<int[]> newIntervals = new ArrayList<>();
            for (int i = 1; i < intervals.length; i++) {
                //case1. 前後區間不重疊
                if (current[1] < intervals[i][0]) {
                    newIntervals.add(current);
                    //更新current 左，右邊界
                    current = intervals[i];
                } else {
                    //case2. 前後區間重疊,更新current 右邊界為重疊區間的最大值
                    current[1] = Math.max(intervals[i][1], current[1]);
                }
            }
            //添加最後一個區間
              //1與最後一個區間不重疊
            if (current[1] < intervals[intervals.length - 1][0]) {
                newIntervals.add(intervals[intervals.length - 1]);
            } else {
                //2與最後一個區間重疊
                newIntervals.add(current);
            }
            return newIntervals.toArray(new int[newIntervals.size()][]);
        }
    }

    class Solution_391{
        //scan line 扫描线 算法 question
        //lintcode question description： 391.天上同时出现飞机的最多数量
        //给出飞机的起飞与降落时间的列表，用序列interval表示，请计算出天上最多有多少架飞机？
        //注意：多家爱飞机降落与起飞在同一时刻，我们任务降落有优先权

        //思路：
        //1事件点：我们将飞机的起飞时间视为一个“进入”事件，降落时间视为一个“离开”事件。然后，我们将这些事件按照时间排序处理。
        //2事件排序：按照时间先后对所有事件排序。如果时间相同，“离开”事件优先于“进入”事件，因为飞机降落时不会再占用天空。
        //3扫描线处理：我们从前向后扫描这些事件，并维护一个当前飞机数量的计数器。在每个“进入”事件时计数器加1，在每个“离开”事件时计数器减1，同时更新最大飞机数量。
        public int countOfAirLanes(List<Interval> airplanes) {
            int maxCount = 0;
            int currentCount = 0;
            List<Event> allEvents = new ArrayList<>();
            for (Interval interval : airplanes) {
                //起飞事件
                allEvents.add(new Event(interval.start, true));
                //降落事件
                allEvents.add(new Event(interval.end, false));
            }
            //对事件按时间升序排序：时间相同的情况，降落事件优先
            Collections.sort(allEvents,(a,b) -> {
                if (a.time == b.time) {
                    return a.isStart ? 1 : -1;
                }
                return a.time - b.time;
            });

            for (Event event : allEvents) {
                //起飞，currentCount加1
                if (event.isStart) {
                    currentCount++;
                } else {
                    //降落，currentCount加-1
                    currentCount--;
                }
                //更新最大飞机数量
                maxCount = Math.max(maxCount, currentCount);
            }
            return maxCount;
        }

        class Event {
            //飞机起飞/降落时间
            int time;
            //飞机是否是起飞事件
            boolean isStart;
            public Event(int time, boolean isStart) {
                this.time = time;
                this.isStart = isStart;
            }
        }
        class Interval {
            //飞机起飞时间
            int start = 0;
            //飞机降落时间
            int end = 0;
        }
    }

    class Solution {
        static class Event {
            int time;
            int type; // 1 for start, -1 for end

            Event(int time, int type) {
                this.time = time;
                this.type = type;
            }
        }

        public int maxProfit(int[][] bookings) {
            List<Event> events = new ArrayList<>();

            // 创建事件
            for (int[] booking : bookings) {
                events.add(new Event(booking[0], 1));  // 开始事件
                events.add(new Event(booking[1], -1)); // 结束事件
            }

            // 使用 Collections.sort() 按时间排序事件
            Collections.sort(events, (a, b) -> {
                if (a.time != b.time) {
                    return Integer.compare(a.time, b.time);
                }
                return Integer.compare(a.type, b.type); // 结束事件优先
            });

            int currentActive = 0;
            int maxProfit = 0;

            // 遍历事件
            for (Event event : events) {
                if (event.type == 1) { // 开始事件
                    currentActive++;
                } else { // 结束事件
                    currentActive--;
                }
                // 计算当前活动请求的利润
                maxProfit = Math.max(maxProfit, currentActive);
            }

            return maxProfit;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)
}