import java.util.*;

/**
 * The next greater element of some element x in an array is the first greater
 * element that is to the right of x in the same array.
 * <p>
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where
 * nums1 is a subset of nums2.
 * <p>
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j]
 * and determine the next greater element of nums2[j] in nums2. If there is no
 * next greater element, then the answer for this query is -1.
 * <p>
 * Return an array ans of length nums1.length such that ans[i] is the next
 * greater element as described above.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 * Output: [-1,3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so
 * the answer is -1.
 * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
 * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so
 * the answer is -1.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums1 = [2,4], nums2 = [1,2,3,4]
 * Output: [3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
 * - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so
 * the answer is -1.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 10⁴
 * All integers in nums1 and nums2 are unique.
 * All the integers of nums1 also appear in nums2.
 * <p>
 * <p>
 * <p>
 * Follow up: Could you find an
 * O(nums1.length + nums2.length) solution?
 * <p>
 * Related Topics Array Hash Table Stack Monotonic Stack 👍 7935 👎 742
 */
       
/*
 2024-07-06 15:32:07

 Next Greater Element I
Category	Difficulty	Likes	Dislikes
algorithms	Easy (72.17%)	7935	742
Tags
stack

Companies
Unknown
*/

class NextGreaterElementI {
    public static void main(String[] args) {
        Solution solution = new NextGreaterElementI().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution0{
        //monotonic stack
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            //先求出 nums2的 nextGreaterElement
            Map<Integer, Integer> map = new HashMap<>();
            int[] res = new int[nums1.length];
            Stack<Integer> stack = new Stack<>();
            //從後往前構造單調棧，維護從棧頂到棧底，從小到大
            for(int i = nums2.length-1; i >= 0; i--){
                while (!stack.isEmpty() && nums2[i]>=stack.peek()){
                    //不斷從棧頂彈出小於當前元素的元素，直到找到較大值
                    stack.pop();
                }
                //找到較大值或棧為空
                map.put(nums2[i], stack.isEmpty()? -1: stack.peek());
                //當前元素入棧，進行下一個元素比較
                stack.push(nums2[i]);
            }

            //遍歷nums1
            for(int i =0; i<nums1.length; i++){
                if (map.containsKey(nums1[i])){
                    res[i] = map.get(nums1[i]);
                }
            }
            return res;
        }
    }
    class Solution1 {
        //monotonic stack:
        //定義monotonic stack， 存nums2元素的下標，
        // 定義 hashmap<num1[i], i> num1map 來存放nums1元素與下標的映射關係，這樣方便在遍歷nums2時再定位到nums1對應的元素
        //遍歷nums2
        // 1 即num[i]<=stack.peek(),則入棧
        //2 否則 num[i] > stack.peek(), 則num[i]為stack.peek()的右邊第一個大的數，此時通過num1map 判斷stack.pop()元素是否屬於num1, 存在即可收集結果
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int[] result = new int[nums1.length];
            Arrays.fill(result, -1);
            HashMap<Integer, Integer> nums1Map = new HashMap<>();
            for (int i = 0; i < nums1.length; i++) {
                nums1Map.put(nums1[i], i);
            }
            Stack<Integer> stack = new Stack<>();
            stack.push(0);
            for (int i = 1; i < nums2.length; i++) {
                if (!stack.isEmpty() && nums2[stack.peek()] >= nums2[i]) {
                    stack.push(i);
                } else {
                    //尋找到右邊第一個較大的數，判斷stack.pop()是否出現在nums1中
                    while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                        if (nums1Map.containsKey(nums2[stack.peek()])) {
                            int indexOfNums1 = nums1Map.get(nums2[stack.peek()]);
                            result[indexOfNums1] = nums2[i];
                        }
                        //為確保stack是遞增的，必須stack.pop()較小的index, 然後加入當前index, stack.push(i)
                        stack.pop();
                    }
                    stack.push(i);
                }
            }
            return result;
        }
    }


    class Solution {
        //monotonic stack: 簡化版本

        //定義monotonic stack， 存nums2元素的下標，（ 从栈底到栈顶单调递减）
        // 定義 hashmap<num1[i], i> num1map 來存放nums1元素與下標的映射關係，這樣方便在遍歷nums2時再定位到nums1對應的元素
        //遍歷nums2
        // 1 即num[i]<=stack.peek(),則入棧
        //2 否則 num[i] > stack.peek(), 則num[i]為stack.peek()的右邊第一個大的數，此時通過num1map 判斷stack.pop()元素是否屬於num1, 存在即可收集結果
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int[] result = new int[nums1.length];
            Arrays.fill(result, -1);
            HashMap<Integer, Integer> nums1Map = new HashMap<>();
            for (int i = 0; i < nums1.length; i++) {
                nums1Map.put(nums1[i], i);
            }
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < nums2.length; i++) {
                //维护stack单调递减的特征：这个循环会一直弹出栈顶元素，直到栈为空或者栈顶元素对应的值大于等于当前遍历到的元素
                while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                    if (nums1Map.containsKey(nums2[stack.peek()])) {
                        int indexOfNums1 = nums1Map.get(nums2[stack.peek()]);
                        result[indexOfNums1] = nums2[i];
                    }
                    //当前元素stack.peek()已找到next greater ,所以从stack中删除
                    stack.pop();
                }
                stack.push(i);
            }
            return result;
        }

        /**
         * 注意：为何设计 monotonic stack，（ 从栈底到栈顶单调递减）？
         * 因为：
         * 1.栈中存储的是 nums2 元素的下标。
         * 2.在遍历 nums2 时，代码的核心逻辑是：
         * java
         * while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
         *     // ... 处理逻辑 ...
         *     stack.pop();
         * }
         * stack.push(i);
         *
         * 3.这个循环会一直弹出栈顶元素，直到栈为空或者栈顶元素对应的值大于等于当前遍历到的元素。
         * 4.然后，当前元素的下标被压入栈中。
         * 这个过程保证了栈中元素（对应的 nums2 中的值）是从栈底到栈顶单调递减的。因为：
         * 如果遇到一个较大的元素，它会把栈中所有比它小的元素都弹出。
         * 较小的元素会被压在较大元素的上面。
         * 所以，这个实现使用的是单调递减栈。这种方法可以有效地找到每个元素右侧第一个比它大的元素，这正是题目要求的"下一个更大元素"。
         * */
    }
//leetcode submit region end(Prohibit modification and deletion)

}