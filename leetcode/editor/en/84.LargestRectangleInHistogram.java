package en;

import java.util.Stack;

/**
 * Given an array of integers heights representing the histogram's bar height
 * where the width of each bar is 1, return the area of the largest rectangle in the
 * histogram.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: heights = [2,4]
 * Output: 4
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= heights.length <= 10⁵
 * 0 <= heights[i] <= 10⁴
 * <p>
 * <p>
 * Related Topics Array Stack Monotonic Stack 👍 17153 👎 280
 */

/*
 2024-07-07 13:04:39

 Largest Rectangle in Histogram
Category	Difficulty	Likes	Dislikes
algorithms	Hard (44.42%)	17153	280
Tags
array | stack

Companies
Unknown
*/

class LargestRectangleInHistogram {
    public static void main(String[] args) {
        Solution solution = new LargestRectangleInHistogram().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //tc: o(n)
        //monotonic stack: 凸槽計算最大面積，與42.TrappingRainWater trap雨水面積類似（計算凹槽面積），但處理方式有些不同，
        //本題是高度變高時，不用計算面積，因為寬度累加後會得到更大的面積，只需當高度變低時，計算面積，更新面積最值
        public int largestRectangleArea(int[] heights) {
            int res = 0;
            //stack存儲index，且維護遞增的單調棧
            Stack<Integer> stack = new Stack<>();
            //step1.豎直（按照高度）方向計算最大面積
            for (int i = 0; i < heights.length; i++) {
                while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                    int prevHeight = heights[stack.pop()];
                    //width = i - prevHeight對應的Index
                    int width = i - (stack.isEmpty() ? 0 : stack.peek() + 1);
                    res = Math.max(res, prevHeight * width);
                }
                stack.push(i);
            }
            //stack不為空時， step2.水平（按照寬度）方向計算最大面積
            while (!stack.isEmpty()) {
                int prevHeight = heights[stack.pop()];
                //width = heights最後位子 - prevHeight對應的Index
                int width = heights.length - (stack.isEmpty() ? 0 : stack.peek() + 1);
                res = Math.max(res, prevHeight * width);
            }
            return res;
        }
    }


    class Solution1 {
        //monotonic stack: 凸槽計算最大面積，與42.TrappingRainWater trap雨水面積類似（計算凹槽面積），但處理方式有些不同，
        public int largestRectangleArea(int[] heights) {
            int[] newHeights = new int[heights.length + 2];
            System.arraycopy(heights, 0, newHeights, 1, heights.length);
            //新數組首部加0， 尾部加0輔助計算，為了讓首，尾元素都能參與計算面積，不會遺漏
            newHeights[0] = 0;
            newHeights[heights.length + 1] = 0;
            Stack<Integer> stack = new Stack<>();
            stack.push(0);
            int result = 0;
            for (int i = 1; i < newHeights.length; i++) {
                //不需要在 while 循环中检查 stack.isEmpty() 条件，
                // 因為初始化时的处理： 在初始化时，我们将 0 压入栈中（stack.push(0)）。
                // 这意味着在任何时候，栈中至少会有一个元素，所以 stack.isEmpty() 永远不会为真。
//                while (！stack.isEmpty() && newHeights[stack.peek()] > newHeights[i]){
                while (newHeights[stack.peek()] > newHeights[i]) {
                    int mid = stack.pop();
                    //width = right - left -1
                    int width = i - stack.peek() - 1;
                    int height = newHeights[mid];
                    result = Math.max(result, width * height);
                }
                //簡化了newHeights[stack.peek()] <= newHeights[i]的case
                stack.push(i);
            }
            return result;
        }
    }

    class Solution0 {
        //雙指針: 凸槽計算最大面積，與trap雨水面積類似（計算凹槽面積），但處理方式有些不同，
        // 面積 are[i] =  width * heights[i] = (rightFirstLessIndex - leftFirstLessIndex -1) *heights[i]
        // result = max(are[i])

        public int largestRectangleArea(int[] heights) {
            int[] leftFirstLessIndex = new int[heights.length];
            int[] rightFirstLessIndex = new int[heights.length];
            //遍歷heights[i], 紀錄每個柱子i的左邊第一個小於i柱子的下標
            //初始化
            leftFirstLessIndex[0] = -1;
            for (int i = 1; i < heights.length; i++) {
                int t = i - 1;
                while (t >= 0 && heights[t] >= heights[i]) {
                    t = leftFirstLessIndex[t];
                }
                //找到左邊第一個小的 heights[t] < heights[i] ，則更新
                leftFirstLessIndex[i] = t;
            }
            //遍歷heights[i], 紀錄每個柱子i的右邊第一個小於i柱子的下標
            //初始化
            rightFirstLessIndex[heights.length - 1] = heights.length;
            for (int i = heights.length - 2; i >= 0; i--) {
                int t = i + 1;
                while (t < heights.length && heights[t] >= heights[i]) {
                    t = rightFirstLessIndex[t];
                }
                //找到右邊第一個小的 heights[t] < heights[i] ，則更新
                rightFirstLessIndex[i] = t;
            }
            int result = 0;
            for (int i = 0; i < heights.length; i++) {
                int sum = heights[i] * (rightFirstLessIndex[i] - leftFirstLessIndex[i] - 1);
                result = Math.max(result, sum);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}