import java.util.*;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to
 * numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai,
 * bi] indicates that you must take course bi first if you want to take course ai.
 * <p>
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to
 * first take course 1.
 * <p>
 * <p>
 * Return the ordering of courses you should take to finish all courses. If there
 * are many valid answers, return any of them. If it is impossible to finish all
 * courses, return an empty array.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you
 * should have finished course 0. So the correct course order is [0,1].
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you
 * should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after
 * you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * <p>
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 * <p>
 * <p>
 * Related Topics Depth-First Search Breadth-First Search Graph Topological Sort ?
 * ? 10863 👎 349
 */
       
/*
 2024-09-06 19:33:51
*/

class CourseScheduleIi {
    public static void main(String[] args) {
        Solution solution = new CourseScheduleIi().new Solution();
        int[][] prerequisites = {{1, 0}, {0, 1}};
        System.out.printf("res:" + solution.findOrder(2, prerequisites));
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
        //BFS  TopSort
        public int[] findOrder(int numCourses, int[][] prerequisites) {
//            List<Integer> res = new LinkedList<>();
            int[] res = new int[numCourses];
            int resIndex = 0;
            Map<Integer, List<Integer>> graph = new HashMap<>();
            int[] indegree = new int[numCourses];
            for (int[] pre : prerequisites) {
                graph.computeIfAbsent(pre[1], key -> new ArrayList<>()).add(pre[0]);
                indegree[pre[0]]++;
            }
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < indegree.length; i++) {
                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }
            while (!queue.isEmpty()) {
                int pre = queue.poll();
//                res.add(pre);
//                numCourses--;
                res[resIndex++] = pre;

                for (int cur : graph.getOrDefault(pre, new ArrayList<>())) {
                    if (--indegree[cur] == 0) {
                        queue.offer(cur);
                    }
                }
            }
            //如果没有环，那么就有拓扑排序
//            return numCourses == 0 ? res.stream().mapToInt(i -> i).toArray()
//                    : new int[0];
            return resIndex == numCourses ?
                    res
                    : new int[0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}