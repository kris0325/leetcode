import java.util.Stack;

/**
 * Implement a first in first out (FIFO) queue using only two stacks. The
 * implemented queue should support all the functions of a normal queue (push, peek, pop,
 * and empty).
 * <p>
 * Implement the MyQueue class:
 * <p>
 * <p>
 * void push(int x) Pushes element x to the back of the queue.
 * int pop() Removes the element from the front of the queue and returns it.
 * int peek() Returns the element at the front of the queue.
 * boolean empty() Returns true if the queue is empty, false otherwise.
 * <p>
 * <p>
 * Notes:
 * <p>
 * <p>
 * You must use only standard operations of a stack, which means only push to top,
 * peek/pop from top, size, and is empty operations are valid.
 * Depending on your language, the stack may not be supported natively. You may
 * simulate a stack using a list or deque (double-ended queue) as long as you use
 * only a stack's standard operations.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input
 * ["MyQueue", "push", "push", "peek", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * Output
 * [null, null, null, 1, 1, false]
 * <p>
 * Explanation
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
 * myQueue.peek(); // return 1
 * myQueue.pop(); // return 1, queue is [2]
 * myQueue.empty(); // return false
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= x <= 9
 * At most 100 calls will be made to push, pop, peek, and empty.
 * All the calls to pop and peek are valid.
 * <p>
 * <p>
 * <p>
 * Follow-up: Can you implement the queue such that each operation is amortized O(
 * 1) time complexity? In other words, performing n operations will take overall O(
 * n) time even if one of those operations may take longer.
 * <p>
 * Related Topics Stack Design Queue 👍 7800 👎 446
 */
       
/*
 2024-10-23 23:11:45
*/

class ImplementQueueUsingStacks {
    public static void main(String[] args) {
        Solution solution = new ImplementQueueUsingStacks().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)

    class MyQueue {


        //stack1 模拟进queue
        Stack<Integer> stack1;
        //stack2 模拟出queue
        Stack<Integer> stack2;

        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }


        //假设队列中已经有元素了，再push元素x
        //为demo方便，假设x = 4，队列中有3个旧元素，那么元素应该存在stack2中，且从栈顶到栈底顺序为：【1，2，3】
        public void push(int x) {
            //1.将stack2的所有元素移回到stack1， 比如stack2从栈顶到栈底，保证fifo，从先到后进队列的顺序： 【1，2，3】
            while(!stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
            //此时stack1的元素顺序，从栈顶到栈底【3，2，1】

            //2. 再将x push stack1栈顶
            //此时stack1的元素顺序，从栈顶到栈底【4，3，2，1】
            stack1.push(x);

            //3.再将stack1全部元素移动到stack2
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            //那么此时stack2的元素顺序，从栈顶到栈底【1，2，3，4】
        }

        public int pop() {
            return stack2.pop();
        }

        public int peek() {
            return stack2.peek();
        }

        public boolean empty() {
            return stack2.isEmpty();
        }
    }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)

}