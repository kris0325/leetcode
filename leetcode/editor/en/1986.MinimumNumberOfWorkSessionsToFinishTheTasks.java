import java.util.Arrays;

/**
 * There are n tasks assigned to you. The task times are represented as an integer
 * array tasks of length n, where the iᵗʰ task takes tasks[i] hours to finish. A
 * work session is when you work for at most sessionTime consecutive hours and then
 * take a break.
 * <p>
 * You should finish the given tasks in a way that satisfies the following
 * conditions:
 * <p>
 * <p>
 * If you start a task in a work session, you must complete it in the same work
 * session.
 * You can start a new task immediately after finishing the previous one.
 * You may complete the tasks in any order.
 * <p>
 * <p>
 * Given tasks and sessionTime, return the minimum number of work sessions needed
 * to finish all the tasks following the conditions above.
 * <p>
 * The tests are generated such that sessionTime is greater than or equal to the
 * maximum element in tasks[i].
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: tasks = [1,2,3], sessionTime = 3
 * Output: 2
 * Explanation: You can finish the tasks in two work sessions.
 * - First work session: finish the first and the second tasks in 1 + 2 = 3 hours.
 * - Second work session: finish the third task in 3 hours.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: tasks = [3,1,3,1,1], sessionTime = 8
 * Output: 2
 * Explanation: You can finish the tasks in two work sessions.
 * - First work session: finish all the tasks except the last one in 3 + 1 + 3 + 1
 * = 8 hours.
 * - Second work session: finish the last task in 1 hour.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: tasks = [1,2,3,4,5], sessionTime = 15
 * Output: 1
 * Explanation: You can finish all the tasks in one work session.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * n == tasks.length
 * 1 <= n <= 14
 * 1 <= tasks[i] <= 10
 * max(tasks[i]) <= sessionTime <= 15
 * <p>
 * <p>
 * Related Topics Array Dynamic Programming Backtracking Bit Manipulation Bitmask
 * 👍 1118 👎 68
 */
       
/*
 2024-09-11 21:41:55
*/

class MinimumNumberOfWorkSessionsToFinishTheTasks {
    public static void main(String[] args) {
        Solution solution = new MinimumNumberOfWorkSessionsToFinishTheTasks().new Solution();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int res;
        int maxSessionTime;
        int[] tasks;
        int[] sessions;

        public int minSessions(int[] tasks, int sessionTime) {

            //排序優先嘗試大的task
            Arrays.sort(tasks);
            this.tasks = tasks;
            this.sessions = new int[tasks.length];
            this.maxSessionTime = sessionTime;
            this.res = tasks.length;
            backtracking(tasks.length - 1, 0);
            return res;
        }

        public void backtracking(int taskId, int sessionCount) {
            //剪枝
            if (sessionCount > res) {
                return;
            }
            if (taskId < 0) {
                res = Math.min(res, sessionCount);
                return;
            }

            for (int i = 0; i < sessionCount; i++) {
                //可以把當前taskid 放入舊session
                if (sessions[i] + tasks[taskId] <= maxSessionTime) {
                    sessions[i] += tasks[taskId];
                    backtracking(taskId - 1, sessionCount);
                    sessions[i] -= tasks[taskId];
                }
            }
            //否則，把當前taskid 放入新session
            sessions[sessionCount] += tasks[taskId];
            backtracking(taskId - 1, sessionCount + 1);
            sessions[sessionCount] -= tasks[taskId];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}