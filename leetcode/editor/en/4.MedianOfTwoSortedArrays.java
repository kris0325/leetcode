/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return
 * the median of the two sorted arrays.
 * <p>
 * The overall run time complexity should be O(log (m+n)).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10⁶ <= nums1[i], nums2[i] <= 10⁶
 * <p>
 * <p>
 * Related Topics Array Binary Search Divide and Conquer 👍 28664 👎 3218
 */
       
/*
 2024-09-12 20:44:46
*/

class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution0 {
        //暴力解法：合併數組後，再求中位數 tc:m=o(m+n)
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int len = nums1.length + nums2.length;
            int[] nums = new int[len];
            int i = 0;
            int j = 0;
            int k = 0;
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    nums[k++] = nums1[i++];
                } else {
                    nums[k++] = nums2[j++];
                }
            }
            if (i < nums1.length) {
                while (i < nums1.length) {
                    nums[k++] = nums1[i++];
                }
            }
            if (j < nums2.length) {
                while (j < nums2.length) {
                    nums[k++] = nums2[j++];
                }
            }
            return len % 2 != 0 ? (double) nums[len / 2]
                    : (double) (nums[(len / 2 - 1)] + nums[len / 2]) / 2.0;
        }
    }


    class Solution {
        //二分查找 O(log min(m,n))
        //找到分隔線 是的數組num1, num2 左邊元素交叉<=右邊元素，即找到中位數,（ 注意：為方便編碼，我們設定奇數個數時，中位數在分割線的右邊）
        //i為分隔線在num1的位置，j為分隔線在num2的位置，那麼i,j滿足條件, 分隔線左邊所有元素的個數需要滿足 i+j = totalLeft ，
        // 奇數： totalLeft = (m+n)/2，偶數 totalLeft = (m+n+1)/2, 由於“/”向下取整的特性，
        // 所以可以合併寫法 totalLeft = (m+n+1)/2,為了避免整型溢出問題，可以寫成totalLeft= m+ (n-m+1)/2
        //由於特性：j = totalLeft-i,所以我們只需在一個數組上尋找到滿足條件的分隔線i，那麼便得到了另一個數組上的分隔線j
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            //選擇在較小數組上進行查找分隔線，查找的规模将会最小化。因此，整个算法的时间复杂度将控制在 O(log(min(m, n)))，而不是 O(log(m + n))。
            if (nums1.length > nums2.length) {
                return findMedianSortedArrays(nums2, nums1);
            }
            int m = nums1.length;
            int n = nums2.length;
            //分隔線左邊所有元素的個數需要滿足 totalLeft = m + (n - m + 1) / 2;
            int totalLeft = m + (n - m + 1) / 2;
            //我們的目的是在nums1的區間上查找分割線，實現滿足左邊元素滿足交叉小於等於右邊元素，即找到中位數
            //即：num1[i-1]<=num2[j] && nums2[j-1]<=num1[i]
            //i-1為nums1中分隔線左邊的元素，i為右邊的元素，同理j， nums2
            int left = 0;
            int right = m;
            while (left < right) {
                //nums1的分隔線， +1的目的是為防止越界
                int i = left + (right - left + 1) / 2;
                //nums2的分隔線
                int j = totalLeft - i;
                //對預期滿足條件進行取反，進行判斷，從而不斷更新left,right，在nums1上搜索滿足找到找到2個數組的中位數的條件
                if (nums1[i - 1] > nums2[j]) {
                    //num1分隔線左邊元素過大，那麼更新右邊界
                    right = i - 1;
                } else {
                    //實際condition即：num1分隔線右邊元素過小，那麼更新左邊界
//                    if(nums2[j-1]> nums1[i])
                    left = i;
                }
            }
            //跳出while循環時，便找到分隔線i= left;
            int i = left;
            int j = totalLeft - i;
            //nums1分隔線左邊旁邊元素，即左邊最大值，考慮索引越界問題，所以i=0無意義，要求左邊最大值，所有i==0? Integer.MIN_VALUE
            //或者可以理解為，此時求合併後數組的leftMax 只需考慮 nums2LeftAMax,因為nums1分隔線左邊已經沒有元素了，所以將其設為最小整數Integer.MIN_VALUE
            int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            //nums1分隔線右邊旁邊元素，即右邊最大值，考慮索引越界問題， 所以i=m無意義，要求右邊最小值，i==m? Integer.MAX_VALUE
            int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];
            //同理nums2
            int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];

            if ((m + n) % 2 == 0) {
                //數組個數為偶數個元素的中位數 = 分割線左右元素之和/2
                return (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2.0;
            } else {
                // 數組個數為奇數個數（ 注意：為方便編碼，我們設定奇數個數時，中位數在分割線的右邊），所以中位數表達如下
                return Math.max(nums1LeftMax, nums2LeftMax);
            }
        }

        /**
         * 因为这个算法是通过二分查找在 nums1 上寻找分割线，分割线的位置是动态调整的，不一定在 nums1 的正中间。下面解释为何可能会出现 i == 0 的情况：
         *
         * 分割线的位置是动态的
         * 这个算法的目标是找到两个数组 nums1 和 nums2 的合并后数组的中位数。我们在 nums1 上进行二分查找，目的是找到一个合适的分割线 i，使得它和 nums2 上的分割线 j 满足以下两个条件：
         *
         * 左半部分的所有元素小于或等于右半部分的所有元素。
         * 左半部分的元素数量和右半部分的元素数量之和恰好等于整个数组长度的一半（加一用于处理奇数情况）。
         * 由于 nums1 和 nums2 的长度可能不同，我们不一定能将分割线放在 nums1 的正中间。分割线的位置 i 是通过调整二分查找的 left 和 right 来确定的。
         *
         * 为什么会出现 i == 0 的情况？
         * i == 0 的情况表示分割线位于 nums1 的最左边，也就是说，nums1 的左半部分是空的。出现这种情况的原因是，可能在**nums2 的左边部分有足够多的元素**，从而导致 nums1 的分割线被推到了最左边。
         *
         * 假设有两个极端情况：
         *
         * 1.nums1 比 nums2 短很多：
         * 如果 nums1 的长度非常短，而 nums2 的左半部分元素可以满足分割线条件，那么分割线可能会被推到 nums1 的最左边。
         * 2.nums1 的所有元素都比 nums2 的右半部分大：
         * 如果 nums1 中的所有元素都比 nums2 的右半部分大，那么 nums1 的所有元素都应该位于分割线的右侧，而分割线在 nums1 的最左边（i == 0）。
         * 此时，nums2 的左边部分已经包含足够多的较小元素，分割线位于 nums1 的最左边而不是中间。
         * */
    }

    public class Solution3 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            // 确保 nums1 是较短的数组
            if (nums1.length > nums2.length) {
                return findMedianSortedArrays(nums2, nums1);
            }

            int m = nums1.length;
            int n = nums2.length;
            int totalLeft = (m + n + 1) / 2;  // 找到合并数组左半部分的元素个数
            int left = 0, right = m;

            while (left <= right) {
                // 对 nums1 进行二分查找，选择 nums1 中间的位置 i
                int i = left + (right - left) / 2;
                // j 是 nums2 中与 i 对应的位置，使得左半部分有 totalLeft 个元素
                int j = totalLeft - i;

                // 边界条件：i > 0 表示 nums1 左侧有元素，j > 0 表示 nums2 左侧有元素
                // 如果 nums1[i-1] > nums2[j]，说明 i 过大，需要向左缩小
                if (i > 0 && nums1[i - 1] > nums2[j]) {
                    right = i - 1;
                }
                // 如果 nums2[j-1] > nums1[i]，说明 i 过小，需要向右扩大
                else if (i < m && nums2[j - 1] > nums1[i]) {
                    left = i + 1;
                }
                // 满足条件的情况，说明找到了正确的分割
                else {
                    // 处理左半部分的最大值
                    int maxLeft;
                    if (i == 0) {
                        maxLeft = nums2[j - 1];  // nums1 为空时，最大值来自 nums2
                    } else if (j == 0) {
                        maxLeft = nums1[i - 1];  // nums2 为空时，最大值来自 nums1
                    } else {
                        maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);  // 左半部分的最大值
                    }

                    // 如果总长度是奇数，直接返回左半部分的最大值
                    if ((m + n) % 2 == 1) {
                        return maxLeft;
                    }

                    // 处理右半部分的最小值
                    int minRight;
                    if (i == m) {
                        minRight = nums2[j];  // nums1 已分割完，最小值来自 nums2
                    } else if (j == n) {
                        minRight = nums1[i];  // nums2 已分割完，最小值来自 nums1
                    } else {
                        minRight = Math.min(nums1[i], nums2[j]);  // 右半部分的最小值
                    }

                    // 如果总长度是偶数，返回左右两部分的平均值
                    return (maxLeft + minRight) / 2.0;
                }
            }
            throw new IllegalArgumentException("Input arrays are not sorted properly.");
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}