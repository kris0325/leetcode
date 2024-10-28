import java.util.Stack;

/**
 * Given n non-negative integers representing an elevation map where the width of
 * each bar is 1, compute how much water it can trap after raining.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,
 * 1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are
 * being trapped.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * n == height.length
 * 1 <= n <= 2 * 10⁴
 * 0 <= height[i] <= 10⁵
 * <p>
 * <p>
 * Related Topics Array Two Pointers Dynamic Programming Stack Monotonic Stack 👍
 * 31954 👎 510
 */
       
/*
 2024-07-06 21:15:51

 Trapping Rain Water
Category	Difficulty	Likes	Dislikes
algorithms	Hard (61.92%)	31954	510
Tags
array | two-pointers | stack

Companies
amazon | apple | bloomberg | google | twitter | zenefits

*/

class TrappingRainWater {
    public static void main(String[] args) {
        Solution solution = new TrappingRainWater().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        //只有高度從低變高時，才能形成凹槽，收集水
        //所以需要構建遞減單調棧，遇到右邊比棧頂高，便計算凹槽的左牆+湖底+右牆
        // 彈出棧頂作為湖底，那麼下一個棧頂為左牆，當前元素為右牆壁， 能收集水的體積 =  validHeight高度： min(左牆，右牆) *  收集水的寬度： i-左牆index -1
        public int trap(int[] height) {
            int res = 0;
            //遞減單調棧，存儲高度對應的index
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < height.length; i++){
                while (!stack.isEmpty() && height[i]>height[stack.peek()]){
                    int bottomIndex = stack.pop();
                    if(stack.isEmpty()){
                        //表示沒有左牆，無法收集水
                        break;
                    }
                    int left = height[stack.peek()];
                    int right = height[i];
                    int validHeight = Math.min(left,right);
                    int width = i -stack.peek() -1;
                    res += (validHeight - height[bottomIndex]) * width;
                }
                stack.push(i);
            }
            return res;
        }
    }

    class Solution2 {
        //只有高度從低變高時，才能形成凹槽，收集水
        //dp 想像從左邊照來一束光，形成陰影，再從右邊照來一束光，形成陰影，
        // 收集的雨水即計算雨水面積，取二者陰影最小高度，再與當前高度的求差即為收集雨水的有效高度，再乘以最小寬度為1，則得到雨水面積
        public int trap(int[] height) {
            if (height.length == 0) return 0;
            int N = height.length;
            int res = 0;
            int[] left = new int[N], right = new int[N];
            left[0] = height[0];
            right[N-1] = height[N-1];
            for (int i = 1; i < N; i++) {
                //光照到當前index的陰影面積
                left[i] = Math.max(left[i-1], height[i]);
            }
            for (int i= N-2; i >= 0; i--) {
                //光照到當前index的陰影面積
                right[i] = Math.max(right[i+1], height[i]);
            }
            for (int i =0; i < N; i++){
                //光照到當前index的可收集水的面積
                res += Math.min(left[i], right[i]) - height[i];
            }
            return res;
        }
    }


    class Solution1 {
        //monotonic stack: 按行 累加計算trap雨水面積
        //stack 放元素下標，遍歷height[i], 棧頂放較小元素下標， 遇到右邊第一個較大的值，更新stack
        // 求能收集雨水面積，即凹槽面積 = 高度*寬度 =( min(棧第二元素高度, height(i)) - height[stack.pop()](凹槽底部) )     *  (i - 棧第二元素高度index -1)
        public int trap(int[] height) {
            Stack<Integer> stack = new Stack<>();
            stack.push(0);
            int sumTrap = 0;
            for (int i = 1; i < height.length; i++) {
                if (height[stack.peek()] > height[i]) {
                    stack.push(i);
                } else if (height[stack.peek()] == height[i]) {
                    // 因为相等的相邻墙，左边一个是不可能存放雨水的，所以pop左边的index, push当前的index
                    stack.pop();
                    stack.push(i);
                }
                //if (height[stack.peek()] < height[i])
                 else  {
                     //loop計算以height[i]為右邊柱，遍歷stack每一個index，計算每一凹槽的trap雨水的面積，並累加
                    while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                        //棧頂stack第一個元素即為凹槽中間（底部）
                        int mid = stack.pop();

                        if(!stack.isEmpty()){
                            //stack第二個元素即為凹槽左邊
                            int left = stack.peek();
                            //height[i]為凹槽右遍，接水的高度取決於凹槽左右遍的最小高度
                            int currentHeight = Math.min(height[left], height[i]) - height[mid];
                            int width = i - left - 1;
                            int trap = currentHeight * width;
                            sumTrap += trap;
                        }
                    }
                    stack.push(i);
                }
            }
            return sumTrap;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}