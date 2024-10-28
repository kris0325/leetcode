import java.util.Arrays;
import java.util.Stack;

/**
 * Given a circular integer array nums (i.e., the next element of nums[nums.length
 * - 1] is nums[0]), return the next greater number for every element in nums.
 * <p>
 * The next greater number of a number x is the first greater number to its
 * traversing-order next in the array, which means you could search circularly to find
 * its next greater number. If it doesn't exist, return -1 for this number.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number.
 * The second 1's next greater number needs to search circularly, which is also 2.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1,2,3,4,3]
 * Output: [2,3,4,-1,4]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 10⁴
 * -10⁹ <= nums[i] <= 10⁹
 * <p>
 * <p>
 * Related Topics Array Stack Monotonic Stack 👍 7924 👎 196
 */
       
/*
 2024-07-06 20:12:22

 Next Greater Element II
Category	Difficulty	Likes	Dislikes
algorithms	Medium (63.83%)	7924	196
Tags
stack

Companies
google
*/

class NextGreaterElementIi {
    public static void main(String[] args) {
        Solution solution = new NextGreaterElementIi().new Solution();
        int[] nums = {1,2,1};
        solution.nextGreaterElements(nums);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution1 {
        public int[] nextGreaterElements(int[] nums) {
            Stack<Integer> stack = new Stack<>();
            int[] res = new int[nums.length];
            int n = nums.length;
            //兩個數組nums拼接即組成來循環數組，遍歷新數組
            // index映射關係：2個數組的index： i % n 映射到單個數組的index ：i
            for (int i = 2 * n - 1; i >= 0; i--) {
                while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                    stack.pop();
                }
                res[i % n] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(nums[i % n]);
            }
            return res;
        }
    }

    class Solution0 {
        //monotonic stack:
        // circular integer array, just join two nums can get newNums =  nums + nums, then traverse newNums
        //can handle index i in newNums by i % nums.length = real index in nums[]
        //處理循環數組：將2個原數組拼接即可得到一個大數組，元素即滿足循環數組的特性，
        // 當然也可以不擴充數組，遍歷數組過程中模擬走兩遍就能實現，即i < nums.length * 2,
        //不過注意處理下標index，將 i % nums.length， 便可將i映射到原數組對應的下標
        public int[] nextGreaterElements(int[] nums) {
            Stack<Integer> stack = new Stack<>();
            int[] result = new int[nums.length];
            Arrays.fill(result, -1);
            for (int i = 0; i < nums.length * 2; i++) {
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i % nums.length]) {
                    result[stack.peek()] = nums[i % nums.length];
                    stack.pop();
                }
                stack.push(i % nums.length);
            }
            return result;
        }
    }

    public class Solution {
        public int[] nextGreaterElements(int[] nums) {
            int n = nums.length;
            int[] result = new int[n];
            // 初始化结果数组，默认值为 -1（表示没有下一个更大元素）
            Arrays.fill(result, -1);
            Stack<Integer> stack = new Stack<>(); // 栈存储元素的索引

            // 遍历数组两次，模拟循环数组的特性
            for (int i = 0; i < 2 * n; i++) {
                // 通过取模操作，使得索引在数组范围内，//注意處理下標index，將 i % nums.length， 便可將i映射到原數組對應的下標
                int num = nums[i % n];
                // 如果当前元素大于栈顶元素对应的值，说明找到了下一个更大元素
                while (!stack.isEmpty() && nums[stack.peek()] < num) {
                    result[stack.pop()] = num; // 更新栈顶元素的下一个更大元素
                }
                // 当前元素入栈，循环数组的特性，只需将 i 在 【0，n-1】范围内，将索引入栈（只入一次）
                //將 i % nums.length， 便可將i映射到原數組對應的下標，保证将入栈的索引对应表示的值是唯一的
                stack.push(i % nums.length);
            }

            return result;
        }
    }

    //如何題目變化數組為普通數組
    class Solution_commonArray {
        //tc : o(n)
        public int[] nextGreaterElements(int[] nums) {
            int n = nums.length;
            int[] result = new int[n];
            // 初始化结果数组，默认值为 -1（表示没有下一个更大元素）
            Arrays.fill(result, -1);
            Stack<Integer> stack = new Stack<>(); // 栈存储元素的索引

            // 只需要遍历一次数组
            for (int i = 0; i < n; i++) {
                // 如果当前元素大于栈顶元素对应的值，说明找到了下一个更大元素
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                    result[stack.pop()] = nums[i]; // 更新栈顶元素的下一个更大元素
                }
                // 将当前元素索引压入栈
                stack.push(i);
            }

            return result;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}