import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an integer array jobs, where jobs[i] is the amount of time it
 * takes to complete the iᵗʰ job.
 * <p>
 * There are k workers that you can assign jobs to. Each job should be assigned
 * to exactly one worker. The working time of a worker is the sum of the time it
 * takes to complete all jobs assigned to them. Your goal is to devise an optimal
 * assignment such that the maximum working time of any worker is minimized.
 * <p>
 * Return the minimum possible maximum working time of any assignment.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: jobs = [3,2,3], k = 3
 * Output: 3
 * Explanation: By assigning each person one job, the maximum time is 3.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: jobs = [1,2,4,7,8], k = 2
 * Output: 11
 * Explanation: Assign the jobs the following way:
 * Worker 1: 1, 2, 8 (working time = 1 + 2 + 8 = 11)
 * Worker 2: 4, 7 (working time = 4 + 7 = 11)
 * The maximum working time is 11.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= k <= jobs.length <= 12
 * 1 <= jobs[i] <= 10⁷
 * <p>
 * <p>
 * Related Topics Array Dynamic Programming Backtracking Bit Manipulation Bitmask
 * 👍 1052 👎 31
 */
       
/*
 2024-09-10 20:07:13
*/

class FindMinimumTimeToFinishAllJobs {
    public static void main(String[] args) {
        Solution solution = new FindMinimumTimeToFinishAllJobs().new Solution();
        int[] jobs = {3,2,3};
        int k = 2;
        solution.minimumTimeRequired(jobs,k);
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

    /**
     * 1. 优先分配大任务
     * 排序后，任务按从小到大的顺序排列，代码中从 pos = jobs.length - 1 开始倒序分配任务，即从最大的任务开始分配。
     *
     * 这样做的目的是：
     * 尽早处理较大的任务。如果大任务提前分配给某个工人，剩余的任务分配会更容易进行，回溯的剪枝条件也更早生效。例如，如果一个大的任务已经让某个工人的工作时间达到当前的最优解的上限，就可以提前进行剪枝，避免继续分配后续任务，减少不必要的递归计算。
     * 减少分配的复杂度。将较大的任务先分配，可以更容易判断某个分配是否会超过当前的最大工作时间 res，从而减少后续的回溯路径。
     * */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //优先分配大任务
        //k < job.length(), 所以將較小的k設置為選擇列表，即橫向遍歷為k 個works
        int res = Integer.MAX_VALUE;
        List<List<Integer>> allPath = new ArrayList<>();
        public int minimumTimeRequired(int[] jobs, int k) {
            //jobs升序排序
            ////剪枝1.
            Arrays.sort(jobs);
            //sum[i]表示第i位work的总工作时间
            int[] sum = new int[k];
            //job的index
            int pos = jobs.length - 1;
            //優先分配較大的任務
            backtracking(jobs, pos, sum);
            //            System.out.println(allPath);
            /* 最終path結果
            * [[8, 0], [6, 2], [5, 3]]
             * */
            return res;
        }

        public void backtracking(int[] jobs, int pos, int[] sum) {
            //job分配完為terminate condition
            if (pos < 0) {
                //更新最小值
//                allPath.add(Arrays.stream(sum).boxed().toList());
                res = Math.min(res, Arrays.stream(sum).max().getAsInt());
                return;
            }
            //剪枝1.
            if (Arrays.stream(sum).max().getAsInt() >= res) {
                return;
            }
            for (int i = 0; i < sum.length; i++) {
                //剪枝2.，前後work[i-1], work[i]在上一層分配中，已經分配到相同長度時間的job任務，
                //此時，在當前層分配中，将当前任务分配给这些工人的效果是相同的，剪枝可以防止遍历完全相同的分配路径。
                //比如上一層sum是[3,3]分配組合，那麼任務2不需要分給work2 ,即不會出現[3,5]
                if (i > 0 && sum[i] == sum[i - 1]) {
                    continue;
                }
                sum[i] += jobs[pos];
                backtracking(jobs, pos -1, sum);
                sum[i] -= jobs[pos];
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}