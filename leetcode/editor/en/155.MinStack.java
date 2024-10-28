import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element
 * in constant time.
 * <p>
 * Implement the MinStack class:
 * <p>
 * <p>
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * <p>
 * <p>
 * You must implement a solution with O(1) time complexity for each function.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * Output
 * [null,null,null,null,-3,null,0,-2]
 * <p>
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * -2³¹ <= val <= 2³¹ - 1
 * Methods pop, top and getMin operations will always be called on non-empty
 * stacks.
 * At most 3 * 10⁴ calls will be made to push, pop, top, and getMin.
 * <p>
 * <p>
 * Related Topics Stack Design 👍 14446 👎 896
 */
       
/*
 2024-10-26 17:01:54
*/

class MinStack {
    public static void main(String[] args) {
        Solution solution = new MinStack().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class MinStack {
        Deque<Integer> stack;
        //peek維護最小值
        Deque<Integer> minStack;

        public MinStack() {
            stack = new LinkedList<Integer>();
            minStack = new LinkedList<Integer>();
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int val) {
            stack.push(val);
            //push 操作時，minStack 與stack同步執行push, size大小相同，
            /**
             * 1如果 val > minStack.peek()，minStack 会推入 minStack.peek()，而不是 val。
             * 2如果 val <= minStack.peek()，minStack 会推入 val。
             *  情況1可能會導致minStack有重複元素
             */
            //所以pop時，只需直接同步執行pop即可，簡化了pop操作
            minStack.push(Math.min(val, minStack.peek()));
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

//    class MinStack2 {
//        private Stack<Integer> stack;
//        private Stack<Integer> minStack;
//
//        public MinStack2() {
//            stack = new Stack<>();
//            minStack = new Stack<>();
//        }
//
//        public void push(int val) {
//            stack.push(val);
//            if (minStack.isEmpty() || val <= minStack.peek()) {
//                //只有較小值才會push到minStack
//                minStack.push(val);
//            }
//        }
//
//        public void pop() {
//            if (!stack.isEmpty()) {
//                if (stack.peek().equals(minStack.peek())) {
//                    minStack.pop();
//                }
//                stack.pop();
//            }
//        }
//
//        public int top() {
//            return stack.peek();
//        }
//
//        public int getMin() {
//            return minStack.peek();
//        }
//    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)

}