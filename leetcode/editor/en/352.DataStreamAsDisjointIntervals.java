import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * Given a data stream input of non-negative integers a1, a2, ..., an, summarize
 * the numbers seen so far as a list of disjoint intervals.
 * <p>
 * Implement the SummaryRanges class:
 * <p>
 * <p>
 * SummaryRanges() Initializes the object with an empty stream.
 * void addNum(int value) Adds the integer value to the stream.
 * int[][] getIntervals() Returns a summary of the integers in the stream
 * currently as a list of disjoint intervals [starti, endi]. The answer should be sorted
 * by starti.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input
 * ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum",
 * "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
 * [[], [1], [], [3], [], [7], [], [2], [], [6], []]
 * Output
 * [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]],
 * null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
 * <p>
 * Explanation
 * SummaryRanges summaryRanges = new SummaryRanges();
 * summaryRanges.addNum(1);      // arr = [1]
 * summaryRanges.getIntervals(); // return [[1, 1]]
 * summaryRanges.addNum(3);      // arr = [1, 3]
 * summaryRanges.getIntervals(); // return [[1, 1], [3, 3]]
 * summaryRanges.addNum(7);      // arr = [1, 3, 7]
 * summaryRanges.getIntervals(); // return [[1, 1], [3, 3], [7, 7]]
 * summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
 * summaryRanges.getIntervals(); // return [[1, 3], [7, 7]]
 * summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
 * summaryRanges.getIntervals(); // return [[1, 3], [6, 7]]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 0 <= value <= 10⁴
 * At most 3 * 10⁴ calls will be made to addNum and getIntervals.
 * At most 10² calls will be made to getIntervals.
 * <p>
 * <p>
 * <p>
 * Follow up: What if there are lots of merges and the number of disjoint
 * intervals is small compared to the size of the data stream?
 * <p>
 * Related Topics Binary Search Design Ordered Set 👍 1736 👎 364
 */
       
/*
 2024-08-15 11:14:28
Data Stream as Disjoint Intervals
Category	Difficulty	Likes	Dislikes
algorithms	Hard (60.02%)	1736	364
Tags
binary-search | ordered-map

Companies
Unknown
*/

class DataStreamAsDisjointIntervals {
    public static void main(String[] args) {
//        Solution solution = new DataStreamAsDisjointIntervals().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class SummaryRanges {
        //scan line + binary search 二分查找
        //treeSet的BST屬性，實現二分查找，找到value的相鄰間隔，判斷與lower，higher的關係，進行插入操作
        public SummaryRanges() {

        }

        //treeset 存放Intervals，按stati降序排序
        TreeSet<int[]> treeSet = new TreeSet<>((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });

        //在treeSet中选择value相邻的Intervals，进行判断并插入
        public void addNum(int value) {
            int[] current = new int[]{value, value};
            int[] lower = treeSet.lower(current);
            int[] higher = treeSet.higher(current);
            //current与lower，higher的相对位子关系，进行插入操作
            //case1.treeSet中存在包含current的的區間
            if ((lower != null && lower[0] <= value && lower[1] >= value) ||
                    (higher != null && higher[0] <= value && higher[1] >= value)) {
                return;
            }
            //case2. current在lower，higher中间，并与二者相邻
            else if (lower != null && lower[1] + 1 == value && higher != null && value + 1 == higher[0]) {
                //merge lower，current, higher
                lower[1] = higher[1];
                treeSet.remove(higher);
            }
            //case3. current在lower，higher中间，仅与lower相邻
            else if (lower != null && lower[1] + 1 == value) {
                lower[1] = Math.max(lower[1], value);
            }
            //case4. current在lower，higher中间，仅与higher相邻
            else if (higher != null && value + 1 == higher[0]) {
                higher[0] = value;
            }
            //case5. current在lower，higher中间，与lower，higher均不相邻
            else {
                treeSet.add(current);
            }
        }

        public int[][] getIntervals() {
            List<int[]> list = new ArrayList<>(treeSet);
            return list.toArray(new int[list.size()][]);
        }
    }

//    class SummaryRanges2 {
//        TreeSet<int[]> ts = new TreeSet<>((a, b) -> a[0] - b[0]);
//        int[] head = new int[]{-10, -10}, tail = new int[]{10010, 10010};
//
//        public SummaryRanges2() {
//            ts.add(head);
//            ts.add(tail);
//        }
//
//        public void addNum(int val) {
//            int[] cur = new int[]{val, val};
//            int[] prev = ts.floor(cur);
//            int[] next = ts.ceiling(cur);
//            if ((prev[0] <= val && val <= prev[1]) || (next[0] <= val && val <= next[1])) {
//                // pass
//            } else if (prev[1] + 1 == val && next[0] - 1 == val) {
//                prev[1] = next[1];
//                ts.remove(next);
//            } else if (prev[1] + 1 == val) {
//                prev[1] = val;
//            } else if (next[0] - 1 == val) {
//                next[0] = val;
//            } else {
//                ts.add(cur);
//            }
//        }
//
//        public int[][] getIntervals() {
//            // using ceiling
//            // int n = ts.size();
//            // int[][] ans = new int[n - 2][2];
//            // int[] cur = head;
//            // for (int i = 0; i < n - 2; i++) {
//            //     ans[i] = ts.ceiling(new int[]{cur[0] + 1, cur[1] + 1});
//            //     cur = ans[i];
//            // }
//            // return ans;
//
//            // using iterator
//            int n = ts.size();
//            int[][] ans = new int[n - 2][2];
//            Iterator<int[]> iterator = ts.iterator();
//            iterator.next();
//            for (int i = 0; i < n - 2; i++) ans[i] = iterator.next();
//            return ans;
//        }
//    }

    /**
     * Your SummaryRanges object will be instantiated and called as such:
     * SummaryRanges obj = new SummaryRanges();
     * obj.addNum(value);
     * int[][] param_2 = obj.getIntervals();
     */

    /**
     * 1229.MeetingScheduler1
     * */
    class Solution1229MeetingScheduler1 {
        //scan line
        public List<Integer>  meetingScheduler(int[][] slot1, int[][] slot2, int duration) {
            //按每个区间的开始时间生序排序
            Arrays.sort(slot1,(a, b)-> Integer.compare(a[0],b[0]));
            Arrays.sort(slot2,(a, b)-> Integer.compare(a[0],b[0]));
            int i = 0;
            int j = 0;
            while (i < slot1.length && j < slot2.length) {
                //case1 無交集&& slot1在slot2左邊
                if (slot1[i][1] < slot2[j][0]) {
                    i++;
                }
                //case2 無交集&& slot1在slot2右邊
                else if (slot2[i][1] < slot1[j][0]) {
                    j++;
                }
                //case3 有交集&& slot1在slot2左邊
                else if (slot1[i][0] <= slot2[j][0]
                        && slot2[j][0] + duration <= slot2[j][1]
                        && slot2[j][0] + duration <= slot1[i][1]) {
                    return List.of(slot2[j][0],slot2[j][0] + duration);
                }
                //case4 有交集&& slot1在slot2右邊
                else if (slot2[j][0] <= slot1[i][0]
                        && slot1[i][0] + duration <= slot1[i][1]
                        && slot1[i][0] + duration <= slot2[j][1]) {
                    return List.of(slot1[i][0], slot1[i][0] + duration);
                }
            }
            return new ArrayList<>();
        }
    }

    class Solution1229MeetingScheduler2 {
        //scan line
        //每次迭代都计算slot1[i] 与slot2[j]的common区间是否大于等于duration，是便找到了最早的common区间，否则更新
        public List<Integer> meetingScheduler(int[][] slot1, int[][] slot2, int duration) {
           //按每个区间的开始时间生序排序
            Arrays.sort(slot1,(a, b)-> Integer.compare(a[0],b[0]));
            Arrays.sort(slot2,(a, b)-> Integer.compare(a[0],b[0]));
            int i = 0;
            int j = 0;
            while (i < slot1.length && j < slot2.length) {
                int intersectStart = Math.max(slot1[i][0], slot2[j][0]);
                int intersectEnd = Math.min(slot1[i][1], slot2[j][1]);
                //case1:出现交集common区间，且交集大于duration
                if(intersectEnd-intersectStart >= duration){
                    return List.of(intersectStart,intersectEnd);
                }
//                case2:没有满足条件的交集，且slot1在slot2的左边，则遍历slot1的下一个区间
                else if(slot1[i][1]<slot2[j][1]){
                    i++;
                }
                //case3:没有满足条件的交集，且slot1在slot2的右边，则遍历slot2的下一个区间
                else {
                    j++;
                }
            }
            return new ArrayList<>();

        }
    }


//leetcode submit region end(Prohibit modification and deletion)


}