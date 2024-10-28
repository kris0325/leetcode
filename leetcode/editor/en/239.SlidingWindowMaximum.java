import java.util.*;

/**
 * You are given an array of integers nums, there is a sliding window of size k
 * which is moving from the very left of the array to the very right. You can only
 * see the k numbers in the window. Each time the sliding window moves right by one
 * position.
 * <p>
 * Return the max sliding window.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 10⁵
 * -10⁴ <= nums[i] <= 10⁴
 * 1 <= k <= nums.length
 * <p>
 * <p>
 * Related Topics Array Queue Sliding Window Heap (Priority Queue) Monotonic
 * Queue 👍 18437 👎 700
 */
       
/*
 2024-09-25 22:21:03
*/

class SlidingWindowMaximum {
    public static void main(String[] args) {
        Solution solution = new SlidingWindowMaximum().new Solution();
        solution.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dequeue + two pointer

        /**
         * 时间复杂度分析：O(n)
         * 每个元素在双端队列中最多只会被添加和移除一次。即，元素最多进入和离开队列各一次。
         * 因此，入队操作和出队操作各是 O(1)，但由于每个元素最多只会进行一次入队和一次出队操作，所以整体复杂度是 O(n)
         */

        /**
         * 注意⚠️：
         * 步骤1和步骤2可以互换顺序，而步骤3必须放在最后。让我解释一下原因：
         * 步骤1和步骤2可以互换：
         * 步骤1（维护窗口大小）：移除队列前端超出窗口范围的元素。
         * 步骤2（维护单调性）：移除队列后端小于当前元素的元素，然后添加当前元素。
         * 这两个步骤是相互独立的操作：
         * 步骤1只关注窗口的左边界，确保队列中的元素都在当前窗口内。
         * 步骤2只关注新元素的加入，维护队列的单调递减特性。
         * 无论先执行哪一步，都不会影响另一步的正确性。因此，它们的顺序可以互换。
         * 步骤3必须放在最后：
         * 步骤3（计算结果）：当窗口形成时，将当前最大值（队列前端元素）加入结果数组。
         * 这个步骤必须放在最后，原因如下：
         * a. 窗口完整性：
         * 只有在完成了步骤1和步骤2之后，我们才能确保当前窗口是完整的，并且队列中的元素正确反映了当前窗口的状态。
         * b. 最大值的正确性：
         * 步骤2确保了队列的单调递减特性，使得队首元素始终是当前窗口的最大值。如果在步骤2之前执行步骤3，可能会得到错误的最大值。
         * c. 窗口大小的保证：
         * 步骤1确保了队列中只包含当前窗口内的元素。如果在步骤1之前执行步骤3，可能会使用到已经不在当前窗口内的元素。
         * d. 逻辑顺序：
         * 从算法的逻辑来看，我们需要先处理新元素的加入（步骤2）和旧元素的移除（步骤1），然后才能得出当前窗口的最大值（步骤3）。
         * 总结：
         * 步骤1和步骤2可以互换，因为它们处理的是窗口的不同方面（大小和单调性），互不干扰。
         * 步骤3必须在最后执行，因为它依赖于前两步的结果，以确保我们获得的是正确的、当前窗口的最大值。
         * 这种设计确保了算法的正确性和效率，使得我们能够在O(n)的时间复杂度内解决滑动窗口最大值问题。
         * */
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0) return new int[0];
            int n = nums.length;
            int[] result = new int[n - k + 1];
            Deque<Integer> deque = new ArrayDeque<>(); // 存放的是索引
            //相當於左指針left： deque.pollFirst()
            //右指針right： i
            for (int i = 0; i < n; i++) {
                //step1.最左邊元素出隊列 維護窗口大小k(索引差k-1, 實際元素為k)
                // 移除窗口外的元素 窗口大小剛大於k時,距離為right -left = i - deque.peekFirst() > k - 1
                if (!deque.isEmpty() && i - deque.peekFirst() > k - 1) {
                    deque.pollFirst();
                }

                //step2.最右邊出隊列維護單調遞減，然後加入當前元素最右邊進隊列
                // 移除所有比当前元素nums[i]小的元素（从队列的尾部开始）
                //双端队列会保持递减顺序，队首始终是窗口中的最大值。
                while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                    deque.pollLast();
                }
                // 将当前元素加入到队列中
                deque.offerLast(i);

                //step3.取最左邊元素 計算結果，
                // 当窗口形成时，将当前最大值加入结果中
                if (i >= k - 1) {
                    result[i - k + 1] = nums[deque.peekFirst()];
                }

            }
            return result;
        }
    }






    class Solution3 {
        //sliding window + two pointer
        // tc:o(n*k) 會超時

        /**
         * 时间复杂度分析： O(n * k)
         * 初次计算第一个窗口的最大值需要 O(k) 时间。
         * 在之后的每次窗口滑动过程中：
         * 如果移除的元素是当前窗口的最大值，则需要重新遍历窗口（大小为 k），找到新的最大值。这一步需要 O(k) 时间。
         * 否则，仅需 O(1) 的时间更新最大值。
         * 最坏情况下，当窗口滑动时每次都需要重新遍历窗口，导致每次滑动都需要 O(k) 时间。
         * 因为总共有 n - k + 1 个窗口，因此最坏情况下，总时间复杂度为 O(n * k)。
         */
        public int[] maxSlidingWindow(int[] nums, int k) {
            List<Integer> res = new ArrayList<>();
            int curMax = Integer.MIN_VALUE;
            //先計算第一個窗口的最大值
            for (int i = 0; i < k; i++) {
                curMax = Math.max(curMax, nums[i]);
            }
            res.add(curMax);

            //每次整體右移動一步，計算窗口最大值
            int left = 0;
            for (int right = k; right < nums.length; right++) {
                left++;
                //如果左邊移除的元素為當前最大值，則需重新計算最大值
                if (nums[left - 1] == curMax) {
                    curMax = Integer.MIN_VALUE;
                    for (int j = left; j <= right; j++) {
                        curMax = Math.max(curMax, nums[j]);
                    }
                } else {
                    //否則只需將右邊加入的值與curMax比較更新即可
                    curMax = Math.max(curMax, nums[right]);
                }
                res.add(curMax);
            }
            return res.stream().mapToInt(i -> i).toArray();
        }
    }

    class Solution2 {
        //monotonic queue
        // tc: 會超時

        /**
         * 总时间复杂度：O(n * k)
         * 对于每个窗口，插入和删除操作的时间复杂度分别为 O(log k) 和 O(k)。
         * 因此，每次滑动窗口的总时间复杂度为 O(k)。
         * 对于 n 个元素，整体时间复杂度为 O(n * k)。
         * 为什么会超时：
         * 最大顶堆的删除操作不是 O(1)，而是 O(k)，这导致每次删除元素时的开销很大。
         * 因此，整个算法的时间复杂度为 O(n * k)，与直接使用暴力法遍历窗口的时间复杂度相同，无法通过大数据集的性能要求。
         */
        public int[] maxSlidingWindow(int[] nums, int k) {
            //降序隊列，即最大頂堆，對頂元素最大
            List<Integer> res = new ArrayList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            int left = 0;
            int right = 0;
//            for (int i = 0; i <= nums.length-1 && left <= nums.length - k; i++) {
            while (right <= nums.length - 1 && left <= nums.length - k) {
                if (pq.size() < k) {
                    pq.offer(nums[right]);
                    right++;
                } else {
                    //維護窗口大小的最大頂堆，並每次poll堆頂元素即為最大值
                    res.add(pq.peek());
                    //移除窗口左邊元素，繼續右移窗口一步，進行下一次計算
                    pq.remove(nums[left]);
                    //左指針右移動一步
                    left++;
                }
            }
            //这个操作位于 while 循环的最后，它在循环结束后再次将当前堆顶元素（即当前窗口的最大值）加入结果集中
            res.add(pq.peek());
            return res.stream().mapToInt(i -> i).toArray();
        }
    }

    class Solution4 {
        //        这个优化后的版本的时间复杂度依然是 O(n * k)，
        //        因为最大顶堆的插入是 O(log k)，删除操作最差仍然是 O(k)。
        public int[] maxSlidingWindow(int[] nums, int k) {
            // 降序堆，最大顶堆
            List<Integer> res = new ArrayList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            int left = 0;
            int right = 0;
            while (right < nums.length) {
                // 向堆中加入当前右边指针指向的元素
                pq.offer(nums[right]);
                right++;

                // 当窗口的大小等于 k 时，加入当前窗口的最大值
                if (right - left == k) {
                    res.add(pq.peek());  // 窗口形成后，添加堆顶元素（即最大值）

                    // 移除窗口左边的元素
                    pq.remove(nums[left]);
                    left++;  // 左指针右移，滑动窗口
                }
            }

            // 直接返回结果
            return res.stream().mapToInt(i -> i).toArray();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}