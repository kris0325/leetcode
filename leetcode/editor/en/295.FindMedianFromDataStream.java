import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * The median is the middle value in an ordered integer list. If the size of the
 * list is even, there is no middle value, and the median is the mean of the two
 * middle values.
 * <p>
 * <p>
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * <p>
 * <p>
 * Implement the MedianFinder class:
 * <p>
 * <p>
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data
 * structure.
 * double findMedian() returns the median of all elements so far. Answers within 1
 * 0⁻⁵ of the actual answer will be accepted.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 * <p>
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * -10⁵ <= num <= 10⁵
 * There will be at least one element in the data structure before calling
 * findMedian.
 * At most 5 * 10⁴ calls will be made to addNum and findMedian.
 * <p>
 * <p>
 * <p>
 * Follow up:
 * <p>
 * <p>
 * If all integer numbers from the stream are in the range [0, 100], how would
 * you optimize your solution?
 * If 99% of all integer numbers from the stream are in the range [0, 100], how
 * would you optimize your solution?
 * <p>
 * <p>
 * Related Topics Two Pointers Design Sorting Heap (Priority Queue) Data Stream 👍
 * 12095 👎 251
 */
       
/*
 2024-10-14 00:44:56
*/

class FindMedianFromDataStream {
    public static void main(String[] args) {
        Solution solution = new FindMedianFromDataStream().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class MedianFinder {
        /**
         * 建立一个 小顶堆 A 和 大顶堆 B ，各保存列表的一半元素，且规定：
         * <p>
         * A 保存 较大 的一半，（ N 为偶数）长度为  n/2 或者（ N 为奇数）（n+1）/2
         * B 保存 较小 的一半，长度为  （ N 为偶数）长度为  n/2 或者（ N 为奇数）（n-1）/2
         * 随后，中位数可仅根据 A,B 的堆顶元素计算得到。
         */
        Queue<Integer> greaterQ;
        Queue<Integer> smallQ;

        public MedianFinder() {
            //小顶堆 ，保存较大的一半，取堆頂元素，即當前queue中的最小元素
            //且維護greaterQ與smallQ元素相同（ N 为偶数），或者至少多一個元素（ N 为奇数）
            greaterQ = new PriorityQueue<>();
            //大頂堆，保存較小的一半，取堆頂元素，即當前queue中的最大元素
            smallQ = new PriorityQueue<>((m, n) -> n - m);
        }

        public void addNum(int num) {
            //維護greaterQ比smallQ至多多一個元素
            if (greaterQ.size() == smallQ.size()) {
                //先加入smallQ
                smallQ.offer(num);
                //取smallQ堆頂元素，即大元素加入greaterQ
                greaterQ.offer(smallQ.poll());
            } else {
                //先加入greaterQ
                greaterQ.offer(num);
                //取greaterQ堆頂元素，即小元素加入smallQ
                smallQ.offer(greaterQ.poll());
            }
        }

        public double findMedian() {
            return smallQ.size() != greaterQ.size()
                    ? greaterQ.peek() : (smallQ.peek() + greaterQ.peek()) / 2.0;
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)

}