import java.util.PriorityQueue;
import java.util.Random;

/**
 * Given an integer array nums and an integer k, return the kᵗʰ largest element in
 * the array.
 * <p>
 * Note that it is the kᵗʰ largest element in the sorted order, not the kᵗʰ
 * distinct element.
 * <p>
 * Can you solve it without sorting?
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * <p>
 * Example 2:
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= k <= nums.length <= 10⁵
 * -10⁴ <= nums[i] <= 10⁴
 * <p>
 * <p>
 * Related Topics Array Divide and Conquer Sorting Heap (Priority Queue)
 * Quickselect 👍 17232 👎 904
 */
       
/*
 2024-09-17 22:58:53
*/

class KthLargestElementInAnArray {
    public static void main(String[] args) {
        Solution solution = new KthLargestElementInAnArray().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        //quick sort的思路
        //TC :(n)
        // 主方法：直接找到数组中第k大的元素
        // 主函数，调用 quickselect 来找到第 k 大的元素
        public int findKthLargest(int[] nums, int k) {
            int n = nums.length;
            // 快速选择算法寻找数组中第 n - k 小的元素
            return quickSelect(nums, 0, n - 1, n - k);
        }

        // 快速选择函数，用于在 nums 的 [left, right] 区间寻找第 k 小的元素
        private int quickSelect(int[] nums, int left, int right, int k) {
            if (left == right) {  // 只有一个元素的情况
                return nums[left];
            }

            // 随机选择一个基准点
            Random rand = new Random();
            int pivotIndex = left + rand.nextInt(right - left + 1);

            // 分区函数，返回分区后基准点的位置
            pivotIndex = partition(nums, left, right, pivotIndex);

            // 根据基准点的位置判断第 k 小的元素是在左边、右边还是基准点本身
            if (k == pivotIndex) {
                return nums[k]; // 第 k 小的元素就是基准点元素
            } else if (k < pivotIndex) {
                // 递归在左边的部分寻找
                return quickSelect(nums, left, pivotIndex - 1, k);
            } else {
                // 递归在右边的部分寻找
                return quickSelect(nums, pivotIndex + 1, right, k);
            }
        }

        // 分区函数，选择 pivot 并对数组进行分区
        private int partition(int[] nums, int left, int right, int pivotIndex) {
            int pivotValue = nums[pivotIndex];
            // 将基准点移到右边
            swap(nums, pivotIndex, right);
            int storeIndex = left;

            // 遍历数组，分区操作
            for (int i = left; i < right; i++) {
                if (nums[i] < pivotValue) {
                    // 将小于基准点的元素放到左边
                    swap(nums, storeIndex, i);
                    storeIndex++;
                }
            }

            // 把基准点放回到最终的位置
            swap(nums, storeIndex, right);

            // 返回基准点的位置
            return storeIndex;
        }

        // 交换数组中的两个元素
        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }


    class Solution2 {
        //TC :(nlogn)
        //求第k大，用pq維護大小為k的最小堆，堆顶即为所求
        //如果是求第k小，则用pq維護大小為k的最大堆，堆顶即为所求
        public int findKthLargest(int[] nums, int k) {
            //構造維護一個包含k個元素的最小堆，那麼pq.peek()便是第k大的元素
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
            for (int num : nums) {
                pq.offer(num);
                if (pq.size() > k) {
                    //每次彈出堆頂元素，都是移除最小元素
                    pq.poll();
                }
            }
            //最後剩下k個元素，堆頂為最小元素，即為第k大的元素
            return pq.peek();
        }
    }

    class Solution3 {
        //TC :(nlogn)
        // 基于merge sort排序，取第k大的元素
        public int findKthLargest(int[] nums, int k) {
            mergeSort(nums, 0, nums.length - 1);
            // 返回第k大的元素，数组已降序排列，直接返回第k-1个元素
            return nums[k - 1];
        }

        // 在指定范围内对数组进行归并排序
        public void mergeSort(int[] nums, int left, int right) {
            if (left >= right) {
                return; // 数组只有一个元素时，直接返回
            }
            int mid = left + (right - left) / 2;
            mergeSort(nums, left, mid); // 递归左半部分
            mergeSort(nums, mid + 1, right); // 递归右半部分
            merge(nums, left, mid, right); // 合并左右部分
        }

        // 合并左右两个已经排序好的数组
        public void merge(int[] nums, int left, int mid, int right) {
            int[] temp = new int[right - left + 1]; // 临时数组存储合并结果
            int i = left, j = mid + 1, k = 0;

            // 将较大元素按降序放入临时数组
            while (i <= mid && j <= right) {
                if (nums[i] > nums[j]) {
                    temp[k++] = nums[i++];
                } else {
                    temp[k++] = nums[j++];
                }
            }

            // 将左边剩余元素放入临时数组
            while (i <= mid) {
                temp[k++] = nums[i++];
            }

            // 将右边剩余元素放入临时数组
            while (j <= right) {
                temp[k++] = nums[j++];
            }

            // 将排序后的结果拷贝回原数组，
            // 注意：从nums的left下标开始拷贝，
            System.arraycopy(temp, 0, nums, left, temp.length);
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}