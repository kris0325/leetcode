/**
 * Given an unsorted integer array nums. Return the smallest positive integer that
 * is not present in nums.
 * <p>
 * You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary
 * space.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,2,0]
 * Output: 3
 * Explanation: The numbers in the range [1,2] are all in the array.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Explanation: 1 is in the array but 2 is missing.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 * Explanation: The smallest positive integer 1 is missing.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 10⁵
 * -2³¹ <= nums[i] <= 2³¹ - 1
 * <p>
 * <p>
 * Related Topics Array Hash Table 👍 16821 👎 1861
 */
       
/*
 2024-07-24 01:13:54
*/

class FirstMissingPositive {
    public static void main(String[] args) {
        Solution solution = new FirstMissingPositive().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 如果暴力解法：从1到n开始试探，推断 res 必定 落在区间 [1,n] 或者等于n+1, 如果直接排序，一般时间复杂度为 o(nlogn), 会超时
         * <p>
         * 那么可以通过indexing sort “交换”法，找到最小正整数，o(n)的是时间复杂度
         *
         * 主要思想：
         * 这个算法的目标是重新排列数组，使得每个正整数
         * i（1<=i<=n）被放在索引i-1的位置上。
         * 完成重排后，第一个满足nums[i]!=i+1条件的 索引i就是要找的答案
         *
         * 或者說 索引與值的關係：（或者说正整数 i-1 被放在i的位置上，索引基于0开始，代码中就是nums[i] == nums[nums[i] - 1]）
         * （大於n的數，就無須排序，只排序處理【1，n】範圍內為數）
         *https://github.com/wisdompeak/LeetCode/blob/master/Greedy/041.First-Missing-Positive/Readme.md
         *
         *
         * 041.First-Missing-Positive
         * 首先，将nums数组之前添加一个零元素。于是本题的一个思路是：如果尝试把正整数1~N尽可能地按照顺序放入nums[1]~nums[N]后，那么从头到尾再遍历一遍nums，找到的第一个nums[i]!=i的数，就是所求的结果。
         *
         * 那么如何尝试one pass就把正整数1~N尽可能地按照顺序放入nums[1]~nums[N]呢？这就是经典的array indexing的算法。遍历所有元素的时候，发现如果nums[i]!=i时，说明当前的nums[i]这个数没有被放置在合适的位置，根据对应的规则，它应该被放在nums[nums[i]]这个位置。
         *
         * 于是我们持续交换nums[i]和nums[nums[i]]。每一次交换的结果是：把nums[i]送到了它应该在的位置；同时i位置的元素又有了新的值，可以再次进行交换。如果这样的步骤持续到 nums[i]==i（说明第i个元素的位置已经得到满足了）或者nums[nums[i]]==nums[i]（说明第nums[i]个元素的位置也已经得到了满足），就不需要交换了。
         *
         * 当把所有的i都遍历一遍之后，就完成了“力所能及”的排序，意思就是nums里面所有处于1~N之间的数，都已经在它规定的位置上了。如前所述，此时从头到尾再遍历一遍nums，找到的第一个nums[i]!=i的数，就是原先nums里面1~N之间所缺失的数。
         *
         * 和本题非常相似的还有:
         *
         * 287.Find the Duplicate Number
         * 442.Find-All-Duplicates-in-an-Array
         * 448.Find-All-Numbers-Disappeared-in-an-Array
         * 645.Set Mismatch，
         * 也用到了array indexing.
         *
         */
        public int firstMissingPositive(int[] nums) {
            int len = nums.length;

            //indexing sort
            // 对于 nums[i] > 0 && nums[i] < len 值<len的元素，
            // 对nums排序，我们期望 index i的元素num[i]，在排序后的值为nums[i] - 1 (index 从0开始)
            //即 index ：0，1，2，3，4 对应的的nums[i]应该为：
            //        【1，2，3，4，5】， 也就是索引与 nums[i]的关系是： i = nums[i]-1，

            //即如果num[i]没放在正确的排序位置上，
            // 则需要 while 持续交换需要交换位置，直到当前元素直到满足条件
            for (int i = 0; i < len; i++) {
                //只需要对 符合【1，n】的元素排序，索引要加上条件：nums[i] > 0 && nums[i] < len
                while (nums[i] > 0 && nums[i] < len && nums[i] != nums[nums[i] - 1]) {
                    //如果没有放在正确的位置上，则需要把元素（index 为： nums[i]-1 ），放到正确位置上（ index 为i）
                    //即正确位置是满足值：nums[i] == nums[nums[i] - 1]，索引i = nums[i] - 1的特征
                    swap(nums, i, nums[i] - 1);
                }
            }

            //check for the first missing positive integer
            for (int i = 0; i < len; i++) {
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }
            //边界情况：
            //如果所有从 1 到 n 的数字都存在，答案就n+1
            return len + 1;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        /**
         * 时间复杂度分析
         * 看似是嵌套循环
         * for循环内部确实包含了一个while循环，这通常会让人认为时间复杂度可能是O(n^2)。
         * 实际执行次数
         * 然而，仔细分析会发现，while循环中的交换操作实际上并不会执行n次。
         * 每个元素最多交换一次
         * 每次交换都会将一个数放到它正确的位置上（索引为value-1的位置）。
         * 一旦一个数被放到正确的位置，它就不会再被移动。
         * 总交换次数不超过n
         * 数组中有n个元素。
         * 每个元素最多被交换一次。
         * 因此，总的交换次数不会超过n次。
         * for循环的作用
         * for循环主要是用来遍历数组，确保每个位置都被检查过。
         * while循环的作用
         * while循环负责将当前元素交换到正确的位置，直到当前位置存放了正确的数，或者不需要再交换。
         * 结论
         * 虽然代码中有嵌套循环，但实际上：
         * 外层for循环执行n次
         * 内层while循环中的交换操作总共不会超过n次
         * 因此，总的操作次数是O(n) + O(n) = O(n)，保持了线性时间复杂度。
         * 这种算法巧妙地利用了问题的特性，通过原地交换的方式，用O(1)的额外空间就实现了O(n)的时间复杂度，是一个非常精妙的解法。
         * */
    }
//leetcode submit region end(Prohibit modification and deletion)

}