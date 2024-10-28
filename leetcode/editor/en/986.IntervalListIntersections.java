
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given two lists of closed intervals, firstList and secondList, where
 * firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of
 * intervals is pairwise disjoint and in sorted order.
 * <p>
 * Return the intersection of these two interval lists.
 * <p>
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with
 * a <= x <= b.
 * <p>
 * The intersection of two closed intervals is a set of real numbers that are
 * either empty or represented as a closed interval. For example, the intersection of [
 * 1, 3] and [2, 4] is [2, 3].
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[1
 * 5,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: firstList = [[1,3],[5,9]], secondList = []
 * Output: []
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 0 <= firstList.length, secondList.length <= 1000
 * firstList.length + secondList.length >= 1
 * 0 <= starti < endi <= 10⁹
 * endi < starti+1
 * 0 <= startj < endj <= 10⁹
 * endj < startj+1
 * <p>
 * <p>
 * Related Topics Array Two Pointers 👍 5559 👎 113
 */
       
/*
 2024-08-15 16:05:47
 Interval List Intersections
Category	Difficulty	Likes	Dislikes
algorithms	Medium (71.60%)	5560	113
Tags
math

Companies
Unknown
*/

class IntervalListIntersections {
    public static void main(String[] args) {
        Solution solution = new IntervalListIntersections().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //scan line, 同时遍历2个list的区间，每次计算交集intersection是否大於等於0，
        // 是：存在交集，計算完層後，取當前右邊界大的繼續與當前小的下一個區間參與計算
        // 否：不存在交集
        public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
            List<int[]> result = new ArrayList<>();
            int i = 0, j = 0;
            while (i < firstList.length && j < secondList.length) {
                int intersectionStart = Math.max(firstList[i][0], secondList[j][0]);
                int intersectionEnd = Math.min(firstList[i][1], secondList[j][1]);
                //case1.有交集
                if (intersectionStart <= intersectionEnd) {
                    result.add(new int[]{intersectionStart, intersectionEnd});
                    if (secondList[j][1] > firstList[i][1]) {
                        //case1.1.firstList[i][1]右邊界小，則需用firstList的下一個區間與secondList的當前區間，進行迭代計算
                        i++;
                    } else {
                        //case1.2.相反
                        j++;
                    }
                } else if (firstList[i][1] < secondList[j][0]) {
                    //case2.1.無交集，firstList在左边，所以较小进行+1，继续迭代计算
                    i++;
                } else if (secondList[j][1] < firstList[i][0]) {
                    //case2.2.無交集，secondList在左边，所以较小进行+1，继续迭代计算
                    j++;
                }
            }
            return result.toArray(new int[result.size()][]);
        }
    }

    class Solution759 {
        class Interval {
            //飞机起飞时间
            int start = 0;
            //飞机降落时间
            int end = 0;
            public Interval(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

        //scan line + priority queue
        //使用优先队列维护所有Intervals的最小堆，即將所有Interval存入pq最小堆，确保我们可以逐个处理时间段
        //每次只需比較前一個Interval start与當前Interval end的大小，是否存在overlap，即可判斷是否存在free time
        //1.最小值的start > 当前Interval.end,则表示有common free time，收集
        //2.否则，merge为工作time，出队列继续下一次比较

        public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
            List<Interval> result = new ArrayList<>();
            //将所有interval入队列,按照start時間降序排序
            PriorityQueue<Interval> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.start, o2.start));
            for (List<Interval> list : schedule) {
                for (Interval interval : list) {
                    //将所有interval入队列
                    pq.offer(interval);
                }
            }
            Interval prev = pq.poll();
            while (!pq.isEmpty()) {
                Interval next = pq.peek();
                //case1.前后区间存在overlap, 工作时间有交叉，所有没有休息时间。因此合并二者工作时间
                if (prev.end >= next.start) {
                    //virtual merge 虛擬合併，extend前一個interval
                    prev.end = Math.max(prev.end, next.end);
                    //出队列
                    pq.poll();
                } else {
                    //case1.前后区间不存在overlap,则存在free time
                    result.add(new Interval(prev.end, next.start));
                    //出队列
                    pq.poll();
                }
            }
            return result;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}