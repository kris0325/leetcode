import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given an array of integers temperatures represents the daily temperatures,
 * return an array answer such that answer[i] is the number of days you have to wait
 * after the iᵗʰ day to get a warmer temperature. If there is no future day for which
 * this is possible, keep answer[i] == 0 instead.
 * <p>
 * <p>
 * Example 1:
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * <p>
 * Example 2:
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * <p>
 * Example 3:
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= temperatures.length <= 10⁵
 * 30 <= temperatures[i] <= 100
 * <p>
 * <p>
 * Related Topics Array Stack Monotonic Stack 👍 13122 👎 319
 */
       
/*
 2024-07-06 14:40:05

 Daily Temperatures
Category	Difficulty	Likes	Dislikes
algorithms	Medium (66.00%)	13122	319
Tags
hash-table | stack

Companies
Unknown
*/

class DailyTemperatures {
    public static void main(String[] args) {
        Solution solution = new DailyTemperatures().new Solution();
        int[] tem = {89,62,70,58,47,47,46,76,100,70};
        solution.dailyTemperatures(tem);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        //monotonic stack
        public int[] dailyTemperatures(int[] temperatures) {
            //Stack 維護temperatures的索引index
            Stack<Integer> stack = new Stack<>();
            int[] res = new int[temperatures.length];
            for (int i = temperatures.length - 1; i >= 0; i--) {
                while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
                    stack.pop();
                }
                res[i] = stack.isEmpty()
                        ? 0 : stack.peek() - i;
                stack.push(i);
            }
            return res;
        }
    }

    class Solution1 {
        //單調棧：放元素的下標, 求result[i]，即找到右邊第一個比result[i]大的數，求二者index差值
        public int[] dailyTemperatures(int[] temperatures) {
            Stack<Integer> stack = new Stack<>();
            //result 默認值都是0
            int[] result = new int[temperatures.length];
            stack.add(0);
            for (int i = 1; i < temperatures.length; i++) {
                if (temperatures[i] < temperatures[stack.peek()]) {
                    stack.push(i);
                } else if (temperatures[i] == temperatures[stack.peek()]) {
                    stack.push(i);
                } else {
                    while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                        //注意：這裡計算的是 下標為stack.peek()的結果，找到，則求二者index差值：i - stack.peek()
                        result[stack.peek()] = i - stack.peek();
                        stack.pop();
                    }
                    //沒找到，則一直stack.pop()，如果stack.isEmpty()，就是一直沒找到，那麼result[stack.peek()]就是默認值0
                    stack.push(i);
                }
            }
            return result;
        }
    }


    class Solution2 {
        //Monotonic Stack 單調棧：放元素的下標, 求result[i]，即找到右邊第一個比result[i]大的數，求二者index差值
        //優化簡化寫法版本
        public int[] dailyTemperatures(int[] temperatures) {
            Stack<Integer> stack = new Stack<>();
            //result 默認值都是0
            int[] result = new int[temperatures.length];
            for (int i = 0; i < temperatures.length; i++) {
                while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                    result[stack.peek()] = i - stack.peek();
                    stack.pop();
                }
                stack.push(i);
            }
            return result;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}